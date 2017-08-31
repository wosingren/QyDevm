package com.wtzn.qy.qydevm.bean;

import java.util.Date;

/**
 * 入库单信息
 * 
 * @author baijw
 * 
 * @date 2017年8月12日 下午4:39:43
 */
public class ImportInfo implements java.io.Serializable {

	private String batchNo;// 入库批次号
	private String wareHouseNo;// 仓库编号
	private String manager;// 管理员
	private String consigno;// 交货人
	private String remark;// 备注
	private Date createTime;// 入库时间

	public ImportInfo() {
		super();
	}

	public ImportInfo(String batchNo, String wareHouseNo, String manager, String consigno, String remark,
			Date createTime) {
		super();
		this.batchNo = batchNo;
		this.wareHouseNo = wareHouseNo;
		this.manager = manager;
		this.consigno = consigno;
		this.remark = remark;
		this.createTime = createTime;
	}

	public String getWareHouseNo() {
		return wareHouseNo;
	}

	public void setWareHouseNo(String wareHouseNo) {
		this.wareHouseNo = wareHouseNo;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getConsigno() {
		return consigno;
	}

	public void setConsigno(String consigno) {
		this.consigno = consigno;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
