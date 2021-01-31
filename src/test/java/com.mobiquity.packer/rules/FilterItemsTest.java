package com.mobiquity.packer.rules;

import com.mobiquity.exception.APIException;
import com.mobiquity.model.Item;
import com.mobiquity.model.ItemContainer;
import com.mobiquity.packer.MockContainer;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class FilterItemsTest {
    @InjectMocks
    FilterItems rule1 = new FilterItems();

    MockContainer mockContainer = new MockContainer();

    @Test
    public void applyRule_filteredcorrectly() throws APIException {

        Item item1 = new Item(1, 45.33, new BigDecimal(23));
        Item item2 = new Item(2, 5.45, new BigDecimal(10));
        Item item3 = new Item(3, 34.67, new BigDecimal(67));
        Item item4 = new Item(4, 78.66, new BigDecimal(45));
        Item item5 = new Item(5, 99.04, new BigDecimal(1));

        ItemContainer container = mockContainer.getContainer(90, new Item[]{item1,item2,item3,item4,item5});

        rule1.applyRule(container);
        List<Item> filteredList = Arrays.asList(container.getFilteredList());
        Assert.assertEquals(3,filteredList.size());
        Assert.assertTrue(filteredList.contains(item1));
        Assert.assertTrue(filteredList.contains(item2));
        Assert.assertTrue(filteredList.contains(item3));
    }

    @Test
    public void applyRule_nomatchfound() throws APIException {

        Item item1 = new Item(1, 45.33, new BigDecimal(23));
        Item item2 = new Item(2, 5.45, new BigDecimal(10));
        Item item3 = new Item(3, 34.67, new BigDecimal(67));
        Item item4 = new Item(4, 78.66, new BigDecimal(45));
        Item item5 = new Item(5, 99.04, new BigDecimal(1));

        ItemContainer container = mockContainer.getContainer(5, new Item[]{item1,item2,item3,item4,item5});

        rule1.applyRule(container);
        List<Item> filteredList = Arrays.asList(container.getFilteredList());
        Assert.assertEquals(0,filteredList.size());
    }
}
