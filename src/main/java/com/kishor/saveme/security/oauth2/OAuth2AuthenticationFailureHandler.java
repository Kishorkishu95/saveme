package com.kishor.saveme.security.oauth2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.kishor.saveme.util.CookieUtils;

@Service
public class OAuth2AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	public static final String REDIRECT_URI_PARAM_COOKIE_NAME = "redirect_uri";

	@Autowired
	HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		String targetUrl = CookieUtils.getCookie(request, REDIRECT_URI_PARAM_COOKIE_NAME).map(Cookie::getValue)
				.orElse(("/"));
		targetUrl = UriComponentsBuilder.fromUriString(targetUrl).queryParam("error", exception.getLocalizedMessage())
				.build().toUriString();

		httpCookieOAuth2AuthorizationRequestRepository.removeAuthorizationRequestCookies(request, response);

		getRedirectStrategy().sendRedirect(request, response, targetUrl);
	}
}
