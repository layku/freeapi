package cn.layku.freeapi.service.spider.impl;

import cn.layku.freeapi.dao.AreaDao;
import cn.layku.freeapi.service.spider.SpiderService;
import cn.layku.freeapi.util.MapUtils;
import com.alibaba.fastjson.JSON;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @author dongdingzhuo
 * @Title: AddressSpiderServiceImpl
 * @Package cn.layku.freeapi.service.spider.impl
 * @Description: 爬取国家统计局地址
 * @date 2020/3/13 14:01
 */
@Service("addressSpider")
public class AddressSpiderServiceImpl implements SpiderService {

    @Autowired
    private AreaDao dao;

    /**
     * 将指定地址的HTML文档转换为需要的数据格式
     *
     * @return
     */
    @Override
    public Object getData() {
        //爬取国家统计局2019年最新地址数据
        String url = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2019/";
        List<Map<String, Object>> provinceList = getProvinces(url);
        for (Map<String, Object> provinceMap : provinceList) {
            String provinceUrl = MapUtils.getString(provinceMap, "url");
            if (provinceUrl == null) {
                continue;
            }
            String provinceCode = MapUtils.getString(provinceMap, "code");
            List<Map<String, Object>> cityList = getCitys(provinceUrl, provinceCode);
            for (Map<String, Object> cityMap : cityList) {
                String cityUrl = MapUtils.getString(cityMap, "url");
                if (cityUrl == null) {
                    continue;
                }
                String cityCode = MapUtils.getString(cityMap, "code");
                List<Map<String, Object>> countyList = getCountys(cityUrl, cityCode);
                for (Map<String, Object> countyMap : countyList) {
                    String countyUrl = MapUtils.getString(countyMap, "url");
                    if (countyUrl == null) {
                        continue;
                    }
                    String countyCode = MapUtils.getString(countyMap, "code");
                    List<Map<String, Object>> townList = getTowns(countyUrl, countyCode);
                    for (Map<String, Object> townMap : townList) {
                        String townUrl = MapUtils.getString(townMap, "url");
                        if (townUrl == null) {
                            continue;
                        }
                        String townCode = MapUtils.getString(townMap, "code");
                        List<Map<String, Object>> villageList = getVillages(townUrl, townCode);
                        townMap.put("child", villageList);
                    }
                    countyMap.put("child", townList);
                }
                cityMap.put("child", countyList);
            }
            provinceMap.put("child", cityList);
        }
        return provinceList;
    }

