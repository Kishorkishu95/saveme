package com.kishor.saveme.security.oauth2.user;

import java.util.Map;

import com.kishor.saveme.exception.OAuth2AuthenticationProcessingException;
import com.kishor.saveme.model.AuthProvider;

public class OAuth2UserInfoFactory {

	public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
		if (registrationId.equalsIgnoreCase(AuthProvider.GOOGLE.toString())) {
			return new GoogleOAuth2UserInfo(attributes);
		} else if (registrationId.equalsIgnoreCase(AuthProvider.GITHUB.toString())) {
			return new GithubOAuth2UserInfo(attributes);
		} else {
			throw new OAuth2AuthenticationProcessingException(
					"Sorry! Login with " + registrationId + " is not supported yet.");
		}
	}
}
