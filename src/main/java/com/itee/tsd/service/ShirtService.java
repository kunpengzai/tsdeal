package com.itee.tsd.service;

import java.util.Map;

import com.itee.tsd.dto.PageInfo;
import com.itee.tsd.dto.SearchInfo;

public interface ShirtService {

	public Map<String, Object> getMoreShirt(SearchInfo searchInfo, PageInfo pageInfo) throws Exception;
}
