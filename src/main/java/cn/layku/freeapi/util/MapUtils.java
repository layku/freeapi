package cn.layku.freeapi.util;

import java.util.Map;

/**
 * @author dongdingzhuo
 * @Title: MapUtils
 * @Package cn.layku.freeapi.util
 * @Description: 获取Map参数值
 * @date 2020/3/14 10:43
 */
public class MapUtils {

    public static String getString(Map<String, Object> map, String fieldName) {
        if (map == null || map.size() == 0 || map.get(fieldName) == null) {
            return null;
        }
        Object o = map.get(fieldName);
        if (o == null || o.toString().trim().length() == 0) {
            return null;
        }
        String s = o.toString().trim();
        if ("undefined".equalsIgnoreCase(s) || "null".equalsIgnoreCase(s)) {
            return null;
        }
        return s;
    }


    public static Integer getInt(Map<String, Object> map, String fieldName) {
        String s = getString(map, fieldName);
        if (s == null) {
            return null;
        }
        Integer i = null;
        try {
            i = Integer.parseInt(s);
        } catch (Exception e) {
            return null;
        }
        return i;
    }


    public static Float getFloat(Map<String, Object> map, String fieldName) {
        String s = getString(map, fieldName);
        if (s == null) {
            return null;
        }
        Float i = null;
        try {
            i = Float.parseFloat(s);
        } catch (Exception e) {
            return null;
        }
        return i;
    }

}
