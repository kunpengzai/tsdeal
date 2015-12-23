package com.itee.tsd.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.itee.tsd.dto.PageInfo;
import com.itee.tsd.dto.SearchInfo;
import com.itee.tsd.dto.ShirtDTO;

/**
 * 
 * @author Jia Qianpeng
 * 2015-12-06
 */

public interface ShirtManagerService {

	public Map<String, Object> getShirtList(SearchInfo searchInfo, PageInfo pageInfo);
	
	public void addShirt(ShirtDTO shirt, MultipartFile imageFile) throws IOException;
	
	public Map<String, Object> deleteShirt(Long shirtId);
}
