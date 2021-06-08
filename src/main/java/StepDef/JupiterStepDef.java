package StepDef;


import Screens.BaseScreen;
import Screens.ContactScreen;
import Screens.HomeScreen;
import Screens.ShopScreen;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import qa.driverFactory;

import java.io.IOException;
import java.util.Map;

public class JupiterStepDef {
    WebDriver dr = driverFactory.getDriver();
    BaseScreen baseScreen = new BaseScreen();
    HomeScreen homeScreen = new HomeScreen();
    ContactScreen contactScreen = new ContactScreen();
    ShopScreen shopScreen = new ShopScreen();


    @Given("I navigate to home page")
    public void I_navigate_to_home_page() throws InterruptedException, IOException {

        baseScreen.launchUrl(dr);
        baseScreen.takeScreenShot(dr, "homepage");


    }

    @When("I click on contact link")
    public void i_click_on_contact_link() throws InterruptedException {

        homeScreen.clickOnContact(dr);
    }


    @When("I populate the form with below Data")
    public void I_populate_the_form_with_below_data(io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
        Map<String, String> testDataMap = baseScreen.getTestDataMap(dataTable);


        contactScreen.fillFormData(dr, dataTable);


    }

    @When("I click on submit link")
    public void i_click_on_submit_link() throws InterruptedException {

        contactScreen.clickOnSubmit(dr);


    }


    @And("I validate Error Message for fields")
    public void iValidateErrorMessageForFields(DataTable dataTable) {

        contactScreen.validateErrorMessage(dr, dataTable);
    }


    @Then("I validate the form is submitted successfully")
    public void i_validate_the_form_is_submitted_successfully() {

        contactScreen.validatefeedbackSubmitresponse(dr);

    }

    @And("I validate the error message for invalid email id")
    public void iValidateTheErrorMessageForInvalidEmailId() {

        contactScreen.validateErrorMessageforInvalidEmailId(dr);
    }

    @When("I click on shop link")
    public void iClickOnShopLink() {
        homeScreen.clickOnShop(dr);
    }


    @When("I click the cart")
    public void iClickTheCart() {
        shopScreen.clickOnCart(dr);
    }


    @When("I click on a item for {string}")
    public void iClickOnAItemFor(String arg0) {
        shopScreen.clickOnitem(dr, arg0);

    }

    @Then("I verify the items in the cart")
    public void iVerifyTheItemsInTheCart(DataTable dataTable) {


        shopScreen.verfiyIteamInCartDetail(dr, dataTable);
    }

    @And("I verify I am at {string} Page")
    public void iVerifyIAmAtPage(String arg0) {

        if (arg0.equalsIgnoreCase("Contact")) {
            contactScreen.verifyTitle(dr, "Jupiter Toys");
        }
        else if(arg0.equalsIgnoreCase("Shop")) {
            shopScreen.verifyTitle(dr, "Jupiter Toys");
        }
    }
}

