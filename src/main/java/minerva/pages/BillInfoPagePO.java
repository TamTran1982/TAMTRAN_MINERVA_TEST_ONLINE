package minerva.pages;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import commons.Constants;
import minerva.interfaces.BillInfoPageUI;

public class BillInfoPagePO extends AbstractPage {
	WebDriver driver;

	public BillInfoPagePO(WebDriver driver_) {
		this.driver = driver_;
	}	
	
	public ProductListPagePO comeBackToProductListPage() {
		openAnyURL(driver, Constants.HOME_URL);
		return PageFactoryManager.getProductListPage(driver);
	}
	
	public boolean checkEmailInfoDisplayed() {
		waitForControlVisible(driver, BillInfoPageUI.EMAIL_INFO);	
		return isControlDisplayed(driver, BillInfoPageUI.EMAIL_INFO);
	}
	
	public boolean checkFullNameInfoDisplayed() {		
		waitForControlVisible(driver, BillInfoPageUI.FULL_NAME_INFO);		
		return isControlDisplayed(driver, BillInfoPageUI.FULL_NAME_INFO);
	}
	
	public boolean checkPhoneInfoDisplayed() {		
		waitForControlVisible(driver, BillInfoPageUI.PHONE_INFO);		
		return isControlDisplayed(driver, BillInfoPageUI.PHONE_INFO);
	}
	
	public boolean checkAddressInfoDisplayed() {		
		waitForControlVisible(driver, BillInfoPageUI.ADDRESS_INFO);		
		return isControlDisplayed(driver, BillInfoPageUI.ADDRESS_INFO);
	}
}
