package tests;

import common.ItemUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static constants.Constant.Urls.SAUCEDEMO_CART_PAGE;

public class SaucedemoCartTest extends BaseTest
{
  private static final String EXPECTED_TEXT = "Checkout: Your Information";

  @BeforeMethod
  @Parameters({"username", "password"})
  public void login(String username, String password)
  {
    saucedemoLoginPage.loginUser(username, password);
  }

  //Check that items that were added to the cart on product page are present on cart page
  // (with the same name and price)
  @Test
  @Parameters({"item1", "item2"})
  public void verifyAddingToCart(String item1, String item2)
  {
    List<String> itemsToAddName = List.of(item1, item2);

    List<ItemUtil.Item> itemsToAdd = new ArrayList<>();
    itemsToAddName.forEach(
        itemToAddName ->
        {
          double price = saucedemoProductPage.getItemPrice(itemToAddName);
          itemsToAdd.add(new ItemUtil.Item(itemToAddName, price));
          saucedemoProductPage.clickAddToCart(itemToAddName);
        });

    saucedemoProductPage.navigateToCart();

    List<ItemUtil.Item> addedItems = ItemUtil.getItems(driver);

    Assert.assertEquals(addedItems, itemsToAdd);
  }

  @Test
  public void verifyCheckoutButton()
  {
    saucedemoProductPage.open(SAUCEDEMO_CART_PAGE);
    saucedemoCartPage.checkoutClick();
    Assert.assertTrue(saucedemoInfoPage.isPageLoaded(EXPECTED_TEXT));
  }
}
