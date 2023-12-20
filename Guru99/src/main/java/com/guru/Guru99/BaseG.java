package com.guru.Guru99;

import java.awt.image.BufferedImage;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseG {

	public static WebDriver driver;
	public static Actions action;
	
	// Method Creation to launch the browser-Using External Drivers
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
	
	//To launch the browser using Browser Options with webdriver Manager
		public static WebDriver launchBrowserOptions(String browserName) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--ignore-certificate-errors");
			options.addArguments("chrome.switches", "--disable-extensions");
			options.addArguments("--disable-notifications");
			options.addArguments("enable-automation");
			options.addArguments("--incognito");
			options.addArguments("--start-maximized");
			
			if(browserName.equalsIgnoreCase("chrome")) {
				WebDriverManager.chromedriver().setup();
				DesiredCapabilities cap = new DesiredCapabilities();
				cap.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
				options.merge(cap);
				driver = new ChromeDriver(options);
			}
			else if (browserName.equalsIgnoreCase("firefox")){
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				FirefoxProfile profile = new FirefoxProfile();
				FirefoxOptions optionsFirefox = new FirefoxOptions();
				profile.setPreference("extensions.enabled", false);
				optionsFirefox.setProfile(profile);
				driver.manage().window().maximize();
			}
			else if (browserName.equalsIgnoreCase("ie")) {
				WebDriverManager.edgedriver().setup();
				driver = new InternetExplorerDriver();
			}
			
			return driver;
		}
	
	// Method Creation for navigating to the URL
	public void getUrl(String url) {
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);	
	}
	
	// Method Creation for passing sendKeys
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
	
	// Method Creation to click on web Elements
		public static void clicOnThelement(WebElement element) {
			element.click();
		}	
		
		//Method Creation for Clearing the input
		public static void inputClear(WebElement ele) {
		ele.clear();
		}

		// Method creation for close the browser
		public static WebDriver closeCurrentWindow() {
			driver.close();
			return driver;
		}

		// Method creation for quit the browser
		public static WebDriver quitMultipleWindows() {
			driver.quit();
			return driver;
		}

		// Method creation to get current url
		public static String currentUrl() {
			return driver.getCurrentUrl();
		}

		// Method creation to get title
		public static String titleOfWebpage() {
			return driver.getTitle();
		}

		// Method creation to get text
		public static String webText(WebElement ele) {
			return ele.getText();
		}

		// Method creation to get attribute
		public static String webAttribute(WebElement ele, String attributeName) {
			String attribute = ele.getAttribute(attributeName);
			return attribute;
		}

		// Method creation for explicit wait
		public static void explicitWait(int timeDuration, WebElement ele) {

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeDuration));
			wait.until(ExpectedConditions.visibilityOf(ele));
		}

		// Method creation for implicit wait
		public static void implicitWait(int timeDuration) {
			driver.manage().timeouts().implicitlyWait(timeDuration, TimeUnit.SECONDS);
		}

		// Method creation for handling frames
		public static WebElement framesWindows(int index, String nameOrId, WebElement ele) {
			if (nameOrId != null && !nameOrId.isEmpty()) {
				driver.switchTo().frame(nameOrId);
			} else if (index >= 0) {
				driver.switchTo().frame(index);
			} else if (ele != null)
				driver.switchTo().frame(ele);
			return null;
		}

		// Method creation for handling Js Alerts
		public static void alertPopUp() {
			driver.switchTo().alert().accept();
		}

		// Method creation for Navigate To
		public static void toNavigate(String url) {

			driver.navigate().to(url);
		}

		// Method creation for Navigate forward
		public static void forwardNavigate() {
			driver.navigate().forward();
		}

		// Method creation for Navigate back
		public static void backNavigate() {
			driver.navigate().back();
		}

		// Method creation for Navigate and refresh
		public static void refreshNavigate() {
			driver.navigate().refresh();
		}

		// Method Creation to perform action-Right Click/context click
		public static void actionClass(WebElement ele) {
			action = new Actions(driver);
			action.contextClick(ele).build().perform();
		}

		// Method Creation to perform action-MoveToElement
		public static void mouseHovering(WebElement ele) {
			action = new Actions(driver);
			action.moveToElement(ele).build().perform();
		}

		// Method Creation to perform action-Drag&Drop---Method(1)
		public static void dragAndDrop(WebElement from, WebElement to) {
			action = new Actions(driver);
			action.dragAndDrop(from, to).build().perform();
		}

		// Method Creation to perform action-Drag&Drop---Method(2)
		public static void clickHoldAndDrop(WebElement from, WebElement to) {
			action = new Actions(driver);
			action.clickAndHold(from).build().perform();
			action.moveToElement(to).build().perform();
			// action.sendKeys(input).build().perform();
		}

		// Method to send input using actions
		public static void sendInput(WebElement input, String value) {
			action = new Actions(driver);
			action.sendKeys(input, value).build().perform();
		}

		// Method creation for Robot Class
		public static void keyboardKeysPress() throws AWTException {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_DOWN);
			robot.keyRelease(KeyEvent.VK_DOWN);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
		}

		// Method creation for JavaScript executor-up
		public static void pageUp() {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,-500)");
		}

		// Method creation for JavaScript executor-down
		public static void pageDown() {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,1000)");
		}

		// Method creation for JavaScript executor-View of Element
		public static void scrollIntoView(WebElement element) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", element);
		}

		// Method creation for windows handling Using FOR-Each to get the title of the
		// Tabs
		public static void windowHandlesTitle() {
			Set<String> element = driver.getWindowHandles();

			for (String st : element) {
				String title = driver.switchTo().window(st).getTitle();
				System.out.println(title);
			}

		}

		// Method creation for windows handling Using FOR-Each to get the desired TAB
		public static void windowHandles() {
			Set<String> element = driver.getWindowHandles();
			
			String selenium = "The Selenium Browser Automation Project | Selenium";
			for (String string : element) {
				if (driver.switchTo().window(string).getTitle().contains(selenium)) {
					break;
				}
			}
		}

		// Method creation for windows handling Using ITERATION to get the desired TAB
		public static void iterationWidowHandling() {
			String homeTab = driver.getWindowHandle();//// Storing the current window in a Variable
			Set<String> windowHandels = driver.getWindowHandles();// Storing all window handels
			Iterator<String> childTab = windowHandels.iterator();// Now iterate using Iterator
			while (childTab.hasNext()) {
				String selenium = childTab.next();
				String title = "WebDriver | Selenium";
				if (driver.switchTo().window(selenium).getTitle().contains(title)) {
					break;
				}
			}
		}

		// Method Creation for the radio Btn
		public static void clickRadoiBtn(WebElement ele) {
			ele.click();
		}

		// Method Creation for the radio Btn Status-isSelected
		public static void radoiBtnStatus(WebElement ele) {
			boolean selected = ele.isSelected();
			if (selected) {
				System.out.println("Button not selected");
			} else {
				System.out.println("Button is selected");
			}
		}

		// Method Creation for the radio Btn Status-Enabled
		public static void radoiBtnEnabled(WebElement ele) {
			boolean enabled = ele.isEnabled();
			if (enabled) {
				System.out.println("Button is enabled");
			} else {
				System.out.println("Button not enabled");
			}
		}

		// Method Creation for the radio Btn Status-Displayed
		public static void radoiBtndisplayed(WebElement ele) {
			boolean display = ele.isDisplayed();
			if (display) {
				System.out.println("Button is display");
			} else {
				System.out.println("Button not display");
			}
		}

		// Method Creation to handel dropdown
		public static void dropDown(WebElement element, String Option, String value) {
			Select sc = new Select(element);

			if (Option.equalsIgnoreCase("byIndex")) {
				int parseInt = Integer.parseInt(value);//Value is by default 'String' in the webpage so it is converted into the 'Integer' 
				sc.selectByIndex(parseInt);
			} else if (Option.equalsIgnoreCase("byValue")) {
				sc.selectByValue(value);
			} else if (Option.equalsIgnoreCase("byVisibleText")) {
				sc.selectByVisibleText(value);
			} else {
				System.out.println("Invalid option");
			}
		}

		// Method Creation to handel dropdown
		public static void dropDownOption(WebElement ele) {
			Select dropOptions = new Select(ele);

			List<WebElement> options = dropOptions.getOptions();

			for (WebElement option : options) {
				System.out.println(option.getText());
			}

		}

		// Method Creation to get firstSelectedOption from dropdown-Note which retrieve
		// the option we select
		public static WebElement dropDownFirstOption(WebElement ele) {
			Select firstOptions = new Select(ele);
			WebElement firstSelectedOption = firstOptions.getFirstSelectedOption();
			String first = firstSelectedOption.getText();
			System.out.println(first);
			return firstSelectedOption;
		}

		// Method Creation to get Get all selected options from dropdown-It retrieves
		// the all the selected options
		public static void dropDownAllOption(WebElement ele) {
			Select allOptions = new Select(ele);
			List<WebElement> allTheOptions = allOptions.getAllSelectedOptions();
			for (WebElement all : allTheOptions) {
				System.out.println(all.getText());
			}
		}

		// Method creation for checkbox
		public static void checkBoxHandle(WebElement ele) {
			ele.click();
		}
	
}