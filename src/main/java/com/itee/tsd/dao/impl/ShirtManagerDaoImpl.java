package com.itee.tsd.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itee.tsd.dao.ShirtManagerDao;
import com.itee.tsd.dto.ShirtDTO;
import com.itee.tsd.entity.Shirt;
import com.itee.tsd.entity.ShirtParam;
import com.itee.tsd.entity.ShirtProperty;

/**
 * 
 * @author Jia Qianpeng
 * 2015-12-06
 */

public class ShirtManagerDaoImpl extends BaseDaoImpl implements ShirtManagerDao {

	private static final Logger log = LoggerFactory.getLogger(ShirtManagerDaoImpl.class);
	
	public List<ShirtDTO> getShirtList(Shirt shirt) {
		try {
			return this.getSqlSession().selectList("shirtManager.getShirtList", shirt);
		} catch(RuntimeException e) {
			log.error("shirtManagerDao.getShirtList", e);
			throw e;
		}
	}
	
	public Integer getShirtNum(Shirt shirt) {
		try {
			return this.getSqlSession().selectOne("shirtManager.getShirtNum", shirt);
		} catch(RuntimeException e) {
			log.error("shirtManagerDao.getShirtNum", e);
			throw e;
		}
	}
	
	public ShirtDTO getShirt(Long shirtId) {
		try {
			return this.getSqlSession().selectOne("shirtManager.getShirt", shirtId);
		} catch(RuntimeException e) {
			log.error("shirtManagerDao.getShirt", e);
			throw e;
		}
	}
	
	public int updateShirt(Shirt shirt) {
		try {
			return this.getSqlSession().update("shirtManager.updateShirt", shirt);
		} catch(RuntimeException e) {
			log.error("shirtManagerDao.updateShirt", e);
			throw e;
		}
	}
	
	public int updateShirtParam(ShirtParam shirtParam) {
		try {
			return this.getSqlSession().update("shirtManager.updateShirtParam", shirtParam);
		} catch(RuntimeException e) {
			log.error("shirtManagerDao.updateShirtParam", e);
			throw e;
		}
	}
	
	public Long saveShirt(Shirt shirt) {
		try {
			this.getSqlSession().insert("shirtManager.saveShirt", shirt);
			return shirt.getId();
		} catch(RuntimeException e) {
			log.error("shirtManagerDao.saveShirt", e);
			throw e;
		}
	}
	
	public Long saveShirtParam(ShirtParam shirtParam) {
		try {
			this.getSqlSession().insert("shirtManager.saveShirtParam", shirtParam);
			return shirtParam.getId();
		} catch(RuntimeException e) {
			log.error("shirtManagerDao.saveShirtParam", e);
			throw e;
		}
	}
	
	public void saveShirtLog(Long shirtId) {
		try {
			this.getSqlSession().insert("shirtManager.saveShirtLog", shirtId);
		} catch(RuntimeException e) {
			log.error("shirtManagerDao.saveShirtLog", e);
			throw e;
		}
	}
	
	public List<ShirtProperty> getColorList(Integer status) {
		try {
			return this.getSqlSession().selectList("shirtManager.getColorList", status);
		} catch(RuntimeException e) {
			log.error("shirtManagerDao.getColorList", e);
			throw e;
		}
	}
	
	public List<ShirtProperty> getBrandList(Integer status) {
		try {
			return this.getSqlSession().selectList("shirtManager.getBrandList", status);
		} catch(RuntimeException e) {
			log.error("shirtManagerDao.getBrandList", e);
			throw e;
		}
	}
	
	public List<ShirtProperty> getSourceList(Integer status) {
		try {
			return this.getSqlSession().selectList("shirtManager.getSourceList", status);
		} catch(RuntimeException e) {
			log.error("shirtManagerDao.getSourceList", e);
			throw e;
		}
	}
	
	public List<ShirtProperty> getPriceRangeList(Integer status) {
		try {
			return this.getSqlSession().selectList("shirtManager.getPriceRangeList", status);
		} catch(RuntimeException e) {
			log.error("shirtManagerDao.getPriceRangeList", e);
			throw e;
		}
	}
}
