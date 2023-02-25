package test.AutomationExerciseBDD.StepDefination;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import test.AutomationExerciseBDD.AppHooks.ApplicationHooks;
import test.AutomationExerciseBDD.Pages.HomePage;
import test.AutomationExerciseBDD.Pages.MenPage;
import test.AutomationExerciseBDD.Utils.WebDriverUtils;

public class MenStepDefination {

	private HomePage homePage = new HomePage(WebDriverUtils.getDriver());
	private MenPage menPage = new MenPage(WebDriverUtils.getDriver());
	
	@When("user clicks on Men link")
	public void user_clicks_on_men_link() throws Exception {
		menPage = homePage.clickOnMenlnk()
	            .waitForMenPageToLoad();
	}

	@Then("Men Page Header {string} is visible")
	public void men_page_header_is_visible(String expectedPageHeader) throws Exception {
		String actualPageHeader = menPage.getMenPageHeader();
		
		ApplicationHooks.softAssertions.assertThat(actualPageHeader)
		   .as("Verifying Men Page Header")
		   .isEqualTo(expectedPageHeader);
	}

}
