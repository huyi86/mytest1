package my.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
@RequestMapping("page")
@Controller
public class PageController {
    @RequestMapping("/{pathName}")
    public String hello(@PathVariable("pathName")String pathName){
        return pathName;
    }
}
