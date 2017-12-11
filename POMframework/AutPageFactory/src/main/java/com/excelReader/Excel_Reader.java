package com.excelReader;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Excel_Reader {

	public FileInputStream fis;
	public FileOutputStream fileout=null;
	public String path;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	
	public Excel_Reader(String path)
	{
		this.path=path;
		try{
			fis=new FileInputStream(path);
			workbook=new XSSFWorkbook(fis);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public String[][] getDataFromSheet(String sheetName,String excelName)
	{
		String dataSets[][]=null;
		try
		{
			//get sheet from excel workbook
			sheet=workbook.getSheet(sheetName);
			
			//count number of rows
			int totalRows=sheet.getLastRowNum()+1;
			
			//count number  of active columns
			int totalColumns=sheet.getRow(0).getLastCellNum();
			
			//create array of rows and columns
			dataSets=new String[totalRows-1][totalColumns];
			
			//run for loop and store data in array
			//this for loop will run on rows
			for(int i=1;i<totalRows-1;i++)
			{
				XSSFRow rows=sheet.getRow(i);
			
			
			//this for loop will run on column of that row
			for(int j=0;j<totalColumns;j++)
			{
				XSSFCell cell=rows.getCell(j);
			
			
			//if cell of type String
			if(cell.getCellType()==Cell.CELL_TYPE_STRING)
				dataSets[i-1][j]=cell.getStringCellValue();
			
			else
				//if cell of type number
				if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC)
				{
					String cellText=String.valueOf(cell.getNumericCellValue());
					dataSets[i-1][j]=cellText;
				}
				else
					//if cell type boolean,then this if condition will work
					dataSets[i-1][j]=String.valueOf(cell.getBooleanCellValue());
		}
		}
			return dataSets;
	}
		catch(Exception e)
		{
			System.out.println("exception in reading xlsx file" +e.getMessage());
			e.printStackTrace();
		}
		return dataSets;
	}
	
	public String getCellData(String sheetName, String colName, int rowNum) {
		try {
			int col_Num = 0;
			int index = workbook.getSheetIndex(sheetName);
			sheet = workbook.getSheetAt(index);
			XSSFRow row = sheet.getRow(0);
			for (int i = 0; i < row.getLastCellNum(); i++) {
				if (row.getCell(i).getStringCellValue().equals(colName)) {
					col_Num = i;
					break;
				}
			}
			row = sheet.getRow(rowNum - 1);
			
			XSSFCell cell = row.getCell(col_Num);
			if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
				return cell.getStringCellValue();
			} else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
				return "";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}