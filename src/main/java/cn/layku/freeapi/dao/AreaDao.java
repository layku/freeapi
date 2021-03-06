package cn.layku.freeapi.dao;

import java.util.List;
import java.util.Map;

/**
 * @author dongdingzhuo
 * @Title: AreaDao
 * @Package cn.layku.freeapi.dao
 * @Description: 地区相关接口
 * @date 2020/3/14 12:03
 */
public interface AreaDao {

    /**
     * 添加数据
     *
     * @param list
     * @return
     */
    int insertBatch(List<Map<String, Object>> list);

    /**
     * 添加行政区域
     *
     * @param map
     * @return
     */
    int insert(Map<String, Object> map);

    /**
     * 获取所有行政区域
     *
     * @return
     */
    List<Map<String, Object>> list();

}
