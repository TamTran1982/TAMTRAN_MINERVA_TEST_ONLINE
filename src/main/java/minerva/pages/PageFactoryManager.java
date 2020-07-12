package minerva.pages;

import org.openqa.selenium.WebDriver;

public class PageFactoryManager {

	private static ProductListPagePO productListPage;
	private static BillInfoPagePO billInfoPage;
	private static ProductDetailsPagePO productDetailsPage;
	private static CheckOutPagePO checkOutPage;
	
	public static ProductListPagePO getProductListPage(WebDriver driver_) {
		if (productListPage == null) {
			return new ProductListPagePO(driver_);
		}
		return productListPage;
	}

	public static BillInfoPagePO getBillInfoPage(WebDriver driver_) {
		if (billInfoPage == null) {
			return new BillInfoPagePO(driver_);
		}
		return billInfoPage;
	}

	public static ProductDetailsPagePO getProductDetailsPage(WebDriver driver_) {
		if (productDetailsPage == null) {
			return new ProductDetailsPagePO(driver_);
		}
		return productDetailsPage;
	}
	
	public static CheckOutPagePO getCheckOutPage(WebDriver driver_) {
		if (checkOutPage == null) {
			return new CheckOutPagePO(driver_);
		}
		return checkOutPage;
	}
	
	
	
	
	
}
