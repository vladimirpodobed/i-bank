package Calculator;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.*;

public class Stepdefs {

    @Given("I am on the home insurance page")
    public void i_am_on_the_home_insurance_page() {
        Configuration.browser = "chrome";
        open("https://swedbank.ee/private/insurance/home/ihome?language=ENG");
        $("title")
                .waitUntil(Condition.exist,1000)
                .shouldHave(Condition.attribute("text", "Home insurance - Swedbank"));
    }


    @Then("I should see home insurance calculator")
    public void i_should_see_home_insurance_calculator() {
        $(".ico.-f.-tick.-caption")
                .click();

        $$(".calculator-container")
                .findBy(Condition.text("Calculate Home insurance monthly payment"))
                .scrollTo()
                .waitUntil(Condition.exist,1000);
    }

    @Then("should be able to select residential building type")
    public void should_be_able_to_select_residential_building_type() {
        $$("div.calculator-form dd.checkable-row label")
                .findBy(Condition.attribute("for", "radio2"))
                .waitUntil(Condition.exist,100)
                .click();
    }

    @Then("should be able to select wooden building")
    public void should_be_able_to_select_wooden_building() {
        $$("div.calculator-form dd.checkable-row label")
                .findBy(Condition.attribute("for", "radio5"))
                .waitUntil(Condition.exist,100)
                .click();
    }

    @Then("should be able to enter total area '100'")
    public void should_be_able_to_enter_total_area_100() {
        $$("div.calculator-form dd.fillable-row input")
                .findBy(Condition.attribute("id", "totalArea"))
                .waitUntil(Condition.exist,100)
                .setValue("100");
    }

    @Then("should be able to calculate monthly payment amount")
    public void should_be_able_to_calculate_monthly_payment_amount() {
        SelenideElement price = $$("div.calculator-results div#homeInsurancePriceBox dd.calculator-result span")
                .findBy(Condition.attribute("id", "homeInsurancePrice"));

        String priceOld = price.text();

        $$("div.form-button-bar a.button")
                .findBy(Condition.attribute("id", "calculateActive"))
                .waitUntil(Condition.exist,100)
                .click();

        if (priceOld == price.text()){
            Assert.fail("Price should have changed.");
        }
    }
}
