package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static constants.Constant.SelectOptions.Z_TO_A;

public class SaucedemoProductsTest extends BaseTest
{
  @BeforeMethod
  @Parameters({"username", "password"})
  public void login(String username, String password)
  {
    saucedemoLoginPage.loginUser(username, password);
  }

  @Test
  public void checkRemoveIsClickableAfterAdd()
  {
    List<String> itemsName =
        saucedemoProductPage
            .getAllItemsName();
    itemsName.forEach(itemToAdd -> saucedemoProductPage.clickAddToCart(itemToAdd));
    itemsName.forEach(addedItem -> saucedemoProductPage.removeIsClickable(addedItem));
  }

  @Test
  public void checkProductsNameReverseOrder()
  {
    List<String> itemsNameReverseActual =
        saucedemoProductPage
            .select(Z_TO_A)
            .getAllItemsName();

    List<String> itemsNameReverseExpected =
        itemsNameReverseActual
            .stream()
            .sorted(Comparator.reverseOrder())
            .collect(Collectors.toList());

    Assert.assertEquals(itemsNameReverseActual, itemsNameReverseExpected);
  }

}
