package test.AutomationExerciseBDD.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MenPage extends StartUpPage {

	@FindBy(css="div.panel li.welcome>span.not-logged-in")
	public WebElement defaultWelMsg;
	
	@FindBy(css="div.content-heading>h2.title")
	public WebElement contentHeader;

	@FindBy(css="div.page-title-wrapper span")
	public WebElement menPageHeader;

	public MenPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public MenPage waitForMenPageToLoad() throws Exception{
		ngHelper.waitTillPageTitleContains("Men", maxWaitTime)
				.waitTillElementIsVisible(menPageHeader, maxWaitTime);
		return PageFactory.initElements(driver, MenPage.class);
	}	
	
	public MenPage scrollToHotSellerSection() throws Exception {
		ngHelper.jScriptScroll(contentHeader)
				.waitTillElementIsVisible(contentHeader, maxWaitTime);
		return PageFactory.initElements(driver, MenPage.class);
	}

	public String getMenPageHeader() throws Exception {
		return ngHelper.waitTillElementIsVisible(menPageHeader, maxWaitTime)
					   .getInnerText(menPageHeader).trim();
	}
	
}
