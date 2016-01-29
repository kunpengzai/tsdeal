package com.itee.tsd.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.itee.tsd.dao.ShirtDao;
import com.itee.tsd.dto.ShirtPropertyDTO;
import com.itee.tsd.entity.ShirtProperty;
import com.itee.tsd.service.BaseService;

@Service("baseService")
public class BaseServiceImpl implements BaseService {

	private List<ShirtPropertyDTO> colorList;
	private List<ShirtPropertyDTO> brandList;
	private List<ShirtPropertyDTO> sourceList;
	private List<ShirtPropertyDTO> priceRangeList;
	
	@Resource
	private ShirtDao shirtDao;
	
	public List<ShirtPropertyDTO> getColorList() {
//		if (colorList == null) {
			colorList = new ArrayList<ShirtPropertyDTO>();
			List<ShirtProperty> list = shirtDao.getColorList(1);
			for (ShirtProperty sp : list) {
				ShirtPropertyDTO dto = new ShirtPropertyDTO();
				dto.setId(sp.getId());
				dto.setName(sp.getName());
				dto.setOrder(sp.getOrder());
				colorList.add(dto);
			}
//		}
		return colorList;
	}
	public List<ShirtPropertyDTO> getBrandList() {
//		if (brandList == null) {
			brandList = new ArrayList<ShirtPropertyDTO>();
			List<ShirtProperty> list = shirtDao.getBrandList(1);
			for (ShirtProperty sp : list) {
				ShirtPropertyDTO dto = new ShirtPropertyDTO();
				dto.setId(sp.getId());
				dto.setName(sp.getName());
				dto.setOrder(sp.getOrder());
				brandList.add(dto);
			}
//		}
		return brandList;
	}
	public List<ShirtPropertyDTO> getSourceList() {
//		if (sourceList == null) {
			sourceList = new ArrayList<ShirtPropertyDTO>();
			List<ShirtProperty> list = shirtDao.getSourceList(1);
			for (ShirtProperty sp : list) {
				ShirtPropertyDTO dto = new ShirtPropertyDTO();
				dto.setId(sp.getId());
				dto.setName(sp.getName());
				dto.setOrder(sp.getOrder());
				sourceList.add(dto);
			}
//		}
		return sourceList;
	}
	
	public List<ShirtPropertyDTO> getPriceRangeList() {
//		if (priceRangeList == null) {
			priceRangeList = new ArrayList<ShirtPropertyDTO>();
			List<ShirtProperty> list = shirtDao.getPriceRangeList(0);
			for (ShirtProperty sp : list) {
				ShirtPropertyDTO dto = new ShirtPropertyDTO();
				dto.setId(sp.getId());
				dto.setMinPrice(sp.getMinPrice());
				dto.setMaxPrice(sp.getMaxPrice());
				dto.setOrder(sp.getOrder());
				priceRangeList.add(dto);
			}
//		}
		return priceRangeList;
	}
}
