package cn.layku.freeapi.service.spider.impl;

import cn.layku.freeapi.dao.AreaDao;
import cn.layku.freeapi.service.spider.SpiderService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @Package cn.layku.freeapi.service.spider.impl
 * @ClassName AdministrativeSpiderServiceImpl
 * @Description TODO
 * @Author dongdingzhuo
 * @Date 2020/03/29 10:56
 */
@Service("admSpider")
public class AdministrativeSpiderServiceImpl implements SpiderService {


    @Autowired
    private AreaDao areaDao;

    /**
     * 将指定地址的HTML文档转换为需要的数据格式
     *
     * @return
     */
    @Override
    public Object getData() {
        Set<Map<String, Object>> dataSet = new LinkedHashSet<>();
        //1980
        String[] param1980 = {"http://www.mca.gov.cn/article/sj/tjbz/a/201713/201708040959.html", "td.xl6616359", "td.xl6716359", "td.xl6816359", "td.xl6916359"};
        getData(param1980, dataSet);

        //1981
        String[] param1981 = {"http://www.mca.gov.cn/article/sj/tjbz/a/201713/201708041004.html", "td.xl668200", "td.xl678200", "td.xl688200", "td.xl698200"};
        getData(param1981, dataSet);

        //1982
        String[] param1982 = {"http://www.mca.gov.cn/article/sj/xzqh/1980/1980/201911180942.html", "td.xl6821218", "td.xl6921218", "td.xl7021218", "td.xl7121218"};
        getData(param1982, dataSet);

        //1983
        String[] param1983 = {"http://www.mca.gov.cn/article/sj/tjbz/a/201713/201708160821.html", "td.xl6819478", "td.xl6919478", "td.xl6719478", "td.xl7019478"};
        getData(param1983, dataSet);

        //1984
        String[] param1984 = {"http://www.mca.gov.cn/article/sj/tjbz/a/201713/201708220856.html", "td.xl6725318", "td.xl6825318", "td.xl6625318", "td.xl6925318"};
        getData(param1984, dataSet);

        //1985
        String[] param1985 = {"http://www.mca.gov.cn/article/sj/tjbz/a/201713/201708220858.html", "td.xl677077", "td.xl637077"};
        getData(param1985, dataSet);

        //1986
        String[] param1986 = {"http://www.mca.gov.cn/article/sj/tjbz/a/201713/201708220859.html", "td.xl6916983", "td.xl6316983"};
        getData(param1986, dataSet);

        //1987
        String[] param1987 = {"http://www.mca.gov.cn/article/sj/xzqh/1980/1980/201911180950.html", "td.xl699898", "td.xl639898"};
        getData(param1987, dataSet);

        //1988
        String[] param1988 = {"http://www.mca.gov.cn/article/sj/tjbz/a/201713/201708220903.html", "td.xl6713319", "td.xl6313319"};
        getData(param1988, dataSet);

        //1989
        String[] param1989 = {"http://www.mca.gov.cn/article/sj/tjbz/a/201713/201708041017.html", "td.xl678084", "td.xl638084"};
        getData(param1989, dataSet);

        //1990
        String[] param1990 = {"http://www.mca.gov.cn/article/sj/tjbz/a/201713/201708041018.html", "td.xl6710373", "td.xl6310373"};
        getData(param1990, dataSet);

        //1991
        String[] param1991 = {"http://www.mca.gov.cn/article/sj/tjbz/a/201713/201708041020.html", "td.xl6728593", "td.xl6328593"};
        getData(param1991, dataSet);

        //1992
        String[] param1992 = {"http://www.mca.gov.cn/article/sj/tjbz/a/201713/201708220910.html", "td.xl6719808", "td.xl6319808"};
        getData(param1992, dataSet);

        //1993
        String[] param1993 = {"http://www.mca.gov.cn/article/sj/tjbz/a/201713/201708041023.html", "td.xl674742", "td.xl634742"};
        getData(param1993, dataSet);

        //1994
        String[] param1994 = {"http://www.mca.gov.cn/article/sj/tjbz/a/201713/201708220911.html", "td.xl6727031", "td.xl6327031"};
        getData(param1994, dataSet);

        //1995
        String[] param1995 = {"http://www.mca.gov.cn/article/sj/tjbz/a/201713/201708220913.html", "td.xl6726143", "td.xl6326143"};
        getData(param1995, dataSet);

        //1996
        String[] param1996 = {"http://www.mca.gov.cn/article/sj/tjbz/a/201713/201708220916.html", "td.xl6730764", "td.xl6330764"};
        getData(param1996, dataSet);

        //1997
        String[] param1997 = {"http://www.mca.gov.cn/article/sj/tjbz/a/201713/201708220916.html", "td.xl6715108", "td.xl6315108"};
        getData(param1997, dataSet);

        //1998
        String[] param1998 = {"http://www.mca.gov.cn/article/sj/tjbz/a/201713/201708220918.html", "td.xl6722531", "td.xl6322531"};
        getData(param1998, dataSet);

        //1999
        String[] param1999 = {"http://www.mca.gov.cn/article/sj/tjbz/a/201713/201708220921.html", "td.xl6724998", "td.xl6324998"};
        getData(param1999, dataSet);

        //2000
        String[] param2000 = {"http://www.mca.gov.cn/article/sj/tjbz/a/201713/201708220923.html", "td.xl6727851", "td.xl6327851"};
        getData(param2000, dataSet);

        //2001
        String[] param2001 = {"http://www.mca.gov.cn/article/sj/tjbz/a/201713/201708220925.html", "td.xl671094", "td.xl631094"};
        getData(param2001, dataSet);

        //2002
        String[] param2002 = {"http://www.mca.gov.cn/article/sj/tjbz/a/201713/201708220927.html", "td.xl671038", "td.xl631038"};
        getData(param2002, dataSet);

        //2003
        String[] param2003 = {"http://www.mca.gov.cn/article/sj/tjbz/a/201713/201708220928.html", "td.xl672680", "td.xl632680"};
        getData(param2003, dataSet);

        //2004
        String[] param2004 = {"http://www.mca.gov.cn/article/sj/tjbz/a/201713/201708220930.html", "td.xl674493", "td.xl634493"};
        getData(param2004, dataSet);

        //2005
        String[] param2005 = {"http://www.mca.gov.cn/article/sj/tjbz/a/201713/201708220935.html", "td.xl675800", "td.xl635800"};
        getData(param2005, dataSet);

        //2006
        String[] param2006 = {"http://www.mca.gov.cn/article/sj/tjbz/a/201713/201708220936.html", "td.xl674807", "td.xl634807"};
        getData(param2006, dataSet);

        //2007
        String[] param2007 = {"http://www.mca.gov.cn/article/sj/tjbz/a/201713/201708220939.html", "td.xl679143", "td.xl639143"};
        getData(param2007, dataSet);

        //2008
        String[] param2008 = {"http://www.mca.gov.cn/article/sj/tjbz/a/201713/201708220941.html", "td.xl6715383", "td.xl6315383"};
        getData(param2008, dataSet);

        //2009
        String[] param2009 = {"http://www.mca.gov.cn/article/sj/tjbz/a/201713/201708220943.html", "td.xl6720100", "td.xl6320100"};
        getData(param2009, dataSet);

        //2010
        String[] param2010 = {"http://www.mca.gov.cn/article/sj/tjbz/a/201713/201708220946.html", "td.xl6823930", "td.xl6323930"};
        getData(param2010, dataSet);

        //2011
        String[] param2011 = {"http://www.mca.gov.cn/article/sj/tjbz/a/201713/201707271552.html", "td.xl7025893", "td.xl7025893"};
        getData(param2011, dataSet);

        //2012
        String[] param2012 = {"http://www.mca.gov.cn/article/sj/tjbz/a/201713/201707271556.html", "td.xl6914864", "td.xl6914864"};
        getData(param2012, dataSet);

        //2013
        String[] param2013 = {"http://files2.mca.gov.cn/cws/201404/20140404125552372.htm", "td.xl704975", "td.xl704975"};
        getData(param2013, dataSet);

        //2014
        String[] param2014 = {"http://files2.mca.gov.cn/cws/201502/20150225163817214.html", "td.xl7032132", "td.xl7032132"};
        getData(param2014, dataSet);

        //2015
        String[] param2015 = {"http://www.mca.gov.cn/article/sj/tjbz/a/2015/201706011127.html", "td.xl696496", "td.xl696496"};
        getData(param2015, dataSet);

        //2016
        String[] param2016 = {"http://www.mca.gov.cn/article/sj/xzqh/1980/201705/201705311652.html", "td.xl7017877", "td.xl7017877"};
        getData(param2016, dataSet);

        //2017
        String[] param2017 = {"http://www.mca.gov.cn/article/sj/xzqh/1980/201803/201803131454.html", "td.xl7412654", "td.xl7312654"};
        getData(param2017, dataSet);

        //2018
        String[] param2018 = {"http://www.mca.gov.cn/article/sj/xzqh/1980/201903/201903011447.html", "td.xl723852", "td.xl733852"};
        getData(param2018, dataSet);

        //2019
        String[] param2019 = {"http://www.mca.gov.cn/article/sj/xzqh/1980/2019/202002281436.html", "td.xl7019292", "td.xl7119292"};
        getData(param2019, dataSet);

        //2020
        String[] param2020 = {"http://www.mca.gov.cn/article/sj/xzqh/2020/2020/202003061536.html", "td.xl7020844", "td.xl7120844"};
        getData(param2020, dataSet);

        for (Map<String, Object> dataMap : dataSet) {
            areaDao.insert(dataMap);
        }

        return dataSet;
    }

