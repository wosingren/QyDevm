package com.wtzn.qy.qydevm.bean;

/**
 * 
 * @author baijw
 * 
 * @date 2017年4月23日 下午8:39:50
 */
public class User {

	private String userName;

	private String nickName;

	private String password;

	private String role;

	private String createDate;

	private String editPermition;

	private Integer leve;

	private String company;

	public User() {
	}

	public User(String userName, String nickName, String password, String role, String createDate, String editPermition,
			Integer leve, String company) {
		super();
		this.userName = userName;
		this.nickName = nickName;
		this.password = password;
		this.role = role;
		this.createDate = createDate;
		this.editPermition = editPermition;
		this.leve = leve;
		this.company = company;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getEditPermition() {
		return editPermition;
	}

	public void setEditPermition(String editPermition) {
		this.editPermition = editPermition;
	}

	public Integer getLeve() {
		return leve;
	}

	public void setLeve(Integer leve) {
		this.leve = leve;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

}
