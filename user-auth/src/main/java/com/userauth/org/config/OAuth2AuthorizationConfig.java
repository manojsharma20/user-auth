package com.userauth.org.config;


import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;


@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;
    
    @Autowired
    private AuthClientDetailsService authClientDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value ("${spring.oauth.tokenexpiration.ms}")
    private Integer tokenExpirationMs;
    
    @Autowired
    private DataSource dataSource;
    
//    @Autowired
//    private SystemConfigJpaRepository systemConfigJpaRepository; 
    
    @Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(jwtAccessTokenConvertor());
	}
    
    @Bean
	public JwtAccessTokenConverter jwtAccessTokenConvertor() {
		final JwtAccessTokenConverter converter = new JwtAccessTokenConverter() {
			@Override
			public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
				accessToken = super.enhance(accessToken, authentication);
				((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(new HashMap<>());
				return accessToken;
			}
		};
		converter.setSigningKey("userauth");
		return converter;
	}
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    	endpoints.allowedTokenEndpointRequestMethods(HttpMethod.POST).tokenStore(tokenStore())
		.authenticationManager(authenticationManager).userDetailsService(userDetailsService)
		.accessTokenConverter(jwtAccessTokenConvertor()).allowedTokenEndpointRequestMethods(HttpMethod.POST);
    }

    @Override
	public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
    	clients.withClientDetails(authClientDetailsService);
	}
    
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .passwordEncoder(passwordEncoder)
                .allowFormAuthenticationForClients();
    }

}
