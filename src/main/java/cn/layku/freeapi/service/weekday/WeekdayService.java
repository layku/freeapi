package cn.layku.freeapi.service.weekday;

/**
 * @Package: cn.layku.freeapi.service.weekday
 * @ClassName: WeekdayService
 * @Author: dongdingzhuo
 * @Description: 国家节假日服务类
 * @Date: 2020/03/11 14:46
 */
public interface WeekdayService {

    /**
     * 判断是否是法定假日
     *
     * @param calendar
     * @return
     */
    boolean isLawHoliday(String calendar);

    /**
     * 判断是否是周末
     *
     * @param calendar
     * @return
     */
    boolean isWeekends(String calendar);

    /**
     * 判断是否是需要额外补班的周末
     *
     * @param calendar
     * @return
     */
    boolean isExtraWorkday(String calendar);

    /**
     * 判断是否是休息日（包含法定节假日和不需要补班的周末）
     *
     * @param calendar
     * @return
     */
    boolean isHoliday(String calendar);


}
