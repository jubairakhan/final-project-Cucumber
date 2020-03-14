package framework.webPages;

import org.openqa.selenium.By;

public class DarkskyRegister extends BasePage {

    private By darkSkyApi = By.xpath("//div[@class='inner']//a[@href='/dev']");
    private By signup= By.xpath("//a[@class='button filled'  and contains(text(),'Sign Up')]");
    private By register = By.xpath("//button[contains(text(),'Register')]");
    private By pageHeader = By.xpath("//h1[@class='stand-alone title']");

    public void clickOndarkSkyApi() {
        clickOn(darkSkyApi);
    }
    public void clickOnsignup() {

        clickOn(signup);}
    public void clickOnregister() { clickOn(register);
    }

    public String getPageHeader() {

        return getTextFromElement(pageHeader);
    }


}
