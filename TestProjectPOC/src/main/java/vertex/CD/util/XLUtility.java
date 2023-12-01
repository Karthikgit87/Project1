package vertex.CD.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtility {
	
	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static CellStyle style;
//	static String path="C:\\Automation\\Working Project\\TestProjectPOC\\src\\main\\java\\vertex\\CD\\testdata\\WorkOrderData.xlsx";
	
	XLUtility(String path)
	{
//		this.path=path;
	}

	public static int getRowCount (String path, String sheetName) throws IOException
	{
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		int rowcount = sheet.getLastRowNum();
		workbook.close();
		fi.close();
		
		return rowcount;
		
	}
	
	public static int getCellCount (String path, String sheetName, int rownum) throws IOException
	{
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rownum);
		int cellcount = row.getLastCellNum();
		workbook.close();
		fi.close();
		return cellcount;
		
	}
	
	public static String getCellData (String xlfile, String xlsheet, int rowNum, int colNum) throws IOException
	{
		fi = new FileInputStream(xlfile);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(xlsheet);
		row = sheet.getRow(rowNum);
		cell = row.getCell(colNum);
		String data;
		try
		{
			DataFormatter df = new DataFormatter();
			String cellData = df.formatCellValue(cell);
			return cellData;
		}
		catch(Exception e)
		{
			data = "";
		}
		workbook.close();
		fi.close();
		
		
		return data;
		
	}
	
	public static void setCellData(String xlfile,String xlsheet,int rownum,int colnum,String data) throws IOException
	{	
		int rowcount = sheet.getLastRowNum();

		fi=new FileInputStream(xlfile);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(xlsheet);
		row = sheet.createRow(rowcount+1);
		cell=row.createCell(colnum);
		cell.setCellValue(data);
		fo=new FileOutputStream(xlfile);
		workbook.write(fo);		
		workbook.close();
		fi.close();
		fo.close();
	}
	
	public static void deleteData(String xlfile,String xlsheet) throws IOException{	
		
		fi=new FileInputStream(xlfile);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(xlsheet);
		int rowcount = sheet.getLastRowNum();
		
		for(int i=rowcount; i>0; i--) {
			XSSFRow removingrow = sheet.getRow(i);
			sheet.removeRow(removingrow);
		}
		
		fo=new FileOutputStream(xlfile);
		workbook.write(fo);		
		workbook.close();
		fi.close();
		fo.close();
	}
	
	
	public static void setResultData(String xlfile,String xlsheet,String firstName, String lastName, String Email,String Phone, String City, String Pincode, String formattedResult, String date1) throws IOException
	{	
		

		fi=new FileInputStream(xlfile);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(xlsheet);
		int rowcount = sheet.getLastRowNum();
		
		row = sheet.createRow(rowcount+1);
		cell=row.createCell(0);
		cell.setCellValue(firstName);
		cell=row.createCell(1);
		cell.setCellValue(lastName);
		cell=row.createCell(2);
		cell.setCellValue(Email);
		cell=row.createCell(3);
		cell.setCellValue(Phone);
		cell=row.createCell(4);
		cell.setCellValue(City);
		cell=row.createCell(5);
		cell.setCellValue(Pincode);
		cell=row.createCell(6);
		cell.setCellValue(formattedResult);
		cell=row.createCell(7);
		cell.setCellValue(date1);
		fo=new FileOutputStream(xlfile);
		workbook.write(fo);		
		workbook.close();
		fi.close();
		fo.close();
	}
	
}
