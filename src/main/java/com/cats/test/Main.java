package com.cats.test;

import com.cats.data.Candidate;
import com.cats.data.excel.ReadExcelFileToList;
import com.cats.helpers.Helpers;
import com.cats.pages.LoginPage;
import com.cats.webdriver.DriverManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class Main {

    @Autowired
    private ReadExcelFileToList readExcelFileToList;

    @Autowired
    private DriverManager driverManager;

    @Autowired
    private Helpers helpers;

    @Autowired
    private LoginPage loginPage;


    Candidate candidates[];

    public void startExecution(String fileName, String userName) {
        candidates = readExcelFileToList.readExcelData(fileName,userName);
        helpers.printData(candidates);
        driverManager.launchBrowser();
//        helpers.navigateTo();
//        new LoginPage();
        loginPage.initLoginPageElements();
        createQuery();
//        helpers.closeSession();
    }

    public void createQuery(){
        loginPage.isDisplayedSignInForm();
    }



}
