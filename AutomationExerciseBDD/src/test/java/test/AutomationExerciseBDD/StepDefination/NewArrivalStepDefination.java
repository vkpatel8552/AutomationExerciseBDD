package test.AutomationExerciseBDD.StepDefination;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import test.AutomationExerciseBDD.AppHooks.ApplicationHooks;
import test.AutomationExerciseBDD.Pages.HomePage;
import test.AutomationExerciseBDD.Pages.NewArrivalPage;
import test.AutomationExerciseBDD.Utils.WebDriverUtils;

public class NewArrivalStepDefination {
	
	private HomePage homePage = new HomePage(WebDriverUtils.getDriver());
	private NewArrivalPage newArrivalPage = new NewArrivalPage(WebDriverUtils.getDriver());
	
	@When("user clicks on WhatsNew link")
	public void user_clicks_on_whats_new_link() throws Exception {
		newArrivalPage = homePage.clickOnWhatsNewlnk()
	    						 .waitForNewArrivalPageToLoad();
	}

	@Then("user scrolls to Latest Collection section")
	public void user_scrolls_to_latest_collection_section() throws Exception {
		newArrivalPage = newArrivalPage.scrollToLatestSection();
	}

	@Then("NewArrival Page Header {string} is visible")
	public void login_page_header_is_visible(String expectedPageHeader) throws Exception {
		String actualPageHeader = newArrivalPage.getNewArrivalPageHeader();
		
		ApplicationHooks.softAssertions.assertThat(actualPageHeader)
		   .as("Verifying New Arrival Page Header")
		   .isEqualTo(expectedPageHeader);
	}
}
