package com.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	public static WebDriver driver;

	@FindBy(name = "uid")
	private WebElement userId;
	
	@FindBy(name ="password")
	private WebElement password;
	
	@FindBy(name ="btnLogin")
	private WebElement loginBtn;
	
	
	
	public HomePage (WebDriver driver) {
		HomePage.driver = driver;
		PageFactory.initElements(driver,this);
	}

	public WebElement getUserId() {
		return userId;
	}
	
	public WebElement getPassword() {
		return password;
	}
	
	public WebElement getLoginBtn() {
		return loginBtn;
	}


}
