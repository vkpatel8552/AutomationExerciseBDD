Feature: Luma:- WomenPage Feature Test 

Background:
	Given user is on Home page 

Scenario: TestCase10: Verify First Products details from Women Page
	Given homepage launched with Page Title "Home Page"
	When user clicks on Women link
	Then Women Page Header "Women" is visible
	Then user scrolls to Hot Seller section
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
	
Scenario: TestCase11: Add Specific product into cart from Women Page
	Given homepage launched with Page Title "Home Page"
	When user clicks on Women link
	Then Women Page Header "Women" is visible
	Then user scrolls to Hot Seller section
	Then "Hot Sellers" header is visible
	And user clicks on "Selene Yoga Hoodie" Product
	Then user select product size as 'M'
	Then user select product color as 'White'
	Then user enters Qty as "2"
	And user clicks on Add to Cart Button
	Then verify message "You added Selene Yoga Hoodie to your shopping cart." should be displayed
	And verify shopping cart shows "2" items
	
Scenario: TestCase12: Add Specific product into Wishlist from Women Page without login
	Given homepage launched with Page Title "Home Page"
	When user clicks on Women link
	Then Women Page Header "Women" is visible
	Then user scrolls to Hot Seller section
	Then "Hot Sellers" header is visible
	And user clicks on "Selene Yoga Hoodie" Product
	Then user select product size as 'M'
	Then user select product color as 'White'
	Then user enters Qty as "1"
	Then user clicks on Add To WishList link
	Then Verify  Error Message 'You must login or register to add items to your wishlist.' is visible
	
Scenario: TestCase13: Add Specific product into Wishlist from Women Page with login
	Given homepage launched with Page Title "Home Page"
	When user clicks on Loging Link
	Then Login Page Header "Customer Login" is visible
	Then user enters "kk1234@gmail.com" and "test@12345" 
	And user clicks on Login button
	Then Verify  that 'Welcome, kk patel!' is visible
	When user clicks on Women link
	Then Women Page Header "Women" is visible
	Then user scrolls to Hot Seller section
	Then "Hot Sellers" header is visible
	And user clicks on "Selene Yoga Hoodie" Product
	Then user select product size as 'M'
	Then user select product color as 'Purple'
	Then user enters Qty as "1"
	Then user clicks on AddToWishList link
	Then Verify Success Message 'Selene Yoga Hoodie has been added to your Wish List. Click here to continue shopping.' is visible
	