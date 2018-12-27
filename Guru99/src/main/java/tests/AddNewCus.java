package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.AddNew_CusPage;
import pages.LoginPage;
import util.BaseTest;
import util.ExcelUtil;
import util.Utils;

public class AddNewCus extends BaseTest {
	LoginPage loginPage;
	AddNew_CusPage addNewCusPage;

	@BeforeTest
	public void setUpSheetData() {
		System.out.println("************* Bắt đầu khởi tạo dữ liệu **********");
		loginPage = new LoginPage(driver);
		WebElement btn_NewCus = driver.findElement(By.xpath("//a[contains(text(),'New Customer')]"));
		loginPage.doLogin("mngr168086", "YqasUbu");
		btn_NewCus.click();
		addNewCusPage = new AddNew_CusPage(driver);
		ExcelUtil.setExcelFileSheet("AddNew_Cus");

	}

	@Test
	public void Addnew_Cus_Pass() {
		String expRs = ExcelUtil.getRowData(1).getCell(12).toString();
		String cusName = ExcelUtil.getRowData(1).getCell(1).toString();
		String gen_Male = ExcelUtil.getRowData(1).getCell(2).toString();
		String birthDate = ExcelUtil.getRowData(1).getCell(4).toString();
		String address = ExcelUtil.getRowData(1).getCell(5).toString();
		String city = ExcelUtil.getRowData(1).getCell(6).toString();
		String state = ExcelUtil.getRowData(1).getCell(7).toString();
		String pin = ExcelUtil.getRowData(1).getCell(8).toString();
		String mobile = ExcelUtil.getRowData(1).getCell(9).toString();
		String email = ExcelUtil.getRowData(1).getCell(10).toString();
		String pass = ExcelUtil.getRowData(1).getCell(11).toString();

		//addNewCusPage.doAddnewCus(cusName,gen_Male, birthDate, address, city, state, pin, mobile, email, pass);
		WebElement exp2 = driver.findElement(By.xpath("//tbody//tbody//tr[1]//td[1]"));
		Utils.compareText(exp2.getText(), expRs);
		ExcelUtil.setCellData("PASSED", 1, 14);

	}

}
