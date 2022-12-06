package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SaucedemoInfoPage extends BasePage
{
  private final By firstNameLocator = By.id("first-name");
  private final By lastNameLocator = By.id("last-name");
  private final By postalCodeLocator = By.id("postal-code");
  private final By continueBtnLocator = By.id("continue");
  private final By errorFormLocator = By.xpath("//div[@class='error-message-container error']/h3");

  public SaucedemoInfoPage(WebDriver webDriver)
  {
    super(webDriver);
  }

  public SaucedemoInfoPage setFirstName(String firstName)
  {
    findElement(firstNameLocator).sendKeys(firstName);
    return this;
  }

  public SaucedemoInfoPage setLastName(String lastName)
  {
    findElement(lastNameLocator).sendKeys(lastName);
    return this;
  }

  public SaucedemoInfoPage setPostalCode(String postalCode)
  {
    findElement(postalCodeLocator).sendKeys(postalCode);
    return this;
  }

  public void continueClick()
  {
    findElement(continueBtnLocator).click();
  }

  public String getErrorMessage()
  {
    return waitElementIsVisible(findElement(errorFormLocator)).getText();
  }

}
