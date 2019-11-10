package ru.stqa.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WebPageHomeHelper extends PageBase{
    @FindBy(xpath = "//span[contains(text(),'Login')]")
    WebElement loginIcon;

    @FindBy(id = "idtitletypesearchevents")
    WebElement textListEvents;

    public WebPageHomeHelper(WebDriver driver) {
        super(driver);
    }

    public WebPageHomeHelper waitUntilPageIsloaded(){
        waitUntilElementIsClickable(loginIcon, 30);
        return this;
    }

    public Boolean correctPageIsLoaded(){
        return textListEvents.getText().equals("List events")&&loginIcon.isDisplayed();
    }


}