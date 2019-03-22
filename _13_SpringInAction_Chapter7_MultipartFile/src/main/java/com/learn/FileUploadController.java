package com.learn;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/fileupload")
public class FileUploadController {

    @RequestMapping(method = RequestMethod.GET)
    public String uploadForm() {
        return "uploadForm";
    }

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String uploadForm1() {
        return "hello";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String processUpload(@RequestPart("file") MultipartFile file) {

        System.out.println(file.getSize());

//    System.out.println("---->  " + file.getName() + "  ::  "  + file.getSize());

        return "redirect:/";
    }

}
