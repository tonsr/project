package com.demo.dbconnector;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.util.Assert;

import com.demo.spring.plugin.PropertyConfigurer;

public class DataSourceFactoryBean extends ApplicationObjectSupport implements InitializingBean{

	private String url;
	private String username;
	private String password;
	private String driverClass;
	private String dataSourceClass;
	private Map<String,String> props;
	
	
	public void setProps(Map<String, String> props) {
		this.props = props;
	}

	public void setDataSourceClass(String dataSourceClass) {
		this.dataSourceClass = dataSourceClass;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setDriverClass(String driverClass) {
		this.driverClass = driverClass;
	}
	
	private static Map<String, DataSourceProperty> dataSourceProperties = null;

	/**
	 * 获取通配数据源配置信息
	 * */
	private DataSourceProperty getDataSourceProperties(String name){
		Assert.hasText(name, "name 不能为空");
		PropertyConfigurer configurer = this.getApplicationContext().getBean(PropertyConfigurer.class);
		if(name.contains("*")){
			
		}
		if(dataSourceProperties != null && dataSourceProperties.containsKey(name)){
			return dataSourceProperties.get(name);
		}
		DataSourceProperty dsprop = null;
		synchronized (DataSourceFactoryBean.class) {
			if(dataSourceProperties == null){
				dataSourceProperties = new ConcurrentHashMap<>();
			}
			dsprop = new DataSourceProperty(name);
			if(this.driverClass.contains("*")){
				dsprop.setDriver(configurer.getProperty(this.driverClass.replace("*", name)));
			}else{
				dsprop.setDriver(configurer.getProperty(this.driverClass));
			}
			if(this.username.contains("*")){
				dsprop.setUsername(configurer.getProperty(this.username.replace("*", name)));
			}else{
				dsprop.setUsername(configurer.getProperty(this.username));
			}
			if(this.password.contains("*")){
				dsprop.setPassword(configurer.getProperty(this.password.replace("*", name)));
			}else{
				dsprop.setPassword(configurer.getProperty(this.password));
			}
	
			if(this.url.contains("*")){
				dsprop.setUrl(configurer.getProperty(this.url.replace("*", name)));
			}else{
				dsprop.setUrl(configurer.getProperty(this.url));
			}
			dataSourceProperties.put(name, dsprop);
			return dsprop;
		}
	}
	
	private static Map<String, DataSource> dataSources = new ConcurrentHashMap<String, DataSource>();
	
	public DataSource getDataSource(String name){
		DataSourceProperty properties = getDataSourceProperties(name);

		if(dataSources != null && dataSources.containsKey(name)){
			return dataSources.get(name);
		}
		
		Object dataSource;
		try {
			dataSource = Class.forName(dataSourceClass).newInstance();
			if(dataSource instanceof DataSource){
				Class<? extends Object> clazz = dataSource.getClass();
				BeanInfo info = Introspector.getBeanInfo(clazz);
				PropertyDescriptor[] pds = info.getPropertyDescriptors();
				for(PropertyDescriptor pd : pds){
					if(pd.getName().equals("url")){
						pd.getWriteMethod().invoke(dataSource, properties.getUrl());
					}else if(pd.getName().equals("driverClass")){
						pd.getWriteMethod().invoke(dataSource, properties.getDriver());
					}else if(pd.getName().equals("username")){
						pd.getWriteMethod().invoke(dataSource, properties.getUsername());
					}else if(pd.getName().equals("password")){
						pd.getWriteMethod().invoke(dataSource, properties.getPassword());
					}else if(props.containsKey(pd.getName())){
						Method method = pd.getWriteMethod();
						Parameter[] parameters = method.getParameters();
						Object val = props.get(pd.getName());
						System.out.println(parameters[0].getType());
						if(parameters[0].getType() == int.class || parameters[0].getType() == Integer.class)
						{
							val = Integer.valueOf(val.toString());
						}
						method.invoke(dataSource, val);
					}
				}
				return (DataSource)dataSource;
			}
		} catch (Throwable e) {
			throw new DataSourceException(e);
		}
		throw new DataSourceException("不存在這樣的數據源！");
	}
	@Override
	public void afterPropertiesSet() throws Exception {
	}
}
