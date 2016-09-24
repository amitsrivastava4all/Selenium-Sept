package com.srivastava.keyworddrivenframework;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

/*
 * This class read the rows from Excel and Store in CommandList 
 * And CommandList has CommandDTO Objects 
 * CommandDTO contains commandName , target and value
 */

public class ExcelReader {

	public static List<CommandDTO> readExcel() throws Exception{
		ArrayList<CommandDTO> commandList =new ArrayList<CommandDTO>();
		CommandDTO commandDTO = null;
		int colCount = 0;
		FileInputStream file =
				new FileInputStream(ConfigReader.readExcelPath());
	HSSFWorkbook workBook = new HSSFWorkbook(file);
	HSSFSheet sheet = workBook.getSheetAt(0);
	Iterator<Row> rows = sheet.rowIterator();
	while(rows.hasNext()){
		Row currentRow = rows.next();
		Iterator<Cell> cells = currentRow.iterator();
		colCount = 1;
		commandDTO = new CommandDTO();
		while(cells.hasNext()){
			Cell currentCell = cells.next();
			
			if(colCount==1){
			commandDTO.setCommand(currentCell.getStringCellValue());
			}
			else
			if(colCount==2){
			commandDTO.setTarget(currentCell.getStringCellValue());	
			}
			else
			if(colCount==3){
			commandDTO.setValue(currentCell.getStringCellValue());	
			}
			colCount++;
		}
		commandList.add(commandDTO);
	}
	workBook.close();
	file.close();
	return commandList;
}
}