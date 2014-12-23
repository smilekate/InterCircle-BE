/*
 * Copyright (C), Acxiom Corporation  
 * FileName: Constants.java
 * Author:   elvfan
 * Date:     Dec 31, 2013 10:31:54 AM
 * Description: 
 * History: 
 * <author>      <time>      <version>    <desc>
 *
 */
package com.qianjin.intercircle.backend.common;

/**
 * Constants for Campaign Planner backend services.
 * 
 * @author elvfan
 * @see
 * @since
 */
public class Constants {

    public static final String DB_UPDATE_HISTORY_TABLE_NAME = "TABLE_UPGRADE_HISTORY";

    
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATE_FORMAT_UTC = DATE_FORMAT + "'Z'";

    public static final String DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String DATETIME_FORMAT_UTC = DATETIME_FORMAT + "'Z'";

    public static final class ParamNames {
        public static final String PAGE_START_INDEX = "pageStartIndex";
        public static final String PAGE_SIZE = "pageSize";
        public static final String ID_LIST = "idList";
        public static final String RESOURCES = "resources";
        public static final String INPUT = "input";
    }
    
    public static final class Resources {
        public static final String PROGRAMS = "programs";
        public static final String CAMPAIGNS = "campaigns";
    }

    public static final class DefaultValues {
        public static final String PAGE_START_INDEX = "0";
        public static final String PAGE_SIZE = "10";
        public static final String PAGE_SIZE_REFERENCE = "10000";
    }

    public static final class PlaceHolders {
        public static final String INVALID_VALUE = "\\{invalidValue\\}";
        public static final String FIELD_NAME = "\\{fieldName\\}";
        public static final String ENTITY_NAME = "\\{entityName\\}";
    }

    public static final class Errors {
        public static final String INTEGER_CODE = "008-0101";
        public static final String INTEGER_MSGKEY = "com.acxiom.cp.service.constraint.Integer.message";

        public static final String DATETIME_CODE = "008-0102";
        public static final String DATETIME_MSGKEY = "com.acxiom.cp.service.constraint.Datetime.message";

        public static final String DATE_CODE = "008-0103";
        public static final String DATE_MSGKEY = "com.acxiom.cp.service.constraint.Date.message";

        public static final String DECIMAL_CODE = "008-0105";
        public static final String DECIMAL_MSGKEY = "com.acxiom.cp.service.constraint.Decimal.message";

        public static final String BOOLEAN_CODE = "008-0106";
        public static final String BOOLEAN_MSGKEY = "com.acxiom.cp.service.constraint.Boolean.message";

        public static final String NOTEMPTY_CODE = "008-0201";
        public static final String NOTEMPTY_MSGKEY = "";

        public static final String LENGTH_CODE = "008-0202";
        public static final String LENGTH_MSGKEY = "";

        public static final String EXISTID_CODE = "008-0203";
        public static final String EXISTID_MSGKEY = "com.acxiom.cp.service.constraint.ExistID.message";

        public static final String EXISTFK_CODE = "008-0204";
        public static final String EXISTFK_MSGKEY = "com.acxiom.cp.service.constraint.ExistFK.message";

        public static final String BADREQUEST_CODE = "008-0400";
        public static final String UNAUTHENTICATED_CODE = "008-0401";
        public static final String UNAUTHORIZED_CODE = "008-0403";
        public static final String NOTFOUND_CODE = "008-0404";
        public static final String NOTALLOWED_CODE = "008-0405";
        public static final String SERVERERROR_CODE = "008-0500";

        // business rule violation code and messages
        public static final String BIZRULE_VIOLATION_CODE = "008-0301";
        public static final String PHASE_DATEOVERLAP_MSGKEY = "com.acxiom.cp.service.constraint.phase.date.overlap";
        public static final String PHASE_DATEGAP_MSGKEY = "com.acxiom.cp.service.constraint.phase.date.gap";
        public static final String PHASE_DATEINCONSISTENT_MSGKEY = "com.acxiom.cp.service.constraint.phase.date.inconsistent";
        public static final String DATEINCORRECT_MSGKEY = "com.acxiom.cp.service.constraint.date.incorrect";
        public static final String CAMPAIGN_DATEINCORRECT_MSGKEY = "com.acxiom.cp.service.constraint.campaign.date.inconsistent";
        public static final String EXISTNAME_CODE = "008-0205";
        public static final String EXISTNAME_MSGKEY = "com.acxiom.cp.service.constraint.ExistName.message";
        public static final String STARTDATE_EXCEED_ENDDATE="com.acxiom.cp.service.constraint.startDateExceedEndDate";
        public static final String DATA_INTEGRITY_VIOLATION_CODE = "008-0206";
        public static final String DATA_INTEGRITY_VIOLATION_MSGKEY = "com.acxiom.cp.service.constraint.DataIntegrityViolation.message";
        
    }

}
