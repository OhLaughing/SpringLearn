package com.demo.controller;

import com.demo.CheckException;
import com.demo.MmlException;
import com.demo.ReturnMsg;
import com.demo.entity.Result;
import com.demo.entity.Server;
import com.demo.service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 *
 */
@Controller
@RequestMapping("/server")
public class ServerController {
    @Autowired
    private ServerService serverService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ReturnMsg add(@RequestBody Server server) {
        System.out.println(server);
        try {
            int i = serverService.add(server);
            if (i > 0) {
                return new ReturnMsg(true, "add server success", server);
            } else {
                return new ReturnMsg(false, "add server failure");
            }
        } catch (MmlException e) {
            e.printStackTrace();
        } catch (CheckException e) {
            e.printStackTrace();
        }

        return new ReturnMsg(false, "add server failure");
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Result delete(@PathVariable("id") int id) {
        int result = serverService.delete(id);
        if (result > 0) {
            return new Result(true, "delete server success");
        } else {
            return new Result(false, "delete server failure");
        }
    }

    /**
     * 跟传统的update有些区别，本update的作用是，客户端主动发起update命令，更新服务器的详细信息
     *
     * @param server
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ReturnMsg update(@RequestBody Server server) {
        int i = serverService.update(server);
        if (i > 0) {
            return new ReturnMsg(true, "update server success", server);
        } else {
            return new ReturnMsg(false, "udpate server failure");
        }
    }

    @RequestMapping(value = "/find/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Server findServer(@PathVariable("id") int id) {
        return serverService.find(id);
    }
}
