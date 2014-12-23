package com.qianjin.intercircle.backend.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qianjin.intercircle.backend.common.ResponseUtility;
import com.qianjin.intercircle.backend.entity.User;
import com.qianjin.intercircle.backend.service.UserService;

@Component
@Path("user")
public class UserResource {

	@Autowired
	private UserService userService;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response register(User user,@Context UriInfo uri){
		user.setId(null);
		userService.create(user);
		user = userService.getObjectById(User.class, user.getId());
		return ResponseUtility.created(user, uri);
	}
}
