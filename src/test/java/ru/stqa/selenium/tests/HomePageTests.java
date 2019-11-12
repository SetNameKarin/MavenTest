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
        homePage = PageFactory.initElements(driver, HomePageHelper.class);
        homePage.waitUntilPageIsLoaded();

    }

    @Test
    public void homePageVerificationTest()  {

        Assert.assertTrue(homePage.correctPageIsLoaded(),
                "Name of the listEvent element is not 'List events'");
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "dataProviderFirst")
    public void singleFilterHolidaysBy(String holiday)  {
        //String holiday = "Purim";
        homePage.filterEventsByHoliday(holiday);
        Assert.assertTrue(homePage.allEventsBelongToHoliday(holiday));
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "filterByKosher")
    public void singleFilterFoodByKosher(String food) {
        homePage.filterEventsByFood(food);
        Assert.assertTrue(homePage.allEventsBelongToFoodKosher(food));
    }



}

