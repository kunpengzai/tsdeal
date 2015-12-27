package com.itee.tsd.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itee.tsd.dao.SystemParamDao;
import com.itee.tsd.entity.SystemParam;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class SystemParamDaoImpl extends BaseDaoImpl implements SystemParamDao {
	
	private static final Logger log = LoggerFactory.getLogger(SystemParamDaoImpl.class);
	
	public SystemParam getSystemParam(String systemName, String name) {
		try{
			Map map = new HashMap();
			map.put("systemName", systemName);
			map.put("name", name);
			return this.getSqlSession().selectOne("systemParam.getSystemParam", map);
		} catch(RuntimeException e){
			log.error("systemParamDao.getSystemParam", e);
			throw e;
		}
	}
}
