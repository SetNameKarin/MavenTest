package ru.stqa.selenium.tests;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.selenium.pages.*;

public class ProfilePageTests extends TestBase {
    ProfilePageHelper profilePage;
    HomePageHelper homePage;
    LoginPageHelper loginPage;
    HomePageAuthHelper homePageAuth;
    FamilyPageHelper familyPage;


    @BeforeMethod
    public void initTests(){

        profilePage = PageFactory.initElements(driver, ProfilePageHelper.class);

        homePage = PageFactory.initElements(driver, HomePageHelper.class);

        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);

        homePageAuth = PageFactory.initElements(driver, HomePageAuthHelper.class);

        familyPage = PageFactory.initElements(driver, FamilyPageHelper.class);

        homePage.waitUntilPageIsLoaded();
        loginPage.openLoginPage()
                 .loginToTheSystem(LOGIN,PASSWORD);
        homePageAuth.waitUntilPageIsLoaded();
        profilePage.goToTheProfile();
    }

    @Test
public  void lastNameOfFamilyChanging() {
              //--------------Open in edit mode-------------
       profilePage.openProfileInEditMode()
                  .lastNameChanging("Petrov")
                  .saveProfile();
       Assert.assertEquals("Petrov", profilePage.getFamilyName());

        //----------------Go to the family page--------------
       familyPage.goToTheFamilyPage();
       Assert.assertEquals("My Family: Petrov", familyPage.getTitle());


        //---------------Return to the profile---------------
        profilePage.goToTheProfile()
                   .openProfileInEditMode()
                   .lastNameChanging("Shuster")
                   .saveProfile();
        Assert.assertEquals("Shuster", profilePage.getFamilyName());



    }

    @Test
    public void profileAndFamilyPageComparing() {
        String confession = profilePage.confessionProfile();

        String languages = profilePage.languagesProfile();

        String foodPreference = profilePage.foodPreferenceProfile();

        String email = profilePage.EmailProfile();

        String phone = profilePage.phoneNumberProfile();

        profilePage.waitingForFamilyIconIsClickable();
        familyPage.goToTheFamilyPage();


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


        Assert.assertTrue(counter == 5);
    }
 }
