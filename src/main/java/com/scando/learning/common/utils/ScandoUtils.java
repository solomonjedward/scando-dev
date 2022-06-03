package com.scando.learning.common.utils;

import com.scando.learning.common.dao.ApplicationDao;
import com.scando.learning.common.models.rest.PageDetails;
import com.scando.learning.common.models.rest.PagedData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class ScandoUtils {

    private static ApplicationDao applicationDao;

    public ScandoUtils(ApplicationDao applicationDao)
    {
        ScandoUtils.applicationDao = applicationDao;
    }
    public static List<Integer> getDays(int d){
        int[] daysArray = new int[]{1, 2, 3, 4, 5, 6, 7};
        List<Integer> positions = new ArrayList();
        int[] var3 = daysArray;
        int var4 = daysArray.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            int k = var3[var5];
            if ((d & 1 << k - 1) > 0) {
                positions.add(k);
            }
        }

        return positions;
    }

    public static int setDays(List<Integer> positions) {
        int n = 0;

        Integer k;
        for(Iterator var2 = positions.iterator(); var2.hasNext(); n |= 1 << k - 1) {
            k = (Integer)var2.next();
        }

        return n;
    }

    public static boolean checkAppId(Long appId) {
        if(null == applicationDao.getAppInfo(appId))
            return  true;
        else
            return false;

    }

    public static <T> PagedData<T> setPageResponse(Page<T> pageInfo) {
        long remainCount = pageInfo.getTotalElements() - ((long) (pageInfo.getNumber() + 1) * pageInfo.getSize());
        if (remainCount < 0) {
            remainCount = 0L;
        }
        PagedData<T> pagedData = new PagedData<>();
        pagedData.setList(pageInfo.getContent());
        PageDetails pageDetails = new PageDetails();
        pageDetails.setPage(pageInfo.getNumber() + 1);
        pageDetails.setPageSize(pageInfo.getSize());
        pageDetails.setPageCount(pageInfo.getTotalPages());
        pageDetails.setTotalElements(pageInfo.getTotalElements());
        pageDetails.setRemainingElements(remainCount);
        pagedData.setPageDetails(pageDetails);
        return pagedData;
    }

}
