package com.itee.tsd.service;

import java.util.Map;

import com.itee.tsd.dto.PageInfo;
import com.itee.tsd.dto.SearchInfo;

/**
 * 
 * @author Jia Qianpeng
 * 2015-12-06
 */

public interface ShirtManagerService {

	public Map<String, Object> getShirtList(SearchInfo searchInfo, PageInfo pageInfo);
	
}
