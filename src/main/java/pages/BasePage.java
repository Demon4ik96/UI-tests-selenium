package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

import static constants.Constant.TimeoutVariable.EXPLICIT_WAIT;

public class BasePage
{
  protected WebDriver driver;

  private final By titleLocator = By.xpath("//span[@class='title']");

  public BasePage(WebDriver driver)
  {
    this.driver = driver;
  }

  public void open(String url)
  {
    driver.get(url);
  }

  public WebElement waitElementIsVisible(WebElement webElement)
  {
    new WebDriverWait(driver, EXPLICIT_WAIT).until(ExpectedConditions.visibilityOf(webElement));
    return webElement;
  }

  public void waitElementIsClickable(WebElement webElement)
  {
    new WebDriverWait(driver, EXPLICIT_WAIT).until(ExpectedConditions.elementToBeClickable(webElement));
  }

  public List<String> getElementsText(By locator)
  {
    return driver.findElements(locator).stream().map(WebElement::getText).collect(Collectors.toList());
  }

  public WebElement findElement(By locator)
  {
    return driver.findElement(locator);
  }

  public List<WebElement> findElements(By locator)
  {
    return driver.findElements(locator);
  }

  public boolean isPageLoaded(String textOnPage)
  {
    return
        findElement(titleLocator)
            .getText()
            .equalsIgnoreCase(textOnPage);
  }
}
