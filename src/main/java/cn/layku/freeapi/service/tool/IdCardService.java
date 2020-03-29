package cn.layku.freeapi.service.tool;

import java.util.Map;

/**
 * @Package cn.layku.freeapi.service.tool
 * @ClassName IdCardService
 * @Description 身份证信息
 * @Author dongdingzhuo
 * @Date 2020/03/29 14:56
 */
public interface IdCardService {

    /**
     * 获取身份证信息
     *
     * @param cardNo
     * @return
     */
    Map<String, Object> getData(String cardNo);

}
