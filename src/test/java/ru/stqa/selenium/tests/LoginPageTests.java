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

    @Test(dataProviderClass = DataProviders.class, dataProvider = "dataProviderSecond")
    public void loginNegativeTest(String login, String psw)  {

        loginPage.loginToTheSystem(login, psw);
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
    @Test(dataProviderClass = DataProviders.class, dataProvider = "loginPositive")
    public void loginPositiveTest(String login, String psw)  {
        loginPage.loginToTheSystem(login, psw);
        homePageAuth.waitUntilPageIsLoaded();
        Assert.assertTrue(homePageAuth.correctPageIsLoaded());
    }

}