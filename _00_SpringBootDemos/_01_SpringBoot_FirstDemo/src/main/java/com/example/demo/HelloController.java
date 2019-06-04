package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gustaov on 2019/4/12.
 */
@RestController
@RequestMapping("demo")
public class HelloController {
    @RequestMapping("hello")
    public String hello() {
        return "_01_SpringBoot_FirstDemo";
    }

    @RequestMapping(value = "/json", method = RequestMethod.GET)
    public void testDownload(HttpServletResponse res) {
        List<Student> list = new ArrayList<>();
        list.add(new Student(1, "james", "M", 23));
        list.add(new Student(2, "你好", "F", 22));
        list.add(new Student(2, "rose", "F", 22));
        list.add(new Student(2, "rose", "F", 22));
        list.add(new Student(2, "rose", "F", 22));
        list.add(new Student(2, "rose", "F", 22));
        list.add(new Student(2, "rose", "F", 22));
        res.setHeader("content-type", "application/json");
        res.setContentType("application/x-download");
        res.setHeader("Content-Disposition", "attachment; filename=test.json");

        byte[] object = JSON.toJSONBytes(list);

        OutputStream os = null;
        try {
            os = res.getOutputStream();
            IOUtils.copy(new ByteArrayInputStream(object), os);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("export file finish");
    }


}
