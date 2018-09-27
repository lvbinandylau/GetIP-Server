package com.lvbin.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ServerController {
    private Logger logger = LoggerFactory.getLogger(ServerController.class);

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping(value = "/ip", method = RequestMethod.POST)
    @ResponseBody
    private String updateIP(HttpServletRequest httpServletRequest, @RequestBody String s) throws IOException {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
        SimpleDateFormat bjSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");     // 北京
        bjSdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));  // 设置北京时区

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
            //logger.warn(ipaddr);
            result = "success";
        }
        return result;
    }
    @RequestMapping(value = "/ip", method = RequestMethod.GET)
    @ResponseBody
    private IPAddress GetIP(){
        IPAddress ipAddress = sysUserService.getIP();
        logger.warn(ipAddress.getTime().toString());
        return ipAddress;
    }

    @RequestMapping(value = "/select", method = RequestMethod.GET)
    @ResponseBody
    private String GetName() {
        return sysUserService.findByUsername("lvbin");
    }

}
