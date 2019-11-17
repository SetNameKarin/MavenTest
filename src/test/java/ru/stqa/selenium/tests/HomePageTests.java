package ru.stqa.selenium.tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.selenium.pages.HomePageHelper;
import ru.stqa.selenium.util.DataProviders;


public class HomePageTests extends TestBase {
    HomePageHelper homePage;

    @BeforeMethod
    public void initTests(){
        log.info("-- HomePageTests - @BeforeMethod - initTests() was started");
        homePage = PageFactory.initElements(driver, HomePageHelper.class);
        log.info("-- HomePage was loaded --");
        homePage.waitUntilPageIsLoaded();

    }

    @Test
    public void homePageVerificationTest()  {
        log.startTestCase("homePageVerificationTest()");
        log.info("- Assert - Verification that name of the listEvent element is 'List events'");

        Assert.assertTrue(homePage.correctPageIsLoaded(),
                "Name of the listEvent element is not 'List events'");
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "dataProviderFirst")
    public void singleFilterHolidaysBy(String holiday)  {
        log.startTestCase("singleFilterHolidaysBy, TestData: holiday - " + holiday);
        log.info("-- Filter events by " + holiday);
        homePage.filterEventsByHoliday(holiday);
        log.info("- Assert - Verification that all events belong to the holiday " + holiday);
        Assert.assertTrue(homePage.allEventsBelongToHoliday(holiday));
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "filterByKosher")
    public void singleFilterFoodByKosher(String food) {
        log.startTestCase("singleFilterFoodByKosher, TestData: food - " + food);
        log.info("-- Filter events by " + food);
        homePage.filterEventsByFood(food);
        log.info("- Assert - Verification that all events belong to the" + food);
        Assert.assertTrue(homePage.allEventsBelongToFoodKosher(food));
    }



}

