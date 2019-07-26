package org.muye.community.controller;

import org.muye.community.dto.ImageDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Zz
 * create 2019--07--25--20:19
 **/
@Controller
public class UploadController {



    @RequestMapping("/upload")
    @ResponseBody
    public ImageDTO uploadImage(){
        ImageDTO imageDTO = new ImageDTO();
        imageDTO.setSuccess(1);
        imageDTO.setUrl("/img/qr.png");
        return imageDTO;
    }
}
