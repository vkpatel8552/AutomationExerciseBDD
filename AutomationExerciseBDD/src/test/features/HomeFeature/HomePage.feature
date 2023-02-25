Feature: Luma:- HomePage Feature Test 

Background:
	Given user is on Home page 

Scenario: TestCase4: Verify First Products details from Hot Seller section on HomePage
	Given homepage launched with Page Title "Home Page - Magento eCommerce"
	When user scrolls to Hot Sellers section
	Then "Hot Sellers" header is visible
	And user clicks on first product
	Then verify product basic details
		|Name|Price|Status|Code|
		|Radiant Tee|$22.00|IN STOCK|WS12|
	Then user clicks on Details Tab
	And verify product details as "So light and comfy, you'll love the Radiant Tee's organic fabric, feel, performance and style. You may never want to stop moving in this shirt."
	Then user clicks on More Information Tab
	And verify product style details
		|Style|Material|Pattern|Climate|
		|Tee|Hemp, Spandex, Organic Cotton|Solid|Indoor, Warm|
	Then user clicks on Reviews Tab
	And verify 'Customer Reviews' header shown under Reviews tab
	
Scenario: TestCase5: Add First product into cart from Hot Seller section
	Given homepage launched with Page Title "Home Page - Magento eCommerce"
	When user scrolls to Hot Sellers section
	Then "Hot Sellers" header is visible
	And user clicks on "Hero Hoodie" Product
	Then user select product size as 'S'
	Then user select product color as 'Green'
	Then user enters Qty as "3"
	And user clicks on Add to Cart Button
	Then verify message "You added Hero Hoodie to your shopping cart." should be displayed
	And verify shopping cart shows "3" items