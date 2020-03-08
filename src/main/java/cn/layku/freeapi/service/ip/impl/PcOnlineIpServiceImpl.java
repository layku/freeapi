package cn.layku.freeapi.service.ip.impl;

import cn.layku.freeapi.service.ip.IpService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @packageName: cn.layku.freeapi.service.ip.impl
 * @name: PcOnlineIpServiceImpl
 * @description:
 * @author: 董定卓
 * @dateTime: 2020/03/08 13:18
 */
@Service("pcOnlineIp")
public class PcOnlineIpServiceImpl implements IpService {

    @Autowired
    RestTemplate restTemplate;

    /**
     * 根据IP获取地址
     *
     * @param ip
     * @return
     */
    @Override
    public Map<String, Object> getIpAddr(String ip) {
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> restParamMap = new LinkedHashMap<>();
        restParamMap.put("ip", ip);
        String resultStr = restTemplate.getForObject("http://whois.pconline.com.cn/ipJson.jsp?ip={ip}&json=true", String.class, restParamMap);
        //{"ip":"61.132.163.68","pro":"安徽省","proCode":"340000","city":"合肥市","cityCode":"340100","region":"","regionCode":"0","addr":"安徽省合肥市 电信","regionNames":"","err":""}
        JSONObject json = JSONObject.parseObject(resultStr);
        //省
        resultMap.put("province", json.getString("pro"));
        //市
        resultMap.put("city", json.getString("city"));
        //区
        resultMap.put("district", json.getString("region"));
        return resultMap;
    }
}
