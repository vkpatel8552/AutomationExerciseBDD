package test.AutomationExerciseBDD.StepDefination;

import java.util.Properties;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import test.AutomationExerciseBDD.AppHooks.ApplicationHooks;
import test.AutomationExerciseBDD.Pages.HomePage;
import test.AutomationExerciseBDD.Pages.LoginPage;
import test.AutomationExerciseBDD.Pages.StartUpPage;
import test.AutomationExerciseBDD.Utils.Efficacies;
import test.AutomationExerciseBDD.Utils.WebDriverUtils;

public class LoginStepDefination {

	private StartUpPage startUpPage;
	private HomePage homePage = new HomePage(WebDriverUtils.getDriver());
	private LoginPage loginPage = new LoginPage(WebDriverUtils.getDriver());
	Properties property;

	private WebDriver driver;

	@Given("user is on Home page")
	public void user_is_on_home_page() throws Exception {
		property = new Efficacies().loadPropertyFile("config.properties");
		driver = WebDriverUtils.getDriver();
		startUpPage = new StartUpPage(driver);
		homePage = startUpPage.navigateToPage(property.getProperty("baseURL"))
							  .waitForHomePageToLoad();
	}
	
	@When("user clicks on Loging Link")
	public void user_clicks_on_login_lnk() throws Exception {
		loginPage = homePage.clickOnLoginlnk()
				.waitForLoginPageToLoad();
	}

	@Then("Login Page Header {string} is visible")
	public void login_page_header_is_visible(String expectedPageHeader) throws Exception {
		String actualPageHeader = loginPage.getLoginPageHeader();
		
		ApplicationHooks.softAssertions.assertThat(actualPageHeader)
		   .as("Verifying Login Page Header")
		   .isEqualTo(expectedPageHeader);
	}

	@Then("user enters {string} and {string}")
	public void user_enters_username_and_password(String userName, String password) throws Exception {
		loginPage.enterUserEmail(userName)
				 .enterPassword(password);
	}

	@And("user clicks on Login button")
	public void user_clicks_on_Login_button() throws Exception {
		 loginPage.clickOnLoginBtn();
	}
	
	@Then("Verify  that {string} is visible")
	public void verify_that_is_visible(String expectedLoggedInUser) throws Exception {
		String actualLoggedInUser= homePage.waitForHomePageWithLoggedInUserToLoad()
				.getLoggedInUser();
		
		ApplicationHooks.softAssertions.assertThat(actualLoggedInUser)
		   .as("Verifying Logged In username")
		   .isEqualTo(expectedLoggedInUser);
	}

	@Then("Verify  Error Message {string} is visible")
	public void verify_errorMsg_is_visible(String errMsg) throws Exception {
		String actualErrMsg= loginPage.getLoginErrMsg();
		
		ApplicationHooks.softAssertions.assertThat(actualErrMsg)
		   .as("Verifying Logged In username")
		   .isEqualTo(errMsg);
	}

	@And("user clicks on Logout button")
	public void user_clicks_on_Logout_button() throws Exception {
		 homePage = homePage.clickOnLogoutlnk()
				 			 .waitForHomePageToLoad();
	}
	
	@Then("verify {string} should be dispayed instead of loggedIn UserName")
	public void verify_default_user_msg(String expectedUser) throws Exception {
		String actualUser = homePage.getDefaultLoggedInUser();

		ApplicationHooks.softAssertions.assertThat(actualUser)
				.as("Verifying Default User Msg after logout")
				.isEqualTo(expectedUser);
	}
}
