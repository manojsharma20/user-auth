package com.userauth.org.config;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.userauth.org.model.Privilege;
import com.userauth.org.model.Role;
import com.userauth.org.model.User;

public class CustomUserPrincipal implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;

	public CustomUserPrincipal(User user) {
		super();
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> roleAuthorities = getRolesAuthorities(user.getRoles());
		return roleAuthorities.stream().collect(Collectors.toList());
	}

	private List<GrantedAuthority> getRolesAuthorities(Set<Role> roles) {
		List<GrantedAuthority> authorities = new LinkedList<>();

		GrantedAuthority authority = null;
		for (Role role : roles) {
			for (Privilege privilege : role.getPrivileges()) {
				authority = new SimpleGrantedAuthority(privilege.getPrivilegeName());
				authorities.add(authority);
			}
		}

		return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmailId();
	}

	@Override
	public boolean isAccountNonExpired() {
		return user.getIsAccountNonExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		return user.getIsAccountNonLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return user.getIsEnabled();
	}
}
