package com.wtzn.qy.qydevm.bean;

/**
 * 
 * @author baijw
 * 
 * @date 2017年5月2日 下午3:34:48
 */
public class LoginCache {

	private String userName;
	private String token;
	private Integer wrongTimes;
	private String loginTime;
	private String editPermition;
	private String leve;

	public LoginCache() {
		super();
		this.wrongTimes = 0;
		this.loginTime = "2000-10-10 10:10:10";
	}

	public LoginCache(String userName, String token, Integer wrongTimes, String loginTime, String editPermition,
			String leve) {
		super();
		this.userName = userName;
		this.token = token;
		this.wrongTimes = wrongTimes;
		this.loginTime = loginTime;
		this.editPermition = editPermition;
		this.leve = leve;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Integer getWrongTimes() {
		return wrongTimes;
	}

	public void setWrongTimes(Integer wrongTimes) {
		this.wrongTimes = wrongTimes;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public String getEditPermition() {
		return editPermition;
	}

	public void setEditPermition(String editPermition) {
		this.editPermition = editPermition;
	}

	public String getLeve() {
		return leve;
	}

	public void setLeve(String leve) {
		this.leve = leve;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoginCache other = (LoginCache) obj;
		if (token == null) {
			if (other.token != null)
				return false;
		} else if (!token.equals(other.token))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		if (wrongTimes == null) {
			if (other.wrongTimes != null)
				return false;
		} else if (!wrongTimes.equals(other.wrongTimes))
			return false;
		if (editPermition == null) {
			if (other.editPermition != null)
				return false;
		} else if (!editPermition.equals(other.editPermition))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LoginCache [userName=" + userName + ", token=" + token + ", wrongTimes=" + wrongTimes + ", loginTime="
				+ loginTime + ", editPermition=" + editPermition + "]";
	}

}
