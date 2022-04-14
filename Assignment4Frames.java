package week4.day1.assignment;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Assignment4Frames {

	public static void main(String[] args) throws IOException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://leafground.com/pages/frame.html");// load url
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		WebElement frame1 = driver.findElement(By.xpath("//div[@id='wrapframe']//iframe[contains(@src,'default')]"));// locate frame1
																														
		driver.switchTo().frame(frame1);// switch to frame1
		driver.findElement(By.xpath("//button[@id='Click']")).click();// locate & click on 'click' inside frame 1
		driver.switchTo().parentFrame();
		WebElement frame2 = driver.findElement(By.xpath("//iframe[@src='page.html']"));
		driver.switchTo().frame(frame2);
		driver.findElement(By.id("Click1")).click();
		// take the screenshot
		File source = driver.getScreenshotAs(OutputType.FILE);
		// Creating physical file
		File destination = new File("./snaps/screenshot.png");
		// Copy source to destination
		FileUtils.copyFile(source, destination);

		driver.switchTo().parentFrame();//switch to parent frame
		
		List<WebElement> iframes = driver.findElements(By.tagName("iframe"));// get no of frames
		System.out.println("no of iframes in the page:" + iframes.size());
		

	}

}
