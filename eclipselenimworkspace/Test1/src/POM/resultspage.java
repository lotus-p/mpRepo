package POM;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class resultspage {
	WebDriver driver=null;
	public int getitemcount()
	{
	List<WebElement> optionsJeans = driver.findElements(By.xpath("//*[@id='search_landing_product']/ul/li/div/div[2]/div[1]"));
	int actualCount = optionsJeans.size();
	System.out.println("Displayed jeans per page: " +actualCount );
	return actualCount;
}
public boolean chksortLHprices()
{
	boolean result = (Boolean) null  ;
	
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

		List <Double> listPrices = new ArrayList<>();// declaring outside the loop otherwise Sort won't work
        List <Double> listSorted = new ArrayList<>();
        
		try {
			for(int i=0;i<list.size(); i++)
			{	
				String str = list.get(i).getText();					// "Now $4.99" OR "Sale $17.15 - 36.99"
				String strSplit[] = str.split("\\s");				// "Now" ; "$4.99"  OR  "Sale" ; "$17.15" ; "-" ; "36.99"

				String strReplace = strSplit[1].replace("$",""); 	// "4.99" OR "17.15"
				double strFinal = Double.parseDouble(strReplace);	// 4.99 OR 17.15

				listPrices.add(strFinal);
			}
			System.out.println();
			System.out.println("Prices before sorting");

			for (double d :listPrices)
			{	
				System.out.println(d);
			}

			//Collections.sort(listToSort);
			Collections.sort(listPrices);

			System.out.println();
			System.out.println("Prices after sorting");

			for (double d :listPrices)
			{	
				listSorted.add(d);
				System.out.println(d);
			}	
			result = listPrices.equals(listSorted);
		}

		catch (Exception e) {
			e.printStackTrace();
		
			
		}

		return result;
}
		
		
		
	
		
	
	public boolean checkCustomersTopRated() throws InterruptedException
	{
		boolean result = (Boolean) null ;
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

		List <Integer> listRatings = new ArrayList<>();	// Storing to this array as we get the integers one by one
		List <Integer> listSorted = new ArrayList<>();	// Will be sorting and storing in this array once we get the inetgers

		try {
			for(int i=0; i<list.size(); i++)
			{
				String str = list.get(i).getText();// "(368)" // StaleElementReferenceException
				String strReplace1 = str.replace("(","");
				String strReplace2 = strReplace1.replace(")","");
				int intFinal = Integer.parseInt(strReplace2); 
				listRatings.add(intFinal);
			}

			System.out.println("Reviews per Item");

			for (int i :listRatings)
			{	
				System.out.println(i);
			}

			//Collections.sort(listRatings);
			Collections.sort(listRatings, Collections.reverseOrder());

			System.out.println();
			System.out.println("Reviews after Sort in Descending order");

			for (int i :listRatings)
			{	
				listSorted.add(i);
				System.out.println(i);
			}

			 result = listRatings.equals(listSorted);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


}
