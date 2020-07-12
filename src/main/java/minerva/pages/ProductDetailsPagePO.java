package minerva.pages;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import minerva.interfaces.ProductDetailsPageUI;

public class ProductDetailsPagePO extends AbstractPage {
	WebDriver driver;

	public ProductDetailsPagePO(WebDriver driver_) {
		this.driver = driver_;
	}	

	
	public CheckOutPagePO clickOrderButton() {
		waitForControlClickable(driver, ProductDetailsPageUI.ORDER_BTN);	
		clickToElement(driver, ProductDetailsPageUI.ORDER_BTN);
		return PageFactoryManager.getCheckOutPage(driver);
	}
	
	
	
	
}
