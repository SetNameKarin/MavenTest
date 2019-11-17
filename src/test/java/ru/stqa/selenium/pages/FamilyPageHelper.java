package ru.stqa.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FamilyPageHelper extends PageBase{
    @FindBy(id = "titleprofile")
    WebElement titleProfile;
    @FindBy(id = "family")
    WebElement familyIcon;
    @FindBy(id = "idfamilyinfoimg")
    WebElement familyAvaInFamily;

    public FamilyPageHelper(WebDriver driver) {
        super(driver);
    }

    public FamilyPageHelper goToTheFamilyPage(){
        log.info("-- FamilyPageHelper - goToTheFamilyPage() method was started ");
        log.info("-- Click the family icon");
        familyIcon.click();
        log.info("-- Wait until Avatar on family is visible");
        waitUntilElementIsVisible(familyAvaInFamily, 30);
        log.info("-- Wait until text 'My Family:' is present");
        waitUntilTextPresentInElement(titleProfile, "My Family:", 30 );
        return this;
    }



    public String getTitle(){

        return driver.findElement(By.id("titleprofile")).getText();
    }

    public String confessionFamily(){
        return  driver.findElement(By.xpath("//span[@id='fieldobjconfession']")).getText();
    }

    public String languagesFamily(){
        return driver.findElement(By.xpath("//span[@id='fieldobjlanguages']")).getText();
    }
    public String foodPreferenceFamily(){
        return driver.findElement(By.xpath("//span[@id='fieldobjfoodPreferences']")).getText();
    }

    public String emailFamily(){
       return driver.findElement(By.xpath("//a[contains(text(),'kokok_ko@mail.ru')]")).getText();
    }

    public String phoneNumberFamily(){
        return driver.findElement(By.xpath("//a[contains(text(),'0507111528')]")).getText();
    }


}
