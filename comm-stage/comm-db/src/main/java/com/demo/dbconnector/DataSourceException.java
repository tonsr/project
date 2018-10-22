package com.demo.dbconnector;

public class DataSourceException extends RuntimeException{

	public DataSourceException(String msg) {
		super(msg);
	}

	public DataSourceException(Throwable e) {
		super(e);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

}
