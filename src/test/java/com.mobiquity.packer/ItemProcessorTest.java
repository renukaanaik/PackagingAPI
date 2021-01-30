package com.mobiquity.packer;

import com.mobiquity.exception.APIException;
import com.mobiquity.model.Item;
import com.mobiquity.model.ItemContainer;
import com.mobiquity.utils.ItemHelper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ItemProcessorTest {

    @InjectMocks
    ItemProcessor processor = new ItemProcessor();

    @Mock
    PackageItemsImpl impl;

    @Mock
    ItemHelper helper;

    @Test
    public void packItems_matchfound() throws APIException {
        String inputLine = "80 : (1,3.38,€45) (2,68.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)";

        doCallRealMethod().when(helper).parseItem(any(String.class));
        doCallRealMethod().when(impl).validateInputs(any(ItemContainer.class));
        doCallRealMethod().when(impl).filterItemsToPack(any(ItemContainer.class));

        String output = processor.packItems(inputLine);
        Assert.assertNotNull(output);
        Assert.assertEquals("2,1",output.trim());

    }

    @Test
    public void packItems_matchnotfound() throws APIException {
        String inputLine = "1 : (1,53.38,€45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)";

        doCallRealMethod().when(helper).parseItem(any(String.class));
        doCallRealMethod().when(impl).validateInputs(any(ItemContainer.class));
        doCallRealMethod().when(impl).filterItemsToPack(any(ItemContainer.class));

        String output = processor.packItems(inputLine);
        Assert.assertNotNull(output);
        Assert.assertEquals("-",output.trim());

    }
}
