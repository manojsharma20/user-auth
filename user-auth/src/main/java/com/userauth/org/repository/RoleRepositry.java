package com.userauth.org.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.userauth.org.model.Role;


public interface RoleRepositry extends PagingAndSortingRepository<Role, Integer> {

}
