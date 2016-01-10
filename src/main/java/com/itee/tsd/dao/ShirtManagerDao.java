package com.itee.tsd.dao;

import java.util.List;

import com.itee.tsd.dto.ShirtDTO;
import com.itee.tsd.entity.Shirt;
import com.itee.tsd.entity.ShirtParam;
import com.itee.tsd.entity.ShirtProperty;

/**
 * 
 * @author Jia Qianpeng
 * 2015-12-06
 */

public interface ShirtManagerDao {

	public List<ShirtDTO> getShirtList(Shirt shirt);
	
	public Integer getShirtNum(Shirt shirt);
	
	public ShirtDTO getShirt(Long shirtId);
	
	public int updateShirt(Shirt shirt);
	
	public int updateShirtParam(ShirtParam shirtParam);
	
	public Long saveShirt(Shirt shirt);
	
	public Long saveShirtParam(ShirtParam shirtParam);
	
	public void saveShirtLog(Long shirtId);
	
	public List<ShirtProperty> getColorList(Integer status);
	
	public List<ShirtProperty> getBrandList(Integer status);
	
	public List<ShirtProperty> getSourceList(Integer status);
	
	public List<ShirtProperty> getPriceRangeList(Integer status);
	
}
