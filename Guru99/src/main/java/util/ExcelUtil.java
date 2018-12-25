package util;

import static util.BaseTest.testDataExcelFileName;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Created by obaskirt on 28-Oct-17.
 */
public class ExcelUtil {
	// Main Directory of the project
	public static final String currentDir = System.getProperty("user.dir");

	// Location of Test data excel file
	public static String testDataExcelPath = null;

	// Excel WorkBook
	private static XSSFWorkbook excelWBook;

	// Excel Sheet
	private static XSSFSheet excelWSheet;

	// Excel cell
	private static XSSFCell cell;

	// Excel row
	private static XSSFRow row;

	// Row Number
	public static int rowNumber;

	// Column Number
	public static int columnNumber;

	// Setter and Getters of row and columns
	public static void setRowNumber(int pRowNumber) {
		rowNumber = pRowNumber;
	}

	public static int getRowNumber() {
		return rowNumber;
	}

	public static void setColumnNumber(int pColumnNumber) {
		columnNumber = pColumnNumber;
	}

	public static int getColumnNumber() {
		return columnNumber;
	}

	/** Lấy file Excel */
	public static void setExcelFileSheet(String sheetName) {
		testDataExcelPath = currentDir + "\\src\\main\\resources\\ExcelFile\\";
		try {
			FileInputStream ExcelFile = new FileInputStream(testDataExcelPath + testDataExcelFileName);
			excelWBook = new XSSFWorkbook(ExcelFile);
			excelWSheet = excelWBook.getSheet(sheetName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** Lấy dữ liệu của Cell theo số hàng */
	public static String getCellData(int RowNum, int ColNum) {
		try {
			cell = excelWSheet.getRow(RowNum).getCell(ColNum);
			DataFormatter formatter = new DataFormatter();
			// Ép kiểu cell về String
			String cellData = formatter.formatCellValue(cell);
			return cellData;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/** Lấy dữ liệu của Row theo số hàng */
	public static XSSFRow getRowData(int RowNum) {
		try {
			row = excelWSheet.getRow(RowNum);
			return row;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/** Insert kết quả vào file Excel */
	public static void setCellData(String value, int RowNum, int ColNum) {
		try {
			row = excelWSheet.getRow(RowNum);
			cell = row.getCell(ColNum);
			if (cell == null) {
				cell = row.createCell(ColNum);
				cell.setCellValue(value);
			} else {
				cell.setCellValue(value);
			}
			// Constant variables Test Data path and Test Data file name
			FileOutputStream fileOut = new FileOutputStream(testDataExcelPath + testDataExcelFileName);
			excelWBook.write(fileOut);
			fileOut.flush();
			fileOut.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static XSSFWorkbook getExcelWBook() {
		return excelWBook;
	}

	public static void setExcelWBook(XSSFWorkbook excelWBook) {
		ExcelUtil.excelWBook = excelWBook;
	}

	public static XSSFSheet getExcelWSheet() {
		return excelWSheet;
	}

	public static void setExcelWSheet(XSSFSheet excelWSheet) {
		ExcelUtil.excelWSheet = excelWSheet;
	}

	public static XSSFCell getCell() {
		return cell;
	}

	public static void setCell(XSSFCell cell) {
		ExcelUtil.cell = cell;
	}

	public static XSSFRow getRow() {
		return row;
	}

	public static void setRow(XSSFRow row) {
		ExcelUtil.row = row;
	}

}