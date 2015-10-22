package com.gusi.platform.core.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

@MappedSuperclass
public class Root implements Serializable {

	protected User creater;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date createDate;
	protected User updater;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date updateDate;
	protected Integer state;
	protected String remark;
	protected String blank;

	public Map<String, Object> hashAttributes = new HashMap<String, Object>();

	public static class State {
		public static final Integer STATE_NORMAL = 0;
		public static final Integer STATE_DELETE = -1;
		public static final Integer STATE_TEMP = 1;
	}

	@ManyToOne
	@JoinColumn(name = "creater")
	public User getCreater() {
		return creater;
	}

	public void setCreater(User creater) {
		this.creater = creater;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@ManyToOne
	@JoinColumn(name = "updater")
	public User getUpdater() {
		return updater;
	}

	public void setUpdater(User updater) {
		this.updater = updater;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getBlank() {
		return blank;
	}

	public void setBlank(String blank) {
		this.blank = blank;
	}

	@Transient
	public Map<String, Object> getHashAttributes() {
		return hashAttributes;
	}

	public void setHashAttributes(Map<String, Object> hashAttributes) {
		this.hashAttributes = hashAttributes;
	}

	@Transient
	public Object getAttribute(String attributeName) {
		return hashAttributes.get(attributeName);
	}

	public void setAttribute(String attributeName, Object value) {
		hashAttributes.put(attributeName, value);
	}

}
