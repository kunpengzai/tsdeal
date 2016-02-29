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

import com.itee.tsd.utils.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.itee.tsd.dao.ShirtManagerDao;
import com.itee.tsd.dao.SystemParamDao;
import com.itee.tsd.dto.PageInfo;
import com.itee.tsd.dto.SearchInfo;
import com.itee.tsd.dto.ShirtDTO;
import com.itee.tsd.entity.Shirt;
import com.itee.tsd.entity.ShirtParam;
import com.itee.tsd.entity.SystemParam;
import com.itee.tsd.service.ShirtManagerService;

/**
 * 
 * @author Jia Qianpeng
 * 2015-12-06
 */

@Service("shirtManagerService")
public class ShirtManagerServiceImpl implements ShirtManagerService {
	
	private static final Logger logger = LoggerFactory.getLogger(ShirtManagerServiceImpl.class);
	
	@Resource
	private ShirtManagerDao shirtManagerDao;
	@Resource
	private SystemParamDao systemParamDao;

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
		Integer totalCount = shirtManagerDao.getShirtNum(shirt);
		m.put("totalCount", totalCount);
		if (totalCount > 0) {
			shirt.setPageNum(pageInfo.getPageNum());
			shirt.setPageSize(pageInfo.getPageSize());
			List<ShirtDTO> list = shirtManagerDao.getShirtList(shirt);
			m.put("shirtList", JsonBinder.buildNormalBinder().toJson(list));
		}
		logger.info("getShirtList");
		return m;
	}
	
	public Map<String, Object> shirtDetail(Long shirtId) {
		Map<String, Object> m = new HashMap<String, Object>();
		if (shirtId != null) {
			m.put("flag", 0);
			ShirtDTO shirt = shirtManagerDao.getShirt(shirtId);
			if (shirt != null) {
				List<ShirtDTO> list = new ArrayList<ShirtDTO>();
				list.add(shirt);
				m.put("shirtList", JsonBinder.buildNormalBinder().toJson(list));
				return m;
			}
		}
		m.put("flag", 1);
		logger.info("shirtDetail=" + shirtId);
		return m;
	}
	
	public void addShirt(ShirtDTO shirt, MultipartFile imageFile) throws IOException {
		Shirt newShirt = new Shirt();
		newShirt.setLinkUrl(shirt.getLinkUrl());
		newShirt.setUrl(shirt.getUrl());
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
		Long shirtId = shirtManagerDao.saveShirt(newShirt);
		
		if (StringUtils.isNotBlank(shirt.getColorIds())) {
			String[] colorIds = shirt.getColorIds().split(",");
			for (String colorId : colorIds) {
				ShirtParam shirtParam = new ShirtParam();
				shirtParam.setShirtId(shirtId);
				shirtParam.setColorId(Long.valueOf(colorId));
				shirtParam.setPrice(shirt.getMinPrice());
				shirtParam.setStatus(0);
				shirtManagerDao.saveShirtParam(shirtParam);
			}
		}
		if (shirt.getImgType() == 1) {//Upload File
			if (imageFile != null && imageFile.getSize() > 0) {
				String newFileName = uploadFile(imageFile, shirtId);
				Shirt updateShirt = new Shirt();
				updateShirt.setId(shirtId);
				updateShirt.setShirtImg(newFileName);
				shirtManagerDao.updateShirt(updateShirt);
			}
		}
		shirtManagerDao.saveShirtLog(shirtId);
		logger.info("addShirt=" + shirtId);
	}
	
	public Map<String, Object> deleteShirt(Long shirtId) {
		Map<String, Object> m = new HashMap<String, Object>();
		if (shirtId != null) {
			Shirt shirt = new Shirt();
			shirt.setId(shirtId);
			shirt.setStatus(-1);
			shirtManagerDao.updateShirt(shirt);
			m.put("flag", 0);
			return m;
		}
		m.put("flag", 1);
		logger.info("deleteShirt=" + shirtId);
		return m;
	}
	
	public void editShirt(ShirtDTO shirt, MultipartFile imageFile) throws IOException {
		if (shirt.getShirtId() != null) {
			ShirtDTO dto = shirtManagerDao.getShirt(shirt.getShirtId());
			if (dto != null) {
				Shirt updateShirt = new Shirt();
				updateShirt.setId(shirt.getShirtId());
				updateShirt.setLinkUrl(shirt.getLinkUrl());
				updateShirt.setUrl(shirt.getUrl());
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
						deleteFile(Config.getProperty("SAVE_SHIRT_COMPRESS_IMG_PATH") + dto.getShirtImg());
					} else if (shirt.getImgType() == 1 && imageFile != null && imageFile.getSize() > 0) {
						deleteFile(Config.getProperty("SAVE_SHIRT_IMG_PATH") + dto.getShirtImg());
						deleteFile(Config.getProperty("SAVE_SHIRT_COMPRESS_IMG_PATH") + dto.getShirtImg());
					}
				}
				if (shirt.getImgType() == 1 && imageFile != null && imageFile.getSize() > 0) {
					String newFileName = uploadFile(imageFile, shirt.getShirtId());
					updateShirt.setShirtImg(newFileName);
				}
				if (shirt.getImgType() == 2) {
					updateShirt.setShirtImg(shirt.getShirtImg());
				}
				shirtManagerDao.updateShirt(updateShirt);
				
				if (StringUtils.isNotBlank(dto.getColorIds())) {
					ShirtParam shirtParam = new ShirtParam();
					shirtParam.setShirtId(shirt.getShirtId());
					shirtParam.setStatus(-1);
					shirtManagerDao.updateShirtParam(shirtParam);
				}
				if (StringUtils.isNotBlank(shirt.getColorIds())) {
					String[] colorIds = shirt.getColorIds().split(",");
					for (String colorId : colorIds) {
						ShirtParam shirtParam = new ShirtParam();
						shirtParam.setShirtId(shirt.getShirtId());
						shirtParam.setColorId(Long.valueOf(colorId));
						shirtParam.setPrice(shirt.getMinPrice());
						shirtParam.setStatus(0);
						shirtManagerDao.saveShirtParam(shirtParam);
					}
				}
			}
		}
		logger.info("editShirt=" + shirt.getShirtId());
	}
	
	public Map<String, Object> changeShirtStatus(Long shirtId, Integer status) {
		Map<String, Object> m = new HashMap<String, Object>();
		Integer weight = null;
		if (status == 1) {//status=1：显示, status=0：不显示
			Shirt wShirt = new Shirt();
			wShirt.setPageNum(1);
			wShirt.setPageSize(1);
			wShirt.setStatus(0);
			wShirt.setIsActive(1);
			wShirt.setOrderCont("s.weight desc");
			List<ShirtDTO> wShirtList = shirtManagerDao.getShirtList(wShirt);
			if (wShirtList.size() == 0) {
				weight = 0;
			} else {
				weight = wShirtList.get(0).getWeight();
				if (weight == 0) {
					weight = 1;
				}
				Shirt updateShirt = new Shirt();
				updateShirt.setId(shirtId);
				updateShirt.setWeight(weight);
				shirtManagerDao.updateShirt(updateShirt);
			}
		} else {
			weight = 0;
			Shirt updateShirt = new Shirt();
			updateShirt.setId(shirtId);
			updateShirt.setWeight(weight);
			shirtManagerDao.updateShirt(updateShirt);
		}
		m.put("weight", weight);
		m.put("flag", 0);
		return m;
	}
	
	public Map<String, Object> changeWeightScheduler(Integer status) {
		Map<String, Object> m = new HashMap<String, Object>();
		SystemParam systemParam = new SystemParam();
		systemParam.setSystemName(Constants.SYSTEM_NAME_MANAGER);
		systemParam.setName(Constants.WEIGHT_SWITCH);
		if (status == 1) {//status=1：开启, status=0：关闭
			systemParam.setValue("1");
		} else {
			systemParam.setValue("0");
		}
		systemParamDao.updateSystemParam(systemParam);
		m.put("flag", 0);
		return m;
	}

	public Map<String, Object> compressImg() {
		Map<String, Object> m = new HashMap<String, Object>();
		String path = Config.getProperty("SAVE_SHIRT_IMG_PATH");
		File file = new File(path);
		String[] files = file.list();
		for (String fileName : files) {
			ImageUtils.aliResizeImg(path+fileName,
					Config.getProperty("SAVE_SHIRT_COMPRESS_IMG_PATH")+fileName,
					Integer.valueOf(Config.getProperty("IMG_WIDTH")),
					Integer.valueOf(Config.getProperty("IMG_HEIGHT")));
		}
		m.put("flag", 0);
		return m;
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
		ImageUtils.aliResizeImg(Config.getProperty("SAVE_SHIRT_IMG_PATH")+newFileName,
				Config.getProperty("SAVE_SHIRT_COMPRESS_IMG_PATH")+newFileName,
				Integer.valueOf(Config.getProperty("IMG_WIDTH")),
				Integer.valueOf(Config.getProperty("IMG_HEIGHT")));
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
