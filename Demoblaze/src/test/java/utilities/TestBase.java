package utilities;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;

public class TestBase {
	public WebDriver driver;
    public ExtentReports extent;
    public ExtentTest test;
    public Logger logger;

    @BeforeSuite
    public void setupExtent() {
        ExtentSparkReporter spark = new ExtentSparkReporter("Reports/DemoblazeReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        logger = Logger.getLogger("DemoblazeLogger");
        PropertyConfigurator.configure("src/main/resources/log4j.properties");

        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com/");
        logger.info("Browser launched and Demoblaze opened.");
    }

    @AfterMethod
    public void tearDownMethod(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            String screenshotPath = takeScreenshot(result.getName());
            test.fail(result.getThrowable());
            test.addScreenCaptureFromPath(screenshotPath);
            logger.error("Test Failed: " + result.getName());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.pass("Test Passed: " + result.getName());
            logger.info("Test Passed: " + result.getName());
        }
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
        logger.info("Browser closed.");
    }

    @AfterSuite
    public void flushReport() {
        extent.flush();
    }

    public String takeScreenshot(String testName) throws IOException {
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String destination = "Screenshots/" + testName + "_" + dateName + ".png";
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);
        return destination;
    }
	

}
