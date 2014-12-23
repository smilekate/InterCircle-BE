package com.qianjin.intercircle.backend.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.qianjin.intercircle.backend.common.Constants;
import com.qianjin.intercircle.backend.common.ResponseErrors;
import com.qianjin.intercircle.backend.common.Utilities;
import com.qianjin.intercircle.backend.dao.SuperDAO;
import com.qianjin.intercircle.backend.entity.ResponseError;
import com.qianjin.intercircle.backend.entity.SuperEntity;

@Repository("superDAO")
public class SuperDAOImpl<T extends SuperEntity> implements SuperDAO<T> {

	private static Logger logger = LoggerFactory.getLogger(SuperDAOImpl.class);

	@Resource
    ResponseErrors responseErrors;
	
	@Autowired
	protected HibernateTemplate hibernateTemplate;

	@Override
	public Integer create(T object) {
		logger.info("Creating object:" + object.getClass().getSimpleName());
		// Timestamp dateTime = getDBCurrentDateTime();
		// object.setCreatedDate(dateTime);
		// object.setModifiedDate(dateTime);
		// String userName = SecurityInfo.getUserName();
		// object.setCreatedBy(userName);
		// object.setModifiedBy(userName);
		String id = hibernateTemplate.save(object).toString();
		return Integer.valueOf(id);
	}

	@Override
	public void createAll(List<T> list) {

//		Timestamp dateTime = getDBCurrentDateTime();
//		String userName = SecurityInfo.getUserName();
//
//		for (T obj : list) {
//			obj.setCreatedDate(dateTime);
//			obj.setModifiedDate(dateTime);
//			obj.setCreatedBy(userName);
//			obj.setModifiedBy(userName);
//		}
		hibernateTemplate.saveOrUpdateAll(list);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getList(T object,Integer pageStartIndex, Integer pageSize) {
        logger.info("Creating object list:" + object.getClass().getSimpleName());
        Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
        Query query=session.createQuery("from "+object.getClass().getSimpleName()+" order by modifiedDate desc");
        query.setFirstResult(pageStartIndex);
        query.setMaxResults(pageSize);
        return query.list();
    }

	@Override
	public Long getTotalCount(Class<T> claz) {
        String hql="select count(*) from " + claz.getName();
        return (Long) hibernateTemplate.find(hql).get(0);
    }

	@Override
    public T getObjectById(Class<T> claz,Integer id) {
        logger.info("Fetching object:" + claz.getSimpleName() + ", id:" + id);
        T object = hibernateTemplate.get(claz, id);
        if (object == null) {
            responseErrors.addError(getIdNotFoundError(claz.getSimpleName(), id));
        }
        return object;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<T> getObjectByIds(Class<T> claz,Integer[] ids) {
        return hibernateTemplate.getSessionFactory().getCurrentSession().createCriteria(claz).add(Restrictions.in("id", ids)).list();
    }

	@Override
    public void delete(Class<T> claz,Integer id) {
        logger.info("Deleting object:" + claz.getSimpleName() + ", id:" + id);
        Object object = hibernateTemplate.get(claz,id);
        if (object == null) {
            responseErrors.addError(getIdNotFoundError(claz.getSimpleName(), id));
            return;
        }
        hibernateTemplate.delete(object);
    }
    
    @Override
    public void deleteAll(Class<T> claz,Integer[] ids) {
        logger.info("Batch deleting object:" + claz.getSimpleName());
        if(ids != null && ids.length > 0){
            List<T> list = getObjectByIds(claz, ids);
            hibernateTemplate.deleteAll(list);
        }
    }
    
    @Override
    public void deleteAll(List<T> list)  {
            hibernateTemplate.deleteAll(list);
    }

	@Override
	public void update(T object) {
        logger.info("Updating object:" + object.getClass().getSimpleName() + ", id:" + object.getId());
        //Timestamp dateTime = getDBCurrentDateTime();
        //object.setModifiedDate(dateTime);
        //String userName = SecurityInfo.getUserName();
        //object.setModifiedBy(userName);
        hibernateTemplate.merge(object);
    }

	private ResponseError getIdNotFoundError(String entityName, Integer invalidValue) {
        String originalMessage = Utilities.getMessage(Constants.Errors.EXISTID_MSGKEY);
        String message = originalMessage.replaceAll(Constants.PlaceHolders.ENTITY_NAME, entityName)
                .replaceAll(Constants.PlaceHolders.FIELD_NAME, "id")
                .replaceAll(Constants.PlaceHolders.INVALID_VALUE, String.valueOf(invalidValue));

        return new ResponseError(Constants.Errors.EXISTID_CODE, message);
    }
}
