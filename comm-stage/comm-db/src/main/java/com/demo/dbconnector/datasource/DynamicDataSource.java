package com.demo.dbconnector.datasource;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.demo.dbconnector.DataSourceFactoryBean;

public class DynamicDataSource extends AbstractRoutingDataSource{

	private String dataSourceNames;
	
	private DataSourceFactoryBean dataSourceFactoryBean;
	
	private String defaultDataSource;
	
	public String getDataSourceNames() {
		return dataSourceNames;
	}

	public void setDataSourceNames(String dataSourceNames) {
		this.dataSourceNames = dataSourceNames;
	}
	
	public DataSourceFactoryBean getDataSourceFactoryBean() {
		return dataSourceFactoryBean;
	}

	public void setDataSourceFactoryBean(DataSourceFactoryBean dataSourceFactoryBean) {
		this.dataSourceFactoryBean = dataSourceFactoryBean;
	}

	public String getDefaultDataSource() {
		return defaultDataSource;
	}

	public void setDefaultDataSource(String defaultDataSource) {
		this.defaultDataSource = defaultDataSource;
	}

public static class HandleDataSource
{
    public static final ThreadLocal<String> holder = new ThreadLocal<String>();

    /**
     * 绑定当前线程数据源
     * 
     * @param key
     */
    public static void putDataSource(String datasource)
    {
        holder.set(datasource);
    }

    /**
     * 获取当前线程的数据源
     * 
     * @return
     */
    public static String getDataSource()
    {
        return holder.get();
    }
}
    private Map<Object,Object> targetSouce = new HashMap<>();

	public DynamicDataSource(){
		this.setTargetDataSources(targetSouce);
	}
	
	@Override
	protected DataSource determineTargetDataSource() {
		Object lookupKey = determineCurrentLookupKey();
		DataSource dataSource = (DataSource) targetSouce.get(lookupKey);
		if (dataSource == null) {
			throw new IllegalStateException("Cannot determine target DataSource for lookup key [" + lookupKey + "]");
		}
		return dataSource;
	}
	
	@Override
	protected Object determineCurrentLookupKey() {
		String name = HandleDataSource.getDataSource();
		if(name == null){
			name = defaultDataSource;
		}
		targetSouce.put(name, dataSourceFactoryBean.getDataSource(name));
		return name;
	}

}