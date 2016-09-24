package com.srivastava.datadrivenframework;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class LoginTest {
	
	@DataProvider(name="excelData")
	public Object[][] excelDataProvider(){
		Object array [][]=null;
		
			try {
				array = ReadWriteExcel.readExcel();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return array;
	}
	
	
	@Test(dataProvider="excelData")
	public void testLogin(String userData, String pwdData) throws Exception{
		if(userData!=null && userData.trim().length()>0 )
		{
		if(pwdData!=null && pwdData.trim().length()>0){	
		
		/*System.setProperty("webdriver.chrome.driver", 
				"D:\\Selenium-WS\\Jars\\chromedriver_win32\\chromedriver.exe");*/
			System.setProperty(ConfigReader.getDriverName()
					, ConfigReader.getDriverPath());
		WebDriver driver =new ChromeDriver();
		driver.get(ConfigReader.readURL());
		WebElement userName = driver.findElement(By.name("userName"));
		userName.clear();
		//userName.sendKeys("amitsrivastava");
		userName.sendKeys(userData);
		Thread.sleep(1000);
		
		/*WebElement pwd = driver.findElement(By.name("password"));
		pwd.clear();
		pwd.sendKeys("amit123456");*/
		
		//driver.findElement(By.name("password")).sendKeys("amit123456");
		driver.findElement(By.name("password")).sendKeys(pwdData);
		
		
		Thread.sleep(1000);
		
		WebElement loginButton = driver.findElement(By.name("login"));
		loginButton.click();
		
		Thread.sleep(2000);
		
		
		
		driver.close();
		}
		}
		
	}

}
