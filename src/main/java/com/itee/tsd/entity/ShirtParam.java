package com.itee.tsd.entity;

import java.io.Serializable;

/**
 * 
 * @author Jia Qianpeng
 * 2015-12-06
 */

@SuppressWarnings("serial")
public class ShirtParam implements Serializable {
	private Long id;
	private Long shirtId;
	private Long colorId;
	private Float price;
	private Integer status;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getShirtId() {
		return shirtId;
	}
	public void setShirtId(Long shirtId) {
		this.shirtId = shirtId;
	}
	public Long getColorId() {
		return colorId;
	}
	public void setColorId(Long colorId) {
		this.colorId = colorId;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
}
