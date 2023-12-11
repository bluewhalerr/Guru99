package com.guru.Guru99;

import java.awt.image.BufferedImage;
import java.awt.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class BaseG {

	public static WebDriver driver;

	public WebDriver browserLaunch(String browserName) throws Exception {
		try {
			if (browserName.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "src\\test\\resources\\Drivers\\chromedriver.exe");
				driver = new ChromeDriver();
			} else if (browserName.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.geko.driver",
						System.getProperty("user.dir") + "src\\test\\resources\\Drivers\\geckodriver.exe");
				driver = new FirefoxDriver();
			} else if (browserName.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.geko.driver",
						System.getProperty("user.dir") + "src\\test\\resources\\Drivers\\geckodriver.exe");
				driver = new FirefoxDriver();
			}else {
				throw new Exception("Browser name id not valid");
			}
			
			return driver;	
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}	
	}

	public void getUrl(String url) {
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);	
	}
	
	public void inputValueElement(WebElement element,String value) {
		element.sendKeys(value);
	}
	
	public void clickOnWebElements(WebElement element) {
		element.click();
	}
	
	public String getElementText(WebElement element) {
		String text = element.getText();
		System.out.println(text);
		return text;
	}
	
	public String getTitleOfWindow() {
		String title = driver.getTitle();
		return title;
	}
	
	public void waitforAlertISPresent(int timeDuration) {
		WebDriverWait wb = new WebDriverWait(driver, Duration.ofSeconds(timeDuration));
		wb.until(ExpectedConditions.alertIsPresent());

	}
	public String  simpleAlert() {
		waitforAlertISPresent(20);
		Alert  alert = driver.switchTo().alert();
		String alertText = alert.getText();
		System.out.println(alertText);
		driver.switchTo().alert().accept();
		return alertText;
	}
	
	public void takeScreenshot(String stepName) throws IOException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmssSSS");
		Date currentDate = new Date();
		String timeStamp = dateFormat.format(currentDate);
		String fileName = stepName+"_"+timeStamp;
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File destination = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\Screenshot\\" + fileName + ".png");
		FileUtils.copyFile(source, destination);
	}	
	
	public void alertScreenshot(String stepName) throws IOException, AWTException {
		 Robot robot = new Robot();
         Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
         BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
         ImageIO.write(screenFullImage, "png", new File(System.getProperty("user.dir") + "\\src\\test\\resources\\Screenshot\\" + stepName + ".png"));

	}	
	
	public void extentReport() {
		String reportPath = "D:/Eclipse/Guru99/target/cucumber-extent report.html";
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(reportPath);
        ExtentReports  extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);
        extentReports.flush();
	}
}