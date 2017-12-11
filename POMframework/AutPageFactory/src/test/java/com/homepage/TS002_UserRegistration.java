package com.homepage;



import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.testBase.Testbase;
import com.uiActions.HomePage;
import com.uiActions.Registration;

public class TS002_UserRegistration  extends Testbase{
	
	HomePage homepage;
	Registration registrationpage;
	public static final Logger log=Logger.getLogger(TS002_UserRegistration.class.getName());
   
	String firstName="anusha";
	String lastName="Reddy";
	String password="anusha123";
	String emailaddress="email"+System.currentTimeMillis()+"@gmail.com";
	String day="10";
	String month="July";
	String year="2007";
	
	@BeforeClass
	public void Setup()
	{
		init();
		homepage=new HomePage(driver);
		registrationpage=new Registration(driver);
	}
	
	@Test(priority=0,enabled=true,description ="Test Registration with valid data") 
	public void userRegistration()throws Exception{
		homepage.enterDataToAnCreateAccount(emailaddress);
		explictWait(driver.findElement(registrationpage.mrRadionButton),30);
		registrationpage.testUserRegistration(firstName,lastName,password,day,month,year);
		waitFor(3);
		boolean isSuccess=registrationpage.verifyRegistrationMsg();
		if(isSuccess)
		{
		Assert.assertTrue(true, "Registration test passed");
		}
		else
		{
			Assert.assertTrue(false, "Registration test failed");
		}
	}
	
	@Test(priority=1,enabled=true,description="Test Registration with invail data")
	public void testRegistrationWithInvaliedData() throws Throwable
	{
		homepage.logOut();
		homepage.enterDataToAnCreateAccount(emailaddress);
		waitFor(3);
		boolean isSuccess=registrationpage.verifyCreateAnAccountErrorMsg();
		if(isSuccess)
		{
			Assert.assertTrue(true, "Registration data is invalied test is pass");
		}
		else
		{
			Assert.assertTrue(false,"Registration data is invalied test is fail");
		}
	}
	
	
	
	@AfterClass
	public void closeBrowser()
	{
		driver.close();
	}

	
}
