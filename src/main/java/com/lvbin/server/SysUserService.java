package com.lvbin.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    public String findByUsername(String username) {

        return sysUserMapper.findByUsername(username);
    }

    public void updateIP (String address, String port, Date time) {
        sysUserMapper.updateIP(address, port, time);
    }

    public IPAddress getIP () {
        return sysUserMapper.getIP();
    };

}
