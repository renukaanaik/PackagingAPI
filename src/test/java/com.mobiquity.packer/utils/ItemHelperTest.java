package com.mobiquity.packer.utils;

import com.mobiquity.exception.APIException;
import com.mobiquity.model.ItemContainer;
import com.mobiquity.utils.ItemHelper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ItemHelperTest {

    @InjectMocks
    ItemHelper helper = new ItemHelper();

    @Test
    public void parseItem_validInput() throws APIException {
        String inputLine = "81 : (1,53.38,€45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)";
        ItemContainer container = helper.parseItem(inputLine);

        Assert.assertNotNull(container);
        Assert.assertEquals(6,container.getItemList().length);
        Assert.assertEquals(81,container.getWeightLimit(),0);
    }

    @Test(expected = APIException.class)
    public void parseItem_weightLimitNotPresent() throws APIException {
        String inputLine = " (1,53.38,€45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)";
        ItemContainer container = helper.parseItem(inputLine);
    }

    @Test(expected = APIException.class)
    public void parseItem_emptyInput() throws APIException {
        String inputLine = "";
        ItemContainer container = helper.parseItem(inputLine);
    }

   /* @Test
    public void parseItem_extraCharsInInput() throws APIException {
        String inputLine = " 81 : (1,53.38,€45) ((2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)";
        ItemContainer container = helper.parseItem(inputLine);

        Assert.assertNotNull(container);
        Assert.assertEquals(6,container.getItemList().length);
        Assert.assertEquals(81,container.getWeightLimit(),0);
    }*/

    @Test(expected = APIException.class)
    public void parseItem_pricemissing() throws APIException {
        String inputLine = " 81 : (2,88.62,€)";
        ItemContainer container = helper.parseItem(inputLine);
    }

    @Test(expected = APIException.class)
    public void parseItem_weightmissing() throws APIException {
        String inputLine = " 81 : (2,€45)";
        ItemContainer container = helper.parseItem(inputLine);
    }

    @Test(expected = APIException.class)
    public void parseItem_itemindexmissing() throws APIException {
        String inputLine = " 81 : (,2,€45)";
        ItemContainer container = helper.parseItem(inputLine);
    }

    @Test(expected = APIException.class)
    public void parseItem_unwantedcharpresent() throws APIException {
        String inputLine = " 81 : (1,2,€45)*";
        ItemContainer container = helper.parseItem(inputLine);
    }
}
