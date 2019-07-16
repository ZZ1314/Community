package org.muye.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Zz
 * create 2019--07--16--00:34
 **/
@Controller
public class ProfileController {
    @GetMapping("/profile/{action}")
    public String profile(@PathVariable("action")String action,
                          Model model){
        if (action.equals("question")){
            model.addAttribute("section","question");
            model.addAttribute("sectionName","我的提问");
        }else if (action.equals("replies")){
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","最新回复");
        }
        return "profile";
    }
}
