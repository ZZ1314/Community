package org.muye.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Zz
 * create 2019--07--10--12:18
 **/
@Controller
public class IndexController {
    @GetMapping({"/","index"})
    public String index(){
        return "index";
    }
}
