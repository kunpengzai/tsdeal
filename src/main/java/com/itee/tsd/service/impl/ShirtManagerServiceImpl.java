package com.itee.tsd.service.impl;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.itee.tsd.dao.ShirtDao;
import com.itee.tsd.dto.PageInfo;
import com.itee.tsd.dto.SearchInfo;
import com.itee.tsd.dto.ShirtDTO;
import com.itee.tsd.entity.Shirt;
import com.itee.tsd.entity.ShirtParam;
import com.itee.tsd.service.ShirtManagerService;
import com.itee.tsd.utils.Config;
import com.itee.tsd.utils.DateUtils;
import com.itee.tsd.utils.DownloadUtils;
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
			Date endDate = DateUtils.addOrMinusDate(DateUtils.yyyyMMddToDate(searchInfo.getEndDate()), 5, 1);
			shirt.setEndTime(new Timestamp(endDate.getTime()));
		}
		shirt.setStatus(0);
		shirt.setOrderCont("s.id desc");
		m.put("pageNum", pageInfo.getPageNum());
		m.put("pageSize", pageInfo.getPageSize());
		Integer totalCount = shirtDao.getShirtNum(shirt);
		m.put("totalCount", totalCount);
		if (totalCount > 0) {
			shirt.setPageNum(pageInfo.getPageNum());
			shirt.setPageSize(pageInfo.getPageSize());
			List<ShirtDTO> list = shirtDao.getShirtList(shirt);
			m.put("shirtList", JsonBinder.buildNormalBinder().toJson(list));
		}
		return m;
	}
	
	public Map<String, Object> shirtDetail(Long shirtId) {
		Map<String, Object> m = new HashMap<String, Object>();
		if (shirtId != null) {
			m.put("flag", 0);
			ShirtDTO shirt = shirtDao.getShirt(shirtId);
			if (shirt != null) {
				List<ShirtDTO> list = new ArrayList<ShirtDTO>();
				list.add(shirt);
				m.put("shirtList", JsonBinder.buildNormalBinder().toJson(list));
				return m;
			}
		}
		m.put("flag", 1);
		return m;
	}
	
	public void addShirt(ShirtDTO shirt, MultipartFile imageFile) throws IOException {
		Shirt newShirt = new Shirt();
		newShirt.setLinkUrl(shirt.getLinkUrl());
		newShirt.setTitle(shirt.getTitle());
		newShirt.setMinPrice(shirt.getMinPrice());
		newShirt.setMaxPrice(shirt.getMaxPrice());
		newShirt.setSourceId(shirt.getSourceId());
		newShirt.setDesign(shirt.getDesign());
		newShirt.setBrandId(shirt.getBrandId());
		newShirt.setSleeve(shirt.getSleeve());
		newShirt.setImgType(shirt.getImgType());
		newShirt.setIsActive(shirt.getIsActive());
		if (shirt.getImgType() == 2) {
			newShirt.setShirtImg(shirt.getShirtImg());
		}
		Long shirtId = shirtDao.saveShirt(newShirt);
		
		if (StringUtils.isNotBlank(shirt.getColorIds())) {
			String[] colorIds = shirt.getColorIds().split(",");
			for (String colorId : colorIds) {
				ShirtParam shirtParam = new ShirtParam();
				shirtParam.setShirtId(shirtId);
				shirtParam.setColorId(Long.valueOf(colorId));
				shirtParam.setPrice(shirt.getMinPrice());
				shirtParam.setStatus(0);
				shirtDao.saveShirtParam(shirtParam);
			}
		}
		if (shirt.getImgType() == 1) {//Upload File
			if (imageFile != null && imageFile.getSize() > 0) {
				String newFileName = uploadFile(imageFile, shirtId);
				Shirt updateShirt = new Shirt();
				updateShirt.setId(shirtId);
				updateShirt.setShirtImg(newFileName);
				shirtDao.updateShirt(updateShirt);
			}
		}
	}
	
	public Map<String, Object> deleteShirt(Long shirtId) {
		Map<String, Object> m = new HashMap<String, Object>();
		if (shirtId != null) {
			Shirt shirt = new Shirt();
			shirt.setId(shirtId);
			shirt.setStatus(-1);
			shirtDao.updateShirt(shirt);
			m.put("flag", 0);
			return m;
		}
		m.put("flag", 1);
		return m;
	}
	
	public void editShirt(ShirtDTO shirt, MultipartFile imageFile) throws IOException {
		if (shirt.getShirtId() != null) {
			ShirtDTO dto = shirtDao.getShirt(shirt.getShirtId());
			if (dto != null) {
				Shirt updateShirt = new Shirt();
				updateShirt.setId(shirt.getShirtId());
				updateShirt.setLinkUrl(shirt.getLinkUrl());
				updateShirt.setTitle(shirt.getTitle());
				updateShirt.setMinPrice(shirt.getMinPrice());
				updateShirt.setMaxPrice(shirt.getMaxPrice());
				updateShirt.setSourceId(shirt.getSourceId());
				updateShirt.setDesign(shirt.getDesign());
				updateShirt.setBrandId(shirt.getBrandId());
				updateShirt.setSleeve(shirt.getSleeve());
				updateShirt.setWeight(shirt.getWeight());
				updateShirt.setImgType(shirt.getImgType());
				updateShirt.setIsActive(shirt.getIsActive());
				if (dto.getImgType() == 1) {
					if (shirt.getImgType() == 2) {
						deleteFile(Config.getProperty("SAVE_SHIRT_IMG_PATH") + dto.getShirtImg());
					} else if (shirt.getImgType() == 1 && imageFile != null && imageFile.getSize() > 0) {
						deleteFile(Config.getProperty("SAVE_SHIRT_IMG_PATH") + dto.getShirtImg());
					}
				}
				if (shirt.getImgType() == 1 && imageFile != null && imageFile.getSize() > 0) {
					String newFileName = uploadFile(imageFile, shirt.getShirtId());
					updateShirt.setShirtImg(newFileName);
				}
				if (shirt.getImgType() == 2) {
					updateShirt.setShirtImg(shirt.getShirtImg());
				}
				shirtDao.updateShirt(updateShirt);
				
				if (StringUtils.isNotBlank(dto.getColorIds())) {
					ShirtParam shirtParam = new ShirtParam();
					shirtParam.setShirtId(shirt.getShirtId());
					shirtParam.setStatus(-1);
					shirtDao.updateShirtParam(shirtParam);
				}
				if (StringUtils.isNotBlank(shirt.getColorIds())) {
					String[] colorIds = shirt.getColorIds().split(",");
					for (String colorId : colorIds) {
						ShirtParam shirtParam = new ShirtParam();
						shirtParam.setShirtId(shirt.getShirtId());
						shirtParam.setColorId(Long.valueOf(colorId));
						shirtParam.setPrice(shirt.getMinPrice());
						shirtParam.setStatus(0);
						shirtDao.saveShirtParam(shirtParam);
					}
				}
			}
		}
	}
	
	private String uploadFile(MultipartFile imageFile, Long shirtId) throws IOException {
		String originalFilename = imageFile.getOriginalFilename();
		String extFileName = originalFilename.substring(originalFilename.lastIndexOf("."));
		String dateStr = DateUtils.dateToyyyyMMddHHmissWithSeparator(
				new Timestamp(new Date().getTime()));
		String newFileName = "shirt_" + shirtId + "_" + 
				new Random().nextInt(10000) + "_" + dateStr + extFileName;
		
		DownloadUtils.getFile(imageFile.getInputStream(), newFileName, 
				Config.getProperty("SAVE_SHIRT_IMG_PATH"));
		return newFileName;
	}
	
	private boolean deleteFile(String fielName) {
		File file = new File(fielName);
		if (file.exists()) {
			return file.delete();
		}
		return true;
	}
}
