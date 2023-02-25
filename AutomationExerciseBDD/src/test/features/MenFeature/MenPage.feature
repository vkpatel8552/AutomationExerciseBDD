Feature: Luma:- MenPage Feature Test 

Background:
	Given user is on Home page 

Scenario: TestCase15: Add Specific product into CompareList from Men Page
	Given homepage launched with Page Title "Home Page - Magento eCommerce"
	When user clicks on Men link
	Then Men Page Header "Men" is visible
	Then user scrolls to Hot Seller section
	Then "Hot Sellers" header is visible
	And user clicks on "Hero Hoodie" Product
	Then user select product size as 'M'
	Then user select product color as 'Black'
	Then user enters Qty as "1"
	Then user clicks on AddToCompare link
	Then Verify Success Message 'You added product Hero Hoodie to the comparison list.' is visible
	