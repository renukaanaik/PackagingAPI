package com.mobiquity.packer;

import com.mobiquity.exception.APIException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(MockitoJUnitRunner.class)
public class PackerTest {

    String absolutePath = null;

    @Before
    public void setAbsolutePath(){
        Path resourceDirectory = Paths.get("src","test","resources");
        absolutePath = resourceDirectory.toFile().getAbsolutePath();
    }

    @Test
    public void pack_withoutException() throws APIException {
        StringBuilder sb = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(absolutePath+"/example_output"))) {
            List<String> outputLines = stream.collect(Collectors.toList());
            for (String itemline : outputLines) {
                sb.append(itemline);
                sb.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(sb.toString(), Packer.pack(absolutePath+"/example_input"));
    }

    @Test(expected = APIException.class)
    public void pack_withException() throws APIException {
        Packer.pack(absolutePath+"/example_invalid_input");
    }
}
