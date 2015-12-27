package com.itee.tsd.dao;

import com.itee.tsd.entity.SystemParam;

public interface SystemParamDao {

	public SystemParam getSystemParam(String systemName, String name);
}
