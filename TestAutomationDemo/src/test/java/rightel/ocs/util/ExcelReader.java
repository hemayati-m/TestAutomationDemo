package rightel.ocs.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	public String path;
	public FileInputStream fis = null;
	public FileOutputStream fileout = null;
	private XSSFWorkbook workbook = null;
	private XSSFSheet sheet = null;
	private XSSFRow row = null;
	private XSSFCell cell = null;

	public ExcelReader(String path) {
		this.path = path;
		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(path);
			sheet = workbook.getSheetAt(0);
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getRowCount(String sheetName) {
		int index = workbook.getSheetIndex(sheetName);
		if (index == -1)
			return 0;
		else {
			sheet = workbook.getSheetAt(index);
			int rowNum = sheet.getLastRowNum() + 1;
			return rowNum;
		}

	}

	public int getColCount(String sheetName) {
		int index = workbook.getSheetIndex(sheetName);
		if (index == -1)
			return 0;
		else {
			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(0);
			int colnum = row.getLastCellNum();
			return colnum;
		}
	}

	public String getCellData(String sheetName, int rowNum, int colNum) {


		try {

			int index = workbook.getSheetIndex(sheetName);
			if (index == -1)
				return "NULL";
			else {
				rowNum = rowNum - 1;
				colNum = colNum - 1;
				sheet = workbook.getSheetAt(index);
				cell = sheet.getRow(rowNum).getCell(colNum);

				switch (cell.getCellType()) {

				case STRING:
					return cell.getStringCellValue();
				case NUMERIC:
					return  String.format("%.0f", cell.getNumericCellValue()); 
				case BOOLEAN:
					return Boolean.toString(cell.getBooleanCellValue());
				default:
					return null;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return "false";
	}

	public String[] readRowData(String sheetName, int rowNum) {

		List<String> cellItems = new ArrayList<String>();
		String[] strArray = null;

		try {

			File f = new File(
					System.getProperty("user.dir") + "\\src\\main\\java\\behsa\\crm\\properties\\testdata.xlsx");
			FileInputStream fi = new FileInputStream(f);
			Workbook testdata = WorkbookFactory.create(fi);
			Sheet sheet = testdata.getSheet(sheetName);
			// Insert cell data into list
			Row row = sheet.getRow(rowNum - 1);

			for (Cell cell : row) {
				switch (cell.getCellType()) {
				case STRING:
					cellItems.add(cell.getStringCellValue());
					break;
				case NUMERIC:
					cellItems.add(Double.toString(cell.getNumericCellValue()));
					break;
				case BLANK:
					break;
				case BOOLEAN:
					cellItems.add(Boolean.toString(cell.getBooleanCellValue()));
					break;
				case ERROR:
					break;
				case FORMULA:
					break;
				case _NONE:
					break;
				default:
					break;
				}
			}

			// Convert list to array
			strArray = new String[cellItems.size()];
			strArray = cellItems.toArray(strArray);

		} catch (Exception e) {
			System.out.println("This row is not available");
		}

		return strArray;
	}

	public int getCellId(String cellName) {

		int cell = 1;
		String value;
		try {
			do {

				try {
					value = getCellData("Sanity", 1, cell).trim();
					if (cellName.equalsIgnoreCase(value)) {

						return cell;

					}
					cell++;

				} catch (NullPointerException n) {
					break;
				}

			} while (!value.equals(null));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int getTestCaseRow(String testcaseName) {

		int row = 2;
		String value;

		do {
			try {
				value = getCellData("Sanity", row, 1).trim();
				if (testcaseName.equalsIgnoreCase(value)) {
					return row;
				}
				row++;

			} catch (NullPointerException n) {
				break;
			}

		} while (!value.equals(null));

		return 0;

	}

}
