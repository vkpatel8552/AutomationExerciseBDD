Feature: Luma:- MyWishListPage Feature Test 

Background:
	Given user is on Home page 
	
Scenario: TestCase14: Remove product from MyWishList Page
	Given homepage launched with Page Title "Home Page - Magento eCommerce"
	When user clicks on Loging Link
	Then Login Page Header "Customer Login" is visible
	Then user enters "kk8989@gmail.com" and "test@123" 
	And user clicks on Login button
	Then Verify  that 'Welcome, kk patel!' is visible
	When user clicks on Men link
	Then Men Page Header "Men" is visible
	Then user scrolls to Hot Seller section
	Then "Hot Sellers" header is visible
	And user clicks on "Hero Hoodie" Product
	Then user select product size as 'M'
	Then user select product color as 'Green'
	Then user enters Qty as "1"
	Then user clicks on AddToWishList link
	Then Verify Success Message 'Hero Hoodie has been added to your Wish List. Click here to continue shopping.' is visible
	Then user clicks on remove item icon of "Hero Hoodie" Product
	Then Verify Success Message 'Hero Hoodie has been removed from your Wish List.' is visible