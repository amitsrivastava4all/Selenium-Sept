package com.srivastava.datadrivenframework;
import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;


public class ReadWriteExcel {
	
	public static void main(String[] args)throws Exception {
		readExcel();
	}
	
	public static Object[][] readExcel() throws Exception{
		boolean isPass = false;
		int rowStart = 0;
		int colStart = 0;
		Object  []array[] = new Object[3][2];
		// Read the XLS File from the Specified Location
		/*FileInputStream file =
				new FileInputStream("D:\\SeleniumJavaBatch2\\excel-files\\user.xls");*/
		System.out.println("Pathis "+ConfigReader.readExcelPath());
		FileInputStream file =
				new FileInputStream(ConfigReader.readExcelPath());
	HSSFWorkbook workBook = new HSSFWorkbook(file);
	HSSFSheet sheet = workBook.getSheetAt(0);
	Iterator<Row> rows = sheet.rowIterator();
	while(rows.hasNext()){
		Row currentRow = rows.next();
		Iterator<Cell> cells = currentRow.iterator();
		colStart=0;
		if(isPass==false){
			isPass=true;
			continue;
		}
		while(cells.hasNext()){
			Cell currentCell = cells.next();
			
			if(currentCell.getCellType()==Cell.CELL_TYPE_STRING){
				
				array[rowStart][colStart]=currentCell.getStringCellValue();
			}
			else
			if(currentCell.getCellType()==Cell.CELL_TYPE_NUMERIC){
				array[rowStart][colStart]=currentCell.getNumericCellValue();
			}
			colStart++;
		}
		rowStart++;
	}
	//*******************************************
	// Printing The 2-D Array
	for(int row = 0 ; row<rowStart; row++){
		for(int column = 0; column<colStart; column++){
			System.out.print(array[row][column]+"\t");
		}
		System.out.println();
	}
	
	workBook.close();
	file.close();
	return array;
	}

}
