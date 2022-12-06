package constants;

import java.time.Duration;

public class Constant
{
  public static class TimeoutVariable
  {
    public static final Duration IMPLICIT_WAIT = Duration.ofSeconds(4);
    public static final Duration EXPLICIT_WAIT = Duration.ofSeconds(8);
  }

  public static class Urls
  {
    public static final String SAUCEDEMO_LOGIN_PAGE = "https://www.saucedemo.com/";
    public static final String SAUCEDEMO_PRODUCTS_PAGE = "https://www.saucedemo.com/inventory.html";
    public static final String SAUCEDEMO_CART_PAGE = "https://www.saucedemo.com/cart.html";
    public static final String SAUCEDEMO_INFO_PAGE = "https://www.saucedemo.com/checkout-step-one.html";
    public static final String SAUCEDEMO_CHECKOUT_PAGE = "https://www.saucedemo.com/checkout-step-two.html";
    public static final String SAUCEDEMO_COMPLETE_PAGE = "https://www.saucedemo.com/checkout-complete.html";
    public static final String HUB_URL = "http://localhost:4444/wd/hub";
  }

  public static class SelectOptions
  {
    public static final String A_TO_Z = "Name (A to Z)";
    public static final String Z_TO_A = "Name (Z to A)";
    public static final String LOW_TO_HIGH = "Price (low to high)";
    public static final String HIGH_TO_LOW = "Price (high to low)";
  }
}
