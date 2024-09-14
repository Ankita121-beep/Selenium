package Demo;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;

import junit.framework.Assert;

import org.testng.annotations.*;

public class OrangeHRM {
	
	WebDriver driver;
	public String baseUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
	
	@BeforeTest
	public void setup() {
		System.out.println("Before test Executed");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		
		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		
	}
	
	//Test method creation
	@Test
	public void loginTest() {
		
		//find username
		driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("Admin");
		
		//find password
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("admin123");
		
		//login button click
		driver.findElement(By.xpath("//button[@type='submit']")).submit();
		
		//verify if login was successfull by checking page title
		String pageTitle = driver.getTitle();
		
	/*	if (pageTitle.equals("OrangeHRM")){
			System.out.println("LOGIN SUCCESSFULL");
		}
		else {
			System.out.println("LOGIN FAILED");
		}

	*/
		
		//Verify using Assert class of TestNG
		Assert.assertEquals("OrangeHRM", pageTitle);
	}
	
	public void logout() throws InterruptedException {
		driver.findElement(By.xpath("//p[@class='oxd-userdropdown-name']")).click();
		
	//	driver.findElement(By.xpath("//a[normalize-space()='Logout']")).click(); 
		
		List <WebElement> elementList = driver.findElements(By.xpath("//a[@class='oxd-userdropdown-link]"));
		for(int i=0; i<elementList.size();i++) {
			Thread.sleep(1000);
			System.out.println(i + ":" + elementList.get(i).getText());
		}
		elementList.get(3).click();
	}
	
	
	
	
	@AfterTest
	public void tearDown() throws InterruptedException {
		logout();
		Thread.sleep(5000);
		
		driver.close();
		driver.quit();
	}

}
