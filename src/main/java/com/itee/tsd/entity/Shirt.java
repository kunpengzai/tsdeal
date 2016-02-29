package com.itee.tsd.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 
 * @author Jia Qianpeng
 * 2015-12-06
 */

@SuppressWarnings("serial")
public class Shirt implements Serializable {
	private Long id;
	private String title;
	private String linkUrl;
	private String url;
	private Integer sourceId;
	private Integer brandId;
	private Float minPrice;
	private Float maxPrice;
	private Integer design;
	private Integer sleeve;
	private Integer isActive;
	private Integer imgType;
	private String shirtImg;
	private Integer status;
	private Integer weight;
	private Integer minWeight;
	private Integer maxWeight;
	private Timestamp createTime;
	private Timestamp updateTime;
	private Integer clickNum;

	private Integer colorId;
	private String colorIds;//Id以逗号分隔
	private Timestamp beginTime;
	private Timestamp endTime;
	private Integer pageNum;
	private Integer pageSize;
	private String orderCont;//排序信息，如：a.id desc
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	public Integer getMinWeight() {
		return minWeight;
	}
	public void setMinWeight(Integer minWeight) {
		this.minWeight = minWeight;
	}
	public Integer getMaxWeight() {
		return maxWeight;
	}
	public void setMaxWeight(Integer maxWeight) {
		this.maxWeight = maxWeight;
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

	public Integer getColorId() {
		return colorId;
	}

	public void setColorId(Integer colorId) {
		this.colorId = colorId;
	}

	public String getColorIds() {
		return colorIds;
	}
	public void setColorIds(String colorIds) {
		this.colorIds = colorIds;
	}
	public Timestamp getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Timestamp beginTime) {
		this.beginTime = beginTime;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public String getOrderCont() {
		return orderCont;
	}
	public void setOrderCont(String orderCont) {
		this.orderCont = orderCont;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
