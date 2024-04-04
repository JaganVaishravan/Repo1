package SeleniumFrameworkDesign.TestComponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import SeleniumFrameworkDesign.POM.loginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseFile {

	public WebDriver driver;
	public loginPage loginPage;

	public WebDriver initializer() throws IOException {

		Properties prop = new Properties();
		FileInputStream propfile = new FileInputStream(System.getProperty("user.dir")
				+ "//src//main//java//SeleniumFrameworkDesign//resource//GlobalData.properties");
		prop.load(propfile);

		String browserName = prop.getProperty("browser");

		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup(); // Using WebDruver Manager instead of System.setproperties
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.gecko.driver", "./Driver/geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("Edge")) {
			System.setProperty(browserName, browserName);
			driver = new EdgeDriver();
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // declare implicitly wait
		
		Dimension dimension = new Dimension(1920,1280);
		driver.manage().window().setSize(dimension);
		return driver;
	}

	@BeforeMethod(alwaysRun=true)
	public loginPage launchApplication() throws IOException {
		driver = initializer();
		loginPage = new loginPage(driver);
		loginPage.goTo();
		return loginPage;
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		driver.quit();
	}

}
