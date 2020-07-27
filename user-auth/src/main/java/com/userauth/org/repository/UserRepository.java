package com.userauth.org.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.userauth.org.model.User;

public interface UserRepository extends PagingAndSortingRepository<User, String> {

	User findByEmailId(String emailId);

}
