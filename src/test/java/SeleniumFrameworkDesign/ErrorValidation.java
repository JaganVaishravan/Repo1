package SeleniumFrameworkDesign;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import SeleniumFrameworkDesign.POM.CartPage;
import SeleniumFrameworkDesign.POM.productCatalogPage;
import SeleniumFrameworkDesign.TestComponents.BaseFile;

public class ErrorValidation extends BaseFile {

	@Test(groups= {"ErrorHandling"})
	public void loginErrorValidation() {
		loginPage.loginApplication("valparaiapril@gmail.com", "V@lparaiapril");
		Assert.assertEquals("Incorrect email or password.", loginPage.getErrorMessage());
	}
	
	
	
	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException {

		String productName = "ZARA COAT 3";
		productCatalogPage productCatalogPage = loginPage.loginApplication("valparaiapril@gmail.com", "V@lparaiapril01");

		List<WebElement> products = productCatalogPage.getProdList();
		productCatalogPage.addCart(productName);
		CartPage CartPage = productCatalogPage.cartbtn();

		Boolean match = CartPage.verifyprodDisplay("ZARA COAT 3");
		Assert.assertFalse(match);
		
		System.out.println("adding new branch");
	}
	
}
