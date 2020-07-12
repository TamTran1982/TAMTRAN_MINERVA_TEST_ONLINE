package commons;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractPage {

	public final Log log;

	protected AbstractPage() {
		log = LogFactory.getLog(getClass());
	}

	// Web browser
	public void openAnyURL(WebDriver driver, String url) {
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	public String getTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getCurrentURL(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public String getPageSource(WebDriver driver) {
		return driver.getPageSource();
	}

	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	public void refreshPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public void forwardPage(WebDriver driver) {
		driver.navigate().forward();
	}

	// Web element
	public void clickToElement(WebDriver driver, String locator) {
		try {
			WebElement element = driver.findElement(By.xpath(locator));
			element.click();
		} catch (Exception e) {
			log.error("Element not clickable : " + e.getMessage());
			throw (e);
			// System.out.println(e.getMessage());
		}
	}

	public void clickToElementByJS(WebDriver driver, String locator) {
		try {
			WebElement element = driver.findElement(By.xpath(locator));
			executeForWebElementByClick(driver, element);
		} catch (Exception e) {
			log.error("Element not clickable : " + e.getMessage());
			throw (e);
			// System.out.println(e.getMessage());
		}
	}

	public void clickToElement(WebDriver driver, String locator, String value) {
		try {
			locator = String.format(locator, value);
			WebElement element = driver.findElement(By.xpath(locator));
			element.click();
		} catch (Exception e) {
			log.error("Element not clickable : " + e.getMessage());
			throw (e);
			// System.out.println(e.getMessage());
		}
	}

	public void clickToElementByJS(WebDriver driver, String locator, String value) {
		try {
			locator = String.format(locator, value);
			WebElement element = driver.findElement(By.xpath(locator));
			executeForWebElementByClick(driver, element);
		} catch (Exception e) {
			log.error("Element not clickable : " + e.getMessage());
			throw (e);
			// System.out.println(e.getMessage());
		}
	}

	public void sendkeyToElement(WebDriver driver, String locator, String text) {
		WebElement element = driver.findElement(By.xpath(locator));
		element.sendKeys(text);
	}

	public void clearToElement(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		element.clear();
	}

	public void selectItemInDropdown(WebDriver driver, String locator, String item) {
		Select select = new Select(driver.findElement(By.xpath(locator)));
		select.selectByVisibleText(item);
	}

	public String getFirstItemSelected(WebDriver driver, String locator) {
		Select select = new Select(driver.findElement(By.xpath(locator)));
		return select.getFirstSelectedOption().getText();
	}

	public int getSizeItems(WebDriver driver, String locator) {
		Select select = new Select(driver.findElement(By.xpath(locator)));
		return select.getOptions().size();
	}

	public String getAttributeValue(WebDriver driver, String locator, String attributeName) {
		WebElement element = driver.findElement(By.xpath(locator));
		return element.getAttribute(attributeName);
	}

	public String getTextElement(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		return element.getText();
	}

	public int getSizeElements(WebDriver driver, String locator) {
		List<WebElement> elements = driver.findElements(By.xpath(locator));
		return elements.size();
	}

	public void checkTheCheckbox(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void checkTheCheckbox(WebDriver driver, String locator, String value) {
		locator = String.format(locator, value);
		WebElement element = driver.findElement(By.xpath(locator));
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void uncheckTheCheckbox(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		if (element.isSelected()) {
			element.click();
		}
	}

	public void uncheckTheCheckbox(WebDriver driver, String locator, String value) {
		locator = String.format(locator, value);
		WebElement element = driver.findElement(By.xpath(locator));
		if (element.isSelected()) {
			element.click();
		}
	}

	public boolean isControlSelected(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		return element.isSelected();
	}

	public boolean isControlDisplayed(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		return element.isDisplayed();
	}

	public boolean isControlDisplayed(WebDriver driver, String locator, String value) {
		locator = String.format(locator, value);
		WebElement element = driver.findElement(By.xpath(locator));
		return element.isDisplayed();
	}

	public boolean isControlEnabled(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		return element.isEnabled();
	}

	public void acceptJsAlert(WebDriver driver) throws Exception {
		if (driver.toString().toLowerCase().contains("internetexplorer")) {
			keyPressAction(driver, Keys.ENTER);
			Thread.sleep(5000);
			keyPressAction(driver, Keys.ENTER);
		} else {
			// waitForAlertPresence(driver);
			Alert alert = driver.switchTo().alert();
			alert.accept();
		}
	}

	public void cancelJsAlert(WebDriver driver) {
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
	}

	public String getTextJsAlert(WebDriver driver) {
		Alert alert = driver.switchTo().alert();
		return alert.getText();
	}

	public void sendKeyToJsAlert(WebDriver driver, String text) {
		Alert alert = driver.switchTo().alert();
		alert.sendKeys(text);
	}

	public void switchToChildWindow(WebDriver driver, String parent) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			if (!runWindow.equals(parent)) {
				driver.switchTo().window(runWindow);
				break;
			}
		}
	}

	public void switchToWindowByTitle(WebDriver driver, String expectedTitle) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			driver.switchTo().window(runWindows);
			String actualTitle = driver.getTitle();
			if (actualTitle.equals(expectedTitle)) {
				break;
			}
		}
	}

	public void switchToIframe(WebDriver driver, String locator) {
		WebElement iframe = driver.findElement(By.xpath(locator));
		driver.switchTo().frame(iframe);
	}

	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	public boolean closeAllWithoutParentWindow(WebDriver driver, String parentWindow) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			if (!runWindows.equals(parentWindow)) {
				driver.switchTo().window(runWindows);
				driver.close();
			}
		}
		driver.switchTo().window(parentWindow);
		if (driver.getWindowHandles().size() == 1)
			return true;
		else
			return false;
	}

	public void doubleClickMouse(WebDriver driver, String locator) {
		WebElement doubleClick = driver.findElement(By.xpath(locator));
		Actions action = new Actions(driver);
		action.doubleClick(doubleClick).perform();
	}

	public void hoverMouse(WebDriver driver, String locator) {
		WebElement hoverMouse = driver.findElement(By.xpath(locator));
		Actions action = new Actions(driver);
		action.moveToElement(hoverMouse).perform();
	}

	public void rightClickMouse(WebDriver driver, String locator) {
		WebElement rightClick = driver.findElement(By.xpath(locator));
		Actions action = new Actions(driver);
		action.contextClick(rightClick).perform();
	}

	public void dragAndDropMouse(WebDriver driver, String locator1, String locator2) {
		WebElement source = driver.findElement(By.xpath(locator1));
		WebElement target = driver.findElement(By.xpath(locator2));
		Actions action = new Actions(driver);
		action.dragAndDrop(source, target).perform();
		action.release();
	}

	public void keyPressAction(WebDriver driver, Keys key) {
		Actions action = new Actions(driver);
		action.sendKeys(key);
	}

	public void keyPressSendKey(WebDriver driver, String locator, Keys key) {
		WebElement element = driver.findElement(By.xpath(locator));
		element.sendKeys(key);
	}

	public Object highlightElement(WebDriver driver, WebElement element) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			return js.executeScript("arguments[0].style.border='6px groove red'", element);
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	public Object executeForWebBrowser(WebDriver driver, String javaSript) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			return js.executeScript(javaSript);
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	public Object executeForWebElementByClick(WebDriver driver, WebElement element) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			return js.executeScript("arguments[0].click();", element);
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	public Object scrollToBottomPage(WebDriver driver) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			return js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	public Object scrollToElement(WebDriver driver, String locator) {
		try {
			WebElement element = driver.findElement(By.id(locator));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			return js.executeScript("arguments[0].scrollIntoView(true);", element);
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	public Object removeAttributeOfElement(WebDriver driver, WebElement element, String attribute) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			return js.executeScript("arguments[0].removeAttribute('" + attribute + "');", element);
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	public boolean checkAnyImageLoaded(WebDriver driver, WebElement image) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (boolean) jsExecutor.executeScript("return arguments[0].complete && "
				+ "typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", image);
	}

	public void uploadFile(WebDriver driver, WebElement fileUpload, String filePath) {
		fileUpload.sendKeys(filePath);
	}

	// Wait for Element
	public void waitForControlVisible(WebDriver driver, String locator) {
		By by = By.xpath(locator);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	public void waitForControlVisible(WebDriver driver, String locator, String value) {
		// locator: //a[text()='%s']
		// value: New Customer
		locator = String.format(locator, value);
		// locator: //a[text()='New Customer']
		By by = By.xpath(locator);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	public void waitForControlNotVisible(WebDriver driver, String locator) {
		By by = By.xpath(locator);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
	}

	public void waitForAlertPresence(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.alertIsPresent());
	}

	public void waitForControlPresence(WebDriver driver, String locator) {
		By by = By.xpath(locator);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(by));
	}

	public void waitForControlClickable(WebDriver driver, String locator) {
		By by = By.xpath(locator);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(by));
	}

	public void sleepInSeconds(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String getHtml5ValidationMessage(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", element);
	}

}
