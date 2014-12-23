package com.qianjin.intercircle.backend.service.impl;

import org.springframework.stereotype.Service;

import com.qianjin.intercircle.backend.entity.User;
import com.qianjin.intercircle.backend.service.UserService;

@Service("userService")
public class UserServiceImpl extends SuperServiceImpl<User> implements UserService {

}
