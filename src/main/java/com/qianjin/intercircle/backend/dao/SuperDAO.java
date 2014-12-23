package com.qianjin.intercircle.backend.dao;

import java.util.List;

import com.qianjin.intercircle.backend.entity.SuperEntity;

public interface SuperDAO<T extends SuperEntity> {

	Integer create(T object);
    List<T> getList(T object,Integer pageStartIndex, Integer pageSize);
    Long getTotalCount(Class<T> claz);
    T getObjectById(Class<T> claz,Integer id);
    List<T> getObjectByIds(Class<T> claz,Integer[] id);
    void delete(Class<T> claz,Integer id);
    void deleteAll(Class<T> claz,Integer[] ids);
    void deleteAll(List<T> list) ;
    void update(T object);
    void createAll(List<T> list);
}
