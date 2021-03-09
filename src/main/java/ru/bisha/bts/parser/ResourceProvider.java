package ru.bisha.bts.parser;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ResourceProvider {

    @Value("classpath:data/*.csv")
    private Resource[] resources;

}
