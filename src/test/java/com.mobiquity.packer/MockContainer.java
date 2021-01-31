package com.mobiquity.packer;

import com.mobiquity.model.Item;
import com.mobiquity.model.ItemContainer;

public class MockContainer {

    public ItemContainer getContainer(double weightLimit, Item[] items) {
        ItemContainer container = new ItemContainer();
        container.setItemList(items);
        container.setWeightLimit(weightLimit);
        return container;
    }
}
