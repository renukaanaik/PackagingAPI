package com.mobiquity.packer;

import com.mobiquity.constants.PackagingConstants;
import com.mobiquity.exception.APIException;
import com.mobiquity.model.Item;
import com.mobiquity.model.ItemContainer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class PackageItemsImpl implements PackageItems {

    @Override
    public void validateInputs(ItemContainer container) throws APIException {
        if (container.getItemList().length > PackagingConstants.MAX_ITEMS_ALLOWED) {
            throw new APIException(PackagingConstants.MAX_ITEMS_ALLOWED_MSG);
        }
        if (container.getWeightLimit() > PackagingConstants.PER_CONTAINER_WEIGHT_LIMIT) {
            throw new APIException(PackagingConstants.PER_CONTAINER_WEIGHT_LIMIT_MSG);
        }
        for (Item item : container.getItemList()) {
            if (item.getItemWeight() > PackagingConstants.PER_ITEM_WEIGHT_LIMIT) {
                throw new APIException(PackagingConstants.PER_ITEM_WEIGHT_LIMIT_MSG);
            }
            if (item.getItemPrice().compareTo(PackagingConstants.PER_ITEM_PRICE_LIMIT) > 0) {
                throw new APIException(PackagingConstants.PER_ITEM_PRICE_LIMIT_MSG);
            }
        }
    }

    @Override
    public void filterItemsToPack(ItemContainer container) throws APIException {

        //Comparator to sort item list first in decending Price and then ascending weight
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

    }
}
