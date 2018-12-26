package util;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class Utils extends BaseTest{

	// Hàm tìm đến đường dẫn file properties
	public static String getAbsoluteFilePath(String relativeFilepath) {
		String curDir = System.getProperty("user.dir");
		String absolutePath = curDir + "/src/main/resources/Properties/" + relativeFilepath;
		return absolutePath;

	}

	// load file vừa tìm được lên
	public static Properties loadProperties(String fileName) {
		Properties configProperies = null;
		FileReader in;
		try {
			in = new FileReader(getAbsoluteFilePath(fileName));
			configProperies = new Properties();
			configProperies.load(in);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return configProperies;
	}
// Tìm element
	public static WebElement getElement(By loc) {
		WebElement ele = null;
		try {
			ele = driver.findElement(loc);
		} catch (Exception e) {
			System.out.println("Can not find Element with locator : " + loc);
			e.printStackTrace();
		}
		return ele;
	}
// Kiểm tra tồn tại element
	public static void checkElementExist(By loc) {
		if (getElement(loc) != null) {
			System.out.println("Element with locator : " + loc + " is exist");
		} else {
			System.out.println("Element with locator : " + loc + " is not exist");
			Assert.assertTrue(false);
		}
	}

// Lấy tên element
	public static String getTextElement(By loc) {
		String text = "";
		WebElement ele = getElement(loc);
		if (ele != null) {
			if (ele.getTagName().contains("input") || ele.getTagName().contains("textarea")) {
				text = ele.getAttribute("value");
			} else {
				text = ele.getText();
			}
		}
		return text;
	}
// So sánh text
	public static boolean compareText(String act, String exp) {
		System.out.println("Expected is :" + exp);
		System.out.println("Actual is :" + act);
		if (!exp.equals(act)) {
			Assert.assertTrue(false);
			return false;
		}
		return true;
	}
// Lấy value qua key	
	public static By getValueByKey(String key,Properties PropName) {
		By rs = By.xpath(PropName.getProperty(key));
		return rs;
		
	}

}
