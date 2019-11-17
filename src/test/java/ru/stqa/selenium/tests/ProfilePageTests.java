package ru.stqa.selenium.tests;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.selenium.pages.*;
import ru.stqa.selenium.util.DataProviders;

public class ProfilePageTests extends TestBase {
    ProfilePageHelper profilePage;
    HomePageHelper homePage;
    LoginPageHelper loginPage;
    HomePageAuthHelper homePageAuth;
    FamilyPageHelper familyPage;


    @BeforeMethod
    public void initTests(){
        log.info("-- ProfilePageTests - @BeforeMethod - initTests() was started");

        profilePage = PageFactory.initElements(driver, ProfilePageHelper.class);

        homePage = PageFactory.initElements(driver, HomePageHelper.class);

        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);

        homePageAuth = PageFactory.initElements(driver, HomePageAuthHelper.class);

        familyPage = PageFactory.initElements(driver, FamilyPageHelper.class);

        log.info("-- Waite until Home Page is loaded");
        homePage.waitUntilPageIsLoaded();
        log.info("-- Opening of Login Page and login to the system");
        loginPage.openLoginPage()
                 .loginToTheSystem(LOGIN,PASSWORD);
        log.info("- Assert - Verification that Authorized Home Page is loaded");
        homePageAuth.waitUntilPageIsLoaded();
        log.info("-- Go to the profile -- ");
        profilePage.goToTheProfile();
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "lastNameChanging")
public  void lastNameOfFamilyChanging(String lastName, String lastName2) {
        log.startTestCase("lastNameOfFamilyChanging()");

              //--------------Open in edit mode-------------
        log.info("-- Open in edit mode, enter last name " + lastName + "save profile by clicking 'save' button");
       profilePage.openProfileInEditMode()
                  .lastNameChanging(lastName)
                  .saveProfile();
        log.info("- Assert - Verification that the last name " + lastName + "is equal the last name on the profile page");
       Assert.assertEquals(lastName, profilePage.getFamilyName());


        log.info("-- Go to the family page");
       familyPage.goToTheFamilyPage();
        log.info("- Assert - Verification that the last name " + lastName + "is equal the last name on the family page");
       Assert.assertEquals("My Family: "+lastName, familyPage.getTitle());


        //---------------Return to the profile---------------
        log.info("-- Return to the profile, open profile in edit mode, enter last name " + lastName2 + "clock 'save' button");
        profilePage.goToTheProfile()
                   .openProfileInEditMode()
                   .lastNameChanging(lastName2)
                   .saveProfile();
        log.info("- Assert - Verification that the last name " + lastName + "is equal the last name on the profile page");
        Assert.assertEquals(lastName2, profilePage.getFamilyName());



    }

    @Test
    public void profileAndFamilyPageComparing() {
        log.startTestCase("profileAndFamilyPageComparing()");
        log.info("-- Saving data from the profile page");
        String confession = profilePage.confessionProfile();

        String languages = profilePage.languagesProfile();

        String foodPreference = profilePage.foodPreferenceProfile();

        String email = profilePage.EmailProfile();

        String phone = profilePage.phoneNumberProfile();

        log.info("-- Waiting until Family icon is clickable");
        profilePage.waitingForFamilyIconIsClickable();
        log.info("-- Go to the family page");
        familyPage.goToTheFamilyPage();

        log.info("-- Comparing data from the family and profile page");
        int counter = 0;
        if (confession.equals(familyPage.confessionFamily())) {
            counter++;
        }
        if (languages.equals(familyPage.languagesFamily())) {
            counter++;
        }
        if (foodPreference.equals(familyPage.foodPreferenceFamily())) {
            counter++;
        }
        if (email.equals(familyPage.emailFamily())) {
            counter++;
        }
        if (phone.equals(familyPage.phoneNumberFamily())) {
            counter++;
        }
        log.info("- Assert - Verification that counter is equal to quantity of equivalent fields");
        Assert.assertTrue(counter == 5);
    }
 }
