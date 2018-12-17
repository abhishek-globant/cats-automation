package com.cats.helpers;


import com.cats.data.Candidate;
import com.cats.webdriver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@Component
public class Helpers {


    @Autowired
    DriverManager driverManager;

    private static WebDriver driver;
    private static Workbook workbook = null;
    private static FileInputStream fis = null;
    private static FileOutputStream fos=null;


    @PostConstruct
    private void init() {
        System.out.println("Instantiate Helpers...");
        driver = driverManager.getDriver();
    }


    public void navigateTo() {
        driver = driverManager.getDriver();
        driver.get("https://in.linkedin.com/");
    }

    public void closeSession(){
        driver.close();
        driver.quit();
    }



    public void printData(Candidate[] candidates) {
        for (int i = 1; i < candidates.length; i++) {
            if (candidates[i].getFirstName()!= null) {
                System.out.println(candidates[i].getFirstName() + "   " + candidates[i].getLastName() + "   "
                        + candidates[i].getEmailID() + "   " + candidates[i].getPhoneNumber() + "   "
                        + candidates[i].getPosition() + "   " + candidates[i].getLocation());
            }
        }
    }

    public static FileInputStream getFileInputStream(String fileName) throws IOException{
        if(fis==null) {
            fis = new FileInputStream(fileName);
        }
        return fis;
    }

    public static FileOutputStream getFileOutputStream(String fileName) throws IOException{
        if(fos==null) {
            fos = new FileOutputStream(fileName);
        }
        return fos;
    }

    public static Workbook getWorkBook(String fileName) throws IOException {
        if(workbook ==null){
            if(fileName.toLowerCase().endsWith("xlsx")){
                workbook = new XSSFWorkbook(fis);
            }else if(fileName.toLowerCase().endsWith("xls")){
                workbook = new HSSFWorkbook(fis);
            }
        }
        return workbook;
    }


}
