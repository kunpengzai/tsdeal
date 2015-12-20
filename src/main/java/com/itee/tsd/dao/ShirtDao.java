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

public interface ShirtDao {

	public List<ShirtDTO> getShirtList(Shirt shirt);
	
	public Integer getShirtNum(Shirt shirt);
	
	public ShirtDTO getShirt(Long shirtId);
	
	public int updateShirt(Shirt shirt);
	
	public int updateShirtParam(ShirtParam shirtParam);
	
	public int updateShirtLog(Long shirtId, Integer clickNum);
	
	public Long saveShirt(Shirt shirt);
	
	public Long saveShirtParam(ShirtParam shirtParam);
	
	public void saveShirtLog(Long shirtId);
	
	public List<ShirtProperty> getColorList();
	
	public List<ShirtProperty> getBrandList();
	
	public List<ShirtProperty> getSourceList();
	
}
