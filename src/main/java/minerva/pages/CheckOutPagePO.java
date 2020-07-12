package minerva.pages;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import minerva.interfaces.CheckOutPageUI;

public class CheckOutPagePO extends AbstractPage{
	WebDriver driver;
	
	public CheckOutPagePO(WebDriver driver_) {
		this.driver = driver_;
	}

	public void inputToEmailTextbox(String email) {
		waitForControlClickable(driver, CheckOutPageUI.EMAIL_TXT);
		clearToElement(driver, CheckOutPageUI.EMAIL_TXT);		
		sendkeyToElement(driver, CheckOutPageUI.EMAIL_TXT, email);
	}

	public String validateEmailIsEmpty() {
		waitForControlClickable(driver, CheckOutPageUI.EMAIL_TXT);
		String nameMessage = getHtml5ValidationMessage(driver, CheckOutPageUI.EMAIL_TXT);
		return nameMessage;		
	}

	public String validateEmailFormat() {
		waitForControlClickable(driver, CheckOutPageUI.FULL_NAME_TXT);
		String nameMessage = getHtml5ValidationMessage(driver, CheckOutPageUI.EMAIL_TXT);
		return nameMessage;		
	}
	
	public String validateFullNameIsEmpty() {
		waitForControlClickable(driver, CheckOutPageUI.FULL_NAME_TXT);
		String nameMessage = getHtml5ValidationMessage(driver, CheckOutPageUI.FULL_NAME_TXT);
		return nameMessage;		
	}
	
	public String validateAddressIsEmpty() {
		waitForControlClickable(driver, CheckOutPageUI.ADDRESS_TXT);
		String nameMessage = getHtml5ValidationMessage(driver, CheckOutPageUI.ADDRESS_TXT);
		return nameMessage;		
	}
	
	public void inputToFullNameTextbox(String full_name) {
		waitForControlClickable(driver, CheckOutPageUI.FULL_NAME_TXT);
		clearToElement(driver, CheckOutPageUI.FULL_NAME_TXT);		
		sendkeyToElement(driver, CheckOutPageUI.FULL_NAME_TXT, full_name);
	}
	
	public void inputToPhoneTextbox(String phone) {
		waitForControlClickable(driver, CheckOutPageUI.PHONE_TXT);
		clearToElement(driver, CheckOutPageUI.PHONE_TXT);		
		sendkeyToElement(driver, CheckOutPageUI.PHONE_TXT, phone);
	}
	
	public void inputToAddressTextbox(String address) {
		waitForControlClickable(driver, CheckOutPageUI.ADDRESS_TXT);
		clearToElement(driver, CheckOutPageUI.ADDRESS_TXT);		
		sendkeyToElement(driver, CheckOutPageUI.ADDRESS_TXT, address);
	}
	
	public void inputToNoteTextarea(String note) {
		waitForControlClickable(driver, CheckOutPageUI.NOTE_TXTAREA);
		clearToElement(driver, CheckOutPageUI.NOTE_TXTAREA);		
		sendkeyToElement(driver, CheckOutPageUI.NOTE_TXTAREA, note);
	}	
	
	public void clickSubmitValidateButton() {
		waitForControlClickable(driver, CheckOutPageUI.SUBMIT_BTN);	
		clickToElement(driver, CheckOutPageUI.SUBMIT_BTN);		
	}
	
	public BillInfoPagePO clickSubmitButton() {
		waitForControlClickable(driver, CheckOutPageUI.SUBMIT_BTN);	
		clickToElement(driver, CheckOutPageUI.SUBMIT_BTN);
		return PageFactoryManager.getBillInfoPage(driver);
	}
	
}
