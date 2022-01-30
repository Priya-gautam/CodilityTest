package CodilityTest.automationutils;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.apache.commons.lang3.StringUtils;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;
import static CodilityTest.automationutils.ObjectFileReader.getElementFromFile;

public class GetPage extends BaseUi {
	protected WebDriver webdriver;
	String pageName;

	public GetPage(WebDriver driver, String pageName) {
		super(driver, pageName);
		this.webdriver = driver;
		this.pageName = pageName;
	}

	protected boolean isElementDisplayed(String elementName) throws NoSuchElementException {
		boolean result = wait.waitForElementToBeVisible(element(elementName)).isDisplayed();
		assertTrue(result, "ASSERT FAILED: element '" + elementName + "' is not displayed.");
		logMessage("ASSERT PASSED: element " + elementName + " is displayed.");
		return result;
	}

	protected boolean isElementDisplayed(String elementName, String elementTextReplace) {
		wait.waitForElementToBeVisible(element(elementName, elementTextReplace));
		boolean result = element(elementName, elementTextReplace).isDisplayed();
		assertTrue(result,
				"ASSERT FAILED: element '" + elementName + "with text " + elementTextReplace + "' is not displayed.");
		logMessage("ASSERT PASSED: element " + elementName + " with text " + elementTextReplace + " is displayed.");
		return result;
	}

	protected boolean isElementDisplayed(String elementName, String elementTextReplace1, String elementTextReplace2) {
		wait.waitForElementToBeVisible(element(elementName, elementTextReplace1, elementTextReplace2));
		boolean result = element(elementName, elementTextReplace1, elementTextReplace2).isDisplayed();
		assertTrue(result, "ASSERT FAILED: element '" + elementName + "with text " + elementTextReplace1 + "and"
				+ elementTextReplace2 + "' is not displayed.");
		logMessage("ASSERT PASSED: element " + elementName + " with text " + elementTextReplace1 + "and"
				+ elementTextReplace2 + " is displayed.");
		return result;
	}

	protected WebElement element(String elementToken) {
		return element(elementToken, "");
	}

	protected WebElement element(String elementToken, String replacement) {
		return _element(elementToken, replacement, "");
	}

	private WebElement _element(String elementToken, String replacement1, String replacement2) {
		if (replacement1.isEmpty() && replacement2.isEmpty()) {
			return driver.findElement(getLocator(elementToken));
		} else if (replacement2.isEmpty() && !replacement1.isEmpty()) {
			return driver.findElement(getLocator(elementToken, replacement1));
		} else if (!replacement1.isEmpty() && !replacement2.isEmpty()) {
			return driver.findElement(getLocator(elementToken, replacement1, replacement2));
		}
		return driver.findElement(getLocator(elementToken));
	}

	protected WebElement element(String elementToken, String replacement1, String replacement2)
			throws NoSuchElementException {
		WebElement elem = null;
		Long starttime = System.currentTimeMillis();
		try {
			elem = webdriver.findElement(getLocator(elementToken, replacement1, replacement2));
		} catch (NoSuchElementException excp) {
			Long endtime = System.currentTimeMillis();
			float sec = (endtime - starttime) / 1000F;
			fail("FAILED: Element " + elementToken + " not found on the " + this.pageName + " after " + sec
					+ " seconds !!!");
		} catch (NullPointerException npe) {

		}
		return elem;
	}

	protected By getLocator(String elementToken) {
		return getLocator(elementToken, "");
	}

	protected By getLocator(String elementToken, String replacement) {
		String[] locator = getElementFromFile(this.pageName, elementToken);
		locator[2] = locator[2].replaceAll("\\$\\{.+\\}", replacement);
		return getBy(locator[1].trim(), locator[2].trim());
	}

	protected By getLocator(String elementToken, String replacement1, String replacement2) {
		String[] locator = getElementFromFile(this.pageName, elementToken);
		locator[2] = StringUtils.replace(locator[2], "$", replacement1);
		locator[2] = StringUtils.replace(locator[2], "%", replacement2);
		return getBy(locator[1].trim(), locator[2].trim());
	}

	protected List<WebElement> elements(String elementToken, String replacement) {
		return wait.waitForElementsToBeVisible(webdriver.findElements(getLocator(elementToken, replacement)));
	}

	protected List<WebElement> elements(String elementToken, String replacement1, String replacement2) {
		return wait.waitForElementsToBeVisible(
				webdriver.findElements(getLocator(elementToken, replacement1, replacement2)));
	}

	protected List<WebElement> elements(String elementToken) {
		return elements(elementToken, "");
	}

	protected List<WebElement> hiddenElements(String elementToken, String replacement) {
		return webdriver.findElements(getLocator(elementToken, replacement));
	}

	protected List<WebElement> hiddenElements(String elementToken) {
		return hiddenElements(elementToken, "");
	}

	private By getBy(String locatorType, String locatorValue) {
		switch (Locators.valueOf(locatorType)) {
		case id:
			return By.id(locatorValue);
		case xpath:
			return By.xpath(locatorValue);
		case css:
			return By.cssSelector(locatorValue);
		case name:
			return By.name(locatorValue);
		case classname:
			return By.className(locatorValue);
		default:
			return By.id(locatorValue);
		}
	}

	public void hardWait(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}
}
