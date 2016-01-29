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

	public List<ShirtProperty> getColorList(Integer status);

	public List<ShirtProperty> getBrandList(Integer status);

	public List<ShirtProperty> getSourceList(Integer status);

	public List<ShirtProperty> getPriceRangeList(Integer status);
	
}
