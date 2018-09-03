package pub.functions;

import java.io.*;
import java.net.URL;
import java.util.Properties;

/**
 * Created by zzl.
 * Date: 2018/3/9
 */
public class PropsFuncs2 {

    public static String getString(String propsFile, String key) {
        return getString(propsFile, key, null);
    }

    public static String getString(String propsFile, String key, String defValue) {
        String[] propsFiles;
        if(propsFile.endsWith(".properties")) {
            propsFiles = new String[] { propsFile };
        }
        else {
            propsFiles = new String[] {
                    propsFile + ".local.properties",
                    propsFile + ".properties"
            };
        }
        return getString(propsFiles, key, defValue);
    }

    public static String getString(String[] propsFiles, String key, String defValue) {
        for(String propsFile: propsFiles) {
            String value = _getString(propsFile, key);
            if(value != null) {
                return value;
            }
        }
        return defValue;
    }

    private static String _getString(String propsFile, String key) {
        File file = new File(propsFile);
        InputStream is = null;
        try {
            if (file.exists()) {
                is = new FileInputStream(file);
            }
            else {
                URL resource = PropsFuncs2.class.getClassLoader().getResource(propsFile);
                if(resource == null) {
                    return null;
                }
                is = resource.openStream();
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

        Properties properties = new Properties();
        try {
            Reader reader = new InputStreamReader(is, "utf-8");
            properties.load(reader);
            reader.close();

        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            IoFuncs.tryClose(is);
        }
        return properties.getProperty(key);
    }
}
