package com.itee.tsd.service;

import java.util.List;

import com.itee.tsd.dto.ShirtPropertyDTO;

public interface BaseService {

	public List<ShirtPropertyDTO> getColorList();
	
	public List<ShirtPropertyDTO> getBrandList();
	
	public List<ShirtPropertyDTO> getSourceList();
}
