package com.demo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.demo.api.DemoService;

@Service(timeout=3000,protocol="dubbo")
public class DemoServiceImpl implements DemoService{

	@Override
	public String getDemo() {
		// TODO Auto-generated method stub
		return "Hello World!";
	}

}
