package com.minerva.script;

import org.testng.annotations.Test;

import commons.AbstractTest;
import minerva.pages.CheckOutPagePO;
import minerva.pages.BillInfoPagePO;
import minerva.pages.ProductDetailsPagePO;
import minerva.pages.ProductListPagePO;
import minerva.pages.PageFactoryManager;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class OrderProduct extends AbstractTest {
	private WebDriver driver;
	private ProductListPagePO productListPage;
	private ProductDetailsPagePO productDetailsPage;
	private CheckOutPagePO checkOutPage;
	private BillInfoPagePO billInfoPage;

	@Parameters({ "browser", "authenuser", "authenpass", "url", "version" })
	@BeforeClass
	public void beforeClass(String browser, String authenuser, String authenpass, String url, String version) {
		driver = openMultiBrowser(browser, authenuser, authenpass, url, version);
		productListPage = PageFactoryManager.getProductListPage(driver);
	}

	@Test
	public void TC_01_CheckOrderProductSuccess() throws Exception {
		log.info("TC_01 - Step 01: Select category");
		productListPage.selectCategoryMenu();
		log.info("TC_01 - Step 02: Select a product on list");
		productDetailsPage = productListPage.selectProductId();
		log.info("TC_01 - Step 03: View details - Click Order button");
		checkOutPage = productDetailsPage.clickOrderButton();
		log.info("TC_01 - Step 04: Check out - Input email");
		checkOutPage.inputToEmailTextbox("test1@yahoo.com.vn");
		log.info("TC_01 - Step 05: Check out - Input full name");
		checkOutPage.inputToFullNameTextbox("Test 1");
		log.info("TC_01 - Step 06: Check out - Input phone");
		checkOutPage.inputToPhoneTextbox("1234567890");
		log.info("TC_01 - Step 07: Check out - Input address");
		checkOutPage.inputToAddressTextbox("12/6 Âu Dương Lân, Phường 2 - Quận 8");
		log.info("TC_01 - Step 08: Check out - Input note");
		checkOutPage.inputToNoteTextarea("Note 1...");
		log.info("TC_01 - Step 09: Check out - Click Submit button");
		billInfoPage = checkOutPage.clickSubmitButton();
		log.info("TC_01 - Step 10: Bill info - Display bill info");
		verifyTrue(billInfoPage.checkEmailInfoDisplayed());
		verifyTrue(billInfoPage.checkFullNameInfoDisplayed());
		verifyTrue(billInfoPage.checkPhoneInfoDisplayed());
		verifyTrue(billInfoPage.checkAddressInfoDisplayed());
		log.info("TC_01 - Step 11: Come back to product list page");
		productListPage = billInfoPage.comeBackToProductListPage();
		productListPage.sleepInSeconds(2);
	}

	@Test
	public void TC_02_CheckEmailIsEmpty() throws Exception {
		log.info("TC_02 - Step 01: Check out - Email is empty");
		productListPage.selectCategoryMenu();
		productDetailsPage = productListPage.selectProductId();
		checkOutPage = productDetailsPage.clickOrderButton();
		checkOutPage.inputToEmailTextbox("");
		checkOutPage.clickSubmitValidateButton();
		log.info("TC_02 - Step 02: Check out - Check message error when email is empty");
		verifyEquals("Please fill out this field.", checkOutPage.validateEmailIsEmpty());
		productListPage.sleepInSeconds(2);
	}

	@Test
	public void TC_03_CheckFullNameIsEmpty() throws Exception {
		log.info("TC_03 - Step 01: Check out - Full name is empty");		
		checkOutPage.inputToFullNameTextbox("");
		checkOutPage.clickSubmitValidateButton();
		log.info("TC_03 - Step 02: Check out - Check message error when full name is empty");
		verifyEquals("Please fill out this field.", checkOutPage.validateFullNameIsEmpty());
		productListPage.sleepInSeconds(2);
	}
	
	@Test
	public void TC_04_CheckAddressIsEmpty() throws Exception {
		log.info("TC_04 - Step 01: Check out - Address is empty");		
		checkOutPage.inputToAddressTextbox("");
		checkOutPage.clickSubmitValidateButton();
		log.info("TC_04 - Step 02: Check out - Check message error when address is empty");
		verifyEquals("Please fill out this field.", checkOutPage.validateAddressIsEmpty());
		productListPage.sleepInSeconds(2);
	}
	
	@Test
	public void TC_05_CheckFormatEmail() throws Exception {
		log.info("TC_05 - Step 01: Check out - Email isn't a email address");		
		checkOutPage.inputToEmailTextbox("test1");
		checkOutPage.clickSubmitValidateButton();
		log.info("TC_05 - Step 02: Check out - Check message error when email isn't a email address");
		verifyEquals("Please include an '@' in the email address. 'test1' is missing an '@'.", checkOutPage.validateEmailFormat());
		productListPage.sleepInSeconds(2);
	}
	
	@Parameters({ "browser" })
	@AfterClass
	public void afterClass(String browser) {
		closeBrowser(driver, browser);
	}

}
