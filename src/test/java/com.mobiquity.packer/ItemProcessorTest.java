package com.mobiquity.packer;

import com.mobiquity.exception.APIException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ItemProcessorTest {

    @InjectMocks
    ItemProcessor processor = new ItemProcessor();

    @Test
    public void packItems_matchfound() throws APIException {
        String inputLine = "80 : (1,3.38,€45) (2,68.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)";

        String output = processor.packItems(inputLine);
        Assert.assertNotNull(output);
        Assert.assertEquals("2,1",output.trim());

    }

    @Test
    public void packItems_matchnotfound() throws APIException {
        String inputLine = "1 : (1,53.38,€45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)";

        String output = processor.packItems(inputLine);
        Assert.assertNotNull(output);
        Assert.assertEquals("-",output.trim());

    }

    @Test(expected = APIException.class)
    public void packItems_parsenotdoneproperly() throws APIException {
        ItemProcessor processor = mock(ItemProcessor.class);

        when(processor.packItems(any(String.class))).thenThrow(new APIException(""));

        String output = processor.packItems("inputLine");

    }
}
