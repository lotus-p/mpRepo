package MMP;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.beust.jcommander.Parameter;

public class VerifyProfileTab {
	
	WebDriver driver;

@BeforeTest

public void Openbrowser() throws InterruptedException
{
	WebDriver driver;
    //driver = new ChromeDriver();
	System.setProperty("webdriver.chrome.driver","//Users//Anil//Downloads//chromedriver");
	driver = new ChromeDriver();
	driver.get("http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/portal/index.php");
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
	driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
	Thread.sleep(4000);
		
}

@AfterTest(alwaysRun=true)
public void closeBrowser() throws InterruptedException{

	Thread.sleep(2000);
	driver.quit(); 
}



@Test

public void profiletab() throws Exception
{

	driver.findElement(By.xpath("//li[3]/a/span")).click();
    driver.findElement(By.linkText("profile.php")).click();
    driver.findElement(By.cssSelector("a > button")).click();
    driver.findElement(By.xpath("//input[@value='Past Appointments']")).click();
    driver.findElement(By.linkText("Logout")).click();
	    
	    
}    
	 
}


























	/*mmptestcases2 mmp = new mmptestcases2();
	mmp.loginfunctionality(user, pass);*/
	
	    /*driver.findElement(By.className("button button-alt")).click();
	    driver.findElement(By.id("username")).clear();
	    WebElement login = driver.findElement(By.id("username"));
	    login.sendKeys(user1);
	    driver.findElement(By.id("password")).clear();
	    WebElement password =  driver.findElement(By.id("password"));
	    password.sendKeys(pass1);
	    driver.findElement(By.name("submit")).click();	*/		 


/*@Parameters({"tabname"})
@Test(description = "navigateMenuBar" )	
public void navigateMenuBar(String tabName) throws InterruptedException
{
	System.out.println("in navigation methods " + tabName);
	driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
	Thread.sleep(4000);

	List<WebElement> list = driver.findElements(By.xpath(""));
      for(int i=0;i<list.size();i++)
	{

	if(list.get(i).getText().trim().equals(tabName))
		{
			

	}
	//type2
	//driver.findElement(By.xpath("//div/ul/li/a/span[contains(text(),'"+tabName+"')]")).click();	
	
	
	
	
}
}*/

