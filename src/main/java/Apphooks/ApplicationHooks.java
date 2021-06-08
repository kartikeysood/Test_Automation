package Apphooks;

import Utils.ConfigReader;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import qa.driverFactory;

import java.util.Properties;

public class ApplicationHooks {

    private driverFactory driverFactory;
    private WebDriver driver;
    private ConfigReader reader;
    Properties prop;

    @Before(order = 0)
    public void getProperty() {
        reader = new ConfigReader();
        prop = reader.init_prop();
    }

    @Before(order = 1)
    public void launchBrowser() {
        String browserName = prop.getProperty("browser");
        driverFactory = new driverFactory();
        driverFactory.init_driver(browserName);


    }

    @AfterStep
    public void takeScreenShot(Scenario scenario) {

        String screenshotName = scenario.getName().replaceAll(" ", "_");
        byte[] sourcePath = ((TakesScreenshot) driverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
        scenario.attach(sourcePath, "image/png", screenshotName);

    }


    @After(order = 0)

    public void quitBrowser() {

        driverFactory.getDriver().close();

    }

    @After(order = 1)
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            // take screenshot:
            String screenshotName = scenario.getName().replaceAll(" ", "_");
            byte[] sourcePath = ((TakesScreenshot) driverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(sourcePath, "image/png", screenshotName);

        }
    }


}
