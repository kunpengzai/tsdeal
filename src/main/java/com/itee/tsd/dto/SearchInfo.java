package com.itee.tsd.dto;

import java.io.Serializable;

/**
 * 
 * @author Jia Qianpeng
 * 2015-12-06
 */

@SuppressWarnings("serial")
public class SearchInfo implements Serializable {
	private Integer brandId;
	private Integer sourceId;
	private Integer isActive;//0:有效 1:失效
	private String beginDate;
	private String endDate;
	
	private String colorIds;//colorId以逗号分隔
	private Integer design;//0:纯色 1:有图案
	private Integer sleeve;//0:长袖 1:短袖
	private Float minPrice;
	private Float maxPrice;
	
	public Integer getBrandId() {
		return brandId;
	}
	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}
	public Integer getSourceId() {
		return sourceId;
	}
	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}
	public Integer getIsActive() {
		return isActive;
	}
	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getColorIds() {
		return colorIds;
	}
	public void setColorIds(String colorIds) {
		this.colorIds = colorIds;
	}
	public Integer getDesign() {
		return design;
	}
	public void setDesign(Integer design) {
		this.design = design;
	}
	public Integer getSleeve() {
		return sleeve;
	}
	public void setSleeve(Integer sleeve) {
		this.sleeve = sleeve;
	}
	public Float getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(Float minPrice) {
		this.minPrice = minPrice;
	}
	public Float getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(Float maxPrice) {
		this.maxPrice = maxPrice;
	}
}
