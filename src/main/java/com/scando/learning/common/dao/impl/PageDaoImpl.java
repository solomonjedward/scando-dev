package com.scando.learning.common.dao.impl;

import com.scando.learning.common.dao.PageDao;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.ReflectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

@Repository
public abstract class PageDaoImpl implements PageDao {

    private static final Map<Class<?>, Type> JAVA_HIBERNATE_TYPE_MAP = new HashMap<>();

    private static final Map<Class<?>, Map<String, Type>> VIEW_ALIAS_TYPE_MAP = new HashMap<>();

    static {
        JAVA_HIBERNATE_TYPE_MAP.put(Boolean.class, StandardBasicTypes.BOOLEAN);
        JAVA_HIBERNATE_TYPE_MAP.put(Byte.class, StandardBasicTypes.BYTE);
        JAVA_HIBERNATE_TYPE_MAP.put(Short.class, StandardBasicTypes.SHORT);
        JAVA_HIBERNATE_TYPE_MAP.put(Integer.class, StandardBasicTypes.INTEGER);
        JAVA_HIBERNATE_TYPE_MAP.put(Long.class, StandardBasicTypes.LONG);
        JAVA_HIBERNATE_TYPE_MAP.put(Float.class, StandardBasicTypes.FLOAT);
        JAVA_HIBERNATE_TYPE_MAP.put(Double.class, StandardBasicTypes.DOUBLE);
        JAVA_HIBERNATE_TYPE_MAP.put(BigInteger.class, StandardBasicTypes.BIG_INTEGER);
        JAVA_HIBERNATE_TYPE_MAP.put(BigDecimal.class, StandardBasicTypes.BIG_DECIMAL);
        JAVA_HIBERNATE_TYPE_MAP.put(Character.class, StandardBasicTypes.CHARACTER);
        JAVA_HIBERNATE_TYPE_MAP.put(String.class, StandardBasicTypes.STRING);
        JAVA_HIBERNATE_TYPE_MAP.put(Date.class, StandardBasicTypes.TIMESTAMP);
    }

    @Autowired
    protected EntityManager entityManager;

    protected static String prepareSearchText(String searchText) {
        if (searchText == null || searchText.isEmpty()) {
            return null;
        }
        searchText = searchText.replaceAll("([%'~()])", "\\\\\\\\\\\\\\\\$1");
        return "%" + searchText + "%";
    }

    protected static Map<String, Type> getAliasTypeMap(Class<?> viewClass) {
        Map<String, Type> aliasTypeMap = VIEW_ALIAS_TYPE_MAP.get(viewClass);
        if (aliasTypeMap != null) {
            return aliasTypeMap;
        }

        List<Field> fieldList = new ArrayList<>();
        ReflectionUtils.doWithFields(viewClass, fieldList::add);

        aliasTypeMap = new HashMap<>();
        for (Field field : fieldList) {
            aliasTypeMap.put(field.getName(), JAVA_HIBERNATE_TYPE_MAP.get(field.getType()));
        }
        VIEW_ALIAS_TYPE_MAP.put(viewClass, aliasTypeMap);

        return aliasTypeMap;
    }

    @SuppressWarnings("deprecation")
    @Override
    public <V> Page<V> getPageFromNativeQuery(String listQueryStr, String countQueryStr,
                                                                           int page, int pageSize, Class<V> viewClass, List<String> resultFieldNames, Map<String, Object> filterMap) {

        Query listQuery = entityManager.createNativeQuery(listQueryStr);
        Query countQuery = entityManager.createNativeQuery(countQueryStr);
        if (filterMap != null && !filterMap.isEmpty()) {
            filterMap.forEach((key, value) -> {
                countQuery.setParameter(key, value);
                listQuery.setParameter(key, value);
            });
        }
        int count = ((BigInteger) countQuery.getSingleResult()).intValue();
        @SuppressWarnings("unchecked")
        NativeQuery<V> nativeListQuery = (NativeQuery<V>) listQuery;
        nativeListQuery.setResultTransformer(Transformers.aliasToBean(viewClass));
        nativeListQuery.setFirstResult(getIndex(page, pageSize));
        nativeListQuery.setMaxResults(pageSize);
        Map<String, Type> aliasTypeMap = getAliasTypeMap(viewClass);
        if (resultFieldNames != null && !resultFieldNames.isEmpty() && !aliasTypeMap.isEmpty()) {
            aliasTypeMap.entrySet().stream().filter(e -> resultFieldNames.contains(e.getKey()))
                    .forEach(e -> nativeListQuery.addScalar(e.getKey(), e.getValue()));
        }
        List<V> resultList = nativeListQuery.list();
        return new PageImpl<>(resultList, convertToPageable(page, pageSize), count);
    }

    protected Pageable convertToPageable(int page, int pageSize) {
        return PageRequest.of(page -1, pageSize);
    }

    private int getIndex(int page, int pageSize) {
        return ((page - 1) * pageSize);
    }
}
