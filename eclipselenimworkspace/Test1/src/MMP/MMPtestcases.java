package MMP;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
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


public class MMPtestcases {
	
WebDriver driver;
	
	@Parameters({"url"})
	@BeforeTest

public void startbrowser(String url) throws InterruptedException {
System.setProperty("webdriver.firefox.driver","//Users//Anil//Desktop//geckodriver" );
driver = new FirefoxDriver();
driver.get(url);
driver.manage().window().maximize();
driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
Thread.sleep(2000);
}	
	
@AfterTest(alwaysRun=true)
public void closeBrowser() throws InterruptedException{

	Thread.sleep(2000);
	driver.quit(); 
}
@Parameters({"user","pass"})
@Test(description = "loginfunctionality")
public void loginfunctionality(String user,String pass)
 { 
	    driver.findElement(By.linkText("Login")).click();
	    driver.findElement(By.id("username")).clear();
	    WebElement login = driver.findElement(By.id("username"));
	    login.sendKeys("user");
	    driver.findElement(By.id("password")).clear();
	    WebElement password =  driver.findElement(By.id("password"));
	    password.sendKeys("pass");
	    driver.findElement(By.name("submit")).click();			 
 }
@Test(description = "searchsymptoms tab",dependsOnMethods = "loginfunctionality")	
public void  Searchsymptoms() 
{
	driver.findElement(By.linkText("searchsymptoms.php")).click();
	//WebDriverWait wait = new WebDriverWait(driver,60);
	//wait.until(ExpectedConditions.visibilityOfElementLocated
		WebElement w = driver.findElement(By.linkText("What Symptoms are you experiencing?"));
		System.out.println(w.getAttribute("href"));
}
@Parameters({"symptoms"})
@Test(description = "verifysearchsymptoms", dependsOnMethods = "loginfunctionality")
		public void verifysearchsymptoms() 

		{
		driver.findElement(By.id("search")).clear();
	    driver.findElement(By.id("search")).sendKeys("symptoms");
	    driver.findElement(By.name("submit")).click();
	
		}
	
	
	
}
