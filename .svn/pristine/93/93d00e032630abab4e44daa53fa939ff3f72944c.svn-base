package pub.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by zhb on 2017/9/1.
 */
public class RandomStrUtils {

    private final static String STRING_LETTER = "abcdefghijklmnopqrstuvwxyz";
    private final static String STRING_NUM = "0123456789";

    //生成订单号
    public static String buildOrderNo() {
        Date date = new Date();
        return getDateTimeFormatStr("yyyyMMdd", date) +
                getSpecifiedLengthRandomNum(3) +
                getDateTimeFormatStr("HHmmss", date) +
                getSpecifiedLengthRandomNum(2);
    }

    //
    public static String buildOrderFix() {
        Date date = new Date();
        return getDateTimeFormatStr("yyyyMMdd", date) +
                getSpecifiedLengthRandomNum(2) +
                getDateTimeFormatStr("HHmmss", date) +
                getSpecifiedLengthRandomNum(1);
    }

    //获取指定位数的随机数（数字）
    public static String getSpecifiedLengthRandomNum(Integer length) {
        return getRandomString(length, STRING_NUM);
    }

    //获取指定位数的随机数（字母）
    public static String getSpecifiedLengthRandomLetter(Integer length, String upper_lower_case) {
        String base = STRING_LETTER;
        if (upper_lower_case.equals("upper")) {
            base = base.toUpperCase();// 转为大写字母
        }
        return getRandomString(length, base);
    }

    //获取指定位数的随机数（字母[含大小写]+数字）
    public static String getSpecifiedLengthRandomStr(Integer length) {
        String base = STRING_LETTER + STRING_LETTER.toLowerCase() + STRING_NUM;
        return getRandomString(length, base);
    }

    //获取yyyyMMdd或HHmmss格式字符串
    public static String getDateTimeFormatStr(String fm, Date dateTime) {
        SimpleDateFormat df = new SimpleDateFormat(fm);
        return df.format(dateTime);
    }

    public static String getRandomString(int length, String base) { //length表示生成字符串的长度
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        RandomStrUtils utils = new RandomStrUtils();
        System.out.println("随机3位数数字：" + getSpecifiedLengthRandomNum(3));
        System.out.println("随机2位数数字：" + getSpecifiedLengthRandomNum(2));
        System.out.println("随机3位降序字母串：" + getSpecifiedLengthRandomLetter(3, ""));
        System.out.println("随机3位升序字母串：" + getSpecifiedLengthRandomLetter(3, "upper"));
        System.out.println("随机8位字符串：" + getSpecifiedLengthRandomStr(8));
        System.out.println("订单号：" + buildOrderNo());
    }
}
