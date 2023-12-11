package stepDefination;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import com.guru.Guru99.BaseG;
import com.pom.HomePage;
import com.pom.ManagerPage;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;

public class loginSteps extends BaseG {

	public static WebDriver driver;
	public static HomePage hp;
	public static ManagerPage mp;

	@Before
	public void browserSetUp(Scenario scenario) throws Exception {
		driver = browserLaunch("firefox");
		getUrl("http://www.demo.guru99.com/V4/");
		String name = scenario.getName();
		System.out.println("Scenario: " + name);
	}

	@After
	public void tearDown(Scenario scenario) throws IOException {
		if (scenario.isFailed()) {
			takeScreenshot("fail" + scenario.getName());
			driver.quit();
		} else {
			driver.quit();
		}
	}

	@Given("the user in on the login page of Guru99")
	public void the_user_in_on_the_login_page_of_guru99() throws Exception {
		takeScreenshot("s1");
		String actualTitle = getTitleOfWindow();
		String expectedTitle = "Guru99 Bank Home Page";
		Assert.assertEquals(expectedTitle, actualTitle);
		String currentUrl = driver.getCurrentUrl();
		System.out.println(currentUrl);
		hp = new HomePage(driver);
	}

	@When("the user enters the valid UserID {string}")
	public void the_user_enters_the_valid_user_id(String string) {

		inputValueElement(hp.getUserId(), string);
	}

	@When("the user enters the valid Password {string}")
	public void the_user_enters_the_valid_password(String string) throws IOException {
		inputValueElement(hp.getPassword(), string);
		takeScreenshot("s2");
	}

	@When("the user clicks on the Login button")
	public void the_user_clicks_on_the_login_button() {
		clickOnWebElements(hp.getLoginBtn());
	}

	@Then("the user should be logged in successfully and navigates to the Manager Page")
	public void the_user_should_be_logged_in_successfully_and_navigates_to_the_manager_page() throws IOException {
		mp = new ManagerPage(driver);
		String actualText = getElementText(mp.getwelcomeManager());
		String expectedText = "Welcome To Manager's Page of Guru99 Bank";
		Assert.assertEquals(actualText, expectedText);
		takeScreenshot("s3");

	}

	@When("the user enters the {string} and {string}")
	public void the_user_enters_the_and(String UserID, String Password) throws IOException {
		inputValueElement(hp.getUserId(), UserID);
		inputValueElement(hp.getPassword(), Password);
		takeScreenshot("s4");
	}

	@Then("the pop-up appears user or password is invalid is shown")
	public void the_pop_up_appears_user_or_password_is_invalid_is_shown() throws IOException, AWTException {
		alertScreenshot("s5");
		simpleAlert();
	}
	
	@When("the user enters the valid UserID {string} and password as {string}")
	public void the_user_enters_the_valid_user_id_and_password_as(String string, String string2) {
		inputValueElement(hp.getUserId(), string);
		inputValueElement(hp.getPassword(), string2);
	}
}
