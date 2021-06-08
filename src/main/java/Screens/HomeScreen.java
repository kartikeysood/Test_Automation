package Screens;

import Utils.ConfigReader;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import qa.driverFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class HomeScreen {

    WebDriver dr = driverFactory.getDriver();
    Properties prop = new Properties();
    WebDriver driver;
    ConfigReader reader = new ConfigReader();
    BaseScreen baseScreen = new BaseScreen();

    public void clickOnContact(WebDriver webDriver) {
        baseScreen.clickOnelement(webDriver, "linkText", "Contact");

    }

    public void clickOnShop(WebDriver webDriver) {
        baseScreen.clickOnelement(webDriver, "linkText", "Shop");

    }


}
