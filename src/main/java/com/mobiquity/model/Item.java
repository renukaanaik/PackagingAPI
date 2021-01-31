package com.mobiquity.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Model class to hold items
 * @author Renuka Naik
 */
@Getter
@Setter
@NoArgsConstructor
public class Item {

    private int itemIndex;
    private double itemWeight;
    private BigDecimal itemPrice;

    public Item(int itemIndex, double itemWeight, BigDecimal itemPrice){
      this.itemIndex = itemIndex;
      this.itemWeight = itemWeight;
      this.itemPrice = itemPrice;
    }
}
