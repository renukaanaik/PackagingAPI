package com.mobiquity.model;

import java.math.BigDecimal;

/**
 * Model class to hold items
 * @author Renuka Naik
 */
public class Item {

    private int itemIndex;
    private double itemWeight;
    private BigDecimal itemPrice;

    public Item(int itemIndex, double itemWeight, BigDecimal itemPrice){
      this.itemIndex = itemIndex;
      this.itemWeight = itemWeight;
      this.itemPrice = itemPrice;
    }

  public int getItemIndex() {
    return itemIndex;
  }

  public void setItemIndex(int itemIndex) {
    this.itemIndex = itemIndex;
  }

  public double getItemWeight() {
    return itemWeight;
  }

  public void setItemWeight(double itemWeight) {
    this.itemWeight = itemWeight;
  }

  public BigDecimal getItemPrice() {
    return itemPrice;
  }

  public void setItemPrice(BigDecimal itemPrice) {
    this.itemPrice = itemPrice;
  }

  @Override
  public String toString() {
    return "("+itemIndex+", "+itemWeight+", "+itemPrice +")\n";
  }
}
