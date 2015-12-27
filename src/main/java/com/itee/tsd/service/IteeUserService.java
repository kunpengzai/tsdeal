package com.itee.tsd.service;

import com.itee.tsd.entity.IteeUser;

public interface IteeUserService {

	public IteeUser getByCredentials(String username, String password) throws Exception;
	
	public void increaseLonginUserNum(Long iteeUserId);
}
