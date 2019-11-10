package ru.stqa.selenium.tests;



import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.selenium.pages.HomePageAuthHelper;
import ru.stqa.selenium.pages.HomePageHelper;
import ru.stqa.selenium.pages.LoginPageHelper;


public class LoginPageTests extends TestBase {
    HomePageHelper homePage;
    LoginPageHelper loginPage;
    HomePageAuthHelper homePageAuth;



    @BeforeMethod
    public void initTests() {

        homePage = PageFactory.initElements(driver, HomePageHelper.class);

        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);

        homePageAuth = PageFactory.initElements(driver, HomePageAuthHelper.class);


        homePage.waitUntilPageIsLoaded();
        loginPage.openLoginPage().waitUntilPageIsLoaded();
        loginPage.waitUntilPageIsLoaded();

    }

    @Test
    public void loginScreenVerificationTest(){

        Assert.assertTrue(loginPage.correctPageIsLoaded(),
                "It is not login screen or there is no 'registration' on login screen");
    }

    @Test
    public void loginNegativeTest()  {

        loginPage.loginToTheSystem(LOGIN,"123");
        Assert.assertTrue(loginPage.loginToTheSystemIncorrect());

        loginPage.closeLoginWindowByX();
        homePage.waitUntilPageIsLoaded();
        Assert.assertTrue(homePage.correctPageIsLoaded());

    }

    @Test
    public void loginExitByCancelTest()  {

        loginPage.closeLoginWindowByX();
        homePage.waitUntilPageIsLoaded();
        Assert.assertTrue(homePage.correctPageIsLoaded());


    }
    @Test
    public void loginPositiveTest()  {
        loginPage.loginToTheSystem(LOGIN,PASSWORD);
        homePageAuth.waitUntilPageIsLoaded();
        Assert.assertTrue(homePageAuth.correctPageIsLoaded());
    }

}