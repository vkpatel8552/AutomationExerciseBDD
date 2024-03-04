Feature: Luma:- LoginPage Feature Test 

Scenario: TestCase1: Login User with correct email and password
	Given user is on Home page 
	When user clicks on Loging Link
	Then Login Page Header "Customer Login" is visible
	Then user enters "kk1234@gmail.com" and "test@12345" 
	And user clicks on Login button
	Then Verify  that 'Welcome, kk patel!' is visible
	
Scenario: TestCase2: Login User with incorrect email and password
	Given user is on Home page 
	When user clicks on Loging Link
	Then Login Page Header "Customer Login" is visible
	Then user enters "kkk@gmail.com" and "test@12" 
	And user clicks on Login button
	Then Verify  Error Message 'The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.' is visible

Scenario: TestCase3: Logout User
	Given user is on Home page 
	When user clicks on Loging Link
	Then Login Page Header "Customer Login" is visible
	Then user enters "kk1234@gmail.com" and "test@12345" 
	And user clicks on Login button
	Then Verify  that 'Welcome, kk patel!' is visible
	And user clicks on Logout button
	Then verify 'Click “Write for us” link in the footer to submit a guest post' should be dispayed instead of loggedIn UserName