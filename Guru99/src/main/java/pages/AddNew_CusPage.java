package pages;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import util.Utils;

public class AddNew_CusPage {
	public WebDriver driver;

	public static final String FILE_NAME = "AddNew_Cus.properties";

	public By cusName;
	public By gen_Male;
	public By gen_Female;
	public By gender;
	public By birthDate;
	public By address;
	public By city;
	public By state;
	public By pin;
	public By mobile;
	public By email;
	public By pass;
	public By btn_submit;
	public By btn_reset;

	public AddNew_CusPage(WebDriver driver) {
		this.driver = driver;
		Properties prop = Utils.loadProperties(FILE_NAME);
		cusName = Utils.getValueByKey("AddCus.CusName", prop);
		gender= Utils.getValueByKey("AddCus.Gender", prop);
		//gen_Male = Utils.getValueByKey("AddCus.Gen_male	", prop);
		//gen_Female = Utils.getValueByKey("AddCus.Gen_female	", prop);
		birthDate = Utils.getValueByKey("AddCus.BirthDate", prop);
		address = Utils.getValueByKey("AddCus.Address", prop);
		city = Utils.getValueByKey("AddCus.City	", prop);
		state = Utils.getValueByKey("AddCus.State", prop);
		pin = Utils.getValueByKey("AddCus.Pin", prop);
		mobile = Utils.getValueByKey("AddCus.Mobile", prop);
		email = Utils.getValueByKey("AddCus.Email", prop);
		pass = Utils.getValueByKey("AddCus.Pass", prop);
		btn_submit = Utils.getValueByKey("AddCus.btn_submit", prop);
		btn_reset = Utils.getValueByKey("AddCus.btn_reset", prop);
	}

	public void doAddnewCus(String incusrName,String gender, String inbirthdate, String inaddress, String incity, String instate,
			String inpin, String inmobile, String inemail, String inpass, String mode) {
		Utils.getElement(cusName).sendKeys(incusrName);
		Utils.getElement(gender).click();
		Utils.getElement(birthDate).sendKeys(inbirthdate);
		Utils.getElement(address).sendKeys(inaddress);
		Utils.getElement(city).sendKeys(incity);
		Utils.getElement(state).sendKeys(instate);
		Utils.getElement(pin).sendKeys(inpin);
		Utils.getElement(mobile).sendKeys(inmobile);
		Utils.getElement(email).sendKeys(inemail);
		Utils.getElement(pass).sendKeys(inpass);
		Utils.getElement(btn_submit).click();
	}
}
