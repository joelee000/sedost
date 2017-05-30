package com.sedost.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sedost.mapper.UserMapper;
import com.sedost.model.User;
/**
 * 
 * @author joelee
 *
 */
@Service
public class UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	public User getUserInfo() {
		User user = userMapper.findUserInfo();
		return user;
	}
}
