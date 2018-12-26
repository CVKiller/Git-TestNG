package tests;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.common.util.concurrent.Uninterruptibles;
import com.google.gson.annotations.Until;

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
		// Utils.compareText(driver.getCurrentUrl(), Links.URL_dashboad);
		if (Utils.compareText(driver.getCurrentUrl(), getExpectRowToCompare(1))) {
			ExcelUtil.setCellData("PASSED", 1, 5);
		} else {
			ExcelUtil.setCellData("FAILED", 1, 5);
		}
	}

	// TC 2->4
	@Test(enabled = false)
	public void TestCaseLoginFail() {
		int lastRow = ExcelUtil.getExcelWSheet().getPhysicalNumberOfRows();
		System.out.println("LastRow:::" + lastRow);

		for (int i = 2; i <= 4; i++) {
			String inUserName = ExcelUtil.getRowData(i).getCell(2).toString();
			String inPassWord = ExcelUtil.getRowData(i).getCell(3).toString();
			loginPage.doLogin(inUserName, inPassWord);
			Alert alert = driver.switchTo().alert();
			String exp = alert.getText();
			if (Utils.compareText(exp, getExpectRowToCompare(i))) {
				ExcelUtil.setCellData("PASSED", i, 5);
			} else {
				ExcelUtil.setCellData("FAILED", i, 5);
			}
			alert.accept();

		}
	}

	// TC6,7
	@Test(enabled = false)
	public void TestCaseLoginFail_null() {
		// int lastRow = ExcelUtil.getExcelWSheet().getPhysicalNumberOfRows();
		// System.out.println("LastRow:::" + lastRow);
		int count = 1;
		for (int i = 5; i <= 7; i++) {
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
		if (Utils.compareText(exp, getExpectRowToCompare(row))) {
			ExcelUtil.setCellData("PASSED", row, 5);
		} else {
			ExcelUtil.setCellData("FAILED", row, 5);
		}
		alert.accept();
	}

	public String getExpectRowToCompare(int row) {
		String expected = ExcelUtil.getRowData(row).getCell(4).toString();
		return expected; // cái đoạn này khai báo bên hàm compare ý

	}

	// TC 8->10
	@Test(enabled = true)
	public void checkValidate() throws InterruptedException {
		int count = 1;
		for (int i = 8; i <= 9; i++) {
			String inUserName = ExcelUtil.getRowData(i).getCell(2) == null ? ""
					: ExcelUtil.getRowData(i).getCell(2).toString();
			String inPassWord = ExcelUtil.getRowData(i).getCell(3) == null ? ""
					: ExcelUtil.getRowData(i).getCell(3).toString();
			System.out.println(
					"Lần [" + count + "] với Username :[" + inUserName + "] - và Password :[" + inPassWord + "]");
			// getAlert(inUserName, inPassWord, i);
			loginPage.doValidation(inUserName, inPassWord);
			if (inUserName.equals("")) {
				WebElement val_user = driver.findElement(By.xpath("//label[@id='message23']"));
				String exp = val_user.getText().toString();
				Utils.compareText(exp, "User-ID must not be blank");
			}
			if (inPassWord.equals("")) {
				WebElement header = driver.findElement(By.xpath("//h2[@class='barone']"));
				header.click();
				Thread.sleep(30000);
				WebElement val_pass = driver.findElement(By.xpath("//label[@id='message18']"));
				String exp2 = val_pass.getText().toString();
				Utils.compareText(exp2, "Password must not be blank");

			}
			System.out.println("Done lần :[" + count + "]");
			count++;
		}
	}
}
