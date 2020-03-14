package framework.webPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import stepdefinition.SharedSD;

import java.util.List;

public class DarkSkyHomePage extends BasePage{

    private By timeline = By.xpath("//div[@class='temps']//span//span");
    public List<WebElement> getElementList(){
        return SharedSD.getDriver().findElements(timeline);
    }
}
