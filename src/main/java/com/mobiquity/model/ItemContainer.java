package com.mobiquity.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class demonstrates Composite pattern and contains a list of Items
 *
 * @author Renuka Naik
 */
@Getter
@Setter
@NoArgsConstructor
public class ItemContainer {

  private double weightLimit;
  private Item[] itemList;
  private Item[] filteredList;

}
