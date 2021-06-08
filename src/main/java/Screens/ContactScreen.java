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

public class ContactScreen {

    WebDriver dr = driverFactory.getDriver();
    Properties prop = new Properties();
    WebDriver driver;
    ConfigReader reader = new ConfigReader();
    BaseScreen baseScreen = new BaseScreen();

    public void verifyTitle(WebDriver webDriver, String expectedTitle) {
        String actualTitle = webDriver.getTitle();
        System.out.println(webDriver.getTitle());
        if (expectedTitle.equalsIgnoreCase(actualTitle)) {
            System.out.println("Titles match");

        } else {
            Assert.fail("Titles mistmatch");
        }
    }

    public void enterVAlueinTextfield(WebDriver webDriver, String l) {
        baseScreen.clickOnelement(webDriver, "LinkTest", "Contact");

    }

    public void fillFormData(WebDriver dr, DataTable dt) {
        Map<String, String> testDataMap = baseScreen.getTestDataMap(dt);
        baseScreen.enterVAlueinTextFiedl(dr, "id", "forename", testDataMap.get("Forename"));

        baseScreen.enterVAlueinTextFiedl(dr, "id", "surname", testDataMap.get("Surname"));
        baseScreen.enterVAlueinTextFiedl(dr, "id", "email", testDataMap.get("Email"));

        int size = dr.findElements(By.id("email-err")).size();
        System.out.println("size==" + size);
        if (size > 0) {
            String errorMessage = baseScreen.getTextFromField(dr, "id", "email-err");
            if (errorMessage.equalsIgnoreCase("Please enter a valid email"))
                System.out.println("invalid email id provided.Please provide valid email");
        }

        baseScreen.enterVAlueinTextFiedl(dr, "id", "telephone", testDataMap.get("Telephone"));
        baseScreen.enterVAlueinTextFiedl(dr, "id", "message", testDataMap.get("Message"));


    }

    public void clickOnSubmit(WebDriver webDriver) {
        baseScreen.clickOnelement(webDriver, "linkText", "Submit");
    }

    public void validateErrorMessage(WebDriver dr, DataTable dt) {

        List<Map<String, String>> listValues = dt.asMaps(String.class, String.class);
        Map<String, String> mapValues = new HashMap<>();
        int rowsCount = listValues.size();
        String forname = null;
        if (rowsCount > 0) {
            for (int i = 0; i < rowsCount; i++) {
                mapValues = listValues.get(i);

                System.out.println("passed value from datatable" + mapValues.get("Field"));
                if (mapValues.get("Field").equalsIgnoreCase("Forename")) {
                    forname = baseScreen.getTextFromField(dr, "id", "forename-err");
                    System.out.println("value fetched  from object==" + forname);
                    System.out.println("expected value==" + mapValues.get("ErrorMessage"));
                    if (forname.trim().equals(mapValues.get("ErrorMessage").trim())) {
                        //  Assert.assertEquals(forname,testDataMap.get("ErrorMessage"));
                        System.out.println("Validation passed for forename");
                    }
                } else if (mapValues.get("Field").equalsIgnoreCase("Email")) {
                    forname = baseScreen.getTextFromField(dr, "id", "email-err");
                    System.out.println("value fetched  from object==" + forname);
                    System.out.println("value pass from table" + mapValues.get("ErrorMessage").trim());
                    if (forname.trim().equals(mapValues.get("ErrorMessage").trim())) {
                        //  Assert.assertEquals(forname,testDataMap.get("ErrorMessage"));
                        System.out.println("Validation passed for email");
                    }
                } else if (mapValues.get("Field").equalsIgnoreCase("Message")) {
                    forname = baseScreen.getTextFromField(dr, "id", "message-err");
                    System.out.println("actual velue fetched from object===" + forname);
                    System.out.println("passed from table---" + mapValues.get("ErrorMessage").trim());
                    if (forname.trim().equals(mapValues.get("ErrorMessage").trim())) {
                        //  Assert.assertEquals(forname,testDataMap.get("ErrorMessage"));
                        System.out.println("Validation passed for message");
                    }
                }
            }
        }


    }

    public void validatefeedbackSubmitresponse(WebDriver dr) {


        String text = baseScreen.getTextFromField(dr, "xpath", "//*[@class='alert alert-success']");
        System.out.println("text==" + text);
        if (text.contains("we appreciate your feedback")) {
            System.out.println("validate feedback Submit response");
        }
    }

    public void validateErrorMessageforInvalidEmailId(WebDriver dr) {
        int size = dr.findElements(By.id("email-err")).size();
        System.out.println("size==" + size);
        if (size > 0) {
            String errorMessage = baseScreen.getTextFromField(dr, "id", "email-err");
            if (errorMessage.equalsIgnoreCase("Please enter a valid email"))
                System.out.println("invalid email id provided.Please provide valid email");

        }
    }
}
