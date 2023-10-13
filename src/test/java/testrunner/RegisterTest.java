package testrunner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseClass;
import utilites.UtilitesClass;


public class RegisterTest extends BaseClass {
 
	
	
	public WebDriver driver;
	
	
	
	@BeforeMethod
	public void setup() {
		
		driver=initalizeBrowserAndOpenApplication("chrome");
		driver.findElement(By.xpath("//span[text()='My Account']")).click();		
		driver.findElement(By.linkText("Register")).click();
		UtilitesClass.Wait(5000);		
	}
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	
	
	@Test(priority=1)
	public void verifyRegisteringAnAccountWithMandatoryFields() {
		
		
		driver.findElement(By.id("input-firstname")).sendKeys("Ram");
		driver.findElement(By.id("input-lastname")).sendKeys("Patil");
		driver.findElement(By.id("input-email")).sendKeys("rampatil"+ UtilitesClass.ganrateTimeStamp()+"@gmail.com");
		driver.findElement(By.id("input-telephone")).sendKeys("9881444475");
		driver.findElement(By.id("input-password")).sendKeys("12345");
		driver.findElement(By.id("input-confirm")).sendKeys("12345");
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		String actualSuccessHeading=driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
		
		Assert.assertEquals(actualSuccessHeading,"Your Account Has Been Created!","Account Success Page is not Diplayed");
	}
	
	
	@Test(priority=2)
	public void verifyRegisteringAnAccountByProvidingAllFields() {
		
		
		driver.findElement(By.id("input-firstname")).sendKeys("Ram");
		driver.findElement(By.id("input-lastname")).sendKeys("Patil");
		driver.findElement(By.id("input-email")).sendKeys("rampatil"+UtilitesClass.ganrateTimeStamp()+"@gmail.com");
		driver.findElement(By.id("input-telephone")).sendKeys("9881444475");
		driver.findElement(By.id("input-password")).sendKeys("12345");
		driver.findElement(By.id("input-confirm")).sendKeys("12345");
		driver.findElement(By.xpath("//input[@name=\"newsletter\"][@value=1]")).click();		
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		String actualSuccessHeading=driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
		
		Assert.assertEquals(actualSuccessHeading,"Your Account Has Been Created!","Account Success Page is not Diplayed");
				
	}
	
	
	
	@Test(priority=3)
	public void verifyRegisteringAnAccountWithExistingEmailAddress() {
		
		
		driver.findElement(By.id("input-firstname")).sendKeys("Ram");
		driver.findElement(By.id("input-lastname")).sendKeys("Patil");
		driver.findElement(By.id("input-email")).sendKeys("rampatil@gmail.com");
		driver.findElement(By.id("input-telephone")).sendKeys("9881444475");
		driver.findElement(By.id("input-password")).sendKeys("12345");
		driver.findElement(By.id("input-confirm")).sendKeys("12345");
		driver.findElement(By.xpath("//input[@name=\"newsletter\"][@value=1]")).click();		
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		String actualWarningMessage=driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		
		Assert.assertTrue(actualWarningMessage.contains("Warning: E-Mail Address is already registered!"),"Warning Message Duplicate E-Mail Address is not Diplayed");

	}
	
	
	
	@Test(priority=4)
	public void verifyRegisteringAnAccountWithoutFillingAnyDetails() {
		
		
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		String actualPrivacyPolicyWarning=driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		Assert.assertTrue(actualPrivacyPolicyWarning.contains("Warning: You must agree to the Privacy Policy!"),"Warning You must agree to the Privacy Policy message not displayed");
		
		String actualFirstNameWarning=driver.findElement(By.xpath("//input[@id='input-firstname']/following-sibling::div")).getText();
		Assert.assertEquals(actualFirstNameWarning, "First Name must be between 1 and 32 characters!","First Name must be between 1 and 32 characters message not displayed");
		
		
		String actualLastNameWarning=driver.findElement(By.xpath("//input[@id='input-lastname']/following-sibling::div")).getText();
		Assert.assertEquals(actualLastNameWarning, "Last Name must be between 1 and 32 characters!","Last Name must be between 1 and 32 characters message not displayed");
		
		String actualEmailAddressWarning=driver.findElement(By.xpath("//input[@id='input-email']/following-sibling::div")).getText();
		Assert.assertEquals(actualEmailAddressWarning, "E-Mail Address does not appear to be valid!","E-Mail Address does not appear to be valid message not displayed");
		
		String actualTelephoneWarning=driver.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div")).getText();
		Assert.assertEquals(actualTelephoneWarning, "Telephone must be between 3 and 32 characters!","Telephone must be between 3 and 32 characters message not displayed");
		
		String actualPasswordWarning=driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div")).getText();
		Assert.assertEquals(actualPasswordWarning, "Password must be between 4 and 20 characters!","Password must be between 4 and 20 characters message not displayed");
	}
	
	
}
