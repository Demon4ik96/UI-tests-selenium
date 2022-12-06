package tests;

import common.ItemUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;

import static constants.Constant.Urls.SAUCEDEMO_CART_PAGE;
import static constants.Constant.Urls.SAUCEDEMO_CHECKOUT_PAGE;

public class SaucedemoCheckoutTest extends BaseTest
{
  private static final String EXPECTED_TEXT = "CHECKOUT: COMPLETE!";

  @BeforeMethod()
  @Parameters({"username", "password", "item1", "item2"})
  public void loginAndAddItemsToCart(String username, String password, String item1, String item2)
  {
    saucedemoLoginPage.loginUser(username, password);
    List<String> itemsToAdd = List.of(item1, item2);
    itemsToAdd.forEach(itemToAdd -> saucedemoProductPage.clickAddToCart(itemToAdd));
  }

  //Check that items that are present in the cart are also present on checkout page
  // (with the same name and price)
  @Test()
  public void verifyItemsConsistencyWithCart()
  {
    saucedemoProductPage.open(SAUCEDEMO_CART_PAGE);
    List<ItemUtil.Item> cartItems = ItemUtil.getItems(driver);
    saucedemoCartPage.open(SAUCEDEMO_CHECKOUT_PAGE);
    List<ItemUtil.Item> checkoutItems = ItemUtil.getItems(driver);
    Assert.assertEquals(cartItems, checkoutItems);
  }

  //Check that payment information is present on page and that it contains only 5
  //numbers after "SauceCard #"
  @Test()
  public void verifyOrderNumberMatchesAllowed()
  {
    saucedemoProductPage.open(SAUCEDEMO_CHECKOUT_PAGE);
    Assert.assertTrue(saucedemoCheckoutPage.isPaymentInformationDisplayed());
    Assert.assertTrue(saucedemoCheckoutPage.isOrderNumberAllowed());
  }

  @Test()
  public void verifyItemTotal()
  {
    saucedemoProductPage.open(SAUCEDEMO_CHECKOUT_PAGE);
    List<ItemUtil.Item> checkoutItems = ItemUtil.getItems(driver);
    double totalFromItems = checkoutItems
        .stream()
        .map(ItemUtil.Item::getPrice)
        .reduce(0.0, Double::sum);

    double itemTotalFromPageBottom = saucedemoCheckoutPage.getItemTotal();

    Assert.assertEquals(totalFromItems, itemTotalFromPageBottom);
  }

  //Check that we can see complete page after clicking on finish button
  @Test()
  public void verifyFinishButton()
  {
    saucedemoProductPage.open(SAUCEDEMO_CHECKOUT_PAGE);
    saucedemoCheckoutPage.finishClick();
    Assert.assertTrue(saucedemoCompletePage.isPageLoaded(EXPECTED_TEXT));
  }
}
