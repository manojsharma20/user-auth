package com.userauth.org.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.userauth.org.model.AuthClientDetail;

public interface AuthClientDetailJpaRepository extends JpaRepository<AuthClientDetail, String> {
	Optional<AuthClientDetail> findByClientId(String clientId);

}
