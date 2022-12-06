package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class SaucedemoProductPage extends BasePage
{
  public SaucedemoProductPage(WebDriver webDriver)
  {
    super(webDriver);
  }

  private final By itemsDivLocator = By.className("inventory_item");
  private final By itemsNameLocator = By.className("inventory_item_name");
  private final By itemsPriceLocator = By.className("inventory_item_price");
  private final By addToCartBtnLocator = By.cssSelector(".btn.btn_primary.btn_small.btn_inventory");
  private final By removeBtnLocator = By.cssSelector(".btn.btn_secondary.btn_small.btn_inventory");
  private final By sortLocator = By.xpath("//select[@class='product_sort_container']");
  private final By cartIconLocator = By.className("shopping_cart_link");

  private static final String MESSAGE = "Wrong product name!";

  public List<String> getAllItemsName()
  {
    return getElementsText(itemsNameLocator);
  }

  public SaucedemoProductPage select(String selectText)
  {
    Select select = new Select(findElement(sortLocator));
    select.selectByVisibleText(selectText);
    return this;
  }

  public void clickAddToCart(String itemName)
  {
    findItemByName(itemName)
        .ifPresentOrElse(item ->
                item.findElement(addToCartBtnLocator).click(),
            () -> System.out.println(MESSAGE));
  }

  public void navigateToCart()
  {
    findElement(cartIconLocator).click();
  }

  public void removeIsClickable(String itemName)
  {
    findItemByName(itemName)
        .ifPresentOrElse(item ->
                waitElementIsClickable(item.findElement(removeBtnLocator)),
            () -> System.out.println(MESSAGE));
  }

  public double getItemPrice(String itemName)
  {
    AtomicReference<Double> result = new AtomicReference<>(0.0);
    findItemByName(itemName)
        .ifPresentOrElse(item ->
            {
              String priceStr = item.findElement(itemsPriceLocator).getText().substring(1);
              result.set(Double.parseDouble(priceStr));
            },
            () -> System.out.println(MESSAGE));
    return result.get();
  }

  private Optional<WebElement> findItemByName(String itemName)
  {
    return
        findElements(itemsDivLocator)
            .stream()
            .filter(item -> item.findElement(itemsNameLocator).getText().equals(itemName))
            .findFirst();
  }
}
