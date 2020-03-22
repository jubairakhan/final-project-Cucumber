package framework.webPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import stepdefinition.SharedSD;

import java.util.ArrayList;
import java.util.List;

public class HotelsHomePage extends BasePage {

    List<String> ls = new ArrayList<>();
    List<String> starRatings = new ArrayList<>();
    List<Float> hiltonListInfo = new ArrayList<>();

    private By destinationSearchBox = By.xpath("//*[@id='qf-0q-destination']");
    private By searchBtn = By.xpath("//*[@type='submit']");
    private By newYorkCitySelection = By.xpath("//*[contains(text(), 'New York, New York, United States of America')]");
    private By todaysDealPrice = By.xpath("//*[@class='hotel deal-of-the-day']//*[@class='strike-tooltip-block']");
    private By roomDropDownSelection = By.xpath("//*[@class='query-rooms']");
    private By roomsLabelSelectionDisplayed = By.xpath("//*[@class='widget-query-adults']");
    private By starRatingLabelFldSearchResults = By.xpath("//*[@class='star-rating-text star-rating-text-strong']");
    private By distanceDropDown = By.xpath("//*[@data-menu='sort-submenu-distance']");
    private By jfkAirport = By.xpath("//a[contains(text(), 'John F. Kennedy International Airport (JFK)')]");
    private By hiltonHotelsList = By.xpath("//a[contains(text(),'Hilton')]");
    private By landmarksInfo = By.xpath("//a[contains(text(),'Hilton')]/ancestor::section[@class='hotel-wrap']//*[@class='property-landmarks']/li[1]");

    public void setDestination() throws InterruptedException {
        Thread.sleep(4000);
        clickOn(destinationSearchBox);
        Thread.sleep(5000);
        clickOn(newYorkCitySelection);
    }

    public void clickSearch() {
        clickOn(searchBtn);
    }

    public void todaysDealPriceCheck() {
        int price = Integer.parseInt(getTextFromElement(todaysDealPrice).substring(1));
        if (price <= 200) {
            System.out.println("Deal Price is less than 200$");
        } else {
            Assert.fail("Deal price is greater than 200$");
        }
    }

    public void roomDropDownSelect(String roomNum) throws InterruptedException {
        Select roomDropDown = new Select(SharedSD.getDriver().findElement(roomDropDownSelection));
        roomDropDown.selectByVisibleText(roomNum);
    }

    public List<WebElement> getRoomsListDisplayed() {
        return SharedSD.getDriver().findElements(roomsLabelSelectionDisplayed);
    }

    public void roomNumVerificationListConversion() {
        for (WebElement j : getRoomsListDisplayed()) {
            ls.add(j.getText().replaceAll("\\D+", ""));
        }
    }

    public void listVerification(String expectedRoomNum) {
        if (Integer.parseInt(expectedRoomNum) == 1) {
            System.out.println("Default selection is displayed with room selected as 1");
        } else if (Integer.parseInt(expectedRoomNum) == 0) {
            System.out.println("No room selection drop down is displayed as the number of rooms selected is 9+");
        } else if (ls.size() == Integer.parseInt(expectedRoomNum)) {
            System.out.println("Rooms selection drop down are matching with number of rooms selected");
            System.out.println("Number of rooms selected: " + ls.size());
            System.out.println("Number of drop downs displayed: " + Integer.parseInt(expectedRoomNum));
        } else {
            System.out.println("Rooms selection drop down aren't matching with number of rooms selected");
            Assert.fail("Rooms selection drop down aren't matching with number of rooms selected");
        }
    }


    // Selecting the rating of the hotel
    public void ratingSelection(String rating) throws InterruptedException {
            By starRatingLeftNav = By.xpath("//*[contains(text(),'"+rating+"')]");
            clickOn(starRatingLeftNav);
            Thread.sleep(5000);
    }

    //List of all ratings of results
    public List<WebElement> getStarRatingAvailableResults() {
        return SharedSD.getDriver().findElements(starRatingLabelFldSearchResults);
    }

    //Converting ratings results from WebElements to list
    public void ratingsListOfResults(){
        for(WebElement i : getStarRatingAvailableResults()){
            starRatings.add(i.getText());
            System.out.println(starRatings.add(i.getText()));
        }
    }

    //Scrolling to bottom and verifying the results
    public void ratingVerificationResults(String rating) {
        scrollToBottom();
        getStarRatingAvailableResults();
        ratingsListOfResults();
        for (String i : starRatings) {
            System.out.println(i);
            if (i.equals(rating) ) {
                System.out.println("All the expected results are being displayed");
            } else if ( i.equals("3.5-star") || i.equals("4.5-star") || i.equals("5.5-star")) {
                System.out.println("0.5 pointer is being displayed");
            } else {
                Assert.fail("Non " + rating + " rated hotels are being displayed");
            }
        }
    }


    //Select airport
    public void airportSelection() throws InterruptedException {
        Actions actions = new Actions(SharedSD.getDriver());
        actions.moveToElement(SharedSD.getDriver().findElement(distanceDropDown));
        actions.moveToElement(SharedSD.getDriver().findElement(jfkAirport));
        actions.click().build().perform();
        Thread.sleep(4000);
    }

    //List of hilton hotels being displayed
    public List<WebElement> getHiltonHotelsList(){
        return SharedSD.getDriver().findElements(landmarksInfo);
    }

    //Conversion of WebElements into list
    public void listHiltonLandMarkInfo(){
        getHiltonHotelsList();
        for (WebElement w : getHiltonHotelsList())
        {
            hiltonListInfo.add(Float.parseFloat(w.getText().substring(0,3)));
            System.out.println(hiltonListInfo.add(Float.parseFloat(w.getText().substring(0,3))));
        }
    }

    //collectResults
    public void verifyResults(){
        scrollToBottom();
        listHiltonLandMarkInfo();
        int count = 0;
        for(float s: hiltonListInfo){
            if(s <= 10.0){
             count ++;
             System.out.println("Hotels are there within range of 10 miles");
            }
        }
        if(count <=0){
            Assert.fail("No Hotels are within range of 10 miles with name Hilton");
        }

    }

}

