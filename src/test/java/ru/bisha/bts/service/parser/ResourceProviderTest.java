package ru.bisha.bts.service.parser;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class ResourceProviderTest {

    @Autowired
    ResourceProvider resourceProvider;

    @Test
    void getResources() {
        Resource[] resources = resourceProvider.getResources();
        assertNotEquals(0, resources.length);


        List<String> resourcesFiles = new ArrayList<>();
        for (Resource resource : resources) {
            resourcesFiles.add(resource.getFilename());
        }

        // resources folder must contain next files.
        assertTrue(resourcesFiles.contains("test.csv"));
        assertTrue(resourcesFiles.contains("lotr.csv"));

    }
}