package com.mobiquity.packer.rules;

import com.mobiquity.constants.PackagingConstants;
import com.mobiquity.exception.APIException;
import com.mobiquity.model.Item;
import com.mobiquity.model.ItemContainer;
import com.mobiquity.packer.MockContainer;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class CheckItemQuantityTest {
    @InjectMocks
    CheckItemQuantity rule1;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    MockContainer mockContainer = new MockContainer();

    @Test
    public void applyRule_correctWeight() throws APIException {
        CheckItemQuantity rule = mock(CheckItemQuantity.class);
        Item item1 = new Item(1, 23.2, new BigDecimal(56));
        Item item2 = new Item(2, 45.33, new BigDecimal(23));
        Item item3 = new Item(3, 45.43, new BigDecimal(66));

        ItemContainer container = mockContainer.getContainer(50, new Item[]{item1, item2, item3});

        rule.applyRule(container);
    }

    @Test
    public void applyRule_noofitemslimiterror() throws APIException {

        ItemContainer container = mockContainer.getContainer(50, new Item[30]);

        expectedEx.expect(APIException.class);
        expectedEx.expectMessage(PackagingConstants.MAX_ITEMS_ALLOWED_MSG);

        rule1.applyRule(container);
    }
}
