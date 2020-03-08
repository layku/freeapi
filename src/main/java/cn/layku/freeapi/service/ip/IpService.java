package cn.layku.freeapi.service.ip;

import java.util.Map;

/**
 * @packageName: cn.layku.freeapi.service.mail
 * @name: IpService
 * @description:
 * @author: 董定卓
 * @dateTime: 2020/03/08 12:30
 */
public interface IpService {

    /**
     * 根据IP获取地址
     *
     * @param ip
     * @return
     */
    Map<String, Object> getIpAddr(String ip) throws Exception;

}
