package tests;

import org.apache.poi.poifs.filesystem.FilteringDirectoryNode;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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

		for (int i = 5; i < lastRow; i++) {
			String val_pass = ExcelUtil.getRowData(5).getCell(4).toString();
			String val_user = ExcelUtil.getRowData(6).getCell(4).toString();
			String inUserName = ExcelUtil.getRowData(i).getCell(1) == null ? ""
					: ExcelUtil.getRowData(i).getCell(1).toString();
			String inPassWord = ExcelUtil.getRowData(i).getCell(2) == null ? ""
					: ExcelUtil.getRowData(i).getCell(2).toString();
			if (inUserName == null) {
				WebElement exp1 = driver.findElement(By.xpath("//label[@id='message23']"));
				Utils.compareText(exp1.getText(), val_user);
				ExcelUtil.setCellData("FAIL", i, 5);
			} else if (inPassWord == null) {
				WebElement exp1 = driver.findElement(By.xpath("//label[@id='message18']"));
				Utils.compareText(exp1.getText(), val_pass);
				ExcelUtil.setCellData("FAIL", i, 5);
			}

		}

	}
}
