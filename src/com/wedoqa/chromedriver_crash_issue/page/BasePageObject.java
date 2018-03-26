package com.wedoqa.chromedriver_crash_issue.page;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BasePageObject extends LoadableComponent<BasePageObject> {

	protected static final Logger logger = LoggerFactory.getLogger(BasePageObject.class);

	protected static int WAIT_TIME_IN_SECONDS = 60;

	protected WebDriver driver;

	public BasePageObject(WebDriver driver) {
		init(driver);
		PageFactory.initElements(driver, this);
		this.get();
	}

	 private void init(WebDriver driver) {
		 this.driver = driver;
	 }

	 @Override
	 protected void load() {

	 }

	 @Override
	 protected void isLoaded() {

	 }

	 protected void waitFor(Integer milis) {
		 try {
			 Thread.sleep(milis);
		 } catch (InterruptedException e1) {

		 }
	 }

	 protected WebElement waitForElementToAppear(By locator) {
		 int count = 0;
		 while (count < 12) {
			 try {
				 WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME_IN_SECONDS / 12);
				 return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			 } catch (StaleElementReferenceException e) {
				 // e.toString();
				 logger.trace("Trying to recover from a stale element reference ecxeption");
				 count = count + 1;
			 } catch (TimeoutException e) {
				 count = count + 1;
			 }
		 }

		 throw new AssertionError("Element did not appear: " + locator);
	 }

	 protected WebElement waitForElementToAppear(WebElement element) {
		 int count = 0;
		 while (count < 12) {
			 try {
				 WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME_IN_SECONDS / 12);
				 return wait.until(ExpectedConditions.visibilityOf(element));
			 } catch (StaleElementReferenceException e) {
				 // e.toString();
				 logger.trace("Trying to recover from a stale element reference ecxeption");
				 count = count + 1;
			 } catch (TimeoutException e) {
				 count = count + 1;
			 }
		 }

		 throw new AssertionError("Element did not appear: " + element);
	 }
	 
	 protected Alert waitForAlert() {
		 int timeout = 0;
		 while (timeout < 20) {
			 try {
				 waitFor(500);
				 return driver.switchTo().alert();
			 } catch (NoAlertPresentException e) {
				 timeout++;
			 } catch (Exception e) {
				 e.printStackTrace();
				 throw new AssertionError("Unexpected exception happened when" + " the test waited for an alert dialog");
			 }
		 }

		 throw new AssertionError("The alert dialog did not appear in 10 seconds");
	 }

	 //TODO fix visibility
	 public boolean isAlertPresent() {
		 try {
			 driver.switchTo().alert();
			 return true;
		 } catch (NoAlertPresentException e) {
			 return false;
		 }
	 }
}
