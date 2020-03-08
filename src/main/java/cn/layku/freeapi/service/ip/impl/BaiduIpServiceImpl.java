package cn.layku.freeapi.service.ip.impl;

import cn.layku.freeapi.service.ip.IpService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @packageName: cn.layku.freeapi.service.ip.impl
 * @name: BaiduIpServiceImpl
 * @description:
 * @author: 董定卓
 * @dateTime: 2020/03/08 12:34
 */
@Service("baiduIp")
public class BaiduIpServiceImpl implements IpService {


    @Autowired
    RestTemplate restTemplate;

    @Value("${baidu.ip.sk}")
    private String sk;

    @Value("${baidu.ip.ak}")
    private String ak;

    private long lastTimeMillis = 0;

    /**
     * 根据IP获取地址
     *
     * @param ip
     * @return
     */
    @Override
    public Map<String, Object> getIpAddr(String ip) throws Exception {

        long currentTimeMillis = System.currentTimeMillis();
        long millis = currentTimeMillis - lastTimeMillis;
        if (millis < 100) {
            Thread.sleep(millis);
        }
        lastTimeMillis = currentTimeMillis;

        Map<String, Object> resultMap = new HashMap<>();

        Map<String, Object> restParamMap = new LinkedHashMap<>();
        restParamMap.put("ak", ak);
        restParamMap.put("ip", ip);
        restParamMap.put("coor", "bd09ll");

        Map paramsMap = new LinkedHashMap<String, String>();
        paramsMap.putAll(restParamMap);
        String paramsStr = toQueryString(paramsMap);
        String wholeStr = "/location/ip?" + paramsStr + sk;
        String tempStr = URLEncoder.encode(wholeStr, "UTF-8");
        restParamMap.put("sn", MD5(tempStr));

        String resultStr = restTemplate.getForObject("http://api.map.baidu.com/location/ip?ak={ak}&ip={ip}&coor={coor}&sn={sn}", String.class, restParamMap);
        JSONObject json = JSONObject.parseObject(resultStr);
        if (json.getIntValue("status") == 0) {
            JSONObject contentJson = json.getJSONObject("content");
            JSONObject addressDetailJson = contentJson.getJSONObject("address_detail");
            //省
            resultMap.put("province", addressDetailJson.getString("province"));
            //市
            resultMap.put("city", addressDetailJson.getString("city"));
            //区
            resultMap.put("district", addressDetailJson.getString("district"));
            //街道
            resultMap.put("street", addressDetailJson.getString("street"));

            JSONObject pointJson = contentJson.getJSONObject("point");
            //当前城市中心点经度
            resultMap.put("pointX", pointJson.getString("x"));
            //当前城市中心点纬度
            resultMap.put("pointY", pointJson.getString("y"));
        }

        return resultMap;
    }

    /**
     * 组装参数
     *
     * @param data
     * @return
     * @throws Exception
     */
    private String toQueryString(Map<?, ?> data) throws Exception {
        StringBuffer queryString = new StringBuffer();
        for (Map.Entry<?, ?> pair : data.entrySet()) {
            queryString.append(pair.getKey() + "=");
            queryString.append(URLEncoder.encode((String) pair.getValue(), "UTF-8") + "&");
        }
        if (queryString.length() > 0) {
            queryString.deleteCharAt(queryString.length() - 1);
        }
        return queryString.toString();
    }

    /**
     * 转MD5
     *
     * @param md5
     * @return
     */
    private String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }

    /**
     * unicode转中文
     *
     * @param string
     * @return
     */
    private String unicodeDecode(String string) {
        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
        Matcher matcher = pattern.matcher(string);
        char ch;
        while (matcher.find()) {
            ch = (char) Integer.parseInt(matcher.group(2), 16);
            string = string.replace(matcher.group(1), ch + "");
        }
        return string;
    }

}
