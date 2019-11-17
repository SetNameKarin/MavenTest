package ru.stqa.selenium.tests;

import java.io.IOException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Capabilities;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import ru.stqa.selenium.SuiteConfiguration;
import ru.stqa.selenium.factory.WebDriverPool;
import ru.stqa.selenium.pages.IntroPageHelper;
import ru.stqa.selenium.util.LogLog4j;

/**
 * Base class for TestNG-based test classes
 */
public class TestBase {

  protected static URL gridHubUrl = null;
  protected static String baseUrl;
  protected static Capabilities capabilities;

  public static final String LOGIN = "karin";
  public static final String PASSWORD = "12345.com";
    IntroPageHelper introPage;
  public static LogLog4j log = new LogLog4j();


  protected WebDriver driver;

  @BeforeSuite
  public void initTestSuite() throws IOException {
    log.info("-- @BeforeSuite-- initTestSuite() was started");
    SuiteConfiguration config = new SuiteConfiguration();
    baseUrl = config.getProperty("site.url");
    if (config.hasProperty("grid.url") && !"".equals(config.getProperty("grid.url"))) {
      gridHubUrl = new URL(config.getProperty("grid.url"));
    }
    capabilities = config.getCapabilities();
  }

  @BeforeMethod
  public void initWebDriver() {
    log.info("-- @BeforeMethod-- initWebDriver() was started");
    log.info("- driver was defined");
    driver = WebDriverPool.DEFAULT.getDriver(gridHubUrl, capabilities);
    log.info("- System was opened by url - " + baseUrl);
    driver.get(baseUrl);
    log.info("- Open browser, fullscreen");
    driver.manage().window().fullscreen();
    log.info("- IntroPage was initialized");
    introPage = PageFactory.initElements(driver, IntroPageHelper.class);
    log.info("- IntroPage: --waitUntilPageLoaded() was started");
    introPage.waitUntilPageLoaded();
    log.info("- IntroPage: --close IntroPage() (close by X button" +
            ")");
    introPage.closeIntroPage();

  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() {
    log.info("-- @AfterMethod tear down");
    WebDriverPool.DEFAULT.dismissAll();
  }
}
