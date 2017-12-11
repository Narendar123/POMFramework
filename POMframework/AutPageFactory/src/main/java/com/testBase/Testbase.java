package com.testBase;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.customListner.Listner;
import com.excelReader.Excel_Reader;


public class Testbase {
	
	public static final Logger log=Logger.getLogger(Testbase.class.getName());
	public static WebDriver driver;
	String url="http://automationpractice.com/index.php";
	String browser="chrome";
	Excel_Reader excel;
	
	public void init(){
		selectBrowser(browser);
		getUrl(url);
		
		String log4jconfigpath="log4j.properties";
		PropertyConfigurator.configure(log4jconfigpath);
	}
	
	public void selectBrowser(String browser){
		if(browser.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + "/drivers/chromedriver.exe");
			driver=new ChromeDriver();
		}
		else
			if(browser.equalsIgnoreCase("firefox"))
			{
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/driver/geckodriver.exe");
				driver=new FirefoxDriver();
			}
	}
	
	public void getUrl(String url){
		driver.get(url);
		log.info("url navigate to"+url);
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
	}
	
	public void explictWait(WebElement element,int timeToWaitSec)
	{
       WebDriverWait wait=new WebDriverWait(driver, timeToWaitSec);
       wait.until(ExpectedConditions.visibilityOf(element));
     }
	
	public void waitFor(int sec) throws Exception
	{
		Thread.sleep(sec*1000);
	}
	
	public Object[][] getData(String excelName,String sheetName)
	{
		String path=System.getProperty("user.dir")+"\\src\\main\\java\\com\\testData\\"+excelName;
		excel=new Excel_Reader(path);
		Object[][] data = excel.getDataFromSheet(sheetName, excelName);
		return data;
	}
	
	public void getScreenshot(String name)
	{
		Calendar calendar=Calendar.getInstance();
		SimpleDateFormat formate=new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		File srcfile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try{
			String reportDircetory=new File(System.getProperty("user.dir")).getAbsolutePath()+"\\src\\main\\java\\com\\screenShorts\\";
			File destFile = new File((String) reportDircetory + name + "_" + formate.format(calendar.getTime()) + ".png");
			FileUtils.copyFile(srcfile, destFile);
			// This will help us to link the screen shot in testNG report
			Reporter.log("<a href='" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath() + "' height='100' width='100'/> </a>");
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}


