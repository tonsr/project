package com.demo;

import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.demo.init.InitEnvProperties;

public class Application extends ApplicationObjectSupport{

	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = null;
		try{
			InitEnvProperties.init("service");
			ctx = new ClassPathXmlApplicationContext(new String[]{"service/spring/applicationContext.xml"});
			ctx.start();
			System.in.read();
		} catch (Throwable e){
			e.printStackTrace();
		} finally {
			if(ctx != null)
				ctx.close();
		}
	}

}
