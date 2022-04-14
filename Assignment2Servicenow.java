package week4.day1.assignment;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Assignment2Servicenow {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://dev122476.service-now.com");// load URL
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebElement frame = driver.findElement(By.xpath("//iframe[@id='gsft_main']"));// locate frame element
		driver.switchTo().frame(frame);// switch to frame
		driver.findElement(By.id("user_name")).sendKeys("admin");// enter user name
		driver.findElement(By.id("user_password")).sendKeys("Testleaf@123");// enter password
		driver.findElement(By.id("sysverb_login")).click();// click login
		driver.switchTo().defaultContent();// switch from frame
		driver.findElement(By.id("filter")).sendKeys("incident", Keys.ENTER);// enter 'incidents' in search bar
		driver.findElement(By.xpath("//li[27]/ul/li[6]/div/div/a/div/div")).click();// click 'All' incidents

		WebElement frame1 = driver.findElement(By.id("gsft_main"));// locate frame element
		driver.switchTo().frame(frame1);// switch to frame
		driver.findElement(By.xpath("//button[text()='New']")).click();// click 'new ' incident
		String incidentNo = driver.findElement(By.id("incident.number")).getAttribute("value");// get incident no
		System.out.println("incident no:" + incidentNo);// print incident no
		driver.findElement(By.xpath(
				"//input[contains(@id,'sys_display.original.incident.caller_id')]//following::span[contains(@class,'icon-search')]"))
				.click();// click caller icon
		String mainWindow = driver.getWindowHandle();// get current window ref
		Set<String> handles = driver.getWindowHandles();// get windows ref
		List<String> HandlesList = new ArrayList<String>(handles);
		String newWindow = HandlesList.get(1);// convert set to list
		driver.switchTo().window(newWindow);// switch to newWindow
		// System.out.println("new window page Title" + driver.getTitle());
		driver.findElement(By.xpath("//a[@class='glide_ref_item_link']")).click();// select 1st listed user
		driver.switchTo().window(mainWindow);// switch back to main window
		// System.out.println("main page Title" + driver.getTitle());
		driver.switchTo().frame(frame1);// switch to frame
		driver.findElement(By.id("incident.short_description")).sendKeys("Test1");// enter short desc
		driver.findElement(By.xpath("//button[text()='Submit']")).click();
		WebElement SearchDropdown = driver.findElement(By.xpath("//select[@role='listbox']"));// locate search dropdown
		Select dd1 = new Select(SearchDropdown);
		dd1.selectByValue("number");// select 'number' in search filter
		driver.findElement(By.xpath("//input[@class='form-control'][@placeholder='Search']")).sendKeys(incidentNo,
				Keys.ENTER);// enter incident no in search filter and press enter key

		String resultIncidentNo = driver.findElement(By.xpath("//a[@class='linked formlink']")).getText();// get result
		if (resultIncidentNo.equals(incidentNo))// check whether incident no is listed in search results
		{
			System.out.println("incident no:" + incidentNo + " is created successfully");
		} else {

			System.out.println("incident no:" + incidentNo + " is not created ");
		}
	}

}
