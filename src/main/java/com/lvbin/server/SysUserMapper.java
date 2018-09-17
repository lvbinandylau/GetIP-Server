package com.lvbin.server;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

@Mapper
public interface SysUserMapper {
    String findByUsername(@Param("username") String username);
    void updateIP(@Param("address") String address, @Param("port") String port, @Param("time") Date time);
    IPAddress getIP();
}
