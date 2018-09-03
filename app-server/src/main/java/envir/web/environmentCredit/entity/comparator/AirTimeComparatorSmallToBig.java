package envir.web.environmentCredit.entity.comparator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class AirTimeComparatorSmallToBig implements Comparator<String> {

    @Override
    public int compare(String a1, String a2) {
        // 比较map中key代表的时间的大小 方便排序
        try {
            //时间格式化工具类
            DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
            //格式化时间
           Date date1= format.parse(a1);
            Date date2= format.parse(a2);
            //自定义比较器比较菜单的code的大小
            // o1 - o2 -> 由小到大排序     o2-o1 -> 由大到小 排序
           return date1.getTime()-date2.getTime()>0?1:-1;
        } catch (Exception e) {
            throw new RuntimeException("比较大小时 出错了！", e);
        }
    }

}
