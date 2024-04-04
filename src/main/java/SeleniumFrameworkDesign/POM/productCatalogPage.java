package SeleniumFrameworkDesign.POM;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import SeleniumFrameworkDesign.base.commonDetails;

public class productCatalogPage extends commonDetails {
	
	WebDriver driver;
	
	public productCatalogPage(WebDriver driver) {
		super(driver);
		this.driver = driver;	
		PageFactory.initElements(driver, this);
	}
	//	List<WebElement> products = driver.findElements(By.cssSelector(".mb-3")); 
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	@FindBy(xpath="\"//button[@routerlink='/dashboard/cart']\"")
	WebElement click;
	
	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.xpath("//div[@class='card-body']/button[@class='btn w-10 rounded']");
	By toastMsg = By.cssSelector("#toast-container");

	public List<WebElement> getProdList() {
		explicitlyWait(productsBy);
		return products;
	}
	
	public WebElement getProductByName(String productName) {
		WebElement prod = getProdList().stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public CartPage addCart(String productName) throws InterruptedException {
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		explicitlyWait(toastMsg);
		explicitlyWaitDisappear(spinner);
		return new CartPage(driver);
	}
	
}