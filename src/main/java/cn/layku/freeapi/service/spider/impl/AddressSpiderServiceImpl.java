package cn.layku.freeapi.service.spider.impl;

import cn.layku.freeapi.service.spider.SpiderService;
import com.alibaba.fastjson.JSON;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author dongdingzhuo
 * @Title: AddressSpiderServiceImpl
 * @Package cn.layku.freeapi.service.spider.impl
 * @Description: 爬取国家统计局地址
 * @date 2020/3/13 14:01
 */
@Service("addressSpider")
public class AddressSpiderServiceImpl implements SpiderService {
    /**
     * 将指定地址的HTML文档转换为需要的数据格式
     *
     * @return
     */
    @Override
    public Object getData() {
        //爬取国家统计局2019年最新地址数据
        String url = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2019/";
        try {
            //获取整个数据
            Document doc = Jsoup.connect(url).get();
            List<Map<String, Object>> list1 = new ArrayList<>();
            Elements elements = doc.select("table.provincetable>tbody>tr.provincetr>td>a");
            for (Element element : elements) {
                String href = element.attr("href");
                String code = href.substring(0, href.lastIndexOf("."));
                String text = element.text();
                Map<String, Object> map1 = new LinkedHashMap<>();
                map1.put(code, text);
                //获取城市
                //http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2019/42.html
                String url2 = url + href;
                Document doc2 = Jsoup.connect(url2).get();
                List<Map<String, Object>> list2 = new ArrayList<>();
                Elements elements2 = doc2.select("table.citytable>tbody>tr.citytr");
                for (Element element2 : elements2) {
                    Elements elements0 = element2.select("td>a");
                    Element element00 = elements0.get(0);
                    String href00 = element00.attr("href");
                    String code0 = element00.text();
                    String text0 = elements0.get(1).text();
                    Map<String, Object> map2 = new LinkedHashMap<>();
                    map2.put(code0, text0);
                    list2.add(map2);
                }
                map1.put("child", list2);

                list1.add(map1);


            }

            return list1;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
