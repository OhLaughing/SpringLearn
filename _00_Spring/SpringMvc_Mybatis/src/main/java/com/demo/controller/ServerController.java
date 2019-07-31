package com.demo.controller;

import com.demo.entity.Result;
import com.demo.entity.Server;
import com.demo.service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 */
@Controller
@RequestMapping("server")
public class ServerController {
    @Autowired
    private ServerService serverService;

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public Server add(@RequestBody Server server) {
        System.out.println(server);
        Server s = serverService.add(server);
        System.out.println(s);
        return s;
    }

    /**
     * 跟传统的update有些区别，本update的作用是，客户端主动发起update命令，更新服务器的详细信息
     *
     * @param server
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Server update(Server server) {
        Server s = serverService.find(server.getId());
        return s;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Result delete(int id) {
        Server server = serverService.delete(id);
        if (server != null) {
            return new Result(true, "delete server success");
        } else {
            return new Result(false, "delete server failure");
        }
    }
}
