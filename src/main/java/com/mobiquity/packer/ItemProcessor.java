package com.mobiquity.packer;

import com.mobiquity.exception.APIException;
import com.mobiquity.model.Item;
import com.mobiquity.model.ItemContainer;
import com.mobiquity.packer.rules.*;
import com.mobiquity.utils.ItemHelper;
import lombok.extern.slf4j.Slf4j;

import java.util.stream.IntStream;

/**
 * Class to handle processing per item container
 * It implements chain of responsibility pattern where all the package constraints
 * seperate classes implementing PackageRules.
 *
 * @author Renuka Naik
 */
@Slf4j
public class ItemProcessor {

    private PackageRules rule;
    private ItemHelper helper = new ItemHelper();

    public ItemProcessor() {
        //initialize the chain
        this.rule = new CheckContainerWeight();
        PackageRules rule2 = new CheckItemQuantity();
        PackageRules rule3 = new CheckItemWeightAndPrice();
        PackageRules rule4 = new FilterItems();

        //set the chain of responsibility
        rule.setNextRule(rule2);
        rule2.setNextRule(rule3);
        rule3.setNextRule(rule4);
    }

    /**
     * Method to validate, select and pack items
     *
     * @param itemline input line
     * @return output String
     * @throws APIException Application specific exception
     */
    public String packItems(String itemline) throws APIException {

        ItemContainer container = helper.parseItem(itemline);

        if(null != container) {
            rule.applyRule(container);
            return formulateOutput(container.getFilteredList());
        }else{
            throw new APIException("Error in parsing item : ");
        }
    }

    /**
     * Method to form output string in the form of "<itemindex>, <itemindex>"
     * from the list of filtered items
     *
     * @param filteredList list of items to be packed
     * @return output string
     */
    private String formulateOutput(Item[] filteredList) {
        log.debug("Formatting filtered items list.");
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
