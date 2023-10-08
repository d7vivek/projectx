package testrunner;

import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest {
	
	public WebDriver driver;
	@BeforeMethod
	public void setup() {
		driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get("https://tutorialsninja.com/demo/");
		driver.findElement(By.xpath("//span[text()='My Account']")).click();		
		driver.findElement(By.linkText("Login")).click();
		Wait();		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	 
	
	@Test(priority=1)
	public void verifyLoginWithValidCredentials() {
		
												
		driver.findElement(By.id("input-email")).sendKeys("rampatil@gmail.com");
		driver.findElement(By.id("input-password")).sendKeys("12345");
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		
		Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed(),"Edit your account information is displayed");
		
		
	}
	
	
	@Test(priority=2)
	public void verifyLoginWithInvalidCredentials() {
		
											
		driver.findElement(By.id("input-email")).sendKeys("rampatil"+ganrateTimeStamp()+"@gmail.com");
		driver.findElement(By.id("input-password")).sendKeys("12345678");
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		
		String actualWarningMessage =driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String expectedWarningMessage ="Warning: No match for E-Mail Address and/or Password.";
		
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Expected Warning Message is not displayed");
		
		
	}
	
	
	@Test(priority=3)
	public void verifyLoginWithInvalidEmailAndValidPassword() {
		
												
		driver.findElement(By.id("input-email")).sendKeys("rampatil"+ganrateTimeStamp()+"@gmail.com");
		driver.findElement(By.id("input-password")).sendKeys("12345");
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		
		String actualWarningMessage =driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String expectedWarningMessage ="Warning: No match for E-Mail Address and/or Password.";
		
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Expected Warning Message is not displayed");
		
		
	}

	
	@Test(priority=4)
	public void verifyLoginWithValidEmailAndInvalidPassword() {
		
											
		driver.findElement(By.id("input-email")).sendKeys("rampatil@gmail.com");
		driver.findElement(By.id("input-password")).sendKeys("12345678");
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		
		String actualWarningMessage =driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String expectedWarningMessage ="Warning: No match for E-Mail Address and/or Password.";
		
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Expected Warning Message is not displayed");
		
	}

	
	@Test//(priority=5)
	public void verifyLoginWithoutProvidingCredentials() {
		
										
		driver.findElement(By.id("input-email")).sendKeys("");
		driver.findElement(By.id("input-password")).sendKeys("");
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		
		String actualWarningMessage =driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String expectedWarningMessage ="Warning: No match for E-Mail Address and/or Password.";
		
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Expected Warning Message is not displayed");
		
	}

	public String ganrateTimeStamp() {
		Date date=new Date();
		return date.toString().replace(" ", "_").replace(":", "_");
		
				
	}
	
	public void Wait() {
		try {
			Thread.sleep(5000);
		}
		catch(Exception e){System.out.println(e.getMessage());}
	}

}
