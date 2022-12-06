package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SaucedemoLoginPage extends BasePage
{
  private final By userNameLocator = By.id("user-name");
  private final By passwordLocator = By.id("password");
  private final By loginButtonLocator = By.id("login-button");

  public SaucedemoLoginPage(WebDriver webDriver)
  {
    super(webDriver);
  }

  public void loginUser(String userName, String password)
  {
    this
        .setUserName(userName)
        .setPassword(password)
        .loginClick();
  }

  private SaucedemoLoginPage setUserName(String uName)
  {
    findElement(userNameLocator).sendKeys(uName);
    return this;
  }

  private SaucedemoLoginPage setPassword(String pass)
  {
    findElement(passwordLocator).sendKeys(pass);
    return this;
  }

  private void loginClick()
  {
    findElement(loginButtonLocator).click();
  }
}
