package com.cats.webdriver;

import com.cats.config.ConfigProps;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;


@Component
public class DriverManager {

//     WebDriver driver;
//     WebDriverWait wait;
//
//   public void getRemoteDriver() throws MalformedURLException {
//        Runtime.getRuntime().exec("java -jar selenium-server-standalone-3.9.1.jar -role hub");
//        Runtime.getRuntime().exec("java -jar -Dwebdriver.chrome.driver=./chromedriver.exe selenium-server-standalone-3.9.1.jar -role node -hub http://localhost:4444/grid/register -port 5555");
//
//        DesiredCapabilities cap = DesiredCapabilities.chrome();
//        cap.setBrowserName("chrome");
//        cap.setPlatform(org.openqa.selenium.Platform.WINDOWS);
//        driver = new RemoteWebDriver(new URL(""), cap);
//        driver.navigate().to("");
//        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//        wait = new WebDriverWait(driver,20);
//
//    }

    private static WebDriver driver;

    @Autowired
    ConfigProps configProps;

    public void launchBrowser(){
//        Path path = FileSystems.getDefault().getPath(".");
//        System.out.println("path " + path);
//        System.out.println("configProps.getPath() " + configProps.getChromeDriverPath());
//        System.setProperty("webdriver.chrome.driver",path + configProps.getChromeDriverPath());
//        this.driver= new ChromeDriver();
//        this.driver.get(configProps.getBaseUrl());
//        driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
//        driver.manage().window().maximize();
        WebDriverManager.chromedriver().setup();
        driver= new ChromeDriver();
        driver.get("https://www.google.com");

    }


    public static WebDriver getDriver()
    {
        return driver;
    }


    public static void close()
    {
        driver.close();
    }


    public static void quit()
    {
        driver.quit();
    }














}
