package ru.bisha.bts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.bisha.bts.fileParser.FileParser;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private FileParser parser;

    @RequestMapping
    public String showIndex() {
        parser.parseFileToDB("src/main/resources/test.csv");
        return "index";
    }
}
