package ru.stqa.selenium.tests;



import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.selenium.pages.HomePageAuthHelper;
import ru.stqa.selenium.pages.HomePageHelper;
import ru.stqa.selenium.pages.LoginPageHelper;
import ru.stqa.selenium.util.DataProviders;


public class LoginPageTests extends TestBase {
    HomePageHelper homePage;
    LoginPageHelper loginPage;
    HomePageAuthHelper homePageAuth;



    @BeforeMethod
    public void initTests() {
        log.info("-- LoginPageTests - @BeforeMethod - initTests() was started");

        homePage = PageFactory.initElements(driver, HomePageHelper.class);

        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);

        homePageAuth = PageFactory.initElements(driver, HomePageAuthHelper.class);

        log.info("-- HomePage was loaded --");
        homePage.waitUntilPageIsLoaded();
        log.info("-- LoginPage opening was started--");
        loginPage.openLoginPage().
                  waitUntilPageIsLoaded();
        log.info("-- Waiting of page loading stared--");
        loginPage.waitUntilPageIsLoaded();

    }

    @Test
    public void loginScreenVerificationTest(){
        log.startTestCase("loginScreenVerificationTest()");
        log.info("- Assert - Verification that: 'login screen was loaded'");
        Assert.assertTrue(loginPage.correctPageIsLoaded(),
                "It is not login screen or there is no 'registration' on login screen");
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "dataProviderSecond")
    public void loginNegativeTest(String login, String psw)  {
        log.startTestCase("loginNegativeTest()");
        log.info("-- Input incorrect login and psw to - ");
        loginPage.loginToTheSystem(login, psw);
        log.info("- Assert - Verification that incorrect data received");
        Assert.assertTrue(loginPage.loginToTheSystemIncorrect());

        log.info("-- Close login window by 'X' button");
        loginPage.closeLoginWindowByX();
        log.info("-- Waiting for HomePage is loaded");
        homePage.waitUntilPageIsLoaded();
        log.info("- Assert- Verification that HomePage is loaded");
        Assert.assertTrue(homePage.correctPageIsLoaded());

    }

    @Test
    public void loginExitByCancelTest()  {
        log.startTestCase("loginExitByCancelTest()");
        log.info("-- Close login window by 'X' button");
        loginPage.closeLoginWindowByX();
        log.info("-- Wait until HomePage is loaded");
        homePage.waitUntilPageIsLoaded();
        log.info("- Assert- Verification that HomePage is loaded");
        Assert.assertTrue(homePage.correctPageIsLoaded());


    }
    @Test(dataProviderClass = DataProviders.class, dataProvider = "loginPositive")
    public void loginPositiveTest(String login, String psw)  {
        log.startTestCase("loginPositiveTest()");
        log.info("-- Input correct login and psw -");
        loginPage.loginToTheSystem(login, psw);
        log.info("-- Wait until profile icon is clickable");
        homePageAuth.waitUntilPageIsLoaded();
        log.info("- Assert - Verification that Authorized Home Page is loaded");
        Assert.assertTrue(homePageAuth.correctPageIsLoaded());
    }

}