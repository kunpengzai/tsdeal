package com.itee.tsd.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itee.tsd.dao.ShirtDao;
import com.itee.tsd.dto.ShirtDTO;
import com.itee.tsd.entity.Shirt;
import com.itee.tsd.entity.ShirtParam;
import com.itee.tsd.entity.ShirtProperty;

/**
 * 
 * @author Jia Qianpeng
 * 2015-12-06
 */

@SuppressWarnings({ "unchecked", "rawtypes" })
public class ShirtDaoImpl extends BaseDaoImpl implements ShirtDao {

	private static final Logger log = LoggerFactory.getLogger(ShirtDaoImpl.class);
	
	public List<ShirtDTO> getShirtList(Shirt shirt) {
		try {
			return this.getSqlSession().selectList("shirt.getShirtList", shirt);
		} catch(RuntimeException e) {
			log.error("shirtDao.getShirtList", e);
			throw e;
		}
	}
	
	public Integer getShirtNum(Shirt shirt) {
		try {
			return this.getSqlSession().selectOne("shirt.getShirtNum", shirt);
		} catch(RuntimeException e) {
			log.error("shirtDao.getShirtNum", e);
			throw e;
		}
	}
	
	public ShirtDTO getShirt(Long shirtId) {
		try {
			return this.getSqlSession().selectOne("shirt.getShirt", shirtId);
		} catch(RuntimeException e) {
			log.error("shirtDao.getShirt", e);
			throw e;
		}
	}
	
	public int updateShirt(Shirt shirt) {
		try {
			return this.getSqlSession().update("shirt.updateShirt", shirt);
		} catch(RuntimeException e) {
			log.error("shirtDao.updateShirt", e);
			throw e;
		}
	}
	
	public int updateShirtParam(ShirtParam shirtParam) {
		try {
			return this.getSqlSession().update("shirt.updateShirtParam", shirtParam);
		} catch(RuntimeException e) {
			log.error("shirtDao.updateShirtParam", e);
			throw e;
		}
	}
	
	public int updateShirtLog(Long shirtId, Integer clickNum) {
		try {
			Map map = new HashMap();
			map.put("shirtId", shirtId);
			map.put("clickNum", clickNum);
			return this.getSqlSession().update("shirt.updateShirtLog", map);
		} catch(RuntimeException e) {
			log.error("shirtDao.updateShirtLog", e);
			throw e;
		}
	}
	
	public Long saveShirt(Shirt shirt) {
		try {
			this.getSqlSession().insert("shirt.saveShirt", shirt);
			return shirt.getId();
		} catch(RuntimeException e) {
			log.error("shirtDao.saveShirt", e);
			throw e;
		}
	}
	
	public Long saveShirtParam(ShirtParam shirtParam) {
		try {
			this.getSqlSession().insert("shirt.saveShirtParam", shirtParam);
			return shirtParam.getId();
		} catch(RuntimeException e) {
			log.error("shirtDao.saveShirtParam", e);
			throw e;
		}
	}
	
	public void saveShirtLog(Long shirtId) {
		try {
			this.getSqlSession().insert("shirt.saveShirtLog", shirtId);
		} catch(RuntimeException e) {
			log.error("shirtDao.saveShirtLog", e);
			throw e;
		}
	}
	
	public List<ShirtProperty> getColorList() {
		try {
			return this.getSqlSession().selectList("shirt.getColorList");
		} catch(RuntimeException e) {
			log.error("shirtDao.getColorList", e);
			throw e;
		}
	}
	
	public List<ShirtProperty> getBrandList() {
		try {
			return this.getSqlSession().selectList("shirt.getBrandList");
		} catch(RuntimeException e) {
			log.error("shirtDao.getBrandList", e);
			throw e;
		}
	}
	
	public List<ShirtProperty> getSourceList() {
		try {
			return this.getSqlSession().selectList("shirt.getSourceList");
		} catch(RuntimeException e) {
			log.error("shirtDao.getSourceList", e);
			throw e;
		}
	}
}
