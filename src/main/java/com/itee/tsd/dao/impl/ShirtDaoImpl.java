package com.itee.tsd.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itee.tsd.dao.ShirtDao;
import com.itee.tsd.dto.ShirtDTO;
import com.itee.tsd.entity.Shirt;
import com.itee.tsd.entity.ShirtProperty;

/**
 * 
 * @author Jia Qianpeng
 * 2015-12-06
 */

@SuppressWarnings({ "unchecked", "rawtypes" })
public class ShirtDaoImpl extends BaseDaoImpl implements ShirtDao {

	private static final Logger log = LoggerFactory.getLogger(ShirtDaoImpl.class);
	
	public List<ShirtDTO> getMoreShirt(Shirt shirt) {
		try {
			return this.getSqlSession().selectList("shirt.getMoreShirt", shirt);
		} catch(RuntimeException e) {
			log.error("shirtDao.getMoreShirt", e);
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
	
	public List<ShirtProperty> getPriceRangeList() {
		try {
			return this.getSqlSession().selectList("shirt.getPriceRangeList");
		} catch(RuntimeException e) {
			log.error("shirtDao.getPriceRangeList", e);
			throw e;
		}
	}
}
