package com.qspiders.frameworkengine;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TestExecutor {

	//hi

	public void executeTest(String scenarioNm) {
		String scenarioName=scenarioNm;
		
		ExcelLibrary lib = new ExcelLibrary();
		WebDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		driver.get("http://demo.actitime.com");
		
		int numOfSteps = lib.getRowCount(scenarioNm);
		
			for(int i=1;i<=numOfSteps;i++){
				String action = lib.getExcelData(scenarioNm, i, 1);
				String idMethod = lib.getExcelData(scenarioNm, i, 2);
				String locatorVal = lib.getExcelData(scenarioNm, i, 3);
				String data = lib.getExcelData(scenarioNm, i, 4);
				System.out.println(action+"--"+idMethod+"--"+locatorVal+"--"+data);
				WebElement element=null;
				
			//GET ELEMENT//
			if(idMethod.equals("name")){
				element = driver.findElement(By.name(locatorVal));
			}
			else if(idMethod.equals("id")){
				element = driver.findElement(By.id(locatorVal));
			}
			else if(idMethod.equals("xpath")){
				element = driver.findElement(By.xpath(locatorVal));
			}
			else if(idMethod.equals("css")){
				element = driver.findElement(By.cssSelector(locatorVal));				
			}
			else if(idMethod.equals("linkText")){
				element = driver.findElement(By.linkText(locatorVal));
			}
			
			//perform actions			
			if(action.equals("sendkeys")){
				element.sendKeys(data);
			}
			else if(action.equals("click")){
				element.click();
			}
			else if(action.equals("select")){
				Select dd = new Select(element);
				dd.selectByVisibleText(data);
			}
			else if(action.equals("alert")){
				Alert alrt = driver.switchTo().alert();
				if(data.equals("ok")){
					alrt.accept();
				}
				else if(data.equals("cancel")){
					alrt.dismiss();
				}
			}
		}	
		driver.quit();
	}

}







