package com.itee.tsd.dto;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 
 * @author Jia Qianpeng
 * 2015-12-06
 */

@SuppressWarnings("serial")
public class ShirtDTO implements Serializable {
	private Long shirtId;
	private String title;
	private String linkUrl;
	private Integer sourceId;
	private Integer brandId;
	private String sourceName;
	private String brandName;
	private Float minPrice;
	private Float maxPrice;
	private Integer design;
	private Integer sleeve;
	private Integer isActive;
	private Integer imgType;
	private String shirtImg;
	private Integer status;
	private Timestamp createTime;
	private Timestamp updateTime;
	private Integer clickNum;
	
	private String colorIds;//Id以逗号分隔
	private String colorNames;//Name以/分隔
	
	public Long getShirtId() {
		return shirtId;
	}
	public void setShirtId(Long shirtId) {
		this.shirtId = shirtId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	public Integer getSourceId() {
		return sourceId;
	}
	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}
	public Integer getBrandId() {
		return brandId;
	}
	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}
	public String getSourceName() {
		return sourceName;
	}
	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
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
	public Integer getIsActive() {
		return isActive;
	}
	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}
	public Integer getImgType() {
		return imgType;
	}
	public void setImgType(Integer imgType) {
		this.imgType = imgType;
	}
	public String getShirtImg() {
		return shirtImg;
	}
	public void setShirtImg(String shirtImg) {
		this.shirtImg = shirtImg;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getClickNum() {
		return clickNum;
	}
	public void setClickNum(Integer clickNum) {
		this.clickNum = clickNum;
	}
	public String getColorIds() {
		return colorIds;
	}
	public void setColorIds(String colorIds) {
		this.colorIds = colorIds;
	}
	public String getColorNames() {
		return colorNames;
	}
	public void setColorNames(String colorNames) {
		this.colorNames = colorNames;
	}
}
