package ru.bisha.bts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.bisha.bts.model.dto.ResourceDto;
import ru.bisha.bts.service.parser.FileParserService;
import ru.bisha.bts.service.parser.ResourceProvider;
import ru.bisha.bts.repo.DbCleaner;
import ru.bisha.bts.service.save.FileSaverService;

import java.util.List;


@Controller
@RequestMapping("/file")
public class DbFileController {

    @Autowired
    private ResourceProvider resourceProvider;

    @Autowired
    private FileParserService fileParserService;

    @Autowired
    private DbCleaner dbCleaner;

    @Autowired
    private FileSaverService fileSaver;

    public static final String RESOURCE_PREFIX = "src/main/resources/data/";

    private ResourceDto resourceDto;

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
        fileParserService.parseFileToDd(RESOURCE_PREFIX + resourceDto.getResource());
        this.resourceDto = resourceDto;
        return "home";
    }

    @GetMapping("/saveFile")
    public String chooseFileToSave(final Model model) {
        model.addAttribute("resourceDto", resourceDto);
        return "save-file";
    }

    @PostMapping("/saveFile/save")
    public String saveFile(
            @ModelAttribute(value = "resourceDto") final ResourceDto resourceDto) {
        String resource = RESOURCE_PREFIX + resourceDto.getResource();
        resourceProvider.addResource(resource);
        fileSaver.saveFileFromDb(resource);
        dbCleaner.deleteAllData();
        fileParserService.parseFileToDd(resource);
        return "redirect:/index";
    }
}
