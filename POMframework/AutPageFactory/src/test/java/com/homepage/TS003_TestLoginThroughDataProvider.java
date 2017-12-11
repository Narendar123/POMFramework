package com.homepage;


import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.testBase.Testbase;
import com.uiActions.HomePage;

public class TS003_TestLoginThroughDataProvider extends Testbase{
 
	HomePage homepage;
	
	@DataProvider(name="loginData")
	public Object[][] getTestData()
	{
		Object[][] testRecords=getData("login.xlsx","TestData");
		return testRecords;
	}
	
	@BeforeClass
	public void setUp()
	{ 
		init();
		homepage=new HomePage(driver);
	}
	
   @Test(dataProvider="loginData")
   public void verifyLoginWithDifferentRecords(String userName,String passsword,String runMode)
   {
	   homepage=new HomePage(driver);
	   if(runMode.equalsIgnoreCase("n")){
		   throw new SkipException("user marked this record no run");
	   }
	   try {
		   homepage.loginToDemoSite(userName, passsword);
		   getScreenshot("verifyLoginWithDifferentRecords");
		   homepage.logOut();
		   
	   }
	   catch(Exception e)
	   {
		   getScreenshot("verifyLoginWithDifferentRecords");
		   e.printStackTrace();
	   }
   }
   @AfterClass
   public void quit()
   {
	   driver.close();
   }
}