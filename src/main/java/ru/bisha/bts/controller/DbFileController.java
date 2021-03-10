package ru.bisha.bts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.bisha.bts.model.dto.ResourceDto;
import ru.bisha.bts.parser.FileParser;
import ru.bisha.bts.parser.ResourceProvider;
import ru.bisha.bts.repo.DbCleaner;


@Controller
@RequestMapping("/")
public class DbFileController {

    @Autowired
    private ResourceProvider resourceProvider;

    @Autowired
    private FileParser fileParser;

    @Autowired
    private DbCleaner dbCleaner;

    @GetMapping
    public String chooseFile(final Model model) {
        Resource[] resources = resourceProvider.getResources();
        model.addAttribute("resourcesAtr", resources);
        model.addAttribute("resourceDto", new ResourceDto());
        return "choose-file";
    }

    @PostMapping("/loadFile")
    public String loadFile(
            @ModelAttribute(value = "resourceDto") final ResourceDto resourceDto) {
        dbCleaner.deleteAllData();
        fileParser.parseFileToDd(resourceDto.getResource());
        return "index";
    }
}
