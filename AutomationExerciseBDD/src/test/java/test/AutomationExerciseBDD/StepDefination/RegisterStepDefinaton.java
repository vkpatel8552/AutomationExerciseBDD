package test.AutomationExerciseBDD.StepDefination;

import java.util.Map;
import java.util.Random;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import test.AutomationExerciseBDD.AppHooks.ApplicationHooks;
import test.AutomationExerciseBDD.Pages.HomePage;
import test.AutomationExerciseBDD.Pages.LoginPage;
import test.AutomationExerciseBDD.Pages.MyAccountPage;
import test.AutomationExerciseBDD.Pages.RegisterUserPage;
import test.automationframework.Utils.WebDriverUtils;

public class RegisterStepDefinaton {

	private HomePage homePage = new HomePage(WebDriverUtils.getDriver());
	private LoginPage loginPage = new LoginPage(WebDriverUtils.getDriver());
	private RegisterUserPage registerUserPage = new RegisterUserPage(WebDriverUtils.getDriver());
	private MyAccountPage myAccountPage = new MyAccountPage(WebDriverUtils.getDriver());
	private Random random = new Random();  

	@When("user clicks on Create an Account Link")
	public void user_clicks_on_create_an_account_link() throws Exception {
		registerUserPage = homePage.clickOnCreateAcclnk()
								   .waitForRegisterUserPageToLoad();
	}

	@Then("user clicks on Create an Account Button under Login Page")
	public void user_clicks_on_create_account_btn() throws Exception {
		registerUserPage = loginPage.clickOnCreateAccBtn()
									.waitForRegisterUserPageToLoad();
	}
	
	@Then("{string} header is visible under Create user page")
	public void header_is_visible_under_create_user_page(String expPageHeader) throws Exception {
		String actualPageHeader = registerUserPage.getRegisterPageHeader();
		ApplicationHooks.softAssertions.assertThat(actualPageHeader)
				.as("Verifying Register User Page Header")
				.isEqualTo(expPageHeader);
	}

	@Then("user enters all personal information")
	public void user_enters_all_personal_information(DataTable dataTable) throws Exception {
		for (Map<String, String> userDetails : dataTable.asMaps()) {
			String fieldValue = null;
			for (String fieldName : userDetails.keySet()) {
				if(!fieldName.equalsIgnoreCase("Email"))
					fieldValue= userDetails.get(fieldName);
				else {
					String[] fieldValues= userDetails.get(fieldName).split("@");
					fieldValues[0] = fieldValues[0] + random.nextInt(100000);
					fieldValue = fieldValues[0] + "@" + fieldValues[1];
					System.err.println(fieldValue);
				}	
				registerUserPage.enterUserPersonalDetails(fieldName, fieldValue);
			}
		}
	}

	@And("user clicks on Create and Account Button")
	public void user_clicks_Create_Account_Btn() throws Exception {
		myAccountPage = registerUserPage.clickOnCreateAccountBtn()
										.waitForMyAccountPageToLoad();
	}
	
	@Then("user account page visible with header {string}")
	public void user_account_page_visible_with_header(String expPageHeader) throws Exception {
		String actualPageHeader = myAccountPage.getMyAccountPageHeader();
		ApplicationHooks.softAssertions.assertThat(actualPageHeader)
				.as("Verifying Register User Page Header")
				.isEqualTo(expPageHeader);
	}
	
	@Then("verify {string} message displayed")
	public void verify_message_displayed(String expMsg) throws Exception {
		String actualMsg= myAccountPage.getNewUserCreationMsg();
		
		ApplicationHooks.softAssertions.assertThat(actualMsg)
		   .as("Verifying New Account creation message on My Account Page")
		   .isEqualTo(expMsg);
	}
}
