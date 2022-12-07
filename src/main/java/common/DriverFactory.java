package common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static constants.Constant.Urls.HUB_URL;
import static org.openqa.selenium.remote.Browser.CHROME;
import static org.openqa.selenium.remote.Browser.EDGE;
import static org.openqa.selenium.remote.Browser.FIREFOX;

public class DriverFactory
{
  private static final ThreadLocal<WebDriver> T_DRIVER = new ThreadLocal<>();

  public static WebDriver createDriver(String browser) throws MalformedURLException
  {
    final DesiredCapabilities caps = new DesiredCapabilities();
    String browserIgnoreCase = browser.toUpperCase();
    switch (browserIgnoreCase)
    {
      case "FIREFOX":
        caps.setBrowserName(FIREFOX.browserName());
        break;
      case "EDGE":
        caps.setBrowserName(EDGE.browserName());
        break;
      case "CHROME":
      default:
        caps.setBrowserName(CHROME.browserName());
    }
    setDriver(new RemoteWebDriver(new URL(HUB_URL), caps));
    return getDriver();
  }

  private static synchronized WebDriver getDriver()
  {
    return T_DRIVER.get();
  }

  private static void setDriver(WebDriver driver)
  {
    T_DRIVER.set(driver);
  }

  private DriverFactory()
  {
  }
}
