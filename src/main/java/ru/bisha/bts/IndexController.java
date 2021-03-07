package ru.bisha.bts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.bisha.bts.parser.FileParser;

/**
 * Controller for index page.
 */
@Controller
@RequestMapping("/")
public final class IndexController {

    @Autowired
    private FileParser parser;

    @RequestMapping
    public String showIndex() {
        parser.parseFileToDd("src/main/resources/test.csv");
        return "index";
    }
}
