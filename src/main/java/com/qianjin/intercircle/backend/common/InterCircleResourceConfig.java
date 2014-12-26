package com.qianjin.intercircle.backend.common;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

public class InterCircleResourceConfig extends ResourceConfig {
	public InterCircleResourceConfig(){
		packages("com.qianjin.intercircle.backend.resource");
		register(RequestContextFilter.class);
		register(JacksonFeature.class);
	}
}
