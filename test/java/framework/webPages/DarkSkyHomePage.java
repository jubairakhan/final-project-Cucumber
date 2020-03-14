package framework.webPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import stepdefinition.SharedSD;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DarkSkyHomePage extends BasePage{

    List<Integer> ls = new ArrayList<Integer>();
    int minTemp;
    int maxTemp;

    private By timeline = By.xpath("//div[@class='temps']//span//span");
    private By currentTemp = By.xpath("//*[@class='currently']//*[@class=\"desc swap\"]//*[@class='summary swap']");

    public List<WebElement> getElementList(){
        return SharedSD.getDriver().findElements(timeline);
    }

    public int getCurrentTemp(){
        return Integer.parseInt(getTextFromElement(currentTemp).substring(0,2));
    }

    public void timelineTemps(){
            for(WebElement j:getElementList()) {
                ls.add(Integer.parseInt(j.getText().substring(0,2)));
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
}
