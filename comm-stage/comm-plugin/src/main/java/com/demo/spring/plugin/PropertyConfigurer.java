package com.demo.spring.plugin;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class PropertyConfigurer extends PropertyPlaceholderConfigurer{

    private Properties props;

    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props)
                            throws BeansException {
        super.processProperties(beanFactoryToProcess, props);
        this.props = props;
    }

    public String getProperty(String key){
        return this.props.getProperty(key);
    }
    
    public Map<String,String> filte(String key){
    	Map<String, String> map = new HashMap<>();
    	Iterator<Entry<Object, Object>> iterator = props.entrySet().iterator();
        while (iterator.hasNext()) {
        	Entry<Object, Object> entry = iterator.next();
        	if(entry.getKey().toString().matches(key)){
        		map.put(entry.getKey().toString(), entry.getValue().toString());
        	}
		}
        return map;
    }

    public String getProperty(String key, String defaultValue) {
        return this.props.getProperty(key, defaultValue);
    }

    public Object setProperty(String key, String value) {
        return this.props.setProperty(key, value);
    }
}
