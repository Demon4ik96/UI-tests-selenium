package tests;

import common.DriverFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import pages.BasePage;
import pages.SaucedemoCartPage;
import pages.SaucedemoCheckoutPage;
import pages.SaucedemoCompletePage;
import pages.SaucedemoInfoPage;
import pages.SaucedemoLoginPage;
import pages.SaucedemoProductPage;

import java.net.MalformedURLException;

import static common.Config.CLEAR_COOKIES_AND_STORAGE;
import static common.Config.HOLD_BROWSER_OPEN;
import static constants.Constant.TimeoutVariable.EXPLICIT_WAIT;
import static constants.Constant.Urls.SAUCEDEMO_LOGIN_PAGE;

public class BaseTest
{
  protected WebDriver driver;
  protected BasePage basePage;
  protected SaucedemoLoginPage saucedemoLoginPage;
  protected SaucedemoProductPage saucedemoProductPage;
  protected SaucedemoCartPage saucedemoCartPage;
  protected SaucedemoInfoPage saucedemoInfoPage;
  protected SaucedemoCheckoutPage saucedemoCheckoutPage;
  protected SaucedemoCompletePage saucedemoCompletePage;

  @BeforeClass
  @Parameters("browser")
  public void createPages(String browser) throws MalformedURLException
  {
    driver = DriverFactory.createDriver(browser);
    basePage = new BasePage(driver);
    saucedemoLoginPage = new SaucedemoLoginPage(driver);
    saucedemoProductPage = new SaucedemoProductPage(driver);
    saucedemoCartPage = new SaucedemoCartPage(driver);
    saucedemoInfoPage = new SaucedemoInfoPage(driver);
    saucedemoCheckoutPage = new SaucedemoCheckoutPage(driver);
    saucedemoCompletePage = new SaucedemoCompletePage(driver);
  }

  @BeforeMethod
  public void open()
  {
    basePage.open(SAUCEDEMO_LOGIN_PAGE);
  }

  @AfterMethod(alwaysRun = true)
  public void clearCookies()
  {
    if (CLEAR_COOKIES_AND_STORAGE)
    {
      JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
      driver.manage().deleteAllCookies();
      javascriptExecutor.executeScript("window.sessionStorage.clear()");
      javascriptExecutor.executeScript("localStorage.clear()");
    }
  }

  @AfterClass(alwaysRun = true)
  public void close()
  {
    if (!HOLD_BROWSER_OPEN)
    {
      driver.quit();
    }
  }

  public boolean isExpectedUrl(String url)
  {
    try
    {
      new WebDriverWait(driver, EXPLICIT_WAIT).until(ExpectedConditions.urlToBe(url));
      return true;
    }
    catch (TimeoutException exc)
    {
      return false;
    }
  }
}
