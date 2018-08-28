package envir.web.app.data;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.zip.CRC32;

/**
 * Created by zzl on 2016/1/12.
 */
public class TokenRepo {

    private static Map<Integer, TokenData> map = new ConcurrentHashMap<>();

    public static TokenData getToken(Integer ownerId) {
        return map.get(ownerId);
    }

    public static TokenData newToken(Integer ownerId) {
        TokenData tokenData = new TokenData();
        tokenData.setOwnerId(ownerId);
        Date now = new Date();
        tokenData.setIssueTime(now);
        tokenData.setToken(generateToken(ownerId, now));
        map.put(ownerId, tokenData);
        return tokenData;
    }

    private static String generateToken(Integer ownerId, Date time) {
        CRC32 crc32 = new CRC32();
        String text = "" + ownerId + "|" + time.getTime();
        crc32.update(text.getBytes());
        String token = String.format("%x", crc32.getValue());
        return token;
    }

    public static void main(String[] args) {
        String token = generateToken(234, new Date());
        System.out.println(token);
    }

}
