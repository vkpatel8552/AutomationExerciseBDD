package test.AutomationExerciseBDD.AppHooks;

import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import test.AutomationExerciseBDD.Pages.StartUpPage;
import test.AutomationExerciseBDD.Utils.Efficacies;
import test.AutomationExerciseBDD.Utils.WebDriverUtils;

import org.assertj.core.api.SoftAssertions;

public class ApplicationHooks {

	private WebDriverUtils utils;
	private WebDriver driver;
	private StartUpPage startUpPage;
	public static Properties property;
	public static Properties emailProperty;

	public static SoftAssertions softAssertions;
	
	@Before(order = 0)
	public void getProperty() throws Exception {
		property = new Efficacies().loadPropertyFile("config.properties");
		
	}

	@Before(order = 1)
	public void launchBrowser() throws Exception {
		utils = new WebDriverUtils();
		utils.initializeDriver(property.getProperty("browser"));
		driver = WebDriverUtils.getDriver();
		startUpPage = new StartUpPage(driver);
		
	}
	
	@Before(order = 2)
	public void intiateAssertions() {
		softAssertions = new SoftAssertions();
	}
	
	@After(order = 0)
	public void quitBrowser() {
		startUpPage.killDriver();
	}
	
	@After(order = 2)
	public void tearDownAssertion() {
		softAssertions.assertAll();
	}

	@After(order = 1)
	public void tearDown(Scenario scenario) {
		if (scenario.isFailed()) {
			// take screenshot:
			String screenshotName = scenario.getName().replaceAll(" ", "_");
			byte[] sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(sourcePath, "image/png", screenshotName);

		}
	}
}
