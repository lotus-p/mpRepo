package testpackage;

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

public class macys {
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
	public void itemSearchFunctionality(String item){

		WebElement searchBox = driver.findElement(By.id("globalSearchInputField"));
		searchBox.sendKeys(item);
		driver.findElement(By.id("subnavSearchSubmit")).click();
	}
	
	@Test(description="getting item count per Page", priority=2)
	public void calculateItemCountPerPage(){
		
		int expectedCount = 60;
		List<WebElement> optionsJeans = driver.findElements(By.xpath("//*[@id='search_landing_product']/ul/li/div/div[2]/div[1]"));//60 nodes
		//List<WebElement> optionsJeans = driver.findElements(By.cssSelector(".shortDescription>a"));
		int actualCount = optionsJeans.size();
		System.out.println("Displayed jeans per page: " +actualCount );
		Assert.assertEquals(actualCount, expectedCount);
	
		//String countPerPage = driver.findElement(By.id("60_items")).getText();						
	
	}
	
	@Test(description="Sorting prices in Descending order" , priority=3)
	public void sortPriceByLowToHigh(){
		
		WebDriverWait wait = new WebDriverWait(driver,60);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("sortBy")));

		Select select = new Select(driver.findElement(By.id("sortBy")));
		
		select.selectByVisibleText("Price: Low to High");
		//M.sortPriceByLowToHigh();
		
		List<WebElement> list = driver.findElements(By.xpath("//*[@id='search_landing_product']/ul/li/div/div[3]/span[2]"));
		//List<WebElement> list = driver.findElements(By.cssSelector(".first-range.priceSale"));

		System.out.println("Jeans - Price by Low to High");
		System.out.println("----------------------------");
		System.out.println("List size: "+ list.size());

		List <Double> listToSort = new ArrayList<>();// declaring outside the loop otherwise Sort won't work

		try {
			for(int i=0;i<list.size(); i++)
			{	
				String str = list.get(i).getText();					// "Now $4.99" OR "Sale $17.15 - 36.99"
				String strSplit[] = str.split("\\s");				// "Now" ; "$4.99"  OR  "Sale" ; "$17.15" ; "-" ; "36.99"

				String strReplace = strSplit[1].replace("$",""); 	// "4.99" OR "17.15"
				double strFinal = Double.parseDouble(strReplace);	// 4.99 OR 17.15

				listToSort.add(strFinal);
			}
			System.out.println();
			System.out.println("Prices before sorting");

			for (double d :listToSort)
			{	
				System.out.println(d);
			}

			//Collections.sort(listToSort);
			Collections.sort(listToSort);

			System.out.println();
			System.out.println("Prices after sorting");

			for (double d :listToSort)
			{	
				System.out.println(d);
			}			
		}

		catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	@Test(description="Getting the descending order of reviews for Customer's Top Rated Jeans " , priority=4)
	public void	getReviewsOfCustomersTopRated() throws InterruptedException{

		WebDriverWait wait = new WebDriverWait(driver,60);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("sortBy")));

		Select select = new Select(driver.findElement(By.id("sortBy")));
		select.selectByVisibleText("Customers' Top Rated");
		
		System.out.println();
		System.out.println("Jeans - Customers Top Rated");
		System.out.println("---------------------------");

		Thread.sleep(30000);
		WebDriverWait wait1 = new WebDriverWait(driver,120);
		wait1.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@class='pdpreviews']/span[2]")));

		List<WebElement> list = driver.findElements(By.xpath("//*[@class='pdpreviews']/span[2]"));

		System.out.println("Size of list: "+ list.size());
		System.out.println();

		List <Integer> listRatings = new ArrayList<>();	

		try {
			for(int i=0; i<list.size(); i++)
			{
				String str = list.get(i).getText();// "(368)" // StaleElementReferenceException
				String strReplace1 = str.replace("(","");
				String strReplace2 = strReplace1.replace(")","");
				int intFinal = Integer.valueOf(strReplace2); 
				listRatings.add(intFinal);
			}

			System.out.println("Reviews per Item");

			for (int i :listRatings)
			{	
				System.out.println(i);
			}

			/*Collections.sort(listRatings);

			System.out.println();
			System.out.println("Reviews after Sort");

			for (int i :listRatings)
			{	
				System.out.println(i);
			}		*/

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
	
	





