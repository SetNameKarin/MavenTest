package ru.stqa.selenium.tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.selenium.pages.HomePageHelper;


public class HomePageTests extends TestBase {
    HomePageHelper homePage;

    @BeforeMethod
    public void initTests(){
        homePage = PageFactory.initElements(driver, HomePageHelper.class);
        homePage.waitUntilPageIsLoaded();

    }

    @Test
    public void homePageVerificationTest()  {

        Assert.assertTrue(homePage.correctPageIsLoaded(),
                "Name of the listEvent element is not 'List events'");
    }

    @Test
    public void singleFilterHolidaysByShabbat()  {
        homePage.filterEventsByHolidayShabbat();
        Assert.assertTrue(homePage.allEventsBelongToHolidayShabbat());
    }

    @Test
    public void singleFilterFoodByKosher() {
        homePage.filterEventsByFoodKosher();
        Assert.assertTrue(homePage.allEventsBelongToFoodKosher());
    }



}

