package com.cats;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cats.excel.read.ReadExcelFileToList;
import com.cats.service.CATSAutomationService;

import static java.lang.System.exit;

@SpringBootApplication
public class SpringBootConsoleApplication implements CommandLineRunner {

    @Autowired
    private CATSAutomationService catsAutomation;
    
    @Autowired
    private ReadExcelFileToList read;

    public static void main(String[] args) throws Exception {

        //disabled banner, don't want to see the spring logo
        SpringApplication app = new SpringApplication(SpringBootConsoleApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);

        //SpringApplication.run(SpringBootConsoleApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        if (args.length > 0 ) {
        	if(catsAutomation.getMessage(args[0].toString()).equalsIgnoreCase("CREATE")) {
        		System.out.println("Running CREATE Query on " + catsAutomation.getMessage(args[1].toString()));
        		read.readExcelData(catsAutomation.getMessage(args[1].toString()));
        		
        	}else if (catsAutomation.getMessage(args[0].toString()).equalsIgnoreCase("UPDATE")){
        		System.out.println("Running UPDATE Query on " + catsAutomation.getMessage(args[1].toString()));
        	}
        }else{
            System.out.println("Please Enter Valid Argument");
        }

        exit(0);
    }
}