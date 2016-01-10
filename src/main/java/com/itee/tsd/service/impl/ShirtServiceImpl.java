package com.itee.tsd.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.itee.tsd.dao.ShirtDao;
import com.itee.tsd.dto.PageInfo;
import com.itee.tsd.dto.SearchInfo;
import com.itee.tsd.dto.ShirtDTO;
import com.itee.tsd.entity.Shirt;
import com.itee.tsd.service.ShirtService;
import com.itee.tsd.utils.Config;
import com.itee.tsd.utils.JsonBinder;

@Service("shirtService")
public class ShirtServiceImpl implements ShirtService {
	
	private static final Logger logger = LoggerFactory.getLogger(ShirtServiceImpl.class);
	
	@Resource
	private ShirtDao shirtDao;

	public Map<String, Object> getMoreShirt(SearchInfo searchInfo, PageInfo pageInfo) {
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("flag", 0);
		Shirt shirt = new Shirt();
		if (searchInfo.getMinPrice() != null) {
			shirt.setMinPrice(searchInfo.getMinPrice());
		}
		if (searchInfo.getMaxPrice() != null) {
			shirt.setMaxPrice(searchInfo.getMaxPrice());
		}
		String colorIds = searchInfo.getColorIds();
		if (StringUtils.isNotBlank(colorIds)) {
			if (colorIds.endsWith(",")) {
				colorIds = colorIds.substring(0, colorIds.length() - 1);
				shirt.setColorIds(colorIds);
			}
		}
		if (searchInfo.getBrandId() != null && searchInfo.getBrandId() > 0) {
			shirt.setBrandId(searchInfo.getBrandId());
		}
		if (searchInfo.getSourceId() != null && searchInfo.getSourceId() > 0) {
			shirt.setSourceId(searchInfo.getSourceId());
		}
		if (searchInfo.getDesign() != null && searchInfo.getDesign() > 0) {
			shirt.setDesign(searchInfo.getDesign());
		}
		if (searchInfo.getSleeve() != null && searchInfo.getSleeve() > 0) {
			shirt.setSleeve(searchInfo.getSleeve());
		}
		if (pageInfo != null) {
			if (pageInfo.getPageNum() == null) {
				pageInfo.setPageNum(1);
			}
			if (pageInfo.getPageSize() == null) {
				pageInfo.setPageSize(20);
			}
			shirt.setPageNum(pageInfo.getPageNum());
			shirt.setPageSize(pageInfo.getPageSize());
		}
		shirt.setIsActive(1);
		shirt.setStatus(0);
		shirt.setOrderCont("s.weight desc, s.id desc");
		List<ShirtDTO> list = shirtDao.getMoreShirt(shirt);
		m.put("shirtList", JsonBinder.buildNormalBinder().toJson(list));
		m.put("baseUrl", Config.getProperty("BASE_IMG_URL"));
		return m;
	}
}