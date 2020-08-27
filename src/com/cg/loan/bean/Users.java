package com.cg.loan.bean;

import java.io.Serializable;

public class Users implements Serializable {
 
	private static final long serialVersionUID = 1L;

	private String loginId;
	private String password;
	private String role;
	
	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Users(String loginId, String password, String role) {
		super();
		this.loginId = loginId;
		this.password = password;
		this.role = role;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
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
	@Override
	public String toString() {
		return "Users [loginId=" + loginId + ", password=" + password + ", role=" + role + "]";
	}
	
	
	
}
