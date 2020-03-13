package cn.layku.freeapi.service.spider;

/**
 * @author dongdingzhuo
 * @Title: SpiderService
 * @Package cn.layku.freeapi.service.spider
 * @Description: 爬虫接口
 * @date 2020/3/13 13:54
 */
public interface SpiderService {

    /**
     * 将指定地址的HTML文档转换为需要的数据格式
     *
     * @return
     */
    Object getData();


}
