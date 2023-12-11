package com.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ManagerPage {

	public static WebDriver driver;
	@FindBy(xpath="//marquee[@class='heading3']")
	private WebElement welcomeManager;
	

	public ManagerPage (WebDriver driver) {
		ManagerPage.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	public WebElement getwelcomeManager() {
		return welcomeManager;
	}
}
