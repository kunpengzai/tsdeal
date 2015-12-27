package com.itee.tsd.entity;

import java.io.Serializable;

/**
 * 
 * @author Jia Qianpeng
 * 2015-12-27
 */

@SuppressWarnings("serial")
public class SystemParam implements Serializable {
	private Long id;
	private String systemName;
	private String name;
	private String value;
	private Integer status;//-1:删除 0:正常
	private String comment;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSystemName() {
		return systemName;
	}
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
}
