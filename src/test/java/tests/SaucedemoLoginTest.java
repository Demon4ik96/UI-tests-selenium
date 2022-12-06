package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static constants.Constant.Urls.SAUCEDEMO_PRODUCTS_PAGE;

public class SaucedemoLoginTest extends BaseTest
{
  @DataProvider(name = "valid-logins")
  public static Object[][] getValidLogins()
  {
    return new Object[][] {
        {"standard_user", "secret_sauce"},
        {"locked_out_user", "secret_sauce"},
        {"problem_user", "secret_sauce"},
        {"performance_glitch_user", "secret_sauce"}
    };
  }

  @Test(dataProvider = "valid-logins")
  public void checkValidLogin(String username, String password)
  {
    saucedemoLoginPage.loginUser(username, password);
    Assert.assertTrue(isExpectedUrl(SAUCEDEMO_PRODUCTS_PAGE));
  }

  @Test
  @Parameters({"invalidUsername", "invalidPassword"})
  public void checkInvalidLogin(String username, String password)
  {
    saucedemoLoginPage.loginUser(username, password);
    Assert.assertFalse(isExpectedUrl(SAUCEDEMO_PRODUCTS_PAGE));
  }
}
