package ru.stqa.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class IntroPageHelper extends PageBase {

    @FindBy(id = "closedIntro")
    WebElement closeIntro;

    public IntroPageHelper(WebDriver driver) {
        super(driver);
    }

    public IntroPageHelper waitUntilPageLoaded(){
        waitUntilElementIsClickable(closeIntro, 30);
        return this;
    }

    public IntroPageHelper closeIntroPage(){
        closeIntro.click();
        return this;
    }
}
