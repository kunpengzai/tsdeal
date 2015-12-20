package com.itee.tsd.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.itee.tsd.dao.ShirtDao;
import com.itee.tsd.dto.PageInfo;
import com.itee.tsd.dto.SearchInfo;
import com.itee.tsd.dto.ShirtDTO;
import com.itee.tsd.entity.Shirt;
import com.itee.tsd.service.ShirtManagerService;
import com.itee.tsd.utils.DateUtils;
import com.itee.tsd.utils.JsonBinder;

/**
 * 
 * @author Jia Qianpeng
 * 2015-12-06
 */

@Service("shirtManagerService")
public class ShirtManagerServiceImpl implements ShirtManagerService {
	
	@Resource
	private ShirtDao shirtDao;

	public Map<String, Object> getShirtList(SearchInfo searchInfo, PageInfo pageInfo) {
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("flag", 0);
		Shirt shirt = new Shirt();
		shirt.setBrandId(searchInfo.getBrandId());
		shirt.setSourceId(searchInfo.getSourceId());
		shirt.setIsActive(searchInfo.getIsActive());
		if (StringUtils.isNotBlank(searchInfo.getBeginDate())) {
			shirt.setBeginTime(DateUtils.yyyyMMddToTimestamp(searchInfo.getBeginDate()));
		}
		if (StringUtils.isNotBlank(searchInfo.getEndDate())) {
			shirt.setEndTime(DateUtils.yyyyMMddToTimestamp(searchInfo.getEndDate()));
		}
		m.put("pageNum", pageInfo.getPageNum());
		Integer shirtNum = shirtDao.getShirtNum(shirt);
		m.put("shirtNum", shirtNum);
		if (shirtNum > 0) {
			shirt.setPageNum(pageInfo.getPageNum());
			shirt.setPageSize(pageInfo.getPageSize());
			List<ShirtDTO> list = shirtDao.getShirtList(shirt);
			m.put("shirtList", JsonBinder.buildNormalBinder().toJson(list));
		}
		return m;
	}
}
