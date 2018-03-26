package com.wedoqa.chromedriver_crash_issue.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.wedoqa.chromedriver_crash_issue.page.GmailPage;
import com.wedoqa.chromedriver_crash_issue.page.GoogleLoginPage;

public class OpenGmailInNewWindowTests {

	private WebDriver driver;
	
	@BeforeEach	
	public void open() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	@Test
	public void testOpenGmailInNewWindowAndLogout() {
		GoogleLoginPage googleLoginPage = new GoogleLoginPage(driver);
		GmailPage gmailPage = googleLoginPage.login("reproducing.chromdriver.issue@gmail.com", "NotSoSecurePassword");
		gmailPage.clickSignOut();
	}
	
	@AfterEach
	public void close() {
		driver.quit();
	}
	
}
