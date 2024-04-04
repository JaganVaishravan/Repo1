package SeleniumFrameworkDesign;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import SeleniumFrameworkDesign.POM.CartPage;
import SeleniumFrameworkDesign.POM.CheckoutPage;
import SeleniumFrameworkDesign.POM.ConfirmationPage;
import SeleniumFrameworkDesign.POM.OrderPage;
import SeleniumFrameworkDesign.POM.productCatalogPage;
import SeleniumFrameworkDesign.TestComponents.BaseFile;

public class standaloneTest extends BaseFile {

	String productName = "ZARA COAT 3";

	@Test
	public void standalone() throws IOException, InterruptedException {

		productCatalogPage productCatalogPage = loginPage.loginApplication("valparaiapril@gmail.com",
				"V@lparaiapril01");

		List<WebElement> products = productCatalogPage.getProdList();
		productCatalogPage.addCart(productName);
		CartPage CartPage = productCatalogPage.cartbtn();

		Boolean match = CartPage.verifyprodDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage CheckoutPage = CartPage.goToCheckOut();

		CheckoutPage.selectCountry("India");

		ConfirmationPage ConfirmationPage = CheckoutPage.orderSubmit();

		String confirmationMsg = ConfirmationPage.getconfirmationMsg();

		Assert.assertTrue(confirmationMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

		System.out.println("validation would be executed properly");
		
		System.out.println("after git updates");

	}
 
	@Test(dependsOnMethods = { "standalone" })
	public void orderHistoryTest() {

		productCatalogPage productCatalogPage = loginPage.loginApplication("valparaiapril@gmail.com",
				"V@lparaiapril01");
		OrderPage orderspage = productCatalogPage.gotoOrderPage();
		Assert.assertTrue(orderspage.verifyOrderDisplay(productName));

	}

}
