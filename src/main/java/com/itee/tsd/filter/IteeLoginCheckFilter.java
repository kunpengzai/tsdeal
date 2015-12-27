package com.itee.tsd.filter;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import com.itee.tsd.utils.CommonUtils;
import com.itee.tsd.utils.Constants;

public class IteeLoginCheckFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String[] filterItems = new String[] {"/sm/"};
		String requestUri = request.getRequestURI();
		for (String tmpUri : filterItems) {
            if (requestUri.indexOf(tmpUri) > 0) {
            	Object obj = request.getSession(true).getAttribute(Constants.SYS_USER_INF_IN_SESSION);
            	if (obj == null) {
            		String notifyUrl = URLEncoder.encode(CommonUtils.getRequestUrl(request), "UTF-8");
            		response.sendRedirect(CommonUtils.getBaseUrl(request) + "iteelogin/login.htm?notifyUrl="+notifyUrl);
					return;
            	}
            }
		}
		
		filterChain.doFilter(request, response);
	}

}
