package com.itee.tsd.utils;

import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @author Jia Qianpeng
 * 2015-12-06
 */

public class CommonUtils {
	
	public static String getRequestUrl(HttpServletRequest request) {
		String requestUrl = request.getRequestURL().toString();
		String queryString = request.getQueryString();
		if (StringUtils.isNotEmpty(queryString)) {
			return requestUrl + "?" + queryString;
		}
		return requestUrl;
	}
	
	public static String getBaseUrl(HttpServletRequest request) {
		String baseUrl = "";//eg:http://jiaolianbao/clubwx/
		String requestUrl = request.getRequestURL().toString();
		String requestUri = request.getRequestURI();
		Integer index = requestUrl.indexOf(requestUri);
		if (index == -1) {
			baseUrl = requestUrl + "/" + Constants.PROJECT_NAME + "/";
		} else {
			baseUrl = requestUrl.substring(0, index) + "/" + Constants.PROJECT_NAME + "/";
		}
		return baseUrl;
	}
	
	public static String getRequestUri(HttpServletRequest request) {
		String requestUrl = request.getRequestURI().substring(("/"+ Constants.PROJECT_NAME).length());
		String queryString = request.getQueryString();
		if (StringUtils.isNotEmpty(queryString)) {
			return requestUrl + "?" + queryString;
		}
		return requestUrl;
	}
	
	public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().toUpperCase();
    }

	public static String getFieldValueByCookie(HttpServletRequest request, String fieldName) {
		String result = "";
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (fieldName.equals(cookie.getName()) && StringUtils.isNotEmpty(cookie.getValue())) {
					result = cookie.getValue();
					return result;
				}
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		
	}
}
