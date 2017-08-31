package com.wtzn.qy.qydevm.bean;

import java.util.Date;

/**
 * 设备类型po
 * 
 * @author baijw
 * 
 * @date 2017年7月31日 上午11:38:08
 */
public class Device implements java.io.Serializable {

	private String devNo;
	private String devName;
	private String supplier;
	private String model;
	private Date createTime;

	public Device() {
		super();
	}

	public Device(String devNo, String devName, String supplier, String model, Date createTime) {
		super();
		this.devNo = devNo;
		this.devName = devName;
		this.supplier = supplier;
		this.model = model;
		this.createTime = createTime;
	}

	public String getDevNo() {
		return devNo;
	}

	public void setDevNo(String devNo) {
		this.devNo = devNo;
	}

	public String getDevName() {
		return devName;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
