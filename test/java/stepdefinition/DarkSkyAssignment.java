package stepdefinition;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import framework.webPages.BasePage;
import framework.webPages.DarkSkyHomePage;
import framework.webPages.DarkskyRegister;
import org.testng.Assert;

import java.text.NumberFormat;
import java.text.ParseException;

public class DarkSkyAssignment extends BasePage {
    private String daily;
    private String CurrentTemperature;
    private int minimum;
    private int maximum;

    private DarkskyRegister darkskyregister = new DarkskyRegister();
    private DarkSkyHomePage dp = new DarkSkyHomePage();

    @Given("^I am on Darksky Home Page$")
    public void iAmOnDarkskyHomePage() {

        Assert.assertEquals(SharedSD.getDriver().getTitle(), "Dark Sky - Broadway, New York, NY", "Invalid Home Page");
    }

    @Then("I verify current temp is not greater or less then temps from daily timeline")
    public void verifyCurrentTemp(){
        dp.getCurrentTemp();
        dp.timelineTemps();
        dp.validatingCurrentTempAgainstTimeline();
    }


    @Then("I verify timeline is displayed with two hours incremented")
    public void verifyTimelineIncrement(){
        dp.timelineHours();
        dp.intConversion();
        dp.twoHourIncrementCheck();
    }

    @When("^I expand todays timelineFeature: Login feature$")
    public void iExpandTodaysTimelineFeatureLoginFeature() throws ParseException {
        CurrentTemperature = dp.getElementList().get(0).getText();
        NumberFormat nft = NumberFormat.getInstance();
        Number num = nft.parse(CurrentTemperature);
        CurrentTemperature = num.toString();
        int value = Integer.parseInt(CurrentTemperature);
        maximum = value;
        minimum = value;
        for (int i = 1; i < dp.getElementList().size(); i++) {
            daily = dp.getElementList().get(i).getText();
           int dailyTemperature = Integer.parseInt((NumberFormat.getInstance().parse(daily).toString()));
            if (dailyTemperature < minimum) {
                minimum = dailyTemperature;
            } else if (dailyTemperature > maximum) {
                maximum = dailyTemperature;
            }

        }
    }


    @Then("^I verify lowest and highest temp is displayed correctly$")
    public void iVerifyLowestAndHighestTempIsDisplayedCorrectly() {
      // System.out.println(minimum); 
        System.out.println(minimum);
        System.out.println(maximum);

    }

    @Given("^I am on the darksky Register page$")
    public void iAmOnTheDarkskyRegisterPage() {
        darkskyregister.clickOndarkSkyApi();
        darkskyregister.clickOnsignup();

    }

    @When("^I click on Register button$")
    public void iClickOnRegisterButton() {
        darkskyregister.clickOnregister();
    }


    @Then("^I verify I am on Register page by asserting Register header$")
    public void iVerifyIAmOnRegisterPageByAssertingRegisterHeader()  {
        Assert.assertEquals(darkskyregister.getPageHeader(), "Register");

    }





}
