package pub.functions;

import java.util.ArrayList;
import java.util.List; /**
 * Created by zzl.
 * Date: 2017-07-25
 */
public class SqlFuncs {

    public static List<String> escape(List<String> strs) {
        List<String> result = new ArrayList<>();
        for(String str: strs) {
            result.add(escape(str));
        }
        return result;
    }

    public static String escape(String str) {
        return str.replace("'", "\\'");
    }

    public static String buildInStr(List<String> values) {
        StringBuilder sb = new StringBuilder("'");
        for(String value: values) {
            sb.append(escape(value)).append("','");
        }
        sb.delete(sb.length() - 2, sb.length());
        return sb.toString();
    }
}
