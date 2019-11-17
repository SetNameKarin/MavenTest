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
        log.info("-- IntroPageHelper - waitUntilPageLoaded() method was started");
        log.info("-- Wait until closeIntro button is clickable");
        waitUntilElementIsClickable(closeIntro, 30);
        return this;
    }

    public IntroPageHelper closeIntroPage(){
        log.info("-- IntroPageHelper - closeIntroPage() method was started");
        log.info("-- Close intro page");
        closeIntro.click();
        return this;
    }
}
