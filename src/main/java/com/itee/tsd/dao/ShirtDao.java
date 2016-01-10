package com.itee.tsd.dao;

import java.util.List;

import com.itee.tsd.dto.ShirtDTO;
import com.itee.tsd.entity.Shirt;
import com.itee.tsd.entity.ShirtProperty;

/**
 * 
 * @author Jia Qianpeng
 * 2015-12-06
 */

public interface ShirtDao {

	public List<ShirtDTO> getMoreShirt(Shirt shirt);
	
	public int updateShirtLog(Long shirtId, Integer clickNum);
	
	public List<ShirtProperty> getColorList();
	
	public List<ShirtProperty> getBrandList();
	
	public List<ShirtProperty> getSourceList();
	
	public List<ShirtProperty> getPriceRangeList();
	
}
