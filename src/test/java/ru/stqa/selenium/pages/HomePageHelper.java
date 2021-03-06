package ru.stqa.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePageHelper extends PageBase {

    @FindBy(id  = "idsignin")
    WebElement loginIcon;
    @FindBy(xpath = "//div[@class = 'itemEventInsert']") List<WebElement> eventsList;
    @FindBy(id = "idtitletypesearchevents")
    WebElement listEvent;
    @FindBy(name = "selectholidays")
    WebElement filterHolidays;
    @FindBy(xpath = "//div[@id='idbtnclearfilter']")
    WebElement clearFilterButton;
    @FindBy(xpath = "//div[@class = 'holidayItemEvents']") List<WebElement> listHolidays;

    @FindBy(id = "selectfood")
    WebElement foodFilter;
    @FindBy(name = "selectfood")
    WebElement selectFood;
    @FindBy(xpath = "//div[@class = 'itemEventInsert']")List<WebElement>foodEventsList;
    @FindBy(xpath = "//div[@class = 'preferenceItemEvents']")List<WebElement>kosherFoodEvents;



    public HomePageHelper(WebDriver driver) {
        super(driver);
    }

    public HomePageHelper waitUntilPageIsLoaded(){
        log.info("-- HomePageHelper: waitUntilPageIsLoaded() was started");
        log.info("-- Wait until loginIcon element is clickable");
        waitUntilElementIsClickable(loginIcon, 20);
        log.info("-- Wait until all events are visible");
        waitUntilAllElementsVisible(eventsList, 20);
        return this;
    }

    public Boolean correctPageIsLoaded(){

        return listEvent.getText().equals("List events")&&loginIcon.isDisplayed();
    }


    public HomePageHelper filterEventsByHoliday(String holiday) {
        // ----- to wait that select-element (filter by holiday) and all options are loaded ---
        log.info("-- HomePageHelper: filterEventsByHoliday() (" + holiday +") was stared");
        log.info("-- To wait that select - element (filter by holiday) and all options are loaded");
        waitUntilElementIsClickable(filterHolidays, 30);
        waitUntilAllElementsVisible(driver.findElements(By.xpath("//select[@name = 'selectholidays']/option")),30);
        log.info("-- Choose filter by holiday " + holiday);
        selectValueFromList(filterHolidays, holiday);

        log.info("-- To wait that clearFilterButton is clickable");
        waitUntilElementIsClickable(clearFilterButton,20);
        log.info("-- to wait that filter by " + holiday + "is chosen");
        waitUntilElementIsPresent(By.xpath("//option[@selected][@value = '"+ holiday + "']"),20);

        log.info("-- To wait that all events are loaded ");
       waitUntilAllElementsVisible(eventsList, 40);
       return  this;
    }



    public Boolean allEventsBelongToHoliday(String holiday) {
            // --- verify that all holidays values are "Shabbat" ----
        log.info("-- HomePageHelper: allEventsBelongToHoliday() was started for holiday- " +holiday);
        int counter = 0;
        for (int i=0; i < listHolidays.size(); i++){
            if (listHolidays.get(i).getText().equals(holiday)) counter++;
        }
        return counter == listHolidays.size();
    }

    public void filterEventsByFood(String food) {
        // -----  wait for select-element (filter by food) and all options being loaded ---

        waitUntilElementIsVisible(foodFilter, 30);
        waitUntilAllElementsVisible(driver.findElements(By.xpath("//select[@name = 'selectfood']/option")),30);


        /* --- verify states clear button ----
        System.out.println("is displayed: " + driver.findElement(By.xpath("//div[@id='idbtnclearfilter']")).isDisplayed());
        System.out.println("is enabled: " + driver.findElement(By.xpath("//div[@id='idbtnclearfilter']")).isEnabled());
*/

        // ------ choose filter "kosher" ------
        selectValueFromList(selectFood, food);

        // ------ wait for filter "kosher" being chosen -----

        waitUntilElementIsClickable(clearFilterButton, 20);
        waitUntilElementIsPresent(By.xpath("//option[@selected][@value = '"+ food +"']"),20);

        // ------ wait for all events by filter "kosher" being loaded ----
        waitUntilAllElementsVisible(foodEventsList, 40);
    }

    public Boolean allEventsBelongToFoodKosher(String food) {
        // --- verify that all food values are "Kosher" ----
        int counter = 0;
        for (WebElement webElement : kosherFoodEvents) {
            if (webElement.getText().equals(food)) counter++;
        }
        return counter == kosherFoodEvents.size();
    }


}