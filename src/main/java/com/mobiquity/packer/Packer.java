package com.mobiquity.packer;

import com.mobiquity.constants.PackagingConstants;
import com.mobiquity.exception.APIException;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * API Class to get input and provide output in desired format
 *
 * @author Renuka Naik
 */
@Slf4j
public class Packer {

    private static ItemProcessor processor = new ItemProcessor();

    private Packer() {

    }

    /**
     * This method takes absolute file path as input which contains item details.
     *
     * @param filePath absolute file path
     * @return output string with item details which can go in the package
     * @throws APIException Application specific exception
     */
    public static String pack(String filePath) throws APIException {
        StringBuilder output = new StringBuilder();
        log.debug("File to be processed :" + filePath);
        try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)) {
            List<String> inputItems = stream.collect(Collectors.toList());
            for (String itemline : inputItems) {
                output.append(processor.packItems(itemline));
                output.append("\n");
            }
        } catch (IOException e) {
            log.error("Error processing File :" + e.getMessage());
            throw new APIException(PackagingConstants.ERROR_READING_FILE, e);
        }
        return output.toString();
    }
}
