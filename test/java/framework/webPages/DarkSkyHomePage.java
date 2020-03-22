package framework.webPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import stepdefinition.SharedSD;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class DarkSkyHomePage extends BasePage {

    List<Integer> ls = new ArrayList<Integer>();
    List<String> timelineHours = new ArrayList<>();
    List<Integer> newList = new ArrayList<>();

    int minTemp;
    int maxTemp;
    int currentTime;

    private By timeline = By.xpath("//div[@class='temps']//span//span");
    private By currentTemp = By.xpath("//*[@class='currently']//*[@class=\"desc swap\"]//*[@class='summary swap']");
    private By hoursTimeline = By.xpath("//span[@class='hour']//span");

    public List<WebElement> getElementList() {
        return SharedSD.getDriver().findElements(timeline);
    }

    public int getCurrentTemp() {
        return Integer.parseInt(getTextFromElement(currentTemp).substring(0, 2));
    }

    public void timelineTemps() {
        for (WebElement j : getElementList()) {
            ls.add(Integer.parseInt(j.getText().substring(0, 2)));
        }
    }

    public void validatingCurrentTempAgainstTimeline() {
        minTemp = Collections.min(ls);
        maxTemp = Collections.max(ls);
        System.out.println("This is max temp:" + " " + minTemp);
        System.out.println("This is min temp:" + " " + maxTemp);
        int current = getCurrentTemp();
        if (minTemp <= current && current <= maxTemp) {
            System.out.println("Current temperature is less than max temperature and greater than min temperature");
        } else {
            Assert.fail("Temp isn't valid");
        }

    }

    public List<WebElement> getHoursTimelineList() {
        return SharedSD.getDriver().findElements(hoursTimeline);
    }

    public void timelineHours() {
        for (WebElement k : getHoursTimelineList()) {
            timelineHours.add(k.getText());
        }
    }

    public void intConversion() {
        for (String i : timelineHours) {
            int newTime = Integer.parseInt(i.replaceAll("[^\\d.]", ""));
            newList.add(newTime);
        }
    }

    public void twoHourIncrementCheck() {
        TimeZone est = TimeZone.getTimeZone("America/New_York");
        DateFormat dateFormat = new SimpleDateFormat("hh");
        dateFormat.setTimeZone(est);
        String dateString = dateFormat.format(new Date());
        System.out.println("Current time in AM/PM: " + dateString);
        currentTime = Integer.parseInt(dateString);

        for (int x : newList) {
            if (currentTime != 12 && x == (currentTime + 2)) {
                currentTime = x;
                System.out.println("Check Passed" + x);
            } else if (currentTime == 12) {
                currentTime = 0;
            } else if (currentTime == 11) {
                currentTime = 1;
            } else {
                Assert.fail("Wrong cal");
            }
        }
    }

}
