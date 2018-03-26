package com.wedoqa.chromedriver_crash_issue.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GmailPage extends BasePageObject{

    @FindBy(xpath = ".//a[text()='Sign out']")
    private WebElement signOutLink;
    
    @FindBy(css = "a[href*='SignOut']")
    private WebElement profileIcon;
    
    public GmailPage(WebDriver driver){
        super(driver);
    }

    @Override
    protected void isLoaded() throws Error {
    	waitForElementToAppear(By.cssSelector("a[href='#inbox'][title='Gmail']"));
    }

    public void clickSignOut(){
    	logger.debug("Click on the profile icon");
    	waitForElementToAppear(profileIcon).click();
    	logger.debug("Click on the sign out button");
        waitForElementToAppear(signOutLink).click();
        waitForElementToAppear(By.xpath(".//h1[contains(text(),'Hi chromedriver')]"));
    }
   
}
