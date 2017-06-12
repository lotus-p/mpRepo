package testpackage;

//public class copyofmacys {
	import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

	import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import POM.homepage;
import POM.resultspage;

	public class copyofmacys {
		WebDriver driver;
		
		@Parameters({"url"})
		@BeforeTest
		public void startBrowser(String url) throws InterruptedException{
			System.setProperty("webdriver.chrome.driver","/Users/Anil/Downloads/chromedriver");

			driver = new ChromeDriver();
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
		
		@Parameters({"item"})
		@Test(description="Searching for item - Jeans", priority=1)
		public void itemSearchFunctionality1(String itemname)
		{
		
			homepage h1 = new homepage();
	    	h1.searchitem("jeans"); 
		    
		}		
		
		//public void itemSearchFunctionality(String item){

		//	WebElement searchBox = driver.findElement(By.id("globalSearchInputField"));
		//	searchBox.sendKeys(item);
		//	driver.findElement(By.id("subnavSearchSubmit")).click();
		//}
		
		@Test(description="getting item count per Page", priority=2)
		public void calculateItemCountPerPage(){
			
			int expectedCount = 60;
			resultspage rpage = new resultspage();	
			int actualCount = rpage.getitemcount();
			
			System.out.println("Displayed jeans per page: " +actualCount );
			Assert.assertEquals(actualCount, expectedCount);						
		
		}
		
		@Test(description="Sorting prices in Descending order" , priority=3)
		public void sortPriceByLowToHigh(){
			
			resultspage rpage = new resultspage();	
			boolean output = rpage.chksortLHprices();
			Assert.assertTrue(output);
		}
		@Test(description="Getting the descending order of reviews for Customer's Top Rated Jeans " , priority=4)
		public void	getReviewsOfCustomersTopRated() throws InterruptedException
		{

			resultspage rpage = new resultspage();
			boolean output = rpage.checkCustomersTopRated();
			Assert.assertTrue(output);
		}
	}
