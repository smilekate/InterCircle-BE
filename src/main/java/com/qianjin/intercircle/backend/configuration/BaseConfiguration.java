package com.qianjin.intercircle.backend.configuration;

import java.io.InputStream;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * 
 * Description basic configuration
 * <p>
 * Copyright (c) 2012 Acxiom Corporation. All Rights Reserved.
 * </p>
 * 
 * @author wexiao date: 2013��12��17��
 *         <p>
 *         Last updated by wexiao date:2013��12��17��
 *         </p>
 */
public class BaseConfiguration extends PropertyPlaceholderConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(BaseConfiguration.class);

    /**
     * Configuration need to add this property to detect the Avid environment
     */
    private String avidDetection;

    private String devPropertiesLocation;

    private String avidPropertiesLocation;

    public String getAvidDetection() {
        return avidDetection;
    }

    public void setAvidDetection(String avidDetection) {
        this.avidDetection = avidDetection;
    }

    public String getDevPropertiesLocation() {
        return devPropertiesLocation;
    }

    public void setDevPropertiesLocation(String devPropertiesLocation) {
        this.devPropertiesLocation = devPropertiesLocation;
    }

    public String getAvidPropertiesLocation() {
        return avidPropertiesLocation;
    }

    public void setAvidPropertiesLocation(String avidPropertiesLocation) {
        this.avidPropertiesLocation = avidPropertiesLocation;
    }

    @Override
    public void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props) throws BeansException {
        // set JVM timezone to UTC
        System.setProperty("user.timezone","Asia/Shanghai");
        
        super.setIgnoreUnresolvablePlaceholders(true);
        if (logger.isInfoEnabled()) {
            if (null == avidDetection || "${avid_Detection}".equals(avidDetection)) {
                logger.info("Avid detection result: no Avid detected, will use Dev properties.");
            } else {
                logger.info("Avid detection result: Avid detected, will use Avid configuration.");
            }

        }
        try {
            if (null == avidDetection || "${avid_Detection}".equals(avidDetection)) {
                Properties prop = new Properties();
                InputStream in = this.getClass().getClassLoader().getResourceAsStream(getDevPropertiesLocation());
                prop.load(in);
                Iterator<Entry<Object, Object>> its = prop.entrySet().iterator();
                while (its.hasNext()) {
                    Entry<Object, Object> entry = its.next();
                    if (logger.isInfoEnabled()) {
                        logger.info(entry.getKey().toString() + " : " + entry.getValue().toString());
                    }
                    props.put(entry.getKey(), entry.getValue());
                    logger.debug("==============:"+entry.getKey()+":"+ entry.getValue());
                }
                in.close();
            } else {
                Properties prop = new Properties();
                InputStream in = this.getClass().getClassLoader().getResourceAsStream(getAvidPropertiesLocation());
                prop.load(in);
                Iterator<Entry<Object, Object>> its = prop.entrySet().iterator();
                while (its.hasNext()) {
                    Entry<Object, Object> entry = its.next();
                    if (logger.isInfoEnabled()) {
                        logger.info(entry.getKey().toString() + " : " + entry.getValue().toString());
                    }
                    props.put(entry.getKey(), entry.getValue());
                }
                in.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        super.processProperties(beanFactory, props);
    }

}
