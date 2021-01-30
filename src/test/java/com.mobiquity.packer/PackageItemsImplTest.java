package com.mobiquity.packer;

import com.mobiquity.constants.PackagingConstants;
import com.mobiquity.exception.APIException;
import com.mobiquity.model.Item;
import com.mobiquity.model.ItemContainer;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class PackageItemsImplTest {

    @InjectMocks
    PackageItemsImpl impl = new PackageItemsImpl();

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void validateInputs_correctInput() throws APIException {

        Item item1 = new Item(1, 23.2, new BigDecimal(56));
        Item item2 = new Item(2, 45.33, new BigDecimal(23));
        Item item3 = new Item(3, 45.43, new BigDecimal(66));

        ItemContainer container = getContainer(50, new Item[]{item1, item2, item3});

        impl.validateInputs(container);
    }

    @Test
    public void validateInputs_weightLimitExceeded() throws APIException {

        ItemContainer container = getContainer(150, new Item[3]);

        expectedEx.expect(APIException.class);
        expectedEx.expectMessage(PackagingConstants.PER_CONTAINER_WEIGHT_LIMIT_MSG);

        impl.validateInputs(container);
    }

    @Test
    public void validateInputs_noofitemslimiterror() throws APIException {

        ItemContainer container = getContainer(10, new Item[20]);

        expectedEx.expect(APIException.class);
        expectedEx.expectMessage(PackagingConstants.MAX_ITEMS_ALLOWED_MSG);

        impl.validateInputs(container);
    }

    @Test
    public void validateInputs_peritemweightLimitExceeded() throws APIException {

        Item item1 = new Item(1, 145.33, new BigDecimal(23));
        ItemContainer container = getContainer(50, new Item[]{item1});

        expectedEx.expect(APIException.class);
        expectedEx.expectMessage(PackagingConstants.PER_ITEM_WEIGHT_LIMIT_MSG);

        impl.validateInputs(container);
    }

    @Test
    public void validateInputs_peritempriceLimitExceeded() throws APIException {

        Item item1 = new Item(1, 45.33, new BigDecimal(123));
        ItemContainer container = getContainer(50, new Item[]{item1});

        expectedEx.expect(APIException.class);
        expectedEx.expectMessage(PackagingConstants.PER_ITEM_PRICE_LIMIT_MSG);

        impl.validateInputs(container);
    }

    @Test
    public void filterItemsToPack_filteredcorrectly() throws APIException {

        Item item1 = new Item(1, 45.33, new BigDecimal(23));
        Item item2 = new Item(2, 5.45, new BigDecimal(10));
        Item item3 = new Item(3, 34.67, new BigDecimal(67));
        Item item4 = new Item(4, 78.66, new BigDecimal(45));
        Item item5 = new Item(5, 99.04, new BigDecimal(1));

        ItemContainer container = getContainer(90, new Item[]{item1,item2,item3,item4,item5});

        impl.filterItemsToPack(container);
        List<Item> filteredList = Arrays.asList(container.getFilteredList());
        Assert.assertEquals(3,filteredList.size());
        Assert.assertTrue(filteredList.contains(item1));
        Assert.assertTrue(filteredList.contains(item2));
        Assert.assertTrue(filteredList.contains(item3));
    }

    @Test
    public void filterItemsToPack_nomatchfound() throws APIException {

        Item item1 = new Item(1, 45.33, new BigDecimal(23));
        Item item2 = new Item(2, 5.45, new BigDecimal(10));
        Item item3 = new Item(3, 34.67, new BigDecimal(67));
        Item item4 = new Item(4, 78.66, new BigDecimal(45));
        Item item5 = new Item(5, 99.04, new BigDecimal(1));

        ItemContainer container = getContainer(5, new Item[]{item1,item2,item3,item4,item5});

        impl.filterItemsToPack(container);
        List<Item> filteredList = Arrays.asList(container.getFilteredList());
        Assert.assertEquals(0,filteredList.size());
    }

    private ItemContainer getContainer(double weightLimit, Item[] items) {
        ItemContainer container = new ItemContainer();
        container.setItemList(items);
        container.setWeightLimit(weightLimit);
        return container;
    }
}
