package com.lvbin.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

@RestController
public class ServerController {
    private Logger logger = LoggerFactory.getLogger(ServerController.class);

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping(value = "/ip", method = RequestMethod.POST)
    @ResponseBody
    private String updateIP(HttpServletRequest httpServletRequest, @RequestBody String s) throws IOException {
        String result = "failed";
        ObjectMapper objectMapper = new ObjectMapper();
        Map jsonObject =  objectMapper.readValue(s, Map.class);
        String auth = jsonObject.get("text").toString();
        if (auth.equals("woshinidaye")) {
            String remoteAddr = httpServletRequest.getRemoteAddr();
            int remotePort = httpServletRequest.getRemotePort();
            Date time = new Date();
            sysUserService.updateIP(remoteAddr, Integer.toString(remotePort), time);
            String ipaddr = String.format("%s:%d@%s", remoteAddr, remotePort, time.toString());
            logger.warn(ipaddr);
            result = "success";
        }
        return result;
    }
    @RequestMapping(value = "/ip", method = RequestMethod.GET)
    @ResponseBody
    private IPAddress GetIP(){
        return sysUserService.getIP();
    }

    @RequestMapping(value = "/select", method = RequestMethod.GET)
    @ResponseBody
    private String GetName() {
        return sysUserService.findByUsername("lvbin");
    }

}
