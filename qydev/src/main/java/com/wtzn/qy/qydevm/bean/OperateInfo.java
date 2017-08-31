package com.wtzn.qy.qydevm.bean;

/**
 * 操作单信息(入库单,出库单,即入即出单,调配单)
 * 
 * @author baijw
 * 
 * @date 2017年8月12日 下午4:39:43
 */
public class OperateInfo implements java.io.Serializable{

	private String batchNo;// 入库批次号
	private String wareHouseNo;// 仓库编号
	private String manager;// 管理员
	private String consigno;// 交货人
	private String consignee;// 收货人
	private String remark;// 备注
	private String createTime;// 时间
	private String operate;// 操作(RK,CK,DP...)
	private String fundsStuation;// 资金回笼情况(出库和即入即出填写)
	private String disManager;// 目的管理员(出库和即入即出填写)

	public OperateInfo() {
		super();
	}

	public OperateInfo(String batchNo, String wareHouseNo, String manager, String consigno, String consignee,
			String remark, String createTime, String operate, String fundsStuation, String disManager) {
		super();
		this.batchNo = batchNo;
		this.wareHouseNo = wareHouseNo;
		this.manager = manager;
		this.consigno = consigno;
		this.consignee = consignee;
		this.remark = remark;
		this.createTime = createTime;
		this.operate = operate;
		this.fundsStuation = fundsStuation;
		this.disManager = disManager;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getWareHouseNo() {
		return wareHouseNo;
	}

	public void setWareHouseNo(String wareHouseNo) {
		this.wareHouseNo = wareHouseNo;
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

	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

	public String getFundsStuation() {
		return fundsStuation;
	}

	public void setFundsStuation(String fundsStuation) {
		this.fundsStuation = fundsStuation;
	}

	public String getDisManager() {
		return disManager;
	}

	public void setDisManager(String disManager) {
		this.disManager = disManager;
	}

}
