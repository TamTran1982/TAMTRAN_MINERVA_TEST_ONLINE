package minerva.pages;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import minerva.pages.PageFactoryManager;
import minerva.interfaces.ProductListPageUI;

public class ProductListPagePO extends AbstractPage {
	WebDriver driver;

	public ProductListPagePO(WebDriver driver_) {
		this.driver = driver_;
	}
	
	public void selectCategoryMenu() {
		waitForControlClickable(driver, ProductListPageUI.CATEGORY_MENU);
		clickToElement(driver, ProductListPageUI.CATEGORY_MENU);
	}

	public ProductDetailsPagePO selectProductId() {
		waitForControlClickable(driver, ProductListPageUI.PRODUCT_ID);
		clickToElement(driver, ProductListPageUI.PRODUCT_ID);
		return PageFactoryManager.getProductDetailsPage(driver);
	}	
	

	
}
