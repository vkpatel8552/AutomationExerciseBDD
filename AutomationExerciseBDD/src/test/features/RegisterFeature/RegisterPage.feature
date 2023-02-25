Feature: Luma:- RegisterPage Feature Test 

Background:
	Given user is on Home page 

Scenario: TestCase6: Register New User by clicking on Create an account link from Home Page
	Given homepage launched with Page Title "Home Page - Magento eCommerce"
	When user clicks on Create an Account Link
	Then "Create New Customer Account" header is visible under Create user page
	And user enters all personal information
		|First Name|Last Name|Email|Password|Confirm Password|
		|Rodricks|Smith|rodricksJr@gmail.com|rod@12345678|rod@12345678|
	And user clicks on Create and Account Button
	Then user account page visible with header 'My Account'
	And verify 'Thank you for registering with Fake Online Clothing Store.' message displayed
	Then Verify  that 'Welcome, Rodricks Smith!' is visible

Scenario: TestCase7: Register New User by clicking on Create an account link from Login Page
	Given homepage launched with Page Title "Home Page - Magento eCommerce"
	When user clicks on Loging Link
	Then Login Page Header "Customer Login" is visible
	Then user clicks on Create an Account Button under Login Page
	Then "Create New Customer Account" header is visible under Create user page
	And user enters all personal information
		|First Name|Last Name|Email|Password|Confirm Password|
		|Rodricks|Smith|rodricksJr@gmail.com|rod@12345678|rod@12345678|
	And user clicks on Create and Account Button
	Then user account page visible with header 'My Account'
	And verify 'Thank you for registering with Fake Online Clothing Store.' message displayed
	Then Verify  that 'Welcome, Rodricks Smith!' is visible