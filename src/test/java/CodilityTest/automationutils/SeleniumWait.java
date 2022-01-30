package CodilityTest.automationutils;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class SeleniumWait {

	WebDriver driver;
	WebDriverWait wait;

	int timeout;

	public SeleniumWait(WebDriver driver, int timeout) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, timeout);
		this.timeout = timeout;
	}

	public WebElement getWhenVisible(By locator) {
		WebElement element;
		element = (WebElement) wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return element;
	}

	public WebElement getWhenClickable(By locator) {
		WebElement element;
		element = (WebElement) wait.until(ExpectedConditions.elementToBeClickable(locator));
		return element;
	}

	public boolean waitForPageTitleToBeExact(String expectedPagetitle) {
		return wait.until(ExpectedConditions.titleIs(expectedPagetitle)) != null;
	}

	public boolean waitForElementToContainsText(WebElement element, String text) {
		return wait.until(ExpectedConditions.textToBePresentInElement(element, text));
	}

	public boolean waitForElementToContainsAtrribute(WebElement element, String attributeName, String attributeValue) {
		return wait.until(ExpectedConditions.attributeToBe(element, attributeName, attributeValue));
	}

	public boolean waitForPageTitleToContain(String expectedPagetitle) {
		Reporter.log(driver.getCurrentUrl(), true);
		return wait.until(ExpectedConditions.titleContains(expectedPagetitle)) != null;
	}

	public void waitforElementToBeRefresehed(By locator) {
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(locator)));
	}

	public void waitforElementToBeStale(By locator) {
		wait.until(ExpectedConditions.stalenessOf(driver.findElement(locator)));
	}

	public WebElement waitForElementToBeVisible(WebElement element) {
		return (WebElement) wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForFrameToBeAvailableAndSwitchToIt(By locator) {
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
	}

	public List<WebElement> waitForElementsToBeVisible(List<WebElement> elements) {
		return (List<WebElement>) wait.until(ExpectedConditions.visibilityOfAllElements(elements));
	}

	public boolean waitForElementToBeInVisible(By locator) {
		return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator)) != null;
	}

	public WebElement waitForElementToBeClickable(WebElement element) {
		return (WebElement) wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public Boolean waitForPageURLToBeContain(String text) {
		return wait.until(ExpectedConditions.urlContains(text));
	}

	public Boolean waitForOpenedWindowCoutToBe(int windowCount) {
		return wait.until(ExpectedConditions.numberOfWindowsToBe(windowCount));
	}

	public void clickWhenReady(By locator) {
		WebElement element = (WebElement) wait.until(ExpectedConditions.elementToBeClickable(locator));
		element.click();
	}

	public void waitforIBPAjaxLoadertoDisappear() {
		int i = 0;
		resetImplicitTimeout(1);
		try {
			while (driver.findElement(By.className("ibpAjaxLoadingSec")).isDisplayed() && i <= timeout) {
				hardWait(1);
				System.out.print("waiting for ibp ajax loader " + i + "..");
				i++;
			}
		} catch (Exception e) {
			System.out.println();
		} finally {
			resetImplicitTimeout(timeout);
		}
	}

	public void waitForSpinnerToDisappear() {
		int i = 0;
		resetImplicitTimeout(1);
		try {
			while (driver.findElement(By.cssSelector("#loading[style*='display block']")).isDisplayed()
					&& i <= timeout) {
				hardWait(1);
				i++;
			}
		} catch (Exception e) {
		} finally {
			resetImplicitTimeout(timeout);
		}
	}

	public void waitForLogoLoaderToDisappear() {
		int i = 0;
		resetImplicitTimeout(5);
		try {
			while (driver.findElement(By.cssSelector("div[class='logo-animated__container___Vuuda']")).isDisplayed()
					&& i <= timeout) {
				hardWait(1);
				i++;
			}
		} catch (Exception e) {
		} finally {
			resetImplicitTimeout(timeout);
		}
	}

	public void waitForLoaderToDisappear() {
		int i = 0;
		resetImplicitTimeout(1);
		try {
			while (driver.findElement(By.cssSelector("#loading[style*='display block']")).isDisplayed()
					&& i <= timeout) {
				hardWait(1);
				i++;
			}
		} catch (Exception e) {
		} finally {
			resetImplicitTimeout(timeout);
		}
	}
	// #greyform

	public void waitForGreySectionToDisappear() {
		int i = 0;
		resetImplicitTimeout(1);
		try {
			while (driver.findElement(By.cssSelector("#greyform")).isDisplayed() && i <= timeout) {
				hardWait(1);
				i++;
			}
		} catch (Exception e) {
		} finally {
			resetImplicitTimeout(timeout);
		}
	}

	public void waitforSpinnerToDisappearOnPayPal() {
		int i = 0;
		resetImplicitTimeout(1);
		try {
			while (driver.findElement(By.id("preloaderSpinner")).isDisplayed() && i <= timeout) {
				hardWait(1);
				i++;
			}
		} catch (Exception e) {
		} finally {
			resetImplicitTimeout(timeout);
		}
	}

	public void waitForElementToDisappear(WebElement element) {
		int i = 0;
		resetImplicitTimeout(2);
		try {
			while (element.isDisplayed() && i <= timeout) {
				hardWait(1);
				i++;
			}
		} catch (Exception e) {
		} finally {
			resetImplicitTimeout(timeout);
		}
	}

	public void resetImplicitTimeout(int newTimeOut) {
		try {
			driver.manage().timeouts().implicitlyWait(newTimeOut, TimeUnit.SECONDS);
		} catch (Exception e) {
		}
	}

	public void resetExplicitTimeout(int newTimeOut) {
		try {
			this.wait = new WebDriverWait(driver, newTimeOut);
		} catch (Exception e) {
		}
	}

	// TODO Implement Wait for page load for page synchronizations
	public void waitForPageToLoadCompletely() {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("*")));
		} catch (Exception e) {

		}
	}

	public void hardWait(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	public void waitForJavaScriptToLoadCompletely() {
		int i = 0;
		System.out.println("In script loading.....function");
		while (i < 60) {
			String status = (String) ((JavascriptExecutor) driver).executeScript("return document.readyState");
			// System.out.println("Script satus is " +status);
			if (status.equalsIgnoreCase("complete"))
				break;
			else
				i++;
			// System.out.println("WAITING FOR PAGE TO LOAD COMLETLYT "+i);
		}
	}

	public void waitForAtriumProcessMessageDisappear() {
		int i = 0;
		resetImplicitTimeout(1);
		try {
			while (driver.findElement(By.cssSelector("#_pui_loading_animation")).isDisplayed() && i <= timeout) {
				hardWait(1);
				i++;
			}
		} catch (Exception e) {
		}
		resetImplicitTimeout(timeout);
	}

	public void waitforSpinnerLoaderToDisappear() {
		int i = 0;
		resetImplicitTimeout(3);
		try {
			while (driver.findElement(By.cssSelector("div[class*='sticky-footer'] div[class*='spinner']")).isDisplayed()
					&& i <= timeout) {
				hardWait(1);
				i++;
			}
		} catch (Exception e) {
		} finally {
			resetImplicitTimeout(timeout);
		}
	}

	public void waitUntilElementValueChanged(By locator, String attributeValue) {
		wait.until(ExpectedConditions.textToBePresentInElementValue(locator, attributeValue));
		wait.until(ExpectedConditions.textToBePresentInElementValue(locator, attributeValue));
	}

}
