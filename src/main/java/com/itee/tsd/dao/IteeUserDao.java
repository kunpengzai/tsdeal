package com.itee.tsd.dao;

import com.itee.tsd.entity.IteeUser;

public interface IteeUserDao {

	public IteeUser getByCredentials(String username, String password);
	
	public void saveIteeUserLogin(Long iteeUserId);
}
