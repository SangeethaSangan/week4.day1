package week4.day1.assignment;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Assignment1MergeContact {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://leaftaps.com/opentaps/control/login");// load URL
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.findElement(By.id("username")).sendKeys("DemoCSR");// Enter UserName
		driver.findElement(By.id("password")).sendKeys("crmsfa", Keys.TAB, Keys.ENTER);// enter Password & login

		driver.findElement(By.partialLinkText("CRM")).click();// Click on CRM/SFA Link

		driver.findElement(By.partialLinkText("Contacts")).click();// Click on contacts Button

		driver.findElement(By.xpath("//a[contains(text(),'Merge')]")).click();// Click on Merge

		driver.findElement(By.xpath("//a[contains(@href,'partyIdFrom')]")).click();// Click on Widget of From Contact
		System.out.println("Current window title:" + driver.getTitle());

		String parent = driver.getWindowHandle();// get main window reference

		Set<String> handles = driver.getWindowHandles();/// get windows reference in set
		List<String> windowsList1 = new ArrayList<String>(handles);// convert set to list

		String resultWindow1 = windowsList1.get(1);// get result window1 reference

		driver.switchTo().window(resultWindow1);// switch to result window1

		System.out.println("resulting window1 title:" + driver.getTitle());// get result window title

		Thread.sleep(5000);
		driver.findElement(By.xpath("//table[@class='x-grid3-row-table']//tr//a[@class='linktext']")).click();// ClickonFirstResultingContact

		driver.switchTo().window(parent);// switch back to main window
		System.out.println("After closed resulting window1:" + driver.getTitle());

		driver.findElement(By.xpath("//a[contains(@href,'partyIdTo')]")).click();// Click on Widget of To Contact
		Set<String> Handles1 = driver.getWindowHandles();// get windows reference in set
		List<String> windowsList2 = new ArrayList<String>(Handles1);// convert set to list
		String resultWindow2 = windowsList2.get(1);

		Thread.sleep(5000);
		driver.switchTo().window(resultWindow2);// switch to result window

		System.out.println("resulting window2 title:" + driver.getTitle()); // get result window2 title

		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[2]/table/tbody/tr[1]/td[1]/div/a[@class='linktext']")).click(); // Click
																											// onsecondResultingContact

		driver.switchTo().window(parent);// switch back to main window
		System.out.println("After closed resulting window2:" + driver.getTitle());
		driver.findElement(By.linkText("Merge")).click();// click merge button

		Alert alert = driver.switchTo().alert();// switch to alert
		alert.accept();// click ok in alert
		System.out.println(driver.getTitle());

	}

}
