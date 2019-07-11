package org.muye.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Zz
 * create 2019--07--11--18:22
 **/
@Controller
public class PublishController {
    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }
}
