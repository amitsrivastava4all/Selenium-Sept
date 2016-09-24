package com.srivastava.datadrivenframework;

import java.util.ResourceBundle;

public class ConfigReader {
	private static ResourceBundle rb = ResourceBundle.getBundle("com/srivastava/datadrivenframework/config");
	
	public static String readURL(){
	return rb.getString("url");	
	}
	
	public static String getDriverName(){
		return rb.getString("drivername");
	}
	
	public static String getDriverPath(){
		return rb.getString("driverpath");
	}
	
	public static String readExcelPath(){
	return rb.getString("excelfilepath");	
	}
	public static String readKeywordExcelPath(){
		return rb.getString("excelfilepath");	
		}

}
