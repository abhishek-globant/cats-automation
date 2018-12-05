package com.cats.excel.read;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ch.qos.logback.core.CoreConstants;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

@Service
public class ReadExcelFileToList {

	public /*List<Candidate>*/ void readExcelData(String fileName) {
//		List<Candidate> candidates = new ArrayList<Candidate>();
		
		try {
			//Create the input stream from the xlsx/xls file
			FileInputStream fis = new FileInputStream(fileName);
			
			//Create Workbook instance for xlsx/xls file input stream
			Workbook workbook = null;
			if(fileName.toLowerCase().endsWith("xlsx")){
				workbook = new XSSFWorkbook(fis);
			}else if(fileName.toLowerCase().endsWith("xls")){
				workbook = new HSSFWorkbook(fis);
			}
			
			//Get the number of sheets in the xlsx file
			int numberOfSheets = workbook.getNumberOfSheets();
			
			//loop through each of the sheets
			for(int i=0; i < numberOfSheets; i++){
				
				//Get the nth sheet from the workbook
				Sheet sheet = workbook.getSheetAt(i);
				
				//every sheet has rows, iterate over them
				Iterator<Row> rowIterator = sheet.iterator();
				while (rowIterator.hasNext()) 
		        {
					//Get the row object
					Row row = rowIterator.next();
					//Every row has columns, get the column iterator and iterate over them
					Iterator<Cell> cellIterator = row.cellIterator();
		             
		            while (cellIterator.hasNext()) 
		            {
		            	//Get the Cell object
		            	Cell cell = cellIterator.next();
//						System.out.println(cell.getCellType());
		            	if(cell.getCellType()== 0){
							System.out.print("[(" + cell.getRowIndex() + "," + cell.getColumnIndex() + "): "+cell.getNumericCellValue() + "]");
						}else if(cell.getCellType() ==1) {
							System.out.print("[(" + cell.getRowIndex() + "," + cell.getColumnIndex() + "): "+cell.getStringCellValue() + "]");
						}



		            	
		            	
		            }
					System.out.println();//end of cell iterator
//		            Candidate c = new Candidate(name, shortCode);
//		            countriesList.add(c);
		        } //end of rows iterator
				
				
			} //end of sheets for loop
			
			//close file input stream
			fis.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/*return countriesList;*/
	}
}