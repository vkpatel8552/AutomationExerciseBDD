Feature: Luma:- LoginPage Feature Test 

Scenario: TestCase1: Login User with correct email and password
	Given user is on Home page 
	When user clicks on Loging Link
	Then Login Page Header "Customer Login" is visible
	Then user enters "kk8989@gmail.com" and "test@123" 
	And user clicks on Login button
	Then Verify  that 'Welcome, kk patel!' is visible
	
Scenario: TestCase2: Login User with incorrect email and password
	Given user is on Home page 
	When user clicks on Loging Link
	Then Login Page Header "Customer Login" is visible
	Then user enters "kkk@gmail.com" and "test@12" 
	And user clicks on Login button
	Then Verify  Error Message 'Incorrect CAPTCHA' is visible

Scenario: TestCase3: Logout User
	Given user is on Home page 
	When user clicks on Loging Link
	Then Login Page Header "Customer Login" is visible
	Then user enters "kk8989@gmail.com" and "test@123" 
	And user clicks on Login button
	Then Verify  that 'Welcome, kk patel!' is visible
	And user clicks on Logout button
	Then verify 'Default welcome msg!' should be dispayed instead of loggedIn UserName