package ru.stqa.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProfilePageHelper extends PageBase{
    @FindBy(id = "profile")
    WebElement profileIcon;
    @FindBy(id = "idbtneditprofile")
    WebElement editProfileButton;
    @FindBy(id = "imgavatarinprofilefamily")
    WebElement familyAvaInProfile;
    @FindBy(id = "titleprofile")
    WebElement titleProfile;
    @FindBy(xpath = "//span[@id='fieldobjfamilyName']//input")
    WebElement inputFamilyName;
    @FindBy(id = "idbtnsaveprofile")
    WebElement saveButton;
    @FindBy(id = "family")
    WebElement familyIcon;
    @FindBy(xpath = "//span[@id='fieldobjfamilyName']")
    WebElement familyName;

    public ProfilePageHelper(WebDriver driver) {
        super(driver);
    }


public ProfilePageHelper goToTheProfile(){
    log.info("-- ProfilePageHelper - goToTheProfile() method was started");
    log.info("-- Click the profileIcon");
    profileIcon.click();
    log.info("-- Wait until profile page is loaded");
    waitUntilPageLoaded();
    return this;
}

public ProfilePageHelper waitUntilPageLoaded(){
    log.info("-- ProfilePageHelper - goToTheProfile() method was started");
    log.info("-- Wait until editProfileButton is loaded");
    waitUntilElementIsClickable(editProfileButton,20);
    log.info("-- Wait until text 'My Profile:' is present in titleProfile element");
    waitUntilTextPresentInElement(titleProfile, "My Profile:", 20 );
    log.info("-- Wait until family avatar is visible on the profile page");
    waitUntilElementIsVisible(familyAvaInProfile, 20);
        return this;
}

public ProfilePageHelper openProfileInEditMode(){
    log.info("-- ProfilePageHelper - openProfileInEditMode() method was started");
    log.info("-- Click the 'Edit' button on the profile page");
    editProfileButton.click();
    log.info("-- Wait until the input line is clickable");
    waitUntilElementIsClickable(inputFamilyName, 20);
    log.info("-- Wait until the 'save' button is clickable");
    waitUntilElementIsClickable(saveButton, 20);
    return this;
}


    public ProfilePageHelper lastNameChanging(String name) {
        log.info("-- ProfilePageHelper - lastNameChanging() method was started");
        log.info("-- Enter new Last name");
        enterValueToField(inputFamilyName, name);
        log.info("-- Wait until the 'save' button is visible");
       waitUntilElementIsVisible(saveButton, 30);
        log.info("-- Wait until the 'save' button is clickable");
       waitUntilElementIsClickable(saveButton, 30);
        return this;
    }


        public ProfilePageHelper saveProfile() {
            log.info("-- ProfilePageHelper - saveProfile() method was started");
            log.info("-- Scroll the page up");
            scrollPageUp();
            log.info("-- Click the 'save' button");
            saveButton.click();
            log.info("-- Wait until text 'Petrov' is present in familyName element");
            waitUntilTextPresentInElement(familyName,"Petrov", 20 );
            log.info("-- Wait until 'edit' button is clickable");
            waitUntilElementIsClickable(editProfileButton,30);
            log.info("-- Wait until family icon is visible");
           waitUntilElementIsVisible(familyIcon, 20);
            return this;
        }

    public String getFamilyName(){
        return familyName.getText();
    }

    public ProfilePageHelper waitingForFamilyIconIsClickable(){
        log.info("-- ProfilePageHelper - waitingForFamilyIconIsClickable() method was started");
        log.info("-- Wait until family icon is clickable");
        waitUntilElementIsClickable(familyIcon, 30);
        return this;

    }
            //--------------------Personal information for comparing --------------------
            public String confessionProfile(){
                return driver.findElement(By.cssSelector("#fieldobjconfession")).getText();
            }

            public String languagesProfile(){
                return driver.findElement(By.cssSelector("#fieldobjlanguages")).getText();
            }


            public String foodPreferenceProfile(){
                return driver.findElement(By.cssSelector("#fieldobjfoodPreferences")).getText();
            }


            public String EmailProfile(){
                return driver.findElement(By.cssSelector("#fieldobjelMail")).getText();
            }


            public String phoneNumberProfile(){
                return driver.findElement(By.cssSelector("#fieldobjphoneNumber")).getText();
                }





}
