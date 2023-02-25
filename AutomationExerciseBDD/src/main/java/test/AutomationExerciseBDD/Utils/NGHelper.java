package test.AutomationExerciseBDD.Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NGHelper {

	public WebDriver driver;
	public static boolean cookie = false;

	public static Integer scriptTimeOut = 0;

	private String toggleCheck = "input[aria-label='<<LABEL>>'].toggle:checked";
	private String toggleControl = "input[aria-label='<<LABEL>>']";

	/**
	 * @param driver
	 */
	public NGHelper(WebDriver driver) {
		this.driver = driver;
		if (scriptTimeOut == 0) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		}
		if (scriptTimeOut == 180) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(180));
		}
	}

	/**
	*
	*/
	public void clmScriptTimeOut() {
		setScriptTimeOut(180);
	}

	/**
	 * @return
	 */
	public static Integer getScriptTimeOut() {
		return scriptTimeOut;
	}

	/**
	 * @param scriptTimeOut
	 */
	public static void setScriptTimeOut(Integer scriptTimeOut) {
		NGHelper.scriptTimeOut = scriptTimeOut;
	}

	/**
	 * Method to get List of elements matching the specified locator
	 * 
	 * @param byelem Which should be a valid locator for e.g. By.xpath("//tr//td"),
	 *               By cssSelector("tr&#62;td"), By.id("validID"), By.Name("Valid
	 *               Name") etc.
	 * @return List of Web Elements matching the Locator
	 */
	public List<WebElement> findTheElements(By byelem) {
		return driver.findElements(byelem);
	}

	/**
	 * Method to Enable/Disable toggle button
	 *
	 * @param toggleLabel Which should be a label of the toggle button
	 * @return new instance of NGHelper
	 * @throws Exception
	 */
	public NGHelper toggleAction(String toggleLabel) throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			WebElement elem = driver
					.findElement(By.cssSelector(toggleCheck.replace("<<LABEL>>", toggleLabel).toString()));
			elem = driver.findElement(By.cssSelector(toggleControl.replace("<<LABEL>>", toggleLabel).toString()));
			js.executeScript("arguments[0].click();", elem);
			return new NGHelper(driver);
		} catch (Exception ex) {
			WebElement elem = driver
					.findElement(By.cssSelector(toggleControl.replace("<<LABEL>>", toggleLabel).toString()));
			js.executeScript("arguments[0].click();", elem);
			return new NGHelper(driver);
		}
	}

	/**
	 * Method to perform click action using java script Note: This method should be
	 * used only if the click is not performing any action
	 *
	 * @param elem Which should be a WebElement created using @FindBy annotations
	 * @return new instance of NGHelper
	 * @throws Exception
	 */
	public NGHelper jsClick(WebElement elem) throws Exception {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", elem);
		return new NGHelper(driver);
	}

	/**
	 * Method to perform click action on specified web element
	 *
	 * @param elem Which should be a WebElement created using @FindBy annotations
	 * @return new instance of NGHelper
	 * @throws Exception
	 */
	public NGHelper click(WebElement elem) throws Exception {
		if (((RemoteWebDriver) driver).getCapabilities().getBrowserName().equalsIgnoreCase("safari")) {
			jsClick(elem);
			return new NGHelper(driver);
		}
		elem.click();
		return new NGHelper(driver);
	}
	
	public NGHelper click(By locator) throws Exception {
		driver.findElement(locator).click();
		return new NGHelper(driver);
	}

	/**
	 * Method to Send Keys (only Strings) to a Text Field.
	 *
	 * @param elem Which should be a WebElement created using @FindBy annotations
	 * @param str  Which should be a String to be sent to the specified WebElement
	 * @return new instance of NGHelper
	 * @throws Exception
	 */
	public NGHelper sendKeysTo(WebElement elem, String str) throws Exception {
		elem.sendKeys(str);
		return new NGHelper(driver);
	}

	/**
	 * Method to Navigate to specified URL
	 *
	 * @param url Which should be a String
	 * @return new instance of NGHelper
	 * @throws Exception
	 */
	public NGHelper NavigateTo(String url) throws Exception {
		driver.get(url);
		return new NGHelper(driver);
	}

	/**
	 * Method to wait for the specified no of seconds
	 *
	 * @param timeval which should be a Double value e.g. 2.5,2,3.5 etc
	 * @return new instance of NGHelper
	 * @throws Exception
	 */
	public NGHelper sleepWait(double timeval) throws Exception {
		Double d = timeval * 1000;
		Integer intTime = d.intValue();
		Thread.sleep(intTime);
		return new NGHelper(driver);
	}

	/**
	 * Method to wait till the specified WebElement is Clickable
	 *
	 * @param elem    Which should be a WebElement created using @FindBy Annotations
	 * @param timeval Which should be a integer value
	 * @return new instance of NGHelper
	 * @throws Exception
	 */
	public NGHelper waitTillElementIsClickable(WebElement elem, int timeval) throws Exception {
		ExecutionTimer.startTimer();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeval));
		wait.until(ExpectedConditions.elementToBeClickable(elem));
		ExecutionTimer.endTimer();
		return new NGHelper(driver);
	}

	/**
	 * Method to wait till the specified WebElement is Visisble
	 *
	 * @param elem    Which should be a WebElement created using @FindBy Annotations
	 * @param timeval Which should be a integer value
	 * @return new instance of NGHelper
	 * @throws Exception
	 */
	public NGHelper waitTillElementIsVisible(WebElement elem, int timeval) throws Exception {
		ExecutionTimer.startTimer();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeval));
		wait.until(ExpectedConditions.visibilityOf(elem));
		ExecutionTimer.endTimer();
		return new NGHelper(driver);
	}

	/**
	 * Method to wait till the specified WebElement has the specified attribute and
	 * attribute value
	 *
	 * @param element        Which should be a WebElement created using @FindBy
	 *                       annotation
	 * @param attributeType  Which should be a String of attribute type for the
	 *                       specified WebElement
	 * @param attributeValue Which should be a String of attribute value for the
	 *                       specified WebElement
	 * @param timeval        Which should be a integer value
	 * @return new instance of NGHelper
	 * @throws Exception
	 */
	public NGHelper waitTillAttributeValue(WebElement element, String attributeType, String attributeValue, int timeval)
			throws Exception {
		ExecutionTimer.startTimer();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeval));
		wait.until(ExpectedConditions.attributeContains(element, attributeType, attributeValue));
		ExecutionTimer.endTimer();
		return new NGHelper(driver);
	}

	/**
	 * Method to wait till the progress bar reaches from 0 to 100%
	 *
	 * @param elem     Which should be a WebElement created using @FindBy annotation
	 * @param waitTime Which should be an integer (wait time till progress bar
	 *                 appears)
	 * @param endTime  Which should be an integer (wait time till progress bar
	 *                 reaches 100%)
	 * @return new instance of NGHelper
	 * @throws Exception
	 */
	public NGHelper waitTillProgressBarIsFull(WebElement elem, double waitTime, int endTime) throws Exception {
		try {
			ExecutionTimer.startTimer();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(endTime));
			sleepWait(waitTime);
			if (elem.isDisplayed() == true) {
				sleepWait(waitTime);
				wait.until(ExpectedConditions.invisibilityOf(elem));
			}
			ExecutionTimer.endTimer();
			return new NGHelper(driver);
		} catch (Exception e) {
			ExecutionTimer.endTimer();
			return new NGHelper(driver);
		}
	}

	/**
	 * Method to wait till the specified WebElement is Invisisble
	 *
	 * @param elem    Which should be a WebElement created using @FindBy Annotations
	 * @param timeval Which should be a integer value
	 * @return new instance of NGHelper
	 * @throws Exception
	 */
	public NGHelper waitTillElementIsInvisible(WebElement elem, int timeval) throws Exception {
		ExecutionTimer.startTimer();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeval));
		wait.until(ExpectedConditions.invisibilityOf(elem));
		ExecutionTimer.endTimer();
		return new NGHelper(driver);
	}

	/**
	 * Method to perform Drag and Drop
	 *
	 * @param source      Which should be a WebElement created using @FindBy
	 *                    annotation
	 * @param destination Which should be a WebElement created using @FindBy
	 *                    annotation
	 * @return new instance of NGHelper
	 * @throws Exception
	 */
	public NGHelper DragAndDropJS(WebElement source, WebElement destination) throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(
				"function createEvent(typeOfEvent) " + "{\n" + "var event =document.createEvent(\"CustomEvent\");\n"
						+ "event.initCustomEvent(typeOfEvent,true, true, null);\n" + "event.dataTransfer = {\n"
						+ "data: {},\n" + "setData: function (key, value) {\n" + "this.data[key] = value;\n" + "},\n"
						+ "getData: function (key) {\n" + "return this.data[key];\n" + "}\n" + "};\n"
						+ "return event;\n" + "}\n" + "\n" + "function dispatchEvent(element, event,transferData) "
						+ "{\n" + "if (transferData !== undefined) " + "{\n" + "event.dataTransfer = transferData;\n"
						+ "}\n" + "if (element.dispatchEvent) {\n" + "element.dispatchEvent(event);\n" + "} "
						+ "else if (element.fireEvent) {\n" + "element.fireEvent(\"on\" + event.type, event);\n" + "}\n"
						+ "}\n" + "\n" + "function simulateHTML5DragAndDrop(element, destination) {\n"
						+ "var dragStartEvent =createEvent('dragstart');\n"
						+ "dispatchEvent(element, dragStartEvent);\n" + "var dropEvent = createEvent('drop');\n"
						+ "dispatchEvent(destination, dropEvent,dragStartEvent.dataTransfer);\n"
						+ "var dragEndEvent = createEvent('dragend');\n"
						+ "dispatchEvent(element, dragEndEvent,dropEvent.dataTransfer);\n" + "}\n" + "\n"
						+ "var source = arguments[0];\n" + "var destination = arguments[1];\n"
						+ "simulateHTML5DragAndDrop(source,destination);",
				source, destination);
		sleepWait(1);
		return new NGHelper(driver);
	}

	/**
	 * Method to Perform scroll and click action on the specified WebElement
	 *
	 * @param elem Which should be a WebElement created using @FindBy annotation
	 * @return new instance of NGHelper
	 * @throws Exception
	 */
	public NGHelper jScriptScrollAndClick(WebElement elem) throws Exception {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elem);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", elem);
		return new NGHelper(driver);
	}

	public static String readPropFile(String filename, String Key) throws Exception {
		Properties prop = new Properties();
		InputStream input = null;
		String value;
		try {
			input = new FileInputStream(filename);
			prop.load(input);
			value = prop.getProperty(Key).toString();
			return value;
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}

	}

	/**
	 * Method to move to the specified WebElement and perform click
	 *
	 * @param elem Which should be a WebElement created using @FindBy annotation
	 * @return new instance of NGHelper
	 * @throws Exception
	 */
	public NGHelper moveToElementAndClick(WebElement elem) throws Exception {
		Actions acc = new Actions(driver);
		acc.moveToElement(elem).click().build();
		acc.perform();
		return new NGHelper(driver);
	}

	/**
	 * Method to move to specified locator and perform click
	 *
	 * @param byelem Which should be a Locator e.g. By.xpath("//tr//td"),
	 *               By.cssSelector("tr&#62;td") etc.
	 * @return new instance of NGHelper
	 * @throws Exception
	 */
	public NGHelper moveToElementAndClick(By byelem) throws Exception {
		Actions acc = new Actions(driver);
		WebElement elem = findTheElement(byelem);
		acc.moveToElement(elem).click().build();
		acc.perform();
		return new NGHelper(driver);
	}

	/**
	 * Method to Send Keys as s string
	 *
	 * @param textString Which should be a String
	 * @return new instance of NGHelper
	 * @throws Exception
	 */
	public NGHelper sendTextKeys(String textString) throws Exception {
		Actions acc = new Actions(driver);
		acc.sendKeys(textString).build().perform();
		return new NGHelper(driver);
	}

	/**
	 * Method to Select combo option
	 *
	 * @param comboElement Which should be a locator e.g. By.xpath("//tr//td"),
	 *                     By.cssSelector("tr&#62;td") etc.
	 * @param option       Which should be a String
	 * @return new instance of NGHelper
	 * @throws Exception
	 * @deprecated use click and SelectComboOption methods.
	 */
	@Deprecated
	public NGHelper selectOptionsUsingKeys(By comboElement, String option) throws Exception {
		findTheElement(comboElement).click();
		sleepWait(0.75);
		sendTextKeys(option);
		sendBoardKeys(Keys.ENTER);
		sendBoardKeys(Keys.ESCAPE);
		sendBoardKeys(Keys.ESCAPE);
		sleepWait(0.25);
		return new NGHelper(driver);
	}

	/**
	 * Method to Send Keys like ENTER, ESCAPE, TAB, SPACE etc.
	 *
	 * @param key Which should be a Key Object e.g. Keys.ENTER, Keys.ESCAPE,
	 *            Keys.SPACE etc.
	 * @return new instance of NGHelper
	 * @throws Exception
	 */
	public NGHelper sendBoardKeys(Keys key) throws Exception {
		Actions acc = new Actions(driver);
		acc.sendKeys(key).build().perform();
		return new NGHelper(driver);
	}

	/**
	 * Method to get the WebElement from a specified locator
	 *
	 * @param byElem Which should be a locator e.g.
	 *               By.xpath("//input[@name='NAME']"), By.class("new"),
	 *               By.Tagname("i") etc.
	 * @return WebElement of the specified locator
	 * @throws Exception
	 */
	public WebElement findTheElement(By byElem) throws Exception {
		WebElement elem = driver.findElement(byElem);
		return elem;
	}

	/**
	 * Method to click a WebElement matching the specified locator
	 *
	 * @param byElem Which should be a locator e.g.
	 *               By.xpath("//input[@name='NAME']"), By.class("new"),
	 *               By.Tagname("i") etc.
	 * @return new instance on NGHelper
	 * @throws Exception
	 */
	public NGHelper clickBy(By byElem) throws Exception {
		if (((RemoteWebDriver) driver).getCapabilities().getBrowserName().equalsIgnoreCase("safari")) {
			jsClick(findTheElement(byElem));
			return new NGHelper(driver);
		}
		findTheElement(byElem).click();
		return new NGHelper(driver);
	}

	/**
	 * Method to Click a Drop Down box matching the specified locator
	 *
	 * @param byElem Which should be a locator e.g.
	 *               By.xpath("//input[@name='NAME']"), By.class("new"),
	 *               By.Tagname("i") etc.
	 * @return new instance of NGHelper
	 * @throws Exception
	 */
	public NGHelper clickComboBy(By byElem) throws Exception {
		jsClick(findTheElement(byElem));
		return new NGHelper(driver);
	}

	/**
	 * Method to click and Send keys to a specified WebElement
	 *
	 * @param elem   Which should be a WebElement created using @FindBy annotation
	 * @param string Which should be a String
	 * @return new instance of NGHelper
	 * @throws Exception
	 */
	public NGHelper clickAndSendkeys(WebElement elem, String string) throws Exception {
		jsClick(elem);
		sleepWait(0.5);
		elem.sendKeys(string);
		return new NGHelper(driver);
	}

	/**
	 * Method to click and clear to a specified inputBox Element
	 *
	 * @param elem   Which should be a WebElement created using @FindBy annotation
	 * @return new instance of NGHelper
	 * @throws Exception
	 */
	public NGHelper clickAndClear(WebElement elem) throws Exception {
		jsClick(elem);
		sleepWait(0.5);
		elem.clear();
		return new NGHelper(driver);
	}
	
	/**
	 * Method to perform click and Send keys to the specified locator
	 *
	 * @param byElem Which should be a Locator e.g.
	 *               By.xpath("//label[@aria-label='Product code']/../input"),
	 *               By.cssSelector("label[value='New value']&#62;input") etc
	 * @param string Which should be a String to send to the specified locator
	 *               element
	 * @return new instance of NGHelper
	 * @throws Exception
	 */
	public NGHelper clickAndSendkeys(By byElem, String string) throws Exception {
		jsClick(findTheElement(byElem));
		sleepWait(0.5);
		findTheElement(byElem).sendKeys(string);
		return new NGHelper(driver);
	}

	/**
	 * Method to retrieve innerHTML of the specified WebElement (Note: To be used
	 * only if the getAttribute("value") is not working)
	 *
	 * @param elem Which should be a WebElement Created using @FindBy annotation
	 * @return value in the form of string for the specified WebElement
	 * @throws Exception
	 */
	public String retrieveValueJS(WebElement elem) throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("return arguments[0].value", elem).toString();
	}

	/**
	 * Method to perform mouse hover on specified WebElement
	 *
	 * @param elem Which should be a WebElement created using @FindBy annotation
	 * @return new instance of NGHelper
	 * @throws Exception
	 */
	public NGHelper mouseHover(WebElement elem) throws Exception {
		Actions acc = new Actions(driver);
		acc.moveToElement(elem).build().perform();
		return new NGHelper(driver);
	}
	
	public NGHelper mouseHover(By locator) throws Exception {
		WebElement elem= driver.findElement(locator);
		Actions acc = new Actions(driver);
		acc.moveToElement(elem).build().perform();
		return new NGHelper(driver);
	}

	/**
	 * Method to scroll to the specified WebElement
	 *
	 * @param elem Which should be a WebElement created using @FindBy annotation
	 * @return new instance of NGHelper
	 * @throws Exception
	 */
	public NGHelper jScriptScroll(WebElement elem) throws Exception {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elem);
		return new NGHelper(driver);
	}

	/**
	 * Method to check whether the specified WebElement is Visible
	 *
	 * @param element Which should be a WebElement created using @Findby annotation
	 * @return Boolean value true/false
	 */
	public Boolean checkVisibility(WebElement element) {
		boolean visible = false;
		try {

			if (element.isDisplayed() == true) {
				visible = true;
			}
		} catch (NoSuchElementException noexcep) {
			visible = false;
		}
		return visible;
	}

	/**
	 * Method to check whether the specified WebElement is Selected
	 *
	 * @param element Which should be a WebElement created using @Findby annotation
	 * @return Boolean value true/false
	 */
	public Boolean checkSelected(WebElement element) {
		boolean selected = false;
		try {

			if (element.isSelected() == true) {
				selected = true;
			}
		} catch (NoSuchElementException noexcep) {
			selected = false;
		}
		return selected;
	}

	/**
	 * Method to check whether the specified WebElement is Enabled
	 *
	 * @param element Which should be a WebElement created using @Findby annotation
	 * @return Boolean value true/false
	 */
	public Boolean checkEnabled(WebElement element) {
		boolean selected = false;
		try {

			if (element.isEnabled() == true) {
				selected = true;
			}
		} catch (NoSuchElementException noexcep) {
			selected = false;
		}
		return selected;
	}

	/**
	 * Method to select drop down list option with specified option
	 *
	 * @param comboOption Which should be a String
	 * @return new instance of NGHelper
	 * @throws Exception
	 */
	public NGHelper selectComboOption(String comboOption) throws Exception {
		jsClick(findTheElement(By.xpath("(//div[normalize-space()=" + "'" + comboOption + "'" + "])"
				+ "[count(//div[normalize-space()=" + "'" + comboOption + "'" + "])]")));
		return new NGHelper(driver);
	}

	/**
	 * Method to get the time taken by application to load, visibility of an
	 * Element, Dismissal of Progress bar, Enabling of an Element.
	 *
	 * @return time as long
	 */
	public long getTotalAppTime() {
		return ExecutionTimer.returnTime();
	}

	/**
	 * Method to wait till the specified WebElement is selected
	 *
	 * @param elem    Which should be a WebElement created using @FindBy annotation
	 * @param timeval Which should be an integer value
	 * @return new instance of NGHelper
	 * @throws Exception
	 */
	public NGHelper waitTillElementIsSelected(WebElement elem, int timeval) throws Exception {
		ExecutionTimer.startTimer();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeval));
		wait.until(ExpectedConditions.elementToBeSelected(elem));
		ExecutionTimer.endTimer();
		return new NGHelper(driver);
	}

	/**
	 * Method to wait till the WebElement matching the specified locator is visible
	 *
	 * @param byElem  Which should be a locator e.g.
	 *                By.xpath("//input[@name='NAME']"), By.class("new"),
	 *                By.Tagname("i") etc.
	 * @param timeval Which should be an integer
	 * @return new instance of NGHelper
	 * @throws Exception
	 */
	public NGHelper waitTillElementIsVisible(By byElem, int timeval) throws Exception {
		ExecutionTimer.startTimer();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeval));
		wait.until(ExpectedConditions.visibilityOfElementLocated(byElem));
		ExecutionTimer.endTimer();
		return new NGHelper(driver);
	}

	/**
	 * Method to wait till the WebElement matching the specified locator is
	 * invisible
	 *
	 * @param byElem  Which should be a locator e.g.
	 *                By.xpath("//input[@name='NAME']"), By.class("new"),
	 *                By.Tagname("i") etc.
	 * @param timeval Which should be an integer
	 * @return new instance of NGHelper
	 * @throws Exception
	 */
	public NGHelper waitTillElementIsInvisible(By byElem, int timeval) throws Exception {
		ExecutionTimer.startTimer();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeval));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(byElem));
		ExecutionTimer.endTimer();
		return new NGHelper(driver);
	}

	/**
	 * Method to wait till the WebElement matching the specified locator is
	 * clickable
	 *
	 * @param byElem  Which should be a locator e.g.
	 *                By.xpath("//input[@name='NAME']"), By.class("new"),
	 *                By.Tagname("i") etc.
	 * @param timeval Which should be an integer
	 * @return new instance of NGHelper
	 * @throws Exception
	 */
	public NGHelper waitTillElementIsClickable(By byElem, int timeval) throws Exception {
		ExecutionTimer.startTimer();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeval));
		wait.until(ExpectedConditions.elementToBeClickable(byElem));
		ExecutionTimer.endTimer();
		return new NGHelper(driver);
	}

	/**
	 * Method to wait till the WebElement matching the specified locator is selected
	 *
	 * @param byElem  Which should be a locator e.g.
	 *                By.xpath("//input[@name='NAME']"), By.class("new"),
	 *                By.Tagname("i") etc.
	 * @param timeval Which should be an integer
	 * @return new instance of NGHelper
	 * @throws Exception Exception
	 */
	public NGHelper waitTillElementIsSelected(By byElem, int timeval) throws Exception {
		ExecutionTimer.startTimer();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeval));
		wait.until(ExpectedConditions.elementToBeSelected(byElem));
		ExecutionTimer.endTimer();
		return new NGHelper(driver);
	}

	/**
	 * Method to select the combo option using visible text (Used for non angular
	 * combo with select tag)
	 *
	 * @param comboElement Which should be a WebElement created using @Findby
	 *                     annotation
	 * @param visibleText  Which should be a String.
	 * @return new instance of NGHelper
	 * @throws Exception
	 */
	public NGHelper selectComboByText(WebElement comboElement, String visibleText) throws Exception {
		Select select = new Select(comboElement);
		select.selectByVisibleText(visibleText);
		return new NGHelper(driver);
	}

	/**
	 * Method to select the combo option using value (Used for non angular combo
	 * with select tag)
	 *
	 * @param comboElement Which should be a WebElement created using @Findby
	 *                     annotation
	 * @param visibleText  Which should be a String.
	 * @return new instance of NGHelper
	 * @throws Exception
	 */
	public NGHelper selectComboByValue(WebElement comboElement, String value) throws Exception {
		Select select = new Select(comboElement);
		select.selectByValue(value);
		return new NGHelper(driver);
	}

	/**
	 * Method to select the combo option using Index of the Option (Used for non
	 * angular combo with select tag)
	 *
	 * @param comboElement Which should be a WebElement created using @Findby
	 *                     annotation
	 * @param visibleText  Which should be an Integer.
	 * @return new instance of NGHelper
	 * @throws Exception
	 */
	public NGHelper selectComboByIndex(WebElement comboElement, Integer index) throws Exception {
		Select select = new Select(comboElement);
		select.selectByIndex(index);
		return new NGHelper(driver);
	}

	/**
	 * Method to Wait till the Alert is displayed
	 *
	 * @param timeval Which should be an Integer
	 * @return new instance of NGHelper
	 * @throws Exception
	 */
	public NGHelper waitTillAlertIsDisplayed(Integer timeval) throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeval));
		wait.until(ExpectedConditions.alertIsPresent());
		return new NGHelper(driver);
	}

	/**
	 * Method to accept the alert pop up
	 *
	 * @return new instance of NGHelper
	 * @throws Exception
	 */
	public NGHelper acceptAlert() throws Exception {
		Alert alert = driver.switchTo().alert();
		alert.accept();
		return new NGHelper(driver);
	}

	/**
	 * Method to dismiss the alert pop up
	 *
	 * @return new instance of NGHelper
	 * @throws Exception
	 */
	public NGHelper dismissAlert() throws Exception {
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
		return new NGHelper(driver);
	}

	/**
	 * Method to move an element to specified co-ordinates
	 *
	 * @param element     Which should be a WebElement
	 * @param xcoordinate Which should be an integer
	 * @param Ycoordinate Which should be an integer
	 * @return new instance of NGHelper
	 * @throws Exception
	 */
	public NGHelper moveElementToOrdinates(WebElement element, Integer xcoordinate, Integer Ycoordinate)
			throws Exception {
		Point point = element.getLocation();
		Integer xcord = point.getX();
		Integer ycord = point.getY();
		Actions actions = new Actions(driver);
		actions.clickAndHold().moveByOffset(xcord + xcoordinate, ycord + Ycoordinate).release().build().perform();
		return new NGHelper(driver);
	}

	/**
	 * Method to Execute JQuery which returns a String
	 *
	 * @param jqueryScript Which should be a String
	 * @return String which is obtained after executing jqueryScript
	 * @throws Exception
	 */
	public String returnStringJQuery(String jqueryScript) throws Exception {
		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
		return (String) javascriptExecutor.executeScript(jqueryScript);
	}

	/**
	 * Method to Execute JQuery which returns a WebElement
	 *
	 * @param jqueryScript Which should be a String
	 * @return WebElement which is obtained after executing jqueryScript
	 * @throws Exception
	 */
	public WebElement returnWebElementUsingJQuery(String jqueryScript) throws Exception {
		return (WebElement) ((JavascriptExecutor) driver).executeScript(jqueryScript);
	}

	/**
	 * Method to Execute JQuery which returns a List of WebElements
	 *
	 * @param jqueryScript Which should be a String
	 * @return List of WebElements matching the jqueryScript
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<WebElement> returnWebElementsListUsingJQuery(String jqueryScript) throws Exception {
		return (List<WebElement>) ((JavascriptExecutor) driver).executeScript(jqueryScript);
	}

	/**
	 * Method to Execute JQuery which returns an Object
	 *
	 * @param jqueryScript Which should be a String
	 * @return Object which is matching the jqueryScript
	 * @throws Exception
	 */
	public Object returnObjectJQuery(String jqueryScript) throws Exception {
		return (Object) ((JavascriptExecutor) driver).executeScript(jqueryScript);
	}

	/**
	 * Method to wait till the Locator has the Specified innerText
	 *
	 * @param byElem    Which should be a locator e.g.
	 *                  By.xpath("//input[@name='NAME']"), By.class("new"),
	 *                  By.Tagname("i") etc.
	 * @param innerText Which should be a String.
	 * @param timeval
	 * @return
	 * @throws Exception
	 */
	public NGHelper waitTillTextIsVisible(By byElem, String innerText, int timeval) throws Exception {
		ExecutionTimer.startTimer();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeval));
		wait.until(ExpectedConditions.textToBePresentInElementLocated(byElem, innerText));
		ExecutionTimer.endTimer();
		return new NGHelper(driver);
	}

	/**
	 * Method to wait till the Locator has the Specified innerText
	 *
	 * @param byElem    Which should be a locator e.g.
	 *                  By.xpath("//input[@name='NAME']"), By.class("new"),
	 *                  By.Tagname("i") etc.
	 * @param innerText Which should be a String.
	 * @param timeval   which should be an Integer
	 * @return new instance of NGHelper;
	 * @throws Exception
	 */
	public NGHelper waitTillTextIsInvisible(By byElem, String innerText, int timeval) throws Exception {
		ExecutionTimer.startTimer();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeval));
		wait.until(ExpectedConditions.invisibilityOfElementWithText(byElem, innerText));
		ExecutionTimer.endTimer();
		return new NGHelper(driver);
	}

	/**
	 * Method to wait till the WebElement has the Specified innerText
	 *
	 * @param elem      which should be a WebElement created using @Findby
	 *                  annotation
	 * @param innerText Which should be a String.
	 * @param timeval   which should be an Integer
	 * @return new instance of NGHelper;
	 * @throws Exception
	 */
	public NGHelper waitTillTextIsVisible(WebElement elem, String innerText, int timeval) throws Exception {
		ExecutionTimer.startTimer();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeval));
		wait.until(ExpectedConditions.textToBePresentInElement(elem, innerText));
		ExecutionTimer.endTimer();
		return new NGHelper(driver);
	}
	
	/**
	 * Method to wait till page title matches
	 *
	 * @param pageTitle expected title of page
	 * @param timeval   which should be an Integer
	 * @return new instance of NGHelper;
	 * @throws Exception
	 */
	public NGHelper waitTillPageTitleMatches(String pageTitle, int timeval) throws Exception{
		ExecutionTimer.startTimer();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeval));
		wait.until(ExpectedConditions.titleIs(pageTitle));
		ExecutionTimer.endTimer();
		return new NGHelper(driver);
	}
	
	/**
	 * Method to wait till page title contains
	 *
	 * @param pageTitle expected title of page
	 * @param timeval   which should be an Integer
	 * @return new instance of NGHelper;
	 * @throws Exception
	 */
	public NGHelper waitTillPageTitleContains(String pageTitle, int timeval) throws Exception{
		ExecutionTimer.startTimer();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeval));
		wait.until(ExpectedConditions.titleContains(pageTitle));
		ExecutionTimer.endTimer();
		return new NGHelper(driver);
	}

	/**
	 * Method to get the title of the browser window
	 *
	 * @return Title of the current webpage
	 * @throws Exception
	 */
	public String getTitle() throws Exception {
		return driver.getTitle();
	}

	/**
	 * Method to get the Current URL
	 *
	 * @return Current URL of the WebPage
	 * @throws Exception
	 */
	public String getCurrentURL() throws Exception {
		return driver.getCurrentUrl();
	}

	/**
	 * Method to get all the window handles of the browser instances
	 *
	 * @return All the window handle in the form of Set of String
	 * @throws Exception
	 */
	public Set<String> getAllWindowHandles() throws Exception {
		return driver.getWindowHandles();
	}

	/**
	 * Method to switch to the specified window handle
	 *
	 * @param windowHandle Which should be a String
	 * @return New instance of NgHelper
	 * @throws Exception
	 */
	public NGHelper switchToWindow(String windowHandle) {
		driver.switchTo().window(windowHandle);
		return new NGHelper(driver);
	}
	
	public NGHelper switchToFrameInstance(int instance) {
		driver.switchTo().frame(instance);
		return new NGHelper(driver);
	}
	
	public NGHelper switchToFrameID(int id) {
		driver.switchTo().frame(id);
		return new NGHelper(driver);
	}
	
	public NGHelper switchToDefaultContent() {
		driver.switchTo().defaultContent();
		return new NGHelper(driver);
	}

	/**
	 * @param element
	 * @return
	 * @throws Exception
	 */
	public NGHelper submit(WebElement element) throws Exception {
		WebElement elem = element;
		elem.submit();
		return new NGHelper(driver);
	}

	/**
	 * @param byElem
	 * @return
	 * @throws Exception
	 */
	public NGHelper submit(By byElem) throws Exception {
		WebElement elem = findTheElement(byElem);
		elem.submit();
		return new NGHelper(driver);
	}

	/**
	 * Method to maximise Window
	 * @return
	 */
	public NGHelper maximizeWindow() {
		driver.manage().window().maximize();
		return new NGHelper(driver);
	}
	
	public NGHelper navigateTo(String url) {
		driver.get(url);
		return new NGHelper(driver);
	}
	
	public String getInnerText(WebElement ele) throws Exception{
		return ele.getText();
	}
	
	public String getInnerText(By locator) throws Exception{
		return driver.findElement(locator).getText();
	}
}
