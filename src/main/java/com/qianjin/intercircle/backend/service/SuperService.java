package com.qianjin.intercircle.backend.service;

import java.util.List;

import com.qianjin.intercircle.backend.entity.SuperEntity;

public interface SuperService<T extends SuperEntity> {

	Integer create(T object);

    List<T> getList(T object, Integer pageStartIndex, Integer pageSize);

    Long getTotalCount(Class<T> claz);

    void delete(Class<T> claz, Integer id);

    void deleteAll(Class<T> claz, Integer[] ids) ;

    void deleteAll(List<T> list) ;

    T getObjectById(Class<T> claz, Integer objectId);

    void update(T object);

    void createAll(List<T> list);
}
