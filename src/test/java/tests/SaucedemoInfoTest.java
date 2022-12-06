package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static constants.Constant.Urls.SAUCEDEMO_CHECKOUT_PAGE;
import static constants.Constant.Urls.SAUCEDEMO_INFO_PAGE;

public class SaucedemoInfoTest extends BaseTest
{
  private final static String TEST_FIRST_NAME = "firstname";
  private final static String TEST_LAST_NAME = "LASTNAME";
  private final static String TEST_POSTAL_CODE = "PoStAlCoDe/zip";
  private final static String ERROR_FIRST = "Error: First Name is required";
  private final static String ERROR_LAST = "Error: Last Name is required";
  private final static String ERROR_POSTAL_CODE = "Error: Postal Code is required";

  @BeforeMethod
  @Parameters({"username", "password"})
  public void loginAndOpenInfo(String username, String password)
  {
    saucedemoLoginPage.loginUser(username, password);
    saucedemoProductPage.open(SAUCEDEMO_INFO_PAGE);
  }

  @Test
  public void proceedWithAllFields()
  {
    saucedemoInfoPage
        .setFirstName(TEST_FIRST_NAME)
        .setLastName(TEST_LAST_NAME)
        .setPostalCode(TEST_POSTAL_CODE)
        .continueClick();

    Assert.assertTrue(isExpectedUrl(SAUCEDEMO_CHECKOUT_PAGE));
  }

  @Test
  public void errorWithoutFirstName()
  {
    saucedemoInfoPage
        .setLastName(TEST_LAST_NAME)
        .setPostalCode(TEST_POSTAL_CODE)
        .continueClick();

    Assert.assertEquals(saucedemoInfoPage.getErrorMessage(), ERROR_FIRST);
  }

  @Test
  public void errorWithoutLastName()
  {
    saucedemoInfoPage
        .setFirstName(TEST_FIRST_NAME)
        .setPostalCode(TEST_POSTAL_CODE)
        .continueClick();

    Assert.assertEquals(saucedemoInfoPage.getErrorMessage(), ERROR_LAST);
  }

  @Test
  public void errorWithoutPostalCode()
  {
    saucedemoInfoPage
        .setFirstName(TEST_FIRST_NAME)
        .setLastName(TEST_LAST_NAME)
        .continueClick();

    Assert.assertEquals(saucedemoInfoPage.getErrorMessage(), ERROR_POSTAL_CODE);
  }
}
