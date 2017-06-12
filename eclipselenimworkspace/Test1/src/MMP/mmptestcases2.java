
package MMP;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
//import org.eclipse.jetty.util.IO;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.beust.jcommander.Parameter;
//import com.gargoylesoftware.htmlunit.javascript.host.file.File;
//import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.IO;

	public class mmptestcases2 
	{
		
	WebDriver driver;
		
		//@Parameters({"url"})
		@BeforeTest
// Navigating into MMP Project website
		
	public void startbrowser() throws InterruptedException {
	System.setProperty("webdriver.chrome.driver","//Users//Anil//Downloads//chromedriver");
	driver = new ChromeDriver();
	driver.get("http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/");
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
	driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
	Thread.sleep(2000);
	}	
//    closing the brower
		
	@AfterTest(alwaysRun=true)
	public void closeBrowser() throws InterruptedException{

		Thread.sleep(2000);
		driver.quit(); 
	}
// passing the parameters for Login functionality
	
	@Parameters({"user","pass"})
	@Test(description = "loginfunctionality")
	public void loginfunctionality(String user,String pass)
	 { 
		    driver.findElement(By.linkText("Login")).click();
		    driver.findElement(By.id("username")).clear();
		    WebElement login = driver.findElement(By.id("username"));
		    login.sendKeys(user);
		    driver.findElement(By.id("password")).clear();
		    WebElement password =  driver.findElement(By.id("password"));
		    password.sendKeys(pass);
		    driver.findElement(By.name("submit")).click();			 
	 }
// verifying the symptoms are displayed 
	
	@Parameters({"symptom"})
	@Test(description = "verifysearchsymptoms", dependsOnMethods = "loginfunctionality")
	public void verifysearchsymptoms(String symptom) 
			{
			driver.findElement(By.linkText("Search Symptoms")).click();
			driver.findElement(By.id("search")).sendKeys(symptom);;
		    driver.findElement(By.name("submit")).click(); 
		    
			}
			
	
	
// Taking a screen shot after verifying the symptoms
	
	@Test(description = "TakesScreenshot", dependsOnMethods = "verifysearchsymptoms")	
	public void screenshot() throws IOException {
	
		
	driver.get("http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/portal/searchsymptoms.php");	
	TakesScreenshot tsh =  (TakesScreenshot) driver;

	File f = tsh.getScreenshotAs(OutputType.FILE);
	//System.out.println(f.getAbsolutePath());
	FileUtils.copyFile(f,new File("Users//Anil//Documents/screenshot.jpg"));
	
	}
		
	
	}
	
	
	
	
	

