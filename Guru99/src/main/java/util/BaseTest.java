package util;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest extends Links {
	public static WebDriver driver;
	
	public static final String testDataExcelFileName = "data.xlsx";

	@BeforeMethod
	public void setUp() {
		// run trước các method test
		// driver.navigate().refresh();
		System.setProperty("webdriver.chrome.driver","F:\\Selenium\\BNKiller\\Guru99(2)\\Guru99\\src\\main\\resources\\Driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(URL_login);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@AfterMethod
	// run sau method test
	public void tearDown() {
		driver.quit();
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

}
