package test.AutomationExerciseBDD.Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverUtils {
	public WebDriver driver;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

	public static String browserVersion = "No Version Found";
	public static String browserName = "No Browser Name Found";

	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

	public WebDriver initializeDriver(String browserType) throws Exception {

		switch (browserType) {
		case "Chrome":
			WebDriverManager.chromedriver().setup();
			tlDriver.set(new ChromeDriver());
			break;
		case "Firefox":
			WebDriverManager.firefoxdriver().setup();
			tlDriver.set(new FirefoxDriver());
			break;
		case "Safari":
			tlDriver.set(new SafariDriver());
			break;

		default:
			WebDriverManager.chromedriver().setup();
			tlDriver.set(new ChromeDriver());
			break;
		}

		//getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		return getDriver();

	}

}
