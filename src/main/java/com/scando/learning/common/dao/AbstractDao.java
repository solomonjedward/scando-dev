package com.scando.learning.common.dao;

import com.scando.learning.common.models.AbstractModel;
import org.springframework.stereotype.Repository;

@Repository
public interface AbstractDao<T extends AbstractModel> {

    T save(T object);

    void delete(T object);
}
