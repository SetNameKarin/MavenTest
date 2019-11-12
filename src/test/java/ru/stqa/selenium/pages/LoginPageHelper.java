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
       waitUntilElementIsClickable(signInButton, 20);
    }

    public LoginPageHelper  openLoginPage(){
        waitUntilElementIsClickable(loginIcon, 20);
        loginIcon.click();
        waitUntilPageIsLoaded();
        return this;
    }

    public Boolean correctPageIsLoaded(){
         return  registrationLink.getText().contains("registration");
    }

    public LoginPageHelper loginToTheSystem(String login, String psw){
        //---- Enter incorrect login/psw ---
        enterValueToField(loginField, login);
        enterValueToField(passwordField, psw);
        signInButton.click();
        return  this;
    }

    public boolean loginToTheSystemIncorrect() {
        //waitUntilElementIsVisible(By.id("wrongloginorpassword"),10);
        waitUntilElementIsVisible(wrongAuth, 20);
        //return  driver.findElement(By.id("wrongloginorpassword")).getText().contains("Wrong Authorization");
        return wrongAuth.getText().contains("Wrong Authorization");
    }

    public LoginPageHelper closeLoginWindowByX() {
        closeByXButton.click();
        return this;
    }
}