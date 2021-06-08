package Screens;

import Utils.ConfigReader;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import qa.driverFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class ShopScreen {

    WebDriver dr = driverFactory.getDriver();
    Properties prop = new Properties();
    WebDriver driver;
    ConfigReader reader = new ConfigReader();
    BaseScreen baseScreen = new BaseScreen();
    public static final String funnyCowXpath = "//*[@class='products ng-scope']/ul/li[6]/div/p/a";
    public static final String FluffyBunnyXpath = "//*[@class='products ng-scope']/ul/li[4]/div/p/a";
    public static final String cartXpath = "//*[@id='nav-cart']/a";
    public static final String cartTableXpath = "//table[@class='table table-striped cart-items']";


    public void verifyTitle(WebDriver webDriver, String expectedTitle) {
        String actualTitle = webDriver.getTitle();
        System.out.println("Actual titile==" + webDriver.getTitle());
        if (expectedTitle.equalsIgnoreCase(actualTitle)) {
            System.out.println("Titles match");

        }
    }


    public void clickOnitem(WebDriver dr1, String itemName) {
        if (itemName.equalsIgnoreCase("Funny Cow"))
            baseScreen.clickOnelement(dr1, "xpath", funnyCowXpath);
        else if (itemName.equalsIgnoreCase("Fluffy Bunny"))
            baseScreen.clickOnelement(dr1, "xpath", FluffyBunnyXpath);

    }


    public void clickOnCart(WebDriver dr1) {
        baseScreen.clickOnelement(dr1, "xpath", cartXpath);

    }

    public void verfiyIteamInCartDetail(WebDriver dr1, DataTable datatable) {

        Map<String, String> testDataMap = baseScreen.getTestDataMap(datatable);

        String ExpectedItemName = testDataMap.get("itemName");
        String ExpectedQuantityName = testDataMap.get("Quantity");

        WebElement BooksTable = dr1.findElement(By.xpath(cartTableXpath));
        List<WebElement> rowVals = BooksTable.findElements(By.tagName("tr"));
        List<WebElement> colHeader = rowVals.get(0).findElements(By.tagName("th"));

        for (int i = 1; i < rowVals.size() - 2; i++) {
            List<WebElement> colVals = rowVals.get(i).findElements(By.tagName("td"));
            String ActualtemName = colVals.get(0).getText();

            System.out.println(colVals.get(0).getText());
            if (ActualtemName.equalsIgnoreCase(ExpectedItemName)) {

                System.out.println(dr1.findElement(By.

                        xpath("//table[@class='table table-striped cart-items']/tbody/tr[" + i + "]/td[3]/input"))
                        .getAttribute("value"));

                String ActualQuantityName = dr1.findElement(By.xpath("//table[@class='table table-striped cart-items']/tbody/tr[" + i + "]/td[3]/input"))
                        .getAttribute("value");

                if (ActualtemName.trim().equalsIgnoreCase(ExpectedItemName.trim()) && ActualQuantityName.trim().equalsIgnoreCase(ExpectedQuantityName.trim())) {

                    System.out.println("Actual item name:" + ActualtemName + " And Expected item name" + ExpectedItemName + " :are matched");
                    System.out.println("Actual quantity :" + ActualQuantityName + "And expected quantity " + ExpectedQuantityName + " :are matched");

                } else {
                    System.out.println("Actual iteam name: " + ActualtemName + "And Expected iteam name " + ExpectedItemName + " :are not matched");
                    System.out.println("Actual quantity : " + ActualQuantityName + "And  expected quantity " + ExpectedQuantityName + ": are not matched");
                    Assert.fail("Actual and Expected are mismatched ");

                }


            }
        }

    }


}