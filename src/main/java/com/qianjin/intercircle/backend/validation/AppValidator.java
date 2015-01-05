package com.qianjin.intercircle.backend.validation;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qianjin.intercircle.backend.common.Constants;
import com.qianjin.intercircle.backend.common.ResponseErrors;
import com.qianjin.intercircle.backend.common.Utilities;
import com.qianjin.intercircle.backend.entity.ResponseError;
import com.qianjin.intercircle.backend.entity.SuperEntity;
import com.qianjin.intercircle.backend.validation.constraint.ExistName;

/**
 * Application validator<br>
 * This validator can help to check the bean with java JSR303 annotations.
 * <p>
 * Copyright (c) 2012 Acxiom Corporation. All Rights Reserved.
 * </p>
 * 
 * @author wexiao date: 2013/12/12
 *         <p>
 *         Last updated by wexiao date:2013/12/12
 *         </p>
 */
@Component
public class AppValidator {

    @Autowired
    Validator validator;

    @Resource
    ResponseErrors responseErrors;

    /**
     * 
     * Description: Validate all attributes with constrain annotations of a bean <br>
     * 
     * 
     * @param obj
     * @param groups
     */
    public <T> void validate(T obj, Class<?>... groups) {
        Set<ConstraintViolation<T>> errors = validator.validate(obj, groups);
        if (errors != null && !errors.isEmpty()) {
            // responseErrors = new ArrayList<ResponseError>(errors.size());
            for (ConstraintViolation<T> error : errors) {
                Object errorCodeObj = getPredefinedErrorCode(error);
                String errorCode = "";
                if (errorCodeObj != null) {
                    errorCode = (String) errorCodeObj;
                }
                responseErrors.getResponseErrors().add(new ResponseError(errorCode, getMessageWithInvalidValue(error)));
            }
        }
    }

    /**
     * 
     * Validate specific attributes with constrain annotations of a bean <br>
     * 
     * @param obj to be validated bean object
     * @param attributeNames to be validated attributes of the bean.
     * @return
     */
    public <T> void validateAttributes(T obj, String... attributeNames) {
        Set<ConstraintViolation<T>> errors = new HashSet<ConstraintViolation<T>>();
        if (attributeNames == null) {
            return;
        }

        for (String attributeName : attributeNames) {
            Set<ConstraintViolation<T>> signleErrors = validator.validateProperty(obj, attributeName);
            if (signleErrors != null) {
                errors.addAll(signleErrors);
            }
        }

        // List<ResponseError> responseErrors = null;
        if (errors != null && !errors.isEmpty()) {
            // responseErrors = new ArrayList<ResponseError>(errors.size());
            for (ConstraintViolation<T> error : errors) {
                Object errorCodeObj = getPredefinedErrorCode(error);
                String errorCode = "";
                if (errorCodeObj != null) {
                    errorCode = (String) errorCodeObj;
                }
                responseErrors.getResponseErrors().add(new ResponseError(errorCode, getMessageWithInvalidValue(error)));

                // TODO debug logger
            }
        }
    }

    /**
     * 
     * Validate specific attributes with constrain annotations of a bean. Filter validation by groups <br>
     * 
     * @param obj
     * @param groups
     * @param attributeNames
     */
    public <T> void validateAttributes(T obj, Class<?>[] groups, String... attributeNames) {
        Set<ConstraintViolation<T>> errors = new HashSet<ConstraintViolation<T>>();
        if (attributeNames == null) {
            return;
        }

        for (String attributeName : attributeNames) {
            Set<ConstraintViolation<T>> signleErrors = validator.validateProperty(obj, attributeName, groups);
            if (signleErrors != null) {
                errors.addAll(signleErrors);
            }
        }

        // List<ResponseError> responseErrors = null;
        if (errors != null && !errors.isEmpty()) {
            // responseErrors = new ArrayList<ResponseError>(errors.size());
            for (ConstraintViolation<T> error : errors) {
                Object errorCodeObj = getPredefinedErrorCode(error);
                String errorCode = "";
                if (errorCodeObj != null) {
                    errorCode = (String) errorCodeObj;
                }
                responseErrors.getResponseErrors().add(new ResponseError(errorCode, getMessageWithInvalidValue(error)));

                // TODO debug logger
            }
        }
    }
    
    private <T> String getPredefinedErrorCode(ConstraintViolation<T> error) {
        // TODO 008 is app id
        if (error.getConstraintDescriptor().getAnnotation() instanceof NotEmpty
                || error.getConstraintDescriptor().getAnnotation() instanceof NotNull) {
            return Constants.Errors.NOTEMPTY_CODE;
        } else if (error.getConstraintDescriptor().getAnnotation() instanceof Length) {
            return Constants.Errors.LENGTH_CODE;
//        } else if (error.getConstraintDescriptor().getAnnotation() instanceof ExistID) {
//            return Constants.Errors.EXISTID_CODE;
//        } else if (error.getConstraintDescriptor().getAnnotation() instanceof ExistFK) {
//            return Constants.Errors.EXISTFK_CODE;
//        } else if(error.getConstraintDescriptor().getAnnotation() instanceof ExistFKs){
//        	return Constants.Errors.EXISTFK_CODE;
        } else if(error.getConstraintDescriptor().getAnnotation() instanceof ExistName){
        	return Constants.Errors.EXISTNAME_CODE;
        } else {
            return Constants.Errors.BADREQUEST_CODE;
        }
       
    }

    @SuppressWarnings("unchecked")
    private <T> String getMessageWithInvalidValue(ConstraintViolation<T> error) {
        if (error == null) {
            return "";
        }
        String errorMessage = error.getMessage();
        if (errorMessage == null) {
            return "";
        }

//        if (error.getConstraintDescriptor().getAnnotation() instanceof ExistFK) {
//            Integer statusId = Utilities.getEntityId(error.getInvalidValue());
//            errorMessage = errorMessage.replaceAll(Constants.PlaceHolders.INVALID_VALUE,
//                    (statusId != null ? statusId.toString() : "null"));
//        } 
//        else if(error.getConstraintDescriptor().getAnnotation() instanceof ExistFKs){
//        	List<SuperEntity> nonExistEntities = (List<SuperEntity>)error.getInvalidValue();
//        	StringBuilder ids = new StringBuilder();
//        	for (SuperEntity e : nonExistEntities){
//        		ids.append(e.getId()).append(",");
//        	}
//        	ids.deleteCharAt(ids.length()-1);
//        	
//            errorMessage = errorMessage.replaceAll(Constants.PlaceHolders.INVALID_VALUE,
//            		 ids.toString());
//        } else 
        if (error.getConstraintDescriptor().getAnnotation() instanceof ExistName) {
        	String name = (String)Utilities.getValue("name", error.getInvalidValue());
        	 String originalMessage = Utilities.getMessage(Constants.Errors.EXISTNAME_MSGKEY);
        	 errorMessage = originalMessage.replaceAll(Constants.PlaceHolders.ENTITY_NAME, error.getInvalidValue().getClass().getSimpleName())
                     .replaceAll(Constants.PlaceHolders.FIELD_NAME, "name")
                     .replaceAll(Constants.PlaceHolders.INVALID_VALUE, name);
        } else {
            errorMessage = errorMessage.replaceAll(Constants.PlaceHolders.INVALID_VALUE, (error.getInvalidValue() != null ? error
                    .getInvalidValue().toString() : " "));
        }
        errorMessage = errorMessage.replaceAll(Constants.PlaceHolders.FIELD_NAME, (error.getPropertyPath() != null ? error
                .getPropertyPath().toString() : " "));

        return errorMessage;
    }
    
    

}