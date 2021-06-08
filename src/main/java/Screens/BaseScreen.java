package Screens;

import Utils.ConfigReader;
import com.aventstack.extentreports.ExtentReports;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import qa.driverFactory;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Properties;


public class BaseScreen {

    Properties prop = new Properties();

    ConfigReader reader = new ConfigReader();
    ExtentReports report = new ExtentReports();


    public void launchUrl(WebDriver driver) {

        prop = reader.init_prop();
        String url = prop.getProperty("URL");
        System.out.println("fetched url from prop file===" + url);
        driver.get(url);


    }

    public void waitforElement() {

    }

    public void clickOnelement(WebDriver dr1, String locatorType, String locatorvalue) {
        WebElement el = null;
        el = EleemntBy(dr1, locatorType, locatorvalue);

        el.click();
    }

    public void enterVAlueinTextFiedl(WebDriver dr1, String locatorType, String locatorvalue, String valuetobeenter) {
        WebElement el = null;
        el = EleemntBy(dr1, locatorType, locatorvalue);

        el.sendKeys(valuetobeenter);
    }

    public WebElement EleemntBy(WebDriver dr1, String locatorType, String locatorvalue) {
        WebElement el = null;

        try {
            if (locatorType.equalsIgnoreCase("linkText")) {
                el = dr1.findElement(By.linkText(locatorvalue));
            } else if (locatorType.equalsIgnoreCase("id")) {

                el = dr1.findElement(By.id(locatorvalue));
            } else if (locatorType.equalsIgnoreCase("xpath")) {

                el = dr1.findElement(By.xpath(locatorvalue));
            }
        } catch (NoSuchElementException e) {
            Assert.fail("No such element found" + e.getMessage());


        }
        return el;
    }


    public Map<String, String> getTestDataMap(DataTable dataTable) {

        Map<String, String> testdataMap = new HashMap<>();
        try {
            List<Map<String, String>> listValues = dataTable.asMaps(String.class, String.class);
            if (listValues.size() > 0) {
                testdataMap = listValues.get(0);
            } else {
                //stackify.logResult("Data table is not provided in test step of scenario", Status.Failed);
                Assert.fail("Data table is not provided in test step of scenario");
            }

        } catch (Exception e) {
            Assert.fail("Data table is not provided correctly, please check the exception: " + e.getMessage());
        }
        return testdataMap;
    }

    public String getTextFromField(WebDriver dr1, String locatorType, String locatorvalue) {
        String value = null;
        try {
            WebElement el = null;
            el = EleemntBy(dr1, locatorType, locatorvalue);

            value = el.getText();
        } catch (NoSuchElementException e) {
            Assert.fail("Element not found" + e.getMessage());
        }
        return value;

    }

    public void takeScreenShot(WebDriver dr1, String fileWithPath) throws IOException {
        try {
            TakesScreenshot scrShot = ((TakesScreenshot) dr1);
            byte[] SrcFile = scrShot.getScreenshotAs(OutputType.BYTES);
        } catch (Exception e) {
            Assert.fail("Unable to take screenshot");
        }

    }

}
