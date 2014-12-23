/*
 * Copyright (C), Acxiom Corporation  
 * FileName: Utilities.java
 * Author:   elvfan
 * Date:     Dec 12, 2013 5:24:27 PM
 * Description: 
 * History: 
 * <author>      <time>      <version>    <desc>
 *
 */
package com.qianjin.intercircle.backend.common;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qianjin.intercircle.backend.entity.ResponseError;

/**
 * <Simple Functional Description><br>
 * <Functional Description>
 * 
 * @author elvfan
 * @see
 * @since
 */
public class Utilities {

    private static Logger logger = LoggerFactory.getLogger(Utilities.class);

    private static ResourceBundle messageBundle = ResourceBundle.getBundle("ValidationMessages");

    /**
     * check whether the given string is empty
     * 
     * @param str source string
     * @return True/False
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    /**
     * 
     * Description:generate response error return object
     * 
     * @param responseErrors
     * @return
     */
    public static Map<String, List<ResponseError>> generateErrorResponse(ResponseErrors responseErrors) {
        Map<String, List<ResponseError>> responseErrorMap = new HashMap<String, List<ResponseError>>();
        responseErrorMap.put("errors", responseErrors.getResponseErrors());
        return responseErrorMap;
    }

    public static Integer getEntityId(Object entity) {
        Integer id = null;
        try {
            id = (Integer) entity.getClass().getMethod("getId", new Class[] {}).invoke(entity, new Object[] {});
        } catch (Exception e) {
            logger.warn("Error occurred during get id from object " + entity.toString());
            return null;
        }
        return id;
    }
    

    public static void setObjectValue(Object object, String methodName, Object value) {
        try {
            @SuppressWarnings("rawtypes")
            Class ownerClass = object.getClass();
            @SuppressWarnings("unchecked")
            Method method = ownerClass.getMethod(methodName, value.getClass());
            if (method != null) {
                method.invoke(object, value);
            } else {
                logger.warn("There is no named " + methodName + " method");
            }
        } catch (Exception e) {
            logger.warn("", e);
        }
    }

    public static Object getObjectValue(Object object, String methodName) {
        try {
            @SuppressWarnings("rawtypes")
            Class ownerClass = object.getClass();
            @SuppressWarnings("unchecked")
            Method method = ownerClass.getMethod(methodName);
            if (method != null) {
                return method.invoke(object);
            } else {
                logger.warn("There is no named " + methodName + " method");
            }
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }
        return null;
    }

    public static String getMessage(String key) {
        return messageBundle.getString(key);
    }

    /**
     * Apply user defined timezone setting
     * 
     * @param value source date
     * @param direction 1:plus, -1:minus
     * @return
     * @see
     * @since
     */
    public static Date applyTimezoneOffset(Date value, int direction) {
        /* no need to consider timezone for MVP
        int userTimezoneOffset = Integer.parseInt(SecurityInfo.getUserTimeZoneOffset());
        Calendar c = Calendar.getInstance();
        c.setTime(value);
        c.add(Calendar.HOUR, userTimezoneOffset * direction);
        return c.getTime();
        */
        return value;
    }


    public static String toDebugString(Object o) {
        if (o == null) {
            return "NULL";
        } else if (o instanceof Date) {
            SimpleDateFormat datetimeFormat = new SimpleDateFormat(Constants.DATETIME_FORMAT);
            return datetimeFormat.format((Date) o);
        } else {
            return o.toString();
        }
    }

	public static Object getValue(String propertyName,Object target){
		PropertyDescriptor desc;
		try {
			desc = new PropertyDescriptor(propertyName, target.getClass());
			Method readMethod =desc.getReadMethod();
	        Object propertyValue = readMethod.invoke(target);
	        return propertyValue;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
