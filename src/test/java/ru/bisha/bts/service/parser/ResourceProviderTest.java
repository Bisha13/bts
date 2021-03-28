package ru.bisha.bts.service.parser;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ResourceProvider Test")
@SpringJUnitConfig(classes = ResourceProviderTest.TestConfig.class)
class ResourceProviderTest {

    @Autowired
    ResourceProvider resourceProvider;

    @Configuration
    static class TestConfig {

        @Bean
        ResourceProvider resourceProvider() {
            return new ResourceProvider();
        }
    }

    @DisplayName("Test resources data contains both test files.")
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

    @Test
    void addResource() {
        Resource[] before = resourceProvider.getResources();
        resourceProvider.addResource("newresource.csv");
        Resource[] after = resourceProvider.getResources();
        assertNotEquals(after, before);
        resourceProvider.addResource("newresource.csv");
        assertEquals(after, resourceProvider.getResources());
    }
}