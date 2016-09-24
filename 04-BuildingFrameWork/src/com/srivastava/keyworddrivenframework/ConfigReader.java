package com.srivastava.keyworddrivenframework;

import java.util.ResourceBundle;

public class ConfigReader {
	private static ResourceBundle rb = ResourceBundle.getBundle("frameworkconfig");
	
	
	
	public static String readExcelPath(){
	return rb.getString("excelfilepath");	
	}
	
}
