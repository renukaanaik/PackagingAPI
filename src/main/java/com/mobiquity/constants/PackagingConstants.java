package com.mobiquity.constants;

import java.math.BigDecimal;

public class PackagingConstants {

  public static final int MAX_ITEMS_ALLOWED = 15;
  public static final double PER_ITEM_WEIGHT_LIMIT = 100;
  public static final BigDecimal PER_ITEM_PRICE_LIMIT = new BigDecimal(100);
  public static final double PER_CONTAINER_WEIGHT_LIMIT = 100;

  public static final String PER_CONTAINER_WEIGHT_LIMIT_MSG = "Package Weight Limit exceeded";
  public static final String MAX_ITEMS_ALLOWED_MSG = "Max Item limit exceeded";
  public static final String PER_ITEM_WEIGHT_LIMIT_MSG = "Per Item Weight limit exceeded.";
  public static final String PER_ITEM_PRICE_LIMIT_MSG = "Per Item Price limit exceeded.";

  public static final String ERROR_READING_FILE = "Error while reading input file";
}
