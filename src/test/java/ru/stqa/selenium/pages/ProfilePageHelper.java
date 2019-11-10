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
    profileIcon.click();
    waitUntilPageLoaded();
    return this;
}

public ProfilePageHelper waitUntilPageLoaded(){
    waitUntilElementIsClickable(editProfileButton,20);
    waitUntilTextPresentInElement(titleProfile, "My Profile:", 20 );
    waitUntilElementIsVisible(familyAvaInProfile, 20);
        return this;
}

public ProfilePageHelper openProfileInEditMode(){

    editProfileButton.click();
    waitUntilElementIsClickable(inputFamilyName, 20);
    waitUntilElementIsClickable(saveButton, 20);
    return this;
}


    public ProfilePageHelper lastNameChanging(String name) {
        //----------------Enter new Last name------------
        enterValueToField(inputFamilyName, name);
       waitUntilElementIsVisible(saveButton, 30);
       waitUntilElementIsClickable(saveButton, 30);
        return this;
    }


        public ProfilePageHelper saveProfile() {
            scrollPageUp();
            saveButton.click();
            waitUntilTextPresentInElement(familyName,"Petrov", 20 );
            waitUntilElementIsClickable(editProfileButton,30);
           waitUntilElementIsVisible(familyIcon, 20);
            return this;
        }





    public String getFamilyName(){
        return familyName.getText();
    }

    public ProfilePageHelper waitingForFamilyIconIsClickable(){
        waitUntilElementIsClickable(familyIcon, 30);
        return this;

    }
            //--------------------Personal information for comparing --------------------
            public String confessionProfile(){

                return driver.findElement(By.cssSelector("#fieldobjconfession")).getText();

            }

            public String languagesProfile(){

                return driver.findElement(By.cssSelector("#fieldobjlanguages")).getText();}


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
