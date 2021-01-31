package com.mobiquity.packer.rules;

import com.mobiquity.exception.APIException;
import com.mobiquity.model.Item;
import com.mobiquity.model.ItemContainer;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Class represents rule 4  - which filter all the items which are within weight limit
 * and at highest possible price
 *
 * @author Renuka Naik
 */
@Slf4j
public class FilterItems implements PackageRules {
    private PackageRules rule;

    @Override
    public void setNextRule(PackageRules nextRule) {
        this.rule = nextRule;
    }

    @Override
    public void applyRule(ItemContainer container) throws APIException {
        try {
            //Comparator to sort item list first in descending Price and then ascending weight
            Comparator<Item> compareByPriceAndWeight = Comparator.comparing(Item::getItemPrice).reversed()
                    .thenComparing(Item::getItemWeight);

            //sort inputItems in container
            Arrays.sort(container.getItemList(), compareByPriceAndWeight);

            double weightLimit = container.getWeightLimit();
            List<Item> itemsToBeSend = new ArrayList<>();

            //Loop over sorted list and check for weight limit
            //Since the list is sorted in descending price,
            //it will always provide higher cost results within weight limit
            for (Item item : container.getItemList()) {
                if (item.getItemWeight() <= weightLimit) {
                    weightLimit = weightLimit - item.getItemWeight();
                    itemsToBeSend.add(item);
                }
                if (weightLimit == 0) {
                    break;
                }
            }
            container.setFilteredList(itemsToBeSend.stream().toArray(Item[]::new));
        } catch (IllegalArgumentException e) {
            log.error("Error while filtering items :" + e.getMessage());
            throw new APIException("Error while filtering items", e);
        }
    }
}
