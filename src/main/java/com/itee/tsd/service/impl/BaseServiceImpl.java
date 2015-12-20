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
	
	@Resource
	private ShirtDao shirtDao;
	
	public List<ShirtPropertyDTO> getColorList() {
		if (colorList == null) {
			colorList = new ArrayList<ShirtPropertyDTO>();
			List<ShirtProperty> list = shirtDao.getColorList();
			for (ShirtProperty sp : list) {
				ShirtPropertyDTO dto = new ShirtPropertyDTO();
				dto.setId(sp.getId());
				dto.setName(sp.getName());
				colorList.add(dto);
			}
		}
		return colorList;
	}
	public List<ShirtPropertyDTO> getBrandList() {
		if (brandList == null) {
			brandList = new ArrayList<ShirtPropertyDTO>();
			List<ShirtProperty> list = shirtDao.getBrandList();
			for (ShirtProperty sp : list) {
				ShirtPropertyDTO dto = new ShirtPropertyDTO();
				dto.setId(sp.getId());
				dto.setName(sp.getName());
				brandList.add(dto);
			}
		}
		return brandList;
	}
	public List<ShirtPropertyDTO> getSourceList() {
		if (sourceList == null) {
			sourceList = new ArrayList<ShirtPropertyDTO>();
			List<ShirtProperty> list = shirtDao.getSourceList();
			for (ShirtProperty sp : list) {
				ShirtPropertyDTO dto = new ShirtPropertyDTO();
				dto.setId(sp.getId());
				dto.setName(sp.getName());
				sourceList.add(dto);
			}
		}
		return sourceList;
	}
}
