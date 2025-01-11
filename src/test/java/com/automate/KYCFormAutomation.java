package com.automate;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import java.util.concurrent.TimeUnit;

public class KYCFormAutomation {

    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\DELL\\Downloads\\chrome1\\chromedriver-win64 (1)\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(05, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://prod-kyc-tijoree.web.app/?i=EOvwwQYQKrznygYTNTCEKJ88fLpvy41736399935065");


        /// Initialize ExtentReports
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("test-output/ExtentReport.html");
        sparkReporter.config().setDocumentTitle("Automation Report");
        sparkReporter.config().setReportName("Form Submission Test Report");

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Tester", "Sagar Waje");
        extent.setSystemInfo("Browser", "Chrome");
    }

    @Test(priority = 1)
    public void verifyHeading() {
        test = extent.createTest("Verify Heading");
        try{
        WebElement heading = driver.findElement(By.xpath("//span[@tabindex=\"-1\"]"));
        Assert.assertTrue(heading.getText().contains("Onboarding KYC for Tijoree"), "Heading does not contain the expected text.");
        test.log(Status.PASS, "Heading verified successfully.");
    } catch (Exception e) {
        test.log(Status.FAIL, "Heading verification failed: " + e.getMessage());
    };
    }

    @Test(priority = 2)
    public void uplaodDocAndSubmitButton() {
        test = extent.createTest("Uplaod Documents and Submit Button");
        // Upload required documents (adjust locators as needed)
        try{

        WebElement uploadDocument1 = driver.findElement(By.xpath("//flt-semantics[@id=\"flt-semantic-node-12\"]"));
        uploadDocument1.sendKeys("C:\\Users\\DELL\\Downloads\\testKYC\\test1.jpg");


        WebElement uploadDocument2 = driver.findElement(By.xpath("//flt-semantics[@id=\"flt-semantic-node-15\"]"));
        uploadDocument1.sendKeys("C:\\Users\\DELL\\Downloads\\testKYC\\test2.jpg");


        WebElement uploadDocument3 = driver.findElement(By.xpath("//flt-semantics[@id=\"flt-semantic-node-18\"]"));
        uploadDocument1.sendKeys("C:\\Users\\DELL\\Downloads\\testKYC\\test2.jpg");



        // Submit the KYC documents
        WebElement submitButton = driver.findElement(By.xpath("//span[contains(text(),'Submit Button')]"));
        submitButton.click();

        WebElement confirmationMessage = driver.findElement(By.id("confirmationMessage"));
        Assert.assertEquals(confirmationMessage.getText(), "Form submitted successfully!");
        test.log(Status.PASS, "Form submitted and confirmation message verified.");
    } catch (Exception e) {
        test.log(Status.FAIL, "Form submission failed: " + e.getMessage());
    }

    }
    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
