package com.cats.pages;

import com.cats.webdriver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Component
public class LoginPage {

    @Autowired
    DriverManager driverManager;
    private static WebDriver driver;

    public void initLoginPageElements() {
        this.driver = driverManager.getDriver();
        PageFactory.initElements(driver, this);
    }
//
//    @PostConstruct
//    private void init() {
//        System.out.println("Instantiate Login Page...");
//        this.driver = driverManager.getDriver();
//        PageFactory.initElements(driver, this);
//    }

    @FindBy(css="#formBlock .goauthcontainer .google-text a")
    private WebElement signInWithGoogle;

    public void isDisplayedSignInForm() {
        signInWithGoogle.click();
    }






}

