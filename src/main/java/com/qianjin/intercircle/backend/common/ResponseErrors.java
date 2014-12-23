package com.qianjin.intercircle.backend.common;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.qianjin.intercircle.backend.entity.ResponseError;

/**
 * Error response list in request scope.
 * <p>
 * Copyright (c) 2012 Acxiom Corporation. All Rights Reserved.
 * </p>
 * 
 * @author wexiao date: 2013/12/16
 *         <p>
 *         Last updated by wexiao date: 2013/12/16
 *         </p>
 */
@Component("responseErrors")
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ResponseErrors {

    private List<ResponseError> responseErrors = new ArrayList<ResponseError>();

    /**
     * @return the responseErrors
     */
    public List<ResponseError> getResponseErrors() {
        return responseErrors;
    }

    /**
     * @param responseErrors the responseErrors to set
     */
    public void setResponseErrors(List<ResponseError> responseErrors) {
        this.responseErrors = responseErrors;
    }

    public boolean isContainError() {
        if (responseErrors.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void addError(ResponseError error) {
        responseErrors.add(error);
    }

}
