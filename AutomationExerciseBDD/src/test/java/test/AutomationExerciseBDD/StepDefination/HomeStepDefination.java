package test.AutomationExerciseBDD.StepDefination;

import java.util.Map;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import test.AutomationExerciseBDD.AppHooks.ApplicationHooks;
import test.AutomationExerciseBDD.Pages.HomePage;
import test.AutomationExerciseBDD.Pages.ProductDetailsPage;
import test.automationframework.Utils.WebDriverUtils;

public class HomeStepDefination {

	private HomePage homePage = new HomePage(WebDriverUtils.getDriver());
	private ProductDetailsPage productDetailsPage = new ProductDetailsPage(WebDriverUtils.getDriver());

	@Given("homepage launched with Page Title {string}")
	public void verify_home_page(String expectedPageTitle) throws Exception {
		String actualPageTitle = homePage.getHomePageTitle();
		ApplicationHooks.softAssertions.assertThat(actualPageTitle)
				.as("Verifying Home Page Title content")
				.isEqualTo(expectedPageTitle);
	}

	@When("user scrolls to Hot Sellers section")
	public void user_scrolls_to_hot_sellers_section() throws Exception {
		homePage.scrollToContentHeader();
	}

	@Then("{string} header is visible")
	public void header_is_visible(String expectedPageHeader) throws Exception {
		String actualPageHeader = homePage.getHotSellerPageHeader();
		ApplicationHooks.softAssertions.assertThat(actualPageHeader)
				.as("Verifying Home Page Header")
				.isEqualTo(expectedPageHeader);
	}

	@Then("user clicks on first product")
	public void user_clicks_on_first_product() throws Exception {
		productDetailsPage = homePage.clickOnFirstProduct()
									 .waitForProductDetailsPageToLoad();
	}

	@Then("verify product basic details")
	public void verify_product_basic_details(DataTable dataTable) throws Exception {
		for (Map<String, String> productDetails : dataTable.asMaps()) {
			
			for (String productColumn : productDetails.keySet()) {
				String actualValue = productDetailsPage.getProductBasicDetails(productColumn);
				ApplicationHooks.softAssertions.assertThat(actualValue)
						.as("Verifying ProductBasic Details for column " + productColumn)
						.isEqualTo(productDetails.get(productColumn));
			}
		}
	}

	@Then("user clicks on Details Tab")
	public void user_clicks_on_details_tab() throws Exception {
		productDetailsPage = productDetailsPage.clickOnProductDetailsTab();
	}

	@Then("verify product details as {string}")
	public void verify_product_details_as(String expProductDtlContent) throws Exception {
		String actualProductDtlContent = productDetailsPage.getProductDetails();
		ApplicationHooks.softAssertions.assertThat(actualProductDtlContent)
				.as("Verifying Product Descritpion Content")
				.isEqualTo(expProductDtlContent);
	}

	@Then("user clicks on More Information Tab")
	public void user_clicks_on_more_information_tab() throws Exception {
		productDetailsPage = productDetailsPage.clickOnProductMoreInfoTab();
	}

	@Then("verify product style details")
	public void verify_product_style_details(DataTable dataTable) throws Exception {
		for (Map<String, String> productDetails : dataTable.asMaps()) {
			
			for (String productColumn : productDetails.keySet()) {
				String actualValue = productDetailsPage.getProductInfo(productColumn);
				ApplicationHooks.softAssertions.assertThat(actualValue)
						.as("Verifying Product Advance Details for column " + productColumn)
						.isEqualTo(productDetails.get(productColumn));
			}
		}
	}

	@Then("user clicks on Reviews Tab")
	public void user_clicks_on_reviews_tab() throws Exception {
		productDetailsPage.clickOnProductReviewsTab();
	}

	@Then("verify {string} header shown under Reviews tab")
	public void verify_header_shown_under_reviews_tab(String expProductHeader) throws Exception {
		String actualProductHeader = productDetailsPage.getCustomerReviewHeader();
		ApplicationHooks.softAssertions.assertThat(actualProductHeader)
				.as("Verifying Product Customer Review Header")
				.isEqualTo(expProductHeader);
	}

	@Then("user clicks on {string} Product")
	public void user_clicks_on_product(String productName) throws Exception {
	    productDetailsPage = homePage.clickOnSpecificProduct(productName)
	    							 .waitForProductDetailsPageToLoad();
	}

	@Then("user select product size as {string}")
	public void user_select_product_size_as(String size) throws Exception {
	    productDetailsPage.selectProductSize(size);
	}

	@Then("user select product color as {string}")
	public void user_select_product_color_as(String color) throws Exception {
	    productDetailsPage.selectProductColor(color);
	}

	@Then("user enters Qty as {string}")
	public void user_enters_qty_as(String qty) throws Exception {
	    productDetailsPage.enterProductQty(qty);
	}

	@Then("user clicks on Add to Cart Button")
	public void user_clicks_on_add_to_cart_button() throws Exception {
	    productDetailsPage.clickOnAddToCart();
	}

	@Then("user clicks on Add To WishList link")
	public void user_clicks_add_to_wishlist_button_withoutLogin() throws Exception {
		productDetailsPage.clickOnAddToWishListlnkWithoutLogin()
						  .waitForLoginPageToLoad();
	}
	
	@Then("user clicks on AddToWishList link")
	public void user_clicks_add_to_wishlist_button() throws Exception {
		productDetailsPage.clickOnAddToWishListlnk()
						  .waitForMyWishListPageToLoad();
	}

	@Then("user clicks on AddToCompare link")
	public void user_clicks_add_to_compare_button() throws Exception {
		productDetailsPage.clickOnAddToCompareListlnk()
						  .waitForProductDetailsPageToLoad();
	}
	
	@Then("verify message {string} should be displayed")
	public void verify_message_should_be_displayed(String expMsg) throws Exception {
		String actMsg = productDetailsPage.getProductAddedMsg();
		ApplicationHooks.softAssertions.assertThat(actMsg)
				.as("Verifying Product Successfully added to cart msg")
				.isEqualTo(expMsg);
	}

	@Then("verify shopping cart shows {string} items")
	public void verify_shopping_cart_shows_items(String expCount) throws Exception {
		String actCount = productDetailsPage.getShoppingCartItemsCount();
		ApplicationHooks.softAssertions.assertThat(actCount)
				.as("Verifying Product Count under Shopping Cart")
				.isEqualTo(expCount);
	}

}
