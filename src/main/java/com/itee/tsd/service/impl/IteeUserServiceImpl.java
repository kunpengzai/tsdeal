package com.itee.tsd.service.impl;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.itee.tsd.dao.IteeUserDao;
import com.itee.tsd.entity.IteeUser;
import com.itee.tsd.service.IteeUserService;
import com.itee.tsd.utils.Constants;
import com.itee.tsd.utils.Des3Util;

/**
 * 
 * @author Jia Qianpeng
 * 2015-12-25
 */

@Service("iteeUserService")
public class IteeUserServiceImpl implements IteeUserService {

	@Resource
	private IteeUserDao iteeUserDao;
	
	public IteeUser getByCredentials(String username, String password) throws Exception {
		IteeUser iUser = iteeUserDao.getByCredentials(username, Des3Util.encode(Constants.DES_KEY, password));
		return iUser;
	}
	
	@Async
	public void increaseLonginUserNum(Long iteeUserId) {
		iteeUserDao.saveIteeUserLogin(iteeUserId);
	}
}
