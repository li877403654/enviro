package pub.system.validation;


import java.lang.reflect.Field;

/**
 * describe: Created by IntelliJ IDEA.
 * @author zzl
 * @version 12-4-15
 */
public class DefaultMessages {

    public static final String required = "不可为空";
    public static final String min = "最小值为{rule.param}";
    public static final String max = "最大值为{rule.param}";
    public static final String telno = "无效电话号码";
    public static final String mobile = "无效手机号码";
    public static final String minLength = "不可少于{rule.param}个字符";
    public static final String maxLength = "不可超过{rule.param}个字符";
    public static final String custom = "无效";
    public static final String number = "请输入数值";
    public static final String integer = "请输入整数";
    public static final String email = "不是有效的email";
    public static final String url = "不是有效的URL.";
    public static final String digits = "只允许输入数字";

    public static String get(String s) {
        try {
            Field field = DefaultMessages.class.getDeclaredField(s);
            return (String) field.get(null);
        }
        catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }



}
