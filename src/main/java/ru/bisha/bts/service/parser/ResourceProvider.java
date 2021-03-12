package ru.bisha.bts.service.parser;

import java.util.Arrays;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ResourceProvider {

    @Value("classpath:data/*.csv")
    private Resource[] resources;

    public void addResource(final String resource) {
        boolean contains = false;
        for (Resource res : resources) {
            if (res == null) {
                continue;
            } //todo make it nice please!
            if (res.getFilename().equals(resource)) {
                contains = true;
            }
        }

        if (!contains) {
            resources = Arrays.copyOf(resources, resources.length + 1);
            resources[resources.length - 1] =
                    new FileSystemResource(resource.toLowerCase());
        }
    }
}
