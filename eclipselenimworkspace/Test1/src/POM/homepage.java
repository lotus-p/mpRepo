package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


public class homepage { 
	
WebDriver driver=null;
public void searchitem(String itemname)
{
	
	WebElement searchBox = driver.findElement(By.id("globalSearchInputField"));
	searchBox.sendKeys(itemname);
	driver.findElement(By.id("subnavSearchSubmit")).click();
}

}
