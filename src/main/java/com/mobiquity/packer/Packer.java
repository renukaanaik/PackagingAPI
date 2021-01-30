package com.mobiquity.packer;

import com.mobiquity.exception.APIException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Packer {

    private static ItemProcessor processor = new ItemProcessor();

    private Packer() {

    }

    public static String pack(String filePath) throws APIException {

        StringBuilder output = new StringBuilder() ;
        try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)) {
            List<String> inputItems = stream.collect(Collectors.toList());
            for (String itemline : inputItems) {
                output.append(processor.packItems(itemline));
                output.append("\n");
            }
        } catch (IOException e) {
            throw new APIException("Error while reading input file", e);
        }
        return output.toString();
    }

}
