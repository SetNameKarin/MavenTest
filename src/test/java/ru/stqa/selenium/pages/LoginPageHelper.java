package ru.stqa.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPageHelper extends PageBase {
    @FindBy(id = "signinrequest")
    WebElement signInButton;
    @FindBy(id = "idsignin")
    WebElement loginIcon;
    @FindBy(id = "clickreg")
    WebElement registrationLink;
    @FindBy(id = "logininput")
    WebElement loginField;
    @FindBy(id = "passwordinput")
    WebElement passwordField;
    @FindBy(id = "wrongloginorpassword")
    WebElement wrongAuth;
    @FindBy(id = "closedsignin")
    WebElement closeByXButton;

    public LoginPageHelper(WebDriver driver) {
        super(driver);
    }

    public void waitUntilPageIsLoaded(){
        log.info("-- LoginPageHelper - waitUntilPageIsLoaded() method was stated");
        log.info("-- Wait until signInButton is clickable");
        waitUntilElementIsClickable(signInButton, 20);
    }

    public LoginPageHelper  openLoginPage(){
        log.info("-- LoginPageHelper - openLoginPage() method was stated");
        log.info("-- Wait until loginIcon is clickable");
        waitUntilElementIsClickable(loginIcon, 20);
        log.info("-- Click the loginIcon");
        loginIcon.click();
        log.info("-- Wait until the login form is loaded");
        waitUntilPageIsLoaded();
        return this;
    }

    public Boolean correctPageIsLoaded(){
         return  registrationLink.getText().contains("registration");
    }

    public LoginPageHelper loginToTheSystem(String login, String psw){
        log.info("-- LoginPageHelper - loginToTheSystem()method was stated");
        log.info("-- Enter incorrect login");
        enterValueToField(loginField, login);
        log.info("-- Enter incorrect psw (less then 4 characters)");
        enterValueToField(passwordField, psw);
        log.info("-- Click the signInButton");
        signInButton.click();
        return  this;
    }

    public boolean loginToTheSystemIncorrect() {
        log.info("-- LoginPageHelper - loginToTheSystemIncorrect()method was stated");
        log.info("-- Wait until notification about wrong authorization is visible");
        waitUntilElementIsVisible(wrongAuth, 20);
        return wrongAuth.getText().contains("Wrong Authorization");
    }

    public LoginPageHelper closeLoginWindowByX() {
        log.info("-- LoginPageHelper - closeLoginWindowByX() method was stated");
        log.info("-- Close the login window by clicking the 'X' button");
        closeByXButton.click();
        return this;
    }
}