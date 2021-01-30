package com.mobiquity.packer;

import com.mobiquity.exception.APIException;
import com.mobiquity.model.Item;
import com.mobiquity.model.ItemContainer;
import com.mobiquity.utils.ItemHelper;

import java.util.stream.IntStream;

/**
 * Class to handle processing per item container
 *
 * @author Renuka Naik
 */
public class ItemProcessor {

    PackageItems packageItems = new PackageItemsImpl();
    ItemHelper helper = new ItemHelper();

    /**
     * Method to validate, select and pack items
     *
     * @param itemline input line
     * @return output String
     * @throws APIException Application specific exception
     */
    public String packItems(String itemline) throws APIException {
        ItemContainer container = helper.parseItem(itemline);

        packageItems.validateInputs(container);
        packageItems.filterItemsToPack(container);

        return formulateOutput(container.getFilteredList());
    }

    /**
     * Method to form output string in the form of "<itemindex>, <itemindex>"
     * from the list of filtered items
     *
     * @param filteredList list of items to be packed
     * @return output string
     */
    private String formulateOutput(Item[] filteredList) {
        if (filteredList.length != 0) {
            StringBuilder sb = new StringBuilder();
            IntStream.range(0, filteredList.length).forEach(i -> {
                if (i == filteredList.length - 1) {
                    sb.append(filteredList[i].getItemIndex());
                } else {
                    sb.append(filteredList[i].getItemIndex()).append(",");
                }
            });
            return sb.toString();
        } else {
            return "-";
        }
    }
}
