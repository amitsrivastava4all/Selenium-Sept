package com.srivastava.keyworddrivenframework;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.srivastava.datadrivenframework.ConfigReader;

public class CommandExecutor {
	
	public static WebElement getWebElement(CommandDTO commandDTO, WebDriver driver){
		WebElement element = null;
		String targetValue = commandDTO.getTarget();
		String targetArray[]=targetValue.split("=");
		if(targetArray[0].equalsIgnoreCase("id")){
			element = driver.findElement(By.id(targetArray[1]));
		}
		else
		if(targetArray[0].equalsIgnoreCase("name")){
			element = driver.findElement(By.name(targetArray[1]));	
		}
		else
		if(targetArray[0].equalsIgnoreCase("xpath")){
			element = driver.findElement(By.xpath(targetArray[1]));		
		}
		else
		if(targetArray[0].equalsIgnoreCase("class")){
			element = driver.findElement(By.className(targetArray[1]));
		}
		return element;
	}
	
	@Test
	public static void executeCommand() throws Exception{
		WebDriver driver = null;
		WebElement element = null;
		
		List<CommandDTO> commandList=ExcelReader.readExcel();
		System.out.println("**********************************");
		System.out.println(commandList);
		System.out.println("***********************************");
		for(int i = 0; i<commandList.size(); i++){
			CommandDTO commandDTO =  commandList.get(i);
			if(commandDTO.getCommand().trim().equalsIgnoreCase("browser")){
				if(commandDTO.getTarget().equalsIgnoreCase("chrome")){
					System.setProperty(ConfigReader.getDriverName(), ConfigReader.getDriverPath());
					driver =new ChromeDriver();
				}
				else
					if(commandDTO.getTarget().equalsIgnoreCase("firefox")){
					 driver = new FirefoxDriver();	
					}
					
			}
			else
			if(commandDTO.getCommand().equalsIgnoreCase("open")){
				driver.get(commandDTO.getTarget());
			}
			else
			if(commandDTO.getCommand().equalsIgnoreCase("type")){
				element = getWebElement(commandDTO, driver);
				if(commandDTO.getValue()!=null && commandDTO.getValue().trim().length()>0){
					element.sendKeys(commandDTO.getValue());
				}
				
			}
			else
			if(commandDTO.getCommand().equalsIgnoreCase("clickandwait")){
				element = getWebElement(commandDTO, driver);
				element.click();
			}
			else
			if(commandDTO.getCommand().equalsIgnoreCase("assertTitle")){
				String title = driver.getTitle();
				String expectedResult = commandDTO.getTarget();
				Assert.assertTrue(title.contains(expectedResult));
			}
			
		}
		if(driver!=null){
		driver.close();
		}
	}

}
