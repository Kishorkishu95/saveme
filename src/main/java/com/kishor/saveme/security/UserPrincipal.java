package com.kishor.saveme.security;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.kishor.saveme.model.User;

public class UserPrincipal implements OAuth2User, UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String email;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;
	private Map<String, Object> attributes;

	public UserPrincipal(Long id, String email, String password, Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}

	public static UserPrincipal create(User user) {
		List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));

		return new UserPrincipal(user.getId(), user.getEmail(), user.getPassword(), authorities);
	}

	public static UserPrincipal create(User user, Map<String, Object> attributes) {
		UserPrincipal userPrincipal = UserPrincipal.create(user);
		userPrincipal.setAttributes(attributes);
		return userPrincipal;
	}

	public Long getId() {
		return id;
	}



	public String getEmail() {
		return email;
	}



	public String getPassword() {
		return password;
	}



	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}


	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return String.valueOf(id);
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

}
