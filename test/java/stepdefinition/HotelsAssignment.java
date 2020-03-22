package stepdefinition;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import framework.webPages.BasePage;
import framework.webPages.HotelsHomePage;
import org.testng.Assert;

public class HotelsAssignment extends BasePage {

    HotelsHomePage hp = new HotelsHomePage();

    @Given("^I am on Hotels Home Page$")
    public void iAmOnHotelsHomePage() {
        Assert.assertEquals(SharedSD.getDriver().getTitle(), "Hotels.com - Deals & Discounts for Hotel Reservations from Luxury Hotels to Budget Accommodations", "Invalid Home Page");
    }

    @Given("^I am on default locations search result screen$")
    public void defaultLocationScreenSearch() throws InterruptedException {
        hp.setDestination();
        hp.clickSearch();
    }

    @Then("^I verify todays deal is less than 200$")
    public void dealPriceCheck(){
        hp.todaysDealPriceCheck();
    }

    @Then("^I select (.+) from room dropdown$")
    public void iSelectSelect_roomsFromRoomDropdown(String roomNum) throws InterruptedException {
        hp.roomDropDownSelect(roomNum);
    }

    @And("^I verify (.+) is displayed$")
    public void iVerifyNumber_of_room_dropdownIsDisplayed(String num) {
        hp.getRoomsListDisplayed();
        hp.roomNumVerificationListConversion();
        hp.listVerification(num);
    }

    @When("^I select property class (.+)$")
    public void locating5StarHotels(String rating) throws InterruptedException {
        hp.ratingSelection(rating);
    }

    @Then("^I verify system displays only (.+) hotels on search result$")
    public void assertionCheckforRatingResults(String rating){
        hp.ratingVerificationResults(rating);
    }

    @When("^I verify system displays all hotels within 10 miles radius of airport$")
    public void selectAirport() throws InterruptedException {
        hp.airportSelection();
    }

    @And("^I verify Hilton Hotel is within radius$")
    public void verification(){
        hp.verifyResults();
    }
}
