package SeleniumFrameworkDesign.POM;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumFrameworkDesign.base.commonDetails;

public class CartPage extends commonDetails{
	
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//li[@class='totalRow']/button")
	WebElement checkOutBtn;
	
	@FindBy(xpath="//div[@class='cartSection']/h3")
	List<WebElement> cartProducts;
	
	public Boolean verifyprodDisplay(String productName) {
		Boolean match = cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckoutPage goToCheckOut() {
		checkOutBtn.click();
		return new CheckoutPage(driver);
	}
	
	//List<WebElement> cartProducts = driver.findElements(By.xpath("//div[@class='cartSection']/h3"));
	
	//Boolean match = cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
	//Assert.assertTrue(match);
	
	//Clicking checkout button
	//driver.findElement(By.xpath("//li[@class='totalRow']/button")).click();

}
