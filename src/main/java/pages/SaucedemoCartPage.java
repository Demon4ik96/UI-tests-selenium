package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SaucedemoCartPage extends BasePage
{
  public SaucedemoCartPage(WebDriver webDriver)
  {
    super(webDriver);
  }

  private final By checkoutBtnLocator = By.cssSelector(".btn.btn_action.btn_medium.checkout_button");

  public void checkoutClick()
  {
    findElement(checkoutBtnLocator).click();
  }

}
