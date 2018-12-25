package tests;

import org.openqa.selenium.Alert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.LoginPage;
import util.BaseTest;
import util.ExcelUtil;
import util.Links;
import util.Utils;

public class TestCaseLogin extends BaseTest {
	public LoginPage loginPage;

	@BeforeTest
	public void setUpSheetData() {
		System.out.println("************* Bắt đầu khởi tạo dữ liệu **********");
		loginPage = new LoginPage(driver);
		ExcelUtil.setExcelFileSheet("Login");
	}

	// TC1
	@Test(enabled = false)
	public void TestCaseLoginPassed() {
		String inUserName = ExcelUtil.getRowData(1).getCell(2).toString();
		String inPassWord = ExcelUtil.getRowData(1).getCell(3).toString();
		loginPage.doLogin(inUserName, inPassWord);
		Utils.compareText(driver.getCurrentUrl(), Links.URL_dashboad);
		ExcelUtil.setCellData("PASSED", 1, 5);
	}

	// TC 2->4
	@Test(enabled = false)
	public void TestCaseLoginFail() {
		int lastRow = ExcelUtil.getExcelWSheet().getPhysicalNumberOfRows();
		System.out.println("LastRow:::" + lastRow);

		for (int i = 2; i < 4; i++) {
			String inUserName = ExcelUtil.getRowData(i).getCell(2).toString();
			String inPassWord = ExcelUtil.getRowData(i).getCell(3).toString();
			loginPage.doLogin(inUserName, inPassWord);
			Alert alert = driver.switchTo().alert();
			String exp = alert.getText();
			Utils.compareText(exp, "User or Password is not valid");
			alert.accept();
			Utils.compareText(driver.getCurrentUrl(), Links.URL_login);
			ExcelUtil.setCellData("Failed", i, 5);
		}
	}

	// TC6,7
	@Test(enabled = true)
	public void TestCaseLoginFail_null() {
		int lastRow = ExcelUtil.getExcelWSheet().getPhysicalNumberOfRows();
		System.out.println("LastRow:::" + lastRow);
		int count = 1;
		for (int i = 5; i <= lastRow; i++) {
			// String val_pass = ExcelUtil.getRowData(5).getCell(4).toString();
			// String val_user = ExcelUtil.getRowData(6).getCell(4).toString();
			String inUserName = ExcelUtil.getRowData(i).getCell(2) == null ? ""
					: ExcelUtil.getRowData(i).getCell(2).toString();
			String inPassWord = ExcelUtil.getRowData(i).getCell(3) == null ? ""
					: ExcelUtil.getRowData(i).getCell(3).toString();
			System.out.println(
					"Lần [" + count + "] với Username :[" + inUserName + "] - và Password :[" + inPassWord + "]");
			getAlert(inUserName, inPassWord, i);
			System.out.println("Done lần :[" + count + "]");
			count++;
		}

	}

	public void getAlert(String username, String password, int row) {
		if (!username.equals("")) {
			Utils.getElement(loginPage.userName).sendKeys(username);
		} else if (!password.equals("")) {
			Utils.getElement(loginPage.passWord).sendKeys(password);
		}
		Utils.getElement(loginPage.btnLogin).click();
		Alert alert = driver.switchTo().alert();
		String exp = alert.getText();
		Utils.compareText(exp, "User or Password is not valid");
		alert.accept();
		Utils.compareText(driver.getCurrentUrl(), Links.URL_login);
		ExcelUtil.setCellData("FAIL", row, 5);
	}
}
