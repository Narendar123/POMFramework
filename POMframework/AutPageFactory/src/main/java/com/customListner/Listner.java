package com.customListner;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.testBase.Testbase;

public class Listner extends Testbase implements ITestListener {

	public void onTestStart(ITestResult result) {
    Reporter.log("Test Started::" +result.getName());		
	}

	public void onTestSuccess(ITestResult result) {
		 if(result.isSuccess()){
	    	   Calendar calendar=Calendar.getInstance();
	    	   SimpleDateFormat formeter=new SimpleDateFormat("dd_mm_YYYY_hh_ss");
	    	   String mathodname=result.getName();
	    	   File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    	   
	    	   try{
	    	String reportdir=new File(System.getProperty("user.dir")).getAbsolutePath() + "//src//main//java//com//screenShorts//";
	    	File destFile=new File((String)reportdir+"/success_screenshot" +mathodname+ "_" +formeter.format(calendar.getTime()+".png"));
	    	FileUtils.copyFile(src, destFile);
			
	    	Reporter.log("<a href='" + destFile.getAbsolutePath() + " '><img src='" + destFile.getAbsolutePath() + " 'height='100' width='100/></a> ");
	    		
	       }catch(Exception e)
	    	   {
	    	   e.printStackTrace();
	    	   }
	       } 
	}

	public void onTestFailure(ITestResult result) {
       if(!result.isSuccess()){
    	   Calendar calendar=Calendar.getInstance();
    	   SimpleDateFormat formeter=new SimpleDateFormat("dd_mm_YYYY_hh_ss");
    	   String mathodname=result.getName();
    	   File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    	   
    	   try{
    	String reportdir=new File(System.getProperty("user.dir")).getAbsolutePath() + "//src//main//java//com//screenShorts//";
    	File destFile=new File((String)reportdir+"/failure_screenshot" +mathodname+ "_" +formeter.format(calendar.getTime()+".png"));
    	FileUtils.copyFile(src, destFile);
		
    	Reporter.log("<a href='" + destFile.getAbsolutePath() + " '><img src='" + destFile.getAbsolutePath() + " 'height='100' width='100/></a> ");
    		
       }catch(Exception e)
    	   {
    	   e.printStackTrace();
    	   }
       } 
       
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
     Reporter.log("Test Finished::" +context.getName());	
	}

}