    private void getData(String[] params, Set<Map<String, Object>> dataSet) {
        try {
            Document doc = Jsoup.connect(params[0]).timeout(1000).get();
            Elements elements = doc.select("table>tbody>tr");
            for (Element element : elements) {
                String code, name;
                Elements tdElements1 = element.select(params[1]);
                Elements tdElements2 = element.select(params[2]);
                Elements tdElements;
                if (params.length == 3) {
                    if (tdElements1 != null && tdElements1.size() > 1) {
                        tdElements = tdElements1;
                    } else if (tdElements2 != null && tdElements2.size() > 1) {
                        tdElements = tdElements2;
                    } else {
                        continue;
                    }
                    code = tdElements.get(0).text();
                    name = tdElements.get(1).text();
                } else {
                    Elements tdElements3 = element.select(params[3]);
                    Elements tdElements4 = element.select(params[4]);
                    if (tdElements1 != null && tdElements1.size() > 0 && tdElements2 != null && tdElements2.size() > 0) {
                        code = tdElements1.get(0).text();
                        name = tdElements2.get(0).text();
                    } else if (tdElements3 != null && tdElements3.size() > 0 && tdElements4 != null && tdElements4.size() > 0) {
                        code = tdElements3.get(0).text();
                        name = tdElements4.get(0).text();
                    } else {
                        continue;
                    }
                }

                Map<String, Object> dataMap = new HashMap<>();
                if (StringUtils.hasText(code) && code.length() != 6) {
                    continue;
                }
                System.out.println(code + "---" + name);
                dataMap.put("code", code);
                dataMap.put("name", name);
                dataSet.add(dataMap);
            }
        } catch (Exception e) {
            System.err.println("超时->" + params[0]);
            e.printStackTrace();
            getData(params, dataSet);
        }
    }

}
