package com.mobiquity.model;

/**
 * This class demonstrates Composite pattern and contains a list of Items
 *
 * @author Renuka Naik
 */
public class ItemContainer {

  private double weightLimit;
  private Item[] itemList;
  private Item[] filteredList;

  public Item[] getFilteredList() {
    return filteredList;
  }

  public void setFilteredList(Item[] filteredList) {
    this.filteredList = filteredList;
  }


  public Item[] getItemList() {
    return itemList;
  }

  public void setItemList(Item[] itemList) {
    this.itemList = itemList;
  }

  public double getWeightLimit() {
    return weightLimit;
  }

  public void setWeightLimit(double weightLimit) {
    this.weightLimit = weightLimit;
  }

}
