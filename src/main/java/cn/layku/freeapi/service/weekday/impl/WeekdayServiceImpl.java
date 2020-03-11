package cn.layku.freeapi.service.weekday.impl;

import cn.layku.freeapi.service.weekday.WeekdayService;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Package: cn.layku.freeapi.service.weekday.impl
 * @ClassName: WeekdayServiceImpl
 * @Author: dongdingzhuo
 * @Description: 国家节假日服务实现类
 * @Date: 2020/03/11 14:49
 */
@Service("weekday")
public class WeekdayServiceImpl implements WeekdayService {

    private final Map<String, List<String>> lawHolidayMap = new HashMap<>();
    private final Map<String, List<String>> extraWorkdayMap = new HashMap<>();

    public WeekdayServiceImpl() {
        //*******************************2011*******************************
        //2011年法律规定的放假日期
        lawHolidayMap.put("2011", Arrays.asList(
                "01-01", "01-02", "01-03",
                "02-02", "02-03", "02-04", "02-05", "02-06", "02-07", "02-08",
                "04-03", "04-04", "04-05", "04-30",
                "05-01", "05-02",
                "06-04", "06-05", "06-06",
                "09-10", "09-11", "09-12",
                "10-01", "10-02", "10-03", "10-04", "10-05", "10-06", "10-07"
        ));
        //2011年周末调休日期
        extraWorkdayMap.put("2011", Arrays.asList(
                "01-30", "02-12", "04-02", "10-08", "10-09", "12-31"
        ));

        //*******************************2012*******************************
        //2012年法律规定的放假日期
        lawHolidayMap.put("2012", Arrays.asList(
                "01-01", "01-02", "01-03", "01-22", "01-23", "01-24", "01-25", "01-26", "01-27", "01-28",
                "04-02", "04-03", "04-04", "04-29", "04-30",
                "05-01",
                "06-22", "06-23", "06-24",
                "09-30",
                "10-01", "10-02", "10-03", "10-04", "10-05", "10-06", "10-07"
        ));
        //2012年周末调休日期
        extraWorkdayMap.put("2012", Arrays.asList(
                "01-21", "01-29", "03-31", "04-01", "04-28", "05-02", "09-29", "10-08"
        ));

        //*******************************2013*******************************
        //2013年法律规定的放假日期
        lawHolidayMap.put("2013", Arrays.asList(
                "01-01", "01-02", "01-03",
                "02-09", "02-10", "02-11", "02-12", "02-13", "02-14", "02-15",
                "04-04", "04-05", "04-06", "04-29", "04-30",
                "05-01",
                "06-10", "06-11", "06-12",
                "09-19", "09-20", "09-21",
                "10-01", "10-02", "10-03", "10-04", "10-05", "10-06", "10-07"
        ));
        //2013年周末调休日期
        extraWorkdayMap.put("2013", Arrays.asList(
                "01-05", "01-06", "02-16", "02-17", "04-07", "04-27", "04-28", "06-08", "06-09", "09-22", "09-29"
        ));

        //*******************************2014*******************************
        //2014年法律规定的放假日期
        lawHolidayMap.put("2014", Arrays.asList(
                "01-01", "01-31",
                "02-01", "02-02", "02-03", "02-04", "02-05", "02-06",
                "04-05", "04-06", "04-07",
                "05-01", "05-02", "05-03", "05-31",
                "06-01", "06-02",
                "09-06", "09-07", "09-08",
                "10-01", "10-02", "10-03", "10-04", "10-05", "10-06", "10-07"
        ));
        //2014年周末调休日期
        extraWorkdayMap.put("2014", Arrays.asList(
                "01-26", "02-08", "05-04", "09-28", "10-11"
        ));

        //*******************************2015*******************************
        //2015年法律规定的放假日期
        lawHolidayMap.put("2015", Arrays.asList(
                "01-01", "01-02", "01-03",
                "02-18", "02-19", "02-20", "02-21", "02-22", "02-23", "02-24",
                "04-04", "04-05", "04-06",
                "05-01", "05-02", "05-03",
                "06-20", "06-21", "06-22",
                "09-03", "09-04", "09-05", "09-26", "09-27",
                "10-01", "10-02", "10-03", "10-04", "10-05", "10-06", "10-07"
        ));
        //2015年周末调休日期
        extraWorkdayMap.put("2015", Arrays.asList(
                "01-04", "02-15", "02-28", "09-06", "10-10"
        ));

        //*******************************2016*******************************
        //2016年法律规定的放假日期
        lawHolidayMap.put("2016", Arrays.asList(
                "01-01", "01-02", "01-03",
                "02-07", "02-08", "02-09", "02-10", "02-11", "02-12", "02-13",
                "04-02", "04-03", "04-04", "04-30",
                "05-01", "05-02",
                "06-09", "06-10", "06-11",
                "09-15", "09-16", "09-17",
                "10-01", "10-02", "10-03", "10-04", "10-05", "10-06", "10-07",
                "12-31"
        ));
        //2016年周末调休日期
        extraWorkdayMap.put("2016", Arrays.asList(
                "02-06", "02-14", "06-12", "09-18", "10-08", "10-09"
        ));

        //*******************************2017*******************************
        //2017年法律规定的放假日期
        lawHolidayMap.put("2017", Arrays.asList(
                "01-01", "01-02", "01-27", "01-28", "01-29", "01-30", "01-31",
                "02-01", "02-02",
                "04-02", "04-03", "04-04", "04-29", "04-30",
                "05-01", "05-28", "05-29", "05-30",
                "10-01", "10-02", "10-03", "10-04", "10-05", "10-06", "10-07", "10-08",
                "12-30", "12-31"
        ));
        //2017年周末调休日期
        extraWorkdayMap.put("2017", Arrays.asList(
                "01-22", "02-04", "04-01", "05-27", "09-30"
        ));

        //*******************************2018*******************************
        //2018年法律规定的放假日期
        lawHolidayMap.put("2018", Arrays.asList(
                "01-01",
                "02-15", "02-16", "02-17", "02-18", "02-19", "02-20", "02-21",
                "04-05", "04-06", "04-07", "04-29", "04-30",
                "05-01",
                "06-16", "06-17", "06-18",
                "09-22", "09-23", "09-24",
                "10-01", "10-02", "10-03", "10-04", "10-05", "10-06", "10-07",
                "12-30", "12-31"
        ));
        //2018年周末调休日期
        extraWorkdayMap.put("2018", Arrays.asList(
                "02-11", "02-24", "04-08", "04-28", "09-29", "09-30", "12-29"
        ));

        //*******************************2019*******************************
        //2019年法律规定的放假日期
        lawHolidayMap.put("2019", Arrays.asList(
                "01-01",
                "02-04", "02-05", "02-06", "02-07", "02-08", "02-09", "02-10",
                "04-05", "04-06", "04-07",
                "05-01", "05-02", "05-03", "05-04",
                "06-07", "06-08", "06-09",
                "09-13", "09-14", "09-15",
                "10-01", "10-02", "10-03", "10-04", "10-05", "10-06", "10-07"
        ));
        //2019年周末调休日期
        extraWorkdayMap.put("2019", Arrays.asList(
                "02-02", "02-03", "04-28", "05-05", "09-29", "10-12"
        ));

        //*******************************2020*******************************
        //2020年法律规定的放假日期
        lawHolidayMap.put("2020", Arrays.asList(
                "01-01", "01-24", "01-25", "01-26", "01-27", "01-28", "01-29", "01-30", "01-31",
                "02-01", "02-02",
                "04-04", "04-05", "04-06",
                "05-01", "05-02", "05-03", "05-04", "05-05",
                "06-25", "06-26", "06-27",
                "10-01", "10-02", "10-03", "10-04", "10-05", "10-06", "10-07", "10-08"
        ));
        //2020年周末调休日期
        extraWorkdayMap.put("2020", Arrays.asList(
                "01-19", "04-26", "05-09", "06-28", "09-27", "10-10"
        ));

    }


