package week4.day1.assignment;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Assignment3Frames {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://chercher.tech/practice/frames-example-selenium-webdriver");// load url
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebElement frame1 = driver.findElement(By.id("frame1"));// locate frame1
		driver.switchTo().frame(frame1);// switch to frame1
		driver.findElement(By.xpath("//b[@id='topic']/following::input")).sendKeys("Not a Friendly Topic");// enter input in topic field

		WebElement frame3 = driver.findElement(By.id("frame3"));// locate frame3
		driver.switchTo().frame(frame3);// switch to frame3

		driver.findElement(By.xpath("//b[contains(text(),'Inner Frame Check box ')]/following::input")).click();// click checkbox
		driver.switchTo().parentFrame();// switch from frame3 to frame1
		driver.switchTo().defaultContent();// switch from frame1
		WebElement frame2 = driver.findElement(By.id("frame2"));// locate frame2
		driver.switchTo().frame(frame2);// switch to frame2
		WebElement dropDown = driver.findElement(By.id("animals"));// select value from drop down
		Select DD1 = new Select(dropDown);
		DD1.selectByIndex(3);//select 'Avatar'
	}

}
