package cn.layku.freeapi.service.tool.impl;

import cn.layku.freeapi.service.tool.IdCardService;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @Package cn.layku.freeapi.service.tool.impl
 * @ClassName IdCardServiceImpl
 * @Description TODO
 * @Author dongdingzhuo
 * @Date 2020/03/29 14:58
 */
@Service("idCard")
public class IdCardServiceImpl implements IdCardService {


    /**
     * 获取身份证信息
     *
     * @param cardNo
     * @return
     */
    @Override
    public Map<String, Object> getData(String cardNo) {
        return null;
    }

    public static String readJsonFile(String fileName) {
        String jsonStr;
        try {
            File jsonFile = new File(fileName);
            FileReader fileReader = new FileReader(jsonFile);

            Reader reader = new InputStreamReader(new FileInputStream(jsonFile), "utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}
