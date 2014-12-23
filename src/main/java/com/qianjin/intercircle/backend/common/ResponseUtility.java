/*
 * Copyright (C), Acxiom Corporation  
 * FileName: ResponseUtility.java
 * Author:   haisun
 * Date:     Dec 18, 2013 10:28:46 AM
 * Description: 
 * History: 
 * <author>      <time>      <version>    <desc>
 *
 */
package com.qianjin.intercircle.backend.common;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriInfo;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.qianjin.intercircle.backend.entity.ResponseError;
import com.qianjin.intercircle.backend.entity.SuperEntity;

/**
 * Response builders.
 * 
 * @author haisun
 * @see
 * @since
 */
public class ResponseUtility {

    private static Logger log = Logger.getLogger(ResponseUtility.class);

    private static Map<String, Response.Status> errorResponseCodeMap = new HashMap<String, Response.Status>();

    private static Map<Response.Status, Integer> statusSeverityMap = new HashMap<Response.Status, Integer>();

    static {
        errorResponseCodeMap.put(Constants.Errors.INTEGER_CODE, Response.Status.BAD_REQUEST);
        errorResponseCodeMap.put(Constants.Errors.DECIMAL_CODE, Response.Status.BAD_REQUEST);
        errorResponseCodeMap.put(Constants.Errors.DATETIME_CODE, Response.Status.BAD_REQUEST);
        errorResponseCodeMap.put(Constants.Errors.DATE_CODE, Response.Status.BAD_REQUEST);
        errorResponseCodeMap.put(Constants.Errors.BOOLEAN_CODE, Response.Status.BAD_REQUEST);
        errorResponseCodeMap.put(Constants.Errors.NOTEMPTY_CODE, Response.Status.BAD_REQUEST);
        errorResponseCodeMap.put(Constants.Errors.LENGTH_CODE, Response.Status.BAD_REQUEST);
        errorResponseCodeMap.put(Constants.Errors.EXISTID_CODE, Response.Status.NOT_FOUND);
        errorResponseCodeMap.put(Constants.Errors.EXISTFK_CODE, Response.Status.BAD_REQUEST);
        errorResponseCodeMap.put(Constants.Errors.EXISTNAME_CODE, Response.Status.BAD_REQUEST);

        errorResponseCodeMap.put(Constants.Errors.BIZRULE_VIOLATION_CODE, Response.Status.BAD_REQUEST);
        errorResponseCodeMap.put(Constants.Errors.BADREQUEST_CODE, Response.Status.BAD_REQUEST);
        errorResponseCodeMap.put(Constants.Errors.UNAUTHENTICATED_CODE, Response.Status.UNAUTHORIZED);
        errorResponseCodeMap.put(Constants.Errors.UNAUTHORIZED_CODE, Response.Status.FORBIDDEN);
        errorResponseCodeMap.put(Constants.Errors.NOTFOUND_CODE, Response.Status.NOT_FOUND);
        errorResponseCodeMap.put(Constants.Errors.NOTALLOWED_CODE, Response.Status.METHOD_NOT_ALLOWED);
        errorResponseCodeMap.put(Constants.Errors.SERVERERROR_CODE, Response.Status.INTERNAL_SERVER_ERROR);
        errorResponseCodeMap.put(Constants.Errors.DATA_INTEGRITY_VIOLATION_CODE, Response.Status.BAD_REQUEST);

        // severities; smaller the number, severer the error.
        statusSeverityMap.put(Response.Status.INTERNAL_SERVER_ERROR, 10);
        statusSeverityMap.put(Response.Status.UNAUTHORIZED, 20);
        statusSeverityMap.put(Response.Status.FORBIDDEN, 30);
        statusSeverityMap.put(Response.Status.NOT_FOUND, 40);
        statusSeverityMap.put(Response.Status.METHOD_NOT_ALLOWED, 50);
        statusSeverityMap.put(Response.Status.BAD_REQUEST, 60);
    }

    private static Response.Status getStatusByErrors(ResponseErrors responseErrors) {
        List<ResponseError> errorList = responseErrors.getResponseErrors();
        List<Response.Status> httpStatusCandidates = new LinkedList<Response.Status>();
        
        for (ResponseError error : errorList) {
            Response.Status status = errorResponseCodeMap.get(error.getCode());
            if (!httpStatusCandidates.contains(status)) {
                httpStatusCandidates.add(status);
            }
        }

        Collections.sort(httpStatusCandidates, new Comparator<Response.Status>() {
            @Override
            public int compare(Response.Status s1, Response.Status s2) {
                if (s1 == null) {
                    return -1;
                } else if (s2 == null) {
                    return 1;
                } else {
                    return statusSeverityMap.get(s1) - statusSeverityMap.get(s2);
                }
            }
        });

        return httpStatusCandidates.get(0);
    }

    /**
     * Get OK.
     * 
     * @param returnObject
     * @return
     * @see
     * @since
     */
    public static Response retrieved(Object returnObject) {
        return Response.ok(returnObject).build();
    }
    

    /**
     * 
     * Post OK.
     * 
     * @param id new resource id
     * @param uri new resource parent URI
     * @return
     * @see
     * @since
     */
    public static Response created(SuperEntity entity, UriInfo uri) {
        String path = uri.getRequestUri().getPath();
        URI locationHeader = null;
        try {
            locationHeader = new URI(path + "/" + entity.getId());
        } catch (URISyntaxException e) {
            log.error("Error occured when create locationHeader");
        }
        return Response.created(locationHeader).entity(entity).build();
    }

    /**
     * 
     * Put OK.
     * 
     * @return
     * @see
     * @since
     */
    public static Response updated() {
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    /**
     * Delete OK.
     * 
     * @return
     * @see
     * @since
     */
    public static Response deleted() {
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    /**
     * Request failed.
     * 
     * @param responseErrors
     * @return
     * @see
     * @since
     */
    public static Response failed(ResponseErrors responseErrors) {
        JSONObject result = new JSONObject();
        
        delDupliateError(responseErrors);
        result.accumulate("errors", responseErrors.getResponseErrors());
        
        ResponseBuilder builder = Response.status(getStatusByErrors(responseErrors));
        builder.entity(result);
        builder.type(MediaType.APPLICATION_JSON);
        return builder.build();
    }
    
    private static void delDupliateError(ResponseErrors responseErrors){
    	List<ResponseError> errorList = responseErrors.getResponseErrors();
        Set<String> set = new HashSet<String>();
        List<ResponseError> delErrorList = new ArrayList<ResponseError>();
        for (ResponseError error : errorList) {
        	String errorCode=error.getCode();
        	if(!set.contains(errorCode)){
        		set.add(errorCode);
        	}else{
        		delErrorList.add(error);
        	}
        }
        errorList.removeAll(delErrorList);
    }

}
