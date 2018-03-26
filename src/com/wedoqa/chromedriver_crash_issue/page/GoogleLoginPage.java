package com.wedoqa.chromedriver_crash_issue.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoogleLoginPage extends BasePageObject {

    public GoogleLoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void isLoaded() {
    	String currentUrl = driver.getCurrentUrl();
        if (currentUrl == null || !currentUrl.contains("https://accounts.google.com/")) {
            throw new Error("The page url is incorrect");
        }
        waitForElementToAppear(emailInputField);
        waitForElementToAppear(nextBtn);
    }

    @Override
    protected void load() {
    	driver.get("https://accounts.google.com/ServiceLogin?service=mail#identifier");
    }

    @FindBy(css = "#Email, [type='email']")
    private WebElement emailInputField;

    @FindBy(css = "#next, #identifierNext")
    private WebElement nextBtn;

    public GmailPage login(String email, String password){
    	logger.debug("Login into the email account");
        emailInputField.sendKeys(email);
        nextBtn.click();
        getPasswordField().sendKeys(password);
        clickSignIn();

        return new GmailPage(driver);
    }

    private WebElement getPasswordField(){
        return waitForElementToAppear(By.cssSelector("#Passwd, [type='password']"));
    }

    private void clickSignIn(){
    	logger.trace("Click on sign in button");
    	
        waitForElementToAppear(By.cssSelector("#signIn, #passwordNext")).click();
    }

    public WebDriver getDriver() {
		return driver;
	}
}
