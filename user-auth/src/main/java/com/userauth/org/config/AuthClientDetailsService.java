package com.userauth.org.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

import com.userauth.org.repository.AuthClientDetailJpaRepository;

@Service
public class AuthClientDetailsService implements ClientDetailsService {

	@Autowired
	private AuthClientDetailJpaRepository authClientDetailJpaRepository;
	@Override
	public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
		return authClientDetailJpaRepository.findByClientId(clientId).orElseThrow(IllegalArgumentException::new);
	}

}
