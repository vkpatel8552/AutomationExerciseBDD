package test.AutomationExerciseBDD.StepDefination;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import test.AutomationExerciseBDD.AppHooks.ApplicationHooks;
import test.AutomationExerciseBDD.Pages.HomePage;
import test.AutomationExerciseBDD.Pages.MyWishListPage;
import test.AutomationExerciseBDD.Pages.WomenPage;
import test.automationframework.Utils.WebDriverUtils;

public class WomenStepDefination {

	private HomePage homePage = new HomePage(WebDriverUtils.getDriver());
	private MyWishListPage myWishListPage = new MyWishListPage(WebDriverUtils.getDriver());
	private WomenPage womenPage = new WomenPage(WebDriverUtils.getDriver());
	
	@When("user clicks on Women link")
	public void user_clicks_on_women_link() throws Exception {
		womenPage = homePage.clickOnWomenlnk()
				            .waitForWomenPageToLoad();
	}

	@Then("Women Page Header {string} is visible")
	public void women_page_header_is_visible(String expectedPageHeader) throws Exception {
		String actualPageHeader = womenPage.getWomenPageHeader();
		
		ApplicationHooks.softAssertions.assertThat(actualPageHeader)
		   .as("Verifying Women Page Header")
		   .isEqualTo(expectedPageHeader);
	}

	@Then("user scrolls to Hot Seller section")
	public void user_scrolls_to_hot_seller_section() throws Exception {
		womenPage = womenPage.scrollToHotSellerSection();
	}

	@Then("Verify Success Message {string} is visible")
	public void verify_success_msg_add_to_wishlist(String expMsg) throws Exception {
		String actualMsg = myWishListPage.getAddToWishListSuccessMsg();
		
		ApplicationHooks.softAssertions.assertThat(actualMsg)
		   .as("Verifying Add To WishList success message")
		   .isEqualTo(expMsg);
	}
	
}
