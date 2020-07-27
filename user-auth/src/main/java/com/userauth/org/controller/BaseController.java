package com.userauth.org.controller;

import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/apis/v1/")
public interface BaseController {

	static final String USER_BASE_PATH = "users";
	static final String CURRENT_USER_BASE_PATH = "users/{userId}/current";

}
