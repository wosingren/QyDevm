package com.wtzn.qy.qydevm.bean;

/**
 * 设备的入库信息
 * 
 * @author baijw
 * 
 * @date 2017年8月12日 下午4:37:49
 */
public class DeviceOperateInfo implements java.io.Serializable{

	private String batchNo;
	private String IMEI;
	private String deviceNo;
	private String price;

	public DeviceOperateInfo() {
		super();
	}

	public DeviceOperateInfo(String batchNo, String iMEI, String deviceNo, String price) {
		super();
		this.batchNo = batchNo;
		IMEI = iMEI;
		this.deviceNo = deviceNo;
		this.price = price;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getIMEI() {
		return IMEI;
	}

	public void setIMEI(String iMEI) {
		IMEI = iMEI;
	}

	public String getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

}
