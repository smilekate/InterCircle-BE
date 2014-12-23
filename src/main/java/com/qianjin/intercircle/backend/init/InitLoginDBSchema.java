package com.qianjin.intercircle.backend.init;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * Description init all related database.
 * <p>
 * Copyright (c) 2013 Acxiom Corporation. All Rights Reserved.
 * </p>
 * 
 * @author wexiao date: 2013-7-8
 *         <p>
 *         Last updated by wexiao date:2013-7-8
 *         </p>
 */
public class InitLoginDBSchema {
    private final static Logger log = LoggerFactory.getLogger(InitLoginDBSchema.class);

    private String loignDBIP;

    private String loignDBPort;

    private String loignDBUser;

    private String loignDBPassword;

    private String loignDBSchema;

    public String getLoignDBSchema() {
        return loignDBSchema;
    }

    public void setLoignDBSchema(String loignDBSchema) {
        this.loignDBSchema = loignDBSchema;
    }

    public String getLoignDBIP() {
        return loignDBIP;
    }

    public void setLoignDBIP(String loignDBIP) {
        this.loignDBIP = loignDBIP;
    }

    public String getLoignDBPort() {
        return loignDBPort;
    }

    public void setLoignDBPort(String loignDBPort) {
        this.loignDBPort = loignDBPort;
    }

    public String getLoignDBUser() {
        return loignDBUser;
    }

    public void setLoignDBUser(String loignDBUser) {
        this.loignDBUser = loignDBUser;
    }

    public String getLoignDBPassword() {
        return loignDBPassword;
    }

    public void setLoignDBPassword(String loignDBPassword) {
        this.loignDBPassword = loignDBPassword;
    }

    public void init() {
    	System.out.println("========================================================:"+loignDBIP);
    	
//        try {
//            DBUtility.createSchemaIfNotExists(this.getLoignDBIP(), this.getLoignDBPort(), this.getLoignDBUser(),
//                    this.getLoignDBPassword(), this.getLoignDBSchema());
//        } catch (SQLException e) {
//            log.error(e.getMessage(), e);
//        }
    }

}
