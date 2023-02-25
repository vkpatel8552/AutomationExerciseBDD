package test.AutomationExerciseBDD.StepDefination;

import io.cucumber.java.en.Then;
import test.AutomationExerciseBDD.Pages.MyWishListPage;
import test.AutomationExerciseBDD.Utils.WebDriverUtils;

public class MyWishListStepDefination {

	private MyWishListPage myWishListPage = new MyWishListPage(WebDriverUtils.getDriver());
	
	@Then("user clicks on remove item icon of {string} Product")
	public void user_clicks_on_remove_item_icon(String productName) throws Exception {
		myWishListPage = myWishListPage.clickOnRemoveProductFromWishList(productName)
								  .waitForMyWishListPageToLoad();
	}
}
