package com.itee.tsd.entity;

import java.io.Serializable;

/**
 * 
 * @author Jia Qianpeng
 * 2015-12-06
 */

@SuppressWarnings("serial")
public class ShirtProperty implements Serializable {
	private Long id;
	private String name;
	private Integer order;
	private Integer status;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
}
