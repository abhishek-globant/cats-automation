package com.cats.excel.read;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

@Service
public class ReadExcelFileToList {
	public int rowCount=0;
	public int colCount=0;
	public Candidate candidates[];

	public void readExcelData(String fileName, String userName) {

		try {
			FileInputStream fis = new FileInputStream(fileName);

			Workbook workbook = null;
			if(fileName.toLowerCase().endsWith("xlsx")){
				workbook = new XSSFWorkbook(fis);
			}else if(fileName.toLowerCase().endsWith("xls")){
				workbook = new HSSFWorkbook(fis);
			}
			FormulaEvaluator objFormulaEvaluator = new XSSFFormulaEvaluator((XSSFWorkbook) workbook);
			DataFormatter objDefaultFormat = new DataFormatter();

			int numberOfSheets = workbook.getNumberOfSheets();
			Sheet sheet = workbook.getSheetAt(1);

			Iterator<Row> rowIterator = sheet.iterator();

			rowCount = sheet.getLastRowNum();
			colCount = sheet.getRow(0).getLastCellNum() -1;
			System.out.println("rowCount : " + rowCount);
			System.out.println("colCount : " + colCount);
			candidates = new Candidate[rowCount+1];
			for(int i=0;i< candidates.length;i++){
				candidates[i] = new Candidate();
			}
			System.out.println("Total Candidates : " + candidates.length);



			for(int i=1; i <=rowCount ; i++ ){
				if (sheet.getRow(i).getCell(1).getStringCellValue().toLowerCase().equals(userName.toLowerCase())) {
					for(int j=0; j<=colCount ; j++) {
						switch (j) {
							case 2:
								candidates[i].setFirstName(sheet.getRow(i).getCell(j).getStringCellValue());
								break;
							case 3:
								candidates[i].setLastName(sheet.getRow(i).getCell(j).getStringCellValue());
								break;
							case 5:
								candidates[i].setPhoneNumber(sheet.getRow(i).getCell(j).getNumericCellValue());
								break;
							case 4:
								candidates[i].setEmailID(sheet.getRow(i).getCell(j).getStringCellValue());
								break;
							case 7:
								candidates[i].setLocation(sheet.getRow(i).getCell(j).getStringCellValue());
								break;
							case 6:
								candidates[i].setPosition(sheet.getRow(i).getCell(j).getStringCellValue());
								break;
						}
					}
				}
			}


			for(int i=1;i< candidates.length;i++) {
				if (sheet.getRow(i).getCell(1).getStringCellValue().toLowerCase().equals(userName.toLowerCase())) {
					System.out.println(candidates[i].getFirstName() + "   " + candidates[i].getLastName() + "   "
							+ candidates[i].getEmailID() + "   " + candidates[i].getPhoneNumber() + "   "
							+ candidates[i].getPosition() + "   " + candidates[i].getLocation());
				}
			}

			fis.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String transformData(){


		return "";
	}



}