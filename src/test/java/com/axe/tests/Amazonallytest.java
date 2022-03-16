package com.axe.tests;

import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.deque.axe.AXE;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Amazonallytest {
	
	WebDriver driver;
	
	private static final URL scriptUrl= Amazonallytest.class.getResource("/axe.min.js");
	
	
	@BeforeMethod
	public void setUp(){
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.amazon.com");
	}
	
	@Test
	public void AmazonAllyTest(){
		JSONObject responseJson= new AXE.Builder(driver, scriptUrl).analyze();
		JSONArray violations=responseJson.getJSONArray("violations");
		
		if(violations.length() ==0){
			System.out.println("No Violations in Application");
		}else{
			AXE.writeResults("AmazonAllyTest",responseJson );
			Assert.assertTrue(false, AXE.report(violations));
			
		}
		
	}
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}

}

