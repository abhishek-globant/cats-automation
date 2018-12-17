package com.cats;

import com.cats.config.ConfigProps;
import com.cats.webdriver.DriverManager;
import com.cats.helpers.Helpers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cats.data.excel.ReadExcelFileToList;
import com.cats.test.Main;

import static java.lang.System.exit;

@SpringBootApplication
public class CATSAutomationMain implements CommandLineRunner {

    @Autowired
    public DriverManager driverManager;

    @Autowired
    private Main main;

//    @Autowired
//    private ReadExcelFileToList readExcelFileToList;

    @Autowired
    private Helpers helpers;

    @Autowired
    ConfigProps configProps;

    public static void main(String[] args) throws Exception {

        //disabled banner, don't want to see the spring logo
        SpringApplication app = new SpringApplication(CATSAutomationMain.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);

        //SpringApplication.run(SpringBootConsoleApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        if (args.length > 0 ) {
        	if(configProps.getMessage(args[0].toString()).equalsIgnoreCase("CREATE")) {
        		System.out.println("Running CREATE Query on " + configProps.getMessage(args[1].toString()) +" for user : " + configProps.getMessage(args[2].toString()));

                main.startExecution(configProps.getMessage(args[1].toString()),configProps.getMessage(args[2].toString()));

        		
        	}else if (configProps.getMessage(args[0].toString()).equalsIgnoreCase("UPDATE")){
        		System.out.println("Running UPDATE Query on " + configProps.getMessage(args[1].toString()) +" for user : " + configProps.getMessage(args[2].toString()));
        	}
        }else{
//            System.out.println("Please Enter Valid Argument");
            main.startExecution("D://abhishek.prasad//cats-automation//CATs_automation.xlsx","Neha");
        }

        exit(0);
    }
}