package shop.miraclecoding.blog.util;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.sql.Timestamp;
import java.util.Date;

public class MyDateUtil {

    public static String timestampFormat(Timestamp time){
        Date currentDate = new Date(time.getTime());
        return DateFormatUtils.format(currentDate, "yyyy-MM-dd HH:mm");
    }
}
