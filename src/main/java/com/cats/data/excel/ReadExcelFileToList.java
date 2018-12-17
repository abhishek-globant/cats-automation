package com.cats.data.excel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import com.cats.data.Candidate;
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
	public static FileInputStream fis = null;
	public static FileOutputStream outputStream = null;
	public static Workbook workbook = null;
	public static FormulaEvaluator objFormulaEvaluator = null;
	public static DataFormatter objDefaultFormat = null;
	public static Sheet sheet = null;

	public Candidate[] readExcelData(String fileName, String userName) {

		try {
			fis = new FileInputStream(fileName);
			if(fileName.toLowerCase().endsWith("xlsx")){
				workbook = new XSSFWorkbook(fis);
			}else if(fileName.toLowerCase().endsWith("xls")){
				workbook = new HSSFWorkbook(fis);
			}
			CellStyle style = workbook.createCellStyle();
			outputStream = new FileOutputStream(fileName);
			objFormulaEvaluator = new XSSFFormulaEvaluator((XSSFWorkbook) workbook);
			objDefaultFormat = new DataFormatter();

			int numberOfSheets = workbook.getNumberOfSheets();
			sheet = workbook.getSheetAt(1);

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
//				System.out.println(sheet.getRow(i).getCell(14).getStringCellValue());
				if (sheet.getRow(i).getCell(2).getStringCellValue().toLowerCase().equals(userName.toLowerCase())
						&& ! sheet.getRow(i).getCell(14).getStringCellValue().toLowerCase().contains("join")
						&& ! sheet.getRow(i).getCell(14).getStringCellValue().toLowerCase().contains("offer")) {
					for(int j=0; j<=colCount ; j++) {
						switch (j) {
							case 3:
								candidates[i].setFirstName(sheet.getRow(i).getCell(j).getStringCellValue());
								break;
							case 4:
								candidates[i].setLastName(sheet.getRow(i).getCell(j).getStringCellValue());
								break;
							case 5:
								candidates[i].setEmailID(sheet.getRow(i).getCell(j).getStringCellValue());
								break;
							case 6:
								candidates[i].setPhoneNumber(sheet.getRow(i).getCell(j).getNumericCellValue());
								break;
							case 7:
								candidates[i].setPosition(sheet.getRow(i).getCell(j).getStringCellValue());
								break;
							case 8:
								candidates[i].setLocation(sheet.getRow(i).getCell(j).getStringCellValue());
								break;
						}
					}
					sheet.getRow(i).getCell(0).setCellValue("TOUCHED");
//					style.setFillBackgroundColor(IndexedColors.DARK_GREEN.getIndex());
//					sheet.getRow(i).setRowStyle(style);
				}
			}


			for(int i=1;i< candidates.length;i++) {
				if (sheet.getRow(i).getCell(2).getStringCellValue().toLowerCase().equals(userName.toLowerCase())) {
					System.out.println(candidates[i].getFirstName() + "   " + candidates[i].getLastName() + "   "
							+ candidates[i].getEmailID() + "   " + candidates[i].getPhoneNumber() + "   "
							+ candidates[i].getPosition() + "   " + candidates[i].getLocation());
				}
			}
			fis.close();
			workbook.write(outputStream);
			outputStream.flush();
			outputStream.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return candidates;
	}

	public String transformData(){


		return "";
	}



}