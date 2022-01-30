package CodilityTest.automationutils;

import java.util.NoSuchElementException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.apache.commons.lang3.StringUtils;
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
	        Long starttime =  System.currentTimeMillis();
	        try {
	            elem = webdriver
	                    .findElement(getLocator(elementToken, replacement1, replacement2));
	        } catch (NoSuchElementException excp) {
	            Long endtime = System.currentTimeMillis();
	            float sec = (endtime - starttime) / 1000F;
	            fail("FAILED: Element " + elementToken + " not found on the " + this.pageName + " after " + sec +  " seconds !!!");
	        }
	        catch (NullPointerException npe){

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
