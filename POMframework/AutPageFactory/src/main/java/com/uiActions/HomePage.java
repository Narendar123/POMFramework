package com.uiActions;


import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.testBase.Testbase;

public class HomePage extends Testbase {
	
public WebDriver driver;
public static final Logger log=Logger.getLogger(HomePage.class.getName());

  By signIn = By.xpath("//*[@id='header']/div[2]/div/div/nav/div[1]/a");
  By createAccEmailAddr = By.xpath("//*[@id='email_create']");
  By allreadyRegEmailAddr = By.xpath("//*[@id='email']");
  By allReadyRegPass = By.xpath("//*[@id='passwd']");
  By submitbutton = By.xpath("//*[@id='SubmitLogin']");
  By createAnAccount = By.xpath("//*[@id='SubmitCreate']");
  By signOut = By.xpath("//*[@id='header']/div[2]/div/div/nav/div[2]/a");

   public  HomePage(WebDriver driver)
   {
	this.driver=driver;
   }
   
   /*   This method will check signIn link*/

   public void clckonSignIn()
   {
	   try{
	  log.info("Clicking on signIn an account");
	   
	   driver.findElement(signIn).click();
	   }
	   catch(Exception e)
	   {
		   e.printStackTrace();
	   }
   }
   
   /**
	 * This method will enter email address to create an account text box.
	 * In Login Page
	 * @param emailAddress
	 */
   
   public void enterEmailAddressToCreateAnAccount(String emailaddress)
   {
	   log.info("Clicking on enterEmailAddressToCreateAnAccount  an account");
	   driver.findElement(createAccEmailAddr).sendKeys(emailaddress);
   }
   
   public void enterAlreadyRegisterEmailAddress(String emailaddress)
   {
	   log.info("Clicking on enter Already Register Email Address an account"); 
	   driver.findElement(allreadyRegEmailAddr).sendKeys(emailaddress);
   }
   
   public void enterAlreadyRegisterPassword(String password)
   {
	   log.info("Clicking on enter Already Register Password an account"); 
	   driver.findElement(allReadyRegPass).sendKeys(password);
   }
   
   public void clickOnSignInToAccount()
   {
	   log.info("Clicking on SignIn ToAccount an account"); 
	   driver.findElement(submitbutton).click();
   } 
   
   public void clickOnCreateOnAccount()
   {
	   log.info("Clicking on Create on Account"); 
	   driver.findElement(createAnAccount).click();
   }
   
   public void logOut()
   {
	   try
	   {
		  log.info("verify SignOut Label displayed or not "); 
		   boolean  isdisplayed=driver.findElement(signOut).isDisplayed();
		   if(isdisplayed)
		   {
			   log.info("click on signout button");   
			   driver.findElement(signOut).click();
		   }
	   }
	   catch(Exception e)
	   {
		   Assert.assertEquals(false,"signout button is not dispalyed");
	   }
   }
   
   public boolean verifyAfterLoginMsg()
   {
	   log.info("verify after SignIn Error msg displayed or not");
	   String text=driver.findElement(By.xpath(".//*[@id='center_column']/div[1]/ol/li")).getText();
	   if(text.contains("Authentication failed."))
	   {
		   return true;
	   }
	   else
	   {
		   return false;
	   }
   }
   
   /*
   This method is login to Application
   */
   
   public void loginToApplication()
   {
	   clckonSignIn();
	   enterAlreadyRegisterEmailAddress("test902@gmail.com");
	   enterAlreadyRegisterPassword("password123");
	   clickOnSignInToAccount();
   }
   
  /* 
   This method is create new account registration
   */
   
   public void enterDataToAnCreateAccount(String emailaddress)
   {
	   clckonSignIn();
	   enterEmailAddressToCreateAnAccount(emailaddress);
	   clickOnCreateOnAccount();
   }
   
   public void loginToDemoSite(String userName,String password)
   {
	   clckonSignIn();
	   enterAlreadyRegisterEmailAddress(userName);
	   enterAlreadyRegisterPassword(password);
	   clickOnSignInToAccount();
   }
}



