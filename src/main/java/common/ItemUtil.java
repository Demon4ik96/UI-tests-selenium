package common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class ItemUtil
{
  private static final By ITEMS_DIV_LOCATOR = By.xpath("//div[@class='cart_item']");
  private static final By ITEMS_NAME_LOCATOR = By.className("inventory_item_name");
  private static final By ITEMS_PRICE_LOCATOR = By.className("inventory_item_price");

  public static List<Item> getItems(WebDriver driver)
  {
    List<Item> addedProducts = new ArrayList<>();

    driver.findElements(ITEMS_DIV_LOCATOR)
        .forEach(productElement ->
        {
          String itemName = productElement.findElement(ITEMS_NAME_LOCATOR).getText();
          double itemPrice = Double.parseDouble(productElement.findElement(ITEMS_PRICE_LOCATOR).getText().substring(1));
          addedProducts.add(new ItemUtil.Item(itemName, itemPrice));
        });
    return addedProducts;
  }

  @Data
  @EqualsAndHashCode
  @AllArgsConstructor
  public static class Item
  {
    private String productName;
    private double price;
  }
}