    /**
     * 获取省级数据
     *
     * @param url
     * @return
     */
    private List<Map<String, Object>> getProvinces(String url) {
        //http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2019/
        List<Map<String, Object>> dataList = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(url).timeout(60000).get();
            Elements elements = doc.select("table.provincetable>tbody>tr.provincetr>td>a");
            int index = 1;
            for (Element element : elements) {
                Map<String, Object> dataMap = new HashMap<>();
                String path = element.attr("href");
                String code = path.substring(0, path.lastIndexOf("."));
                String text = element.text();
                dataMap.put("code", code);
                dataMap.put("name", text);
                dataMap.put("url", url + path);
                dataMap.put("parentCode", "0");
                dataMap.put("level", 1);
                dataMap.put("viewSort", index++);
                dataList.add(dataMap);

            }
        } catch (Exception e) {
            System.err.println("超时->" + url);
            getProvinces(url);
        }
        if (!dataList.isEmpty()) {
            dao.insertBatch(dataList);
        }
        return dataList;
    }

    /**
     * 获取市级数据
     *
     * @param url
     * @param parentCode
     * @return
     */
    private List<Map<String, Object>> getCitys(String url, String parentCode) {
        //http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2019/42.html
        List<Map<String, Object>> dataList = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(url).timeout(60000).get();
            Elements trElements = doc.select("table.citytable>tbody>tr.citytr");
            int index = 1;
            for (Element element : trElements) {
                Elements tdElements = element.select("td>a");

                Element aElement = tdElements.get(0);
                String path = aElement.attr("href");
                String code = aElement.text();
                String text = tdElements.get(1).text();

                Map<String, Object> dataMap = new HashMap<>();
                dataMap.put("code", code);
                dataMap.put("name", text);
                dataMap.put("url", url.substring(0, url.lastIndexOf("/") + 1) + path);
                dataMap.put("parentCode", parentCode);
                dataMap.put("level", 2);
                dataMap.put("viewSort", index++);
                dataList.add(dataMap);
            }
        } catch (Exception e) {
            System.err.println("超时->" + url);
            getCitys(url, parentCode);
        }
        if (!dataList.isEmpty()) {
            dao.insertBatch(dataList);
        }
        return dataList;
    }

    /**
     * 获取区级数据
     *
     * @param url
     * @param parentCode
     * @return
     */
    private List<Map<String, Object>> getCountys(String url, String parentCode) {
        //http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2019/42/4203.html
        List<Map<String, Object>> dataList = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(url).timeout(60000).get();
            Elements trElements = doc.select("table.countytable>tbody>tr.countytr");
            int index = 1;
            for (Element element : trElements) {
                Elements tdElements = element.select("td>a");
                if (tdElements == null || tdElements.isEmpty()) {
                    tdElements = element.select("td");
                }
                Element aElement = tdElements.get(0);
                String path = aElement.attr("href");
                String code = aElement.text();
                String text = tdElements.get(1).text();

                Map<String, Object> dataMap = new HashMap<>();
                dataMap.put("code", code);
                dataMap.put("name", text);
                if (StringUtils.hasText(path)) {
                    dataMap.put("url", url.substring(0, url.lastIndexOf("/") + 1) + path);
                }
                dataMap.put("parentCode", parentCode);
                dataMap.put("level", 3);
                dataMap.put("viewSort", index++);
                dataList.add(dataMap);
            }
        } catch (Exception e) {
            System.err.println("超时->" + url);
            getCountys(url, parentCode);
        }
        if (!dataList.isEmpty()) {
            dao.insertBatch(dataList);
        }
        return dataList;
    }

    /**
     * 获取街道级数据
     *
     * @param url
     * @param parentCode
     * @return
     */
    private List<Map<String, Object>> getTowns(String url, String parentCode) {
        //http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2019/42/01/420105.html
        List<Map<String, Object>> dataList = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(url).timeout(60000).get();
            Elements trElements = doc.select("table.towntable>tbody>tr.towntr");
            int index = 1;
            for (Element element : trElements) {
                Elements tdElements = element.select("td>a");
                if (tdElements == null || tdElements.isEmpty()) {
                    tdElements = element.select("td");
                }
                Element aElement = tdElements.get(0);
                String path = aElement.attr("href");
                String code = aElement.text();
                String text = tdElements.get(1).text();

                Map<String, Object> dataMap = new HashMap<>();
                dataMap.put("code", code);
                dataMap.put("name", text);
                if (StringUtils.hasText(path)) {
                    dataMap.put("url", url.substring(0, url.lastIndexOf("/") + 1) + path);
                }
                dataMap.put("parentCode", parentCode);
                dataMap.put("level", 4);
                dataMap.put("viewSort", index++);
                dataList.add(dataMap);
            }
        } catch (Exception e) {
            System.err.println("超时->" + url);
            getTowns(url, parentCode);
        }
        if (!dataList.isEmpty()) {
            dao.insertBatch(dataList);
        }
        return dataList;
    }

    /**
     * 获取社区级数据
     *
     * @param url
     * @param parentCode
     * @return
     */
    private List<Map<String, Object>> getVillages(String url, String parentCode) {
        //http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2019/42/01/05/420105007.html
        List<Map<String, Object>> dataList = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(url).timeout(60000).get();
            Elements trElements = doc.select("table.villagetable>tbody>tr.villagetr");
            int index = 1;
            for (Element element : trElements) {
                Elements tdElements = element.select("td");
                String code = tdElements.get(0).text();
                String text = tdElements.get(2).text();
                Map<String, Object> dataMap = new HashMap<>();
                dataMap.put("code", code);
                dataMap.put("name", text);
                dataMap.put("parentCode", parentCode);
                dataMap.put("level", 5);
                dataMap.put("viewSort", index++);
                dataList.add(dataMap);
            }
        } catch (Exception e) {
            System.err.println("超时->" + url);
            getVillages(url, parentCode);
        }
        if (!dataList.isEmpty()) {
            dao.insertBatch(dataList);
        }
        return dataList;
    }

}
