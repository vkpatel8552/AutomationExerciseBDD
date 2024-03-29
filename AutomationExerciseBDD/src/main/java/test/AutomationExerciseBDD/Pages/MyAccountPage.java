package test.AutomationExerciseBDD.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAccountPage extends StartUpPage{

	@FindBy(css="div.panel li.welcome>span.not-logged-in")
	public WebElement defaultWelMsg;
	
	@FindBy(css="div.page-title-wrapper span")
	public WebElement myAccPageHeader;

	@FindBy(css="div[role='alert']>div.message>div")
	public WebElement newAccountCreationMsg;
	
	public MyAccountPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public MyAccountPage waitForMyAccountPageToLoad() throws Exception{
		ngHelper.waitTillPageTitleContains("My Account", maxWaitTime)
				.waitTillElementIsInvisible(defaultWelMsg, maxWaitTime);
		return PageFactory.initElements(driver, MyAccountPage.class);
	}

	public String getMyAccountPageHeader() throws Exception {
		return ngHelper.waitTillElementIsVisible(myAccPageHeader, maxWaitTime)
					   .getInnerText(myAccPageHeader).trim();
	}
	
	public String getNewUserCreationMsg() throws Exception {
		return ngHelper.waitTillElementIsVisible(newAccountCreationMsg, maxWaitTime)
					   .getInnerText(newAccountCreationMsg);
	}
}
