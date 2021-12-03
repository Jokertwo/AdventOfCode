package org.jokertwo.adventofcode.common;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class FileReader {

    public BufferedReader open(String filePath) throws FileNotFoundException {
        log.debug("Opening input string to file: {}", filePath);
        InputStream resourceStream = getClass().getClassLoader().getResourceAsStream(filePath);
        if (resourceStream == null) {
            log.warn("Unable to find file: {}", filePath);
            throw new FileNotFoundException("File not found: " + filePath);
        }
        return new BufferedReader(
            new InputStreamReader(resourceStream, StandardCharsets.UTF_8));
    }
}
