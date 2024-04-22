package introduction;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import Utilities.ReadExcel;

public class ApplicationFlow extends Base {

	@Test(dataProviderClass = ReadExcel.class, dataProvider = "userdata")
	public void loginFlow(String username, String password) {

		launchApplication();

		// Login page flow
		loginPage login = new loginPage(driver);
		
		login.loginApplication(username, password);

		// ProductsPage Flow
		productsPage products = new productsPage(driver);
		products.addProduct("Sauce Labs Backpack");

		products.clickCart();

		// Cart page Flow

		cartPage cart = new cartPage(driver);
		cart.clickCheckout();

		// Yourinformationflow
		yourInformation info = new yourInformation(driver);
		info.fillInformation("test", "test", "30022");
		info.clickContinueButton();

		// checkoutFlow
		checkoutOverview checkout = new checkoutOverview(driver);
		checkout.clickFinish();

		// Completepage Flow
		CompletePage flow = new CompletePage(driver);

		Assert.assertEquals(flow.confirmationmessage(), "Thank you for your order!");

	}
	
	@Test(groups= {"negative"})
	public void negativeTest() {
		
		System.out.print("call only negative ");
	}
	
	
	

}
