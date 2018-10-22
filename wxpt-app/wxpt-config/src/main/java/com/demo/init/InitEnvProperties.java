package com.demo.init;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.PropertyConfigurator;

public class InitEnvProperties  {

	private static Set<String> ENV_SET = new HashSet<>();
	static{
		ENV_SET.add("alpha");
		ENV_SET.add("beta");
		ENV_SET.add("gamma");
		ENV_SET.add("product");
	}
	public static void init(String type) throws IOException {
		String envStr = System.getenv("env");
		if(!ENV_SET.contains(envStr))
			throw new RuntimeException(String.format("env:%s set error! enum:%s", envStr, ENV_SET));
		System.setProperty("env", envStr);
		URL url = InitEnvProperties.class.getResource("/"+type+"/log4j.properties");
		PropertyConfigurator.configure(url.getFile());
	}
	
}
