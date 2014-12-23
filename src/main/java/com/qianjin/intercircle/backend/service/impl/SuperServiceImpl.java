package com.qianjin.intercircle.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qianjin.intercircle.backend.dao.SuperDAO;
import com.qianjin.intercircle.backend.entity.SuperEntity;
import com.qianjin.intercircle.backend.service.SuperService;

@Service("superService")
public class SuperServiceImpl<T extends SuperEntity> implements SuperService<T> {
	
	@Autowired
    protected SuperDAO<T> superDAO;

    @Override
    public Integer create(T object) {
        return superDAO.create(object);
    }

    @Override
    public List<T> getList(T object, Integer pageStartIndex, Integer pageSize) {
        return superDAO.getList(object, pageStartIndex, pageSize);
    }

    @Override
    public void delete(Class<T> claz, Integer id) {
        superDAO.delete(claz, id);
    }

    @Override
    public void deleteAll(Class<T> claz, Integer[] ids) {
        superDAO.deleteAll(claz, ids);
    }

    @Override
    public void deleteAll(List<T> list) {
        superDAO.deleteAll(list);
    }

    @Override
    public T getObjectById(Class<T> claz, Integer id) {
        return superDAO.getObjectById(claz, id);
    }

    @Override
    public void update(T object) {
        superDAO.update(object);
    }

    @Override
    public Long getTotalCount(Class<T> claz) {
        return superDAO.getTotalCount(claz);
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.acxiom.cp.backend.service.SuperService#bulkCreate(java.util.List)
     */
    @Override
    public void createAll(List<T> list) {
        superDAO.createAll(list);
    }

}
