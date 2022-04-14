package week4.day1.assignment;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Assignment5Nykaa {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.nykaa.com/");// load url
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		Thread.sleep(5000);
		WebElement element = driver.findElement(By.xpath("//header//a[text()='brands']"));// locate brands

		Actions builder = new Actions(driver);
		builder.moveToElement(element).perform();// mouse hover on brands

		driver.findElement(By.xpath("//a[contains(text(),'Paris')][contains(@href,'Paris')]")).click();// Click
																										// L'OrealParis
		System.out.println("Print titile of the page:" + driver.getTitle());

		driver.findElement(By.xpath("//div[@id='filter-sort']//button")).click();// click sort by
		driver.findElement(By.xpath("//span[contains(text(),'customer ')]")).click();// click customer toprated
		driver.findElement(By.xpath("//span[text()='Category']")).click();// click category
		Thread.sleep(5000);
		driver.findElement(By.xpath("//span[text()='Hair']/following::span")).click();// click hair

		driver.findElement(By.xpath("//span[contains(text(),'Hair Care')]")).click();// click haircare
		//
		driver.findElement(By.xpath("//span[text()='Shampoo']/following::div")).click();// locate shampoo

		List<WebElement> findElements = driver.findElements(By.className("filter-value"));
		List<String> lists = new ArrayList<String>();
		for (WebElement list : findElements) {
			lists.add(list.getText());
		}

		System.out.println("filtered with shampoo:" + lists.contains("Shampoo"));

		Thread.sleep(5000);
		driver.findElement(By.xpath("//span[text()='Concern']")).click();// click concern
		Thread.sleep(5000);
		driver.findElement(By.xpath("//span[contains(text(),'Color Protection')]/following::div")).click();// check clr
																											// protection

		driver.findElement(By.xpath("//img[contains(@alt,'Colour Protect Shampoo')]")).click();// click on product

		// handle windows
		Set<String> handles = driver.getWindowHandles();
		List<String> windowsList = new ArrayList<String>(handles);
		//String currentTab = windowsList.get(0);
		String nextTab = windowsList.get(1);

		driver.switchTo().window(nextTab);// switch to next window
		System.out.println("Print title of this page" + driver.getTitle());// get title of the page
		String oldMRP = driver.findElement(By.xpath("//span[text()='MRP:']/following::span")).getText();// get MRP value
		System.out.println("Print old MRP value" + oldMRP);// print MRP value
		WebElement ddValues = driver.findElement(By.xpath("//select[@title='SIZE']"));// locate dropdown
		Select DD = new Select(ddValues);
		DD.selectByIndex(2);// select dropdown value

		String newMRP = driver.findElement(By.xpath("//span[text()='MRP:']/following::span")).getText();// get new MRP
																										// value
		System.out.println("Print new MRP value" + newMRP);
		driver.findElement(By.xpath("//span[contains(text(),'ADD')]")).click();// click add to bag
		driver.findElement(By.xpath("//span[@class='cart-count']")).click();// click bag

		WebElement frame = driver.findElement(By.xpath("//iframe[contains(@src,'IframeCart')]"));// find frame
		driver.switchTo().frame(frame);
		Thread.sleep(2000);
		String grandTotal = driver.findElement(By.xpath("//div[contains(@class,'value medium-strong')]")).getText();// get
																													// grand
																													// total
																													// value
		System.out.println("print grand Total in cart page:" + grandTotal);

		driver.findElement(By.xpath("//button[contains(@class,'proceed')]")).click();// click proceed
		driver.findElement(By.xpath("//button[contains(text(),'GUEST')]")).click();// click continue as Guest

		String grandTotal1 = driver
				.findElement(By.xpath("//div[contains(@class,'name')][contains(text(),'Grand')]/following::div"))
				.getText();// get grand total value
		System.out.println("print grand Total in address page:" + grandTotal1);
		if (grandTotal == grandTotal1)// compare grand total
		{
			System.out.println("grand total is same in both pages");
		} else {
			System.out.println("grand total is same in both pages");
		}
		driver.close();
	}

}
