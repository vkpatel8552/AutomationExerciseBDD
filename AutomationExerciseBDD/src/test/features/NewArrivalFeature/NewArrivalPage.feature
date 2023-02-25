Feature: Luma:- NewArrivalPage Feature Test 

Background:
	Given user is on Home page 

Scenario: TestCase8: Verify First Products details from New Arrival Page
	Given homepage launched with Page Title "Home Page - Magento eCommerce"
	When user clicks on WhatsNew link
	Then NewArrival Page Header "What's New" is visible
	Then user scrolls to Latest Collection section
	Then "Luma's Latest" header is visible
	And user clicks on first product
	Then verify product basic details
		|Name|Price|Status|Code|
		|Phoebe Zipper Sweatshirt|$59.00|IN STOCK|WH07|
	Then user clicks on Details Tab
	And verify product details as "A sophisticated layer of warmth awaits you in our full-zip sweatshirt jacket. You'll reach for this one in any season to enjoy its sturdy exterior and plush interior."
	Then user clicks on More Information Tab
	And verify product style details
		|Style|Material|Pattern|Climate|
		|Full Zip, Sweatshirt, Hoodie|Cotton, Polyester|Solid|Cool, Indoor, Mild|
		
Scenario: TestCase9: Add Specific product into cart from Latest Collection section
	Given homepage launched with Page Title "Home Page - Magento eCommerce"
	When user clicks on WhatsNew link
	Then NewArrival Page Header "What's New" is visible
	Then user scrolls to Latest Collection section
	Then "Luma's Latest" header is visible
	And user clicks on "Zoltan Gym Tee" Product
	Then user select product size as 'XL'
	Then user select product color as 'Yellow'
	Then user enters Qty as "1"
	And user clicks on Add to Cart Button
	Then verify message "You added Zoltan Gym Tee to your shopping cart." should be displayed
	And verify shopping cart shows "1" items