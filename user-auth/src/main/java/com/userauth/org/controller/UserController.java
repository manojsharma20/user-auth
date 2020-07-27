package com.userauth.org.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.userauth.org.common.ApiResponse;
import com.userauth.org.common.utility.Utility;
import com.userauth.org.model.User;
import com.userauth.org.repository.UserRepository;

@RestController
public class UserController implements BaseController {

	@Autowired
	private UserRepository userRepository;

	@PreAuthorize("hasAuthority('PRIVILEGE_ADMIN_ALL')")
	@RequestMapping(value = { BaseController.USER_BASE_PATH,
			"/" + BaseController.USER_BASE_PATH }, method = RequestMethod.GET)
	public ResponseEntity<Object> getUsers(Pageable pageable) {
		Page<User> userPages = userRepository.findAll(pageable);
		ApiResponse response = Utility.prepareApiResponseData(HttpStatus.OK, "List of user fatched.", userPages);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('PRIVILEGE_ADMIN_ALL')")
	@RequestMapping(value = { BaseController.CURRENT_USER_BASE_PATH,
			"/" + BaseController.CURRENT_USER_BASE_PATH }, method = RequestMethod.GET)
	public ResponseEntity<Object> getCurrentUser(@PathVariable(name="userId") String userId) {
		ApiResponse response = null;
		if(userId == null) {
			response = Utility.prepareApiResponseErrorData(HttpStatus.OK, "User Id not found, Please contact administrator.", false);
		}
		
		Optional<User> user = userRepository.findById(userId);
		
		if(user.isPresent()) {
			response = Utility.prepareApiResponseData(HttpStatus.OK, "Current user details fatched.", user.get());
		} else {
			response = Utility.prepareApiResponseErrorData(HttpStatus.OK, "User details not found, Please contact administrator.", false);
		}
		
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
}
