package com.wyps.entity;

import java.util.List;

public class LoginConf {
	private String loginUrl;
	private Path username;
	private String usernameval="";
	private Path password;
	private String passwordval="";
	private boolean needLogin=false;
	private List<String> loginSign;
	public String getLoginUrl() {
		return loginUrl;
	}
	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}
	public Path getUsername() {
		return username;
	}
	public void setUsername(Path username) {
		this.username = username;
	}
	public String getUsernameval() {
		return usernameval;
	}
	public void setUsernameval(String usernameval) {
		this.usernameval = usernameval;
	}
	public Path getPassword() {
		return password;
	}
	public void setPassword(Path password) {
		this.password = password;
	}
	public String getPasswordval() {
		return passwordval;
	}
	public void setPasswordval(String passwordval) {
		this.passwordval = passwordval;
	}
	public boolean isNeedLogin() {
		return needLogin;
	}
	public void setNeedLogin(boolean needLogin) {
		this.needLogin = needLogin;
	}
	public List<String> getLoginSign() {
		return loginSign;
	}
	public void setLoginSign(List<String> loginSign) {
		this.loginSign = loginSign;
	}
	
}
