package com.mobiquity.utils;

import com.mobiquity.exception.APIException;
import com.mobiquity.model.Item;
import com.mobiquity.model.ItemContainer;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Helper class
 * @author Renuka Naik
 */
public class ItemHelper {

  /**
   * This method parses input line from the file and throws APIException for invalid data
   * @param line line from input file
   * @return Object of ItemContainer to be processed further
   * @throws APIException Application Specific Exception
   */
    public ItemContainer parseItem(String line) throws APIException {
        ItemContainer container = new ItemContainer();
        try {
            String[] weightAndItemsArr = line.split(":");
            double weightLimit = Double.valueOf(weightAndItemsArr[0].trim());

            String rawItem = weightAndItemsArr[1];
            List<Item> itemsList = Arrays.stream(rawItem
                    .replaceFirst(" ", "")
                    .split(" "))
                    .map(item -> replaceExtraCharacters(item))
                    .map(item -> {
                        return buildItem(item.split(","));
                    }).collect(Collectors.toList());

            container.setItemList(itemsList.stream().toArray(Item[]::new));
            container.setWeightLimit(weightLimit);
        } catch (NumberFormatException nfe) {
            throw new APIException("Invalid Input file", nfe);
        }
        return container;
    }

  /**
   * This method build Item object to be added to container's item list
   * @param data String array with input data
   * @return Item object
   */
    private Item buildItem(String[] data) {
        int itemIndex = Integer.valueOf(data[0].trim());
        double itemWeight = Double.valueOf(data[1].trim());
        BigDecimal itemPrice = new BigDecimal(data[2].substring(1).trim());
        return new Item(itemIndex, itemWeight, itemPrice);
    }

    /**
     * This method replaces extra characters from the input line
     * so we can parse the data correctly.
     *
     * @param item item input
     * @return String with required data
     */
    private String replaceExtraCharacters(String item) {
        return item.replaceAll("\\(", "")
                .replaceAll("\\)", "");
    }
}
