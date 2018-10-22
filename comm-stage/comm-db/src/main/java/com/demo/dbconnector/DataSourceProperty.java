package com.demo.dbconnector;

import org.springframework.util.StringUtils;

public class DataSourceProperty {
	
	public DataSourceProperty(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	
	private String name;
	
	private String url;
	private String password;
	private String username;
	private String driver;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		if(StringUtils.isEmpty(url)){
			throw new DataSourceException("数据源配置错误！无法设置数据库url "+this);
		}
		this.url = url;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		if(StringUtils.isEmpty(password)){
			throw new DataSourceException("数据源配置错误！无法设置数据库password "+this);
		}
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		if(StringUtils.isEmpty(username)){
			throw new DataSourceException("数据源配置错误！无法设置数据库username "+this);
		}
		this.username = username;
	}
	
	@Override
	public String toString() {
		return "DataSourceProperty [name=" + name + ", url=" + url
				+ ", password=" + password + ", username=" + username
				+ ", driver=" + driver + "]";
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
}
