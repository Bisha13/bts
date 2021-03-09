package ru.bisha.bts.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for index page.
 */
@Controller
@RequestMapping("/index")
public final class IndexController {

    @RequestMapping
    public String showIndex() {
        return "index";
    }
}
