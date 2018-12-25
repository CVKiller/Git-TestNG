package pages;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import util.Utils;

public class LoginPage {
	public WebDriver driver;

	public static final String FILE_NAME = "LoginPage.properties";

	public By userName;
	public By passWord;
	public By btnLogin;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		Properties prop = Utils.loadProperties(FILE_NAME);
		userName = By.xpath(prop.getProperty("LoginPage.user_name"));
		passWord = Utils.getValueByKey("LoginPage.pass", prop);
		btnLogin = Utils.getValueByKey("LoginPage.btn_login", prop);
	}

	public void doLogin(String inuserName, String inPassWord) {
		Utils.getElement(userName).sendKeys(inuserName);
		Utils.getElement(passWord).sendKeys(inPassWord);
		Utils.getElement(btnLogin).click();
	}

}
