package ru.stqa.selenium.tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.selenium.pages.WebPageHomeHelper;

public class WebHomePageTests extends TestBase{
    WebPageHomeHelper webPageHome;

    @BeforeMethod
    public void initTests (){
        webPageHome = PageFactory.initElements(driver, WebPageHomeHelper.class);
        webPageHome.waitUntilPageIsloaded();

    }

    @Test
    public void webHomePageTest()  {

        Assert.assertTrue(webPageHome.correctPageIsLoaded(),
                "'ListEvent' element is not displayed or 'Login' button is not displayed");
    }


}