    /**
     * 判断是否是法定假日
     *
     * @param calendar
     * @return
     */
    @Override
    public boolean isLawHoliday(String calendar) {
        String[] dates = calendarSplit(calendar);
        String year = dates[0];
        String monthAndDay = dates[1] + "-" + dates[2];
        if (lawHolidayMap.get(year).contains(monthAndDay)) {
            return true;
        }
        return false;
    }

    /**
     * 判断是否是周末
     *
     * @param calendar
     * @return
     */
    @Override
    public boolean isWeekends(String calendar) {
        // 先将字符串类型的日期转换为Calendar类型
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(calendar);
            Calendar ca = Calendar.getInstance();
            ca.setTime(date);
            if (ca.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || ca.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 判断是否是需要额外补班的周末
     *
     * @param calendar
     * @return
     */
    @Override
    public boolean isExtraWorkday(String calendar) {
        String[] dates = calendarSplit(calendar);
        String year = dates[0];
        String monthAndDay = dates[1] + "-" + dates[2];
        if (extraWorkdayMap.get(year).contains(monthAndDay)) {
            return true;
        }
        return false;
    }

    /**
     * 判断是否是休息日（包含法定节假日和不需要补班的周末）
     *
     * @param calendar
     * @return
     */
    @Override
    public boolean isHoliday(String calendar) {
        // 首先法定节假日必定是休息日
        if (isLawHoliday(calendar)) {
            return true;
        }
        // 排除法定节假日外的非周末必定是工作日
        if (!isWeekends(calendar)) {
            return false;
        }
        // 所有周末中只有非补班的才是休息日
        if (isExtraWorkday(calendar)) {
            return false;
        }
        return false;
    }

    private String[] calendarSplit(String calendar) {
        return calendar.split("-");
    }

}
