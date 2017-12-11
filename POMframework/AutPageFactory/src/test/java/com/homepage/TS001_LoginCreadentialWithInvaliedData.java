package com.homepage;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.testBase.Testbase;
import com.uiActions.HomePage;

public class TS001_LoginCreadentialWithInvaliedData extends Testbase {
//	WebDriver driver;
	HomePage homepage;
	public static final Logger log=Logger.getLogger(TS001_LoginCreadentialWithInvaliedData.class.getName());
	@BeforeClass
	public void setup() throws Exception
	{
		init();
	
		
	}
	@Test
	public void verifyLoginWithInvalidCredentails()
	{
		log.info("================== starting verifying login with invalied creadential=============");
		homepage=new HomePage(driver);
		homepage.loginToApplication();
		homepage.verifyAfterLoginMsg();
		log.info("================== Finished verifying login with invalied creadential=============");
	}
    @AfterClass
         public void endTest()
         {
    	driver.close();
         }


}
