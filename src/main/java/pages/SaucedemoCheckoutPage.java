package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.regex.Pattern;

import static constants.Constant.TimeoutVariable.EXPLICIT_WAIT;

public class SaucedemoCheckoutPage extends BasePage
{
  private final By payInfoLocator = By.xpath("//div[contains(text(), 'Payment')]");
  private final By payInfoValueLocator = By.xpath("//div[@class='summary_value_label']");
  private final By summarySubtotalLocator = By.className("summary_subtotal_label");
  private final By finishBtnLocator = By.cssSelector(".btn.btn_action.btn_medium.cart_button");

  private static final String REGEX = "(SauceCard #)(\\d{5})";

  public SaucedemoCheckoutPage(WebDriver driver)
  {
    super(driver);
  }

  public boolean isPaymentInformationDisplayed()
  {
    return findElement(payInfoLocator).isDisplayed();
  }

  public boolean isOrderNumberAllowed()
  {
    try
    {
      new WebDriverWait(driver, EXPLICIT_WAIT)
          .until(ExpectedConditions.textMatches(payInfoValueLocator, Pattern.compile(REGEX)));
      return true;
    }
    catch (TimeoutException exc)
    {
      return false;
    }
  }

  public double getItemTotal()
  {
    String priceStr = driver
        .findElement(summarySubtotalLocator)
        .getText()
        .substring(13);

    return Double.parseDouble(priceStr);
  }

  public void finishClick()
  {
    findElement(finishBtnLocator).click();
  }
}
