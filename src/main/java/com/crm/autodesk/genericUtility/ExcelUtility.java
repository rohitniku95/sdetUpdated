package com.crm.autodesk.genericUtility;

	import java.io.FileInputStream;
	import java.io.FileNotFoundException;
	import java.io.FileOutputStream;

	import org.apache.poi.ss.usermodel.Cell;
	import org.apache.poi.ss.usermodel.Row;
	import org.apache.poi.ss.usermodel.Sheet;
	import org.apache.poi.ss.usermodel.Workbook;
	import org.apache.poi.ss.usermodel.WorkbookFactory;
/**
 * its devloped using Apache Poi libraries, which is used to handle Microsoft Excel Sheet
 * @author Rohit
 *
 */
	public class ExcelUtility {
	/**
	 * used to get/read the data from the excel based on the below arguments
	 * @param sheetname
	 * @param rownum
	 * @param celnum
	 * @return
	 * @throws Throwable
	 */
		public String getDataFromExcel(String sheetname , int rownum , int celnum) throws Throwable {
			FileInputStream fis = new FileInputStream("./data/testScriptData.xlsx");
			Workbook wb = WorkbookFactory.create(fis);
			Sheet sh = wb.getSheet(sheetname);
			Row row = sh.getRow(rownum);
			String data = row.getCell(celnum).getStringCellValue();
			wb.close();
		    return data;
			
		}
		
		/**
		 * used to get the last used row number on specified Sheet
		 * @param sheetname
		 * @return
		 * @throws Throwable
		 */
		public int getRowCount(String sheetname) throws Throwable {
			FileInputStream fis = new FileInputStream("./data/testScriptData.xlsx");
			Workbook wb  = WorkbookFactory.create(fis);
			 Sheet sh = wb.getSheet(sheetname);
			 wb.close();
			 return sh.getLastRowNum();
			}

		public void setDataExcel(String sheetName , int rowNum, int celNum ,String data) throws Throwable {
			FileInputStream fis  = new FileInputStream("./data/testScriptData.xlsx");
			Workbook wb = WorkbookFactory.create(fis);
			Sheet sh = wb.getSheet(sheetName);
			Row row = sh.getRow(rowNum);
			Cell cel = row.createCell(celNum);
			cel.setCellValue(data);
			FileOutputStream fos = new FileOutputStream("./data/testScriptData.xlsx");
			wb.write(fos);
			wb.close();
			
		}
	}



