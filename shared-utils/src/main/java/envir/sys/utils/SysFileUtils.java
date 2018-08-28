package envir.sys.utils;

import envir.sys.entity.SysFile;
import org.apache.commons.io.FileUtils;
import pub.functions.ReqFuncs;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by zzl.
 * Date: 2017-10-14
 */
public class SysFileUtils {

    public static String replaceUrlToPlaceholder(String html) {
        if(html == null) {
            return null;
        }
        String home = ReqFuncs.getBaseUrl();
        String prolog = "\"" + home + "/file/get.do?id=";

        StringBuilder sbHtml = new StringBuilder();
        int pos = 0;
        while(true) {
            int pos1 = html.indexOf(prolog, pos);
            if(pos1 == -1) {
                sbHtml.append(html.substring(pos));
                break;
            }
            int pos2 = html.indexOf('"', pos1 + 1);
            String fileId = html.substring(pos1 + prolog.length(), pos2);
            sbHtml.append(html.substring(pos, pos1));
            sbHtml.append("\"@(FILE-ID=" + fileId + ")\"");
            pos = pos2 + 1;
        }
        String html2 = sbHtml.toString();
        return html2;
    }

    public static String replacePlaceholderToUrl(String html) {
        if(html == null) {
            return null;
        }
        String home = ReqFuncs.getBaseUrl();
        String fileUrl = home + "/file/get.do?id=";

        String prolog = "@(FILE-ID=";

        StringBuilder sbHtml = new StringBuilder();
        int pos = 0;
        while(true) {
            int pos1 = html.indexOf(prolog, pos);
            if(pos1 == -1) {
                sbHtml.append(html.substring(pos));
                break;
            }
            int pos2 = html.indexOf(')', pos1);
            String fileId = html.substring(pos1 + prolog.length(), pos2);
            sbHtml.append(html.substring(pos, pos1));
            sbHtml.append(fileUrl + fileId);
            pos = pos2 + 1;
        }
        String html2 = sbHtml.toString();
        return html2;
    }

    public static SysFile createFromFile(String filePath) throws IOException {
        if(filePath == null || filePath.length() == 0) {
            return null;
        }
        SysFile sysFile = new SysFile();
        File file = new File(filePath);
        sysFile.setName(file.getName());
        sysFile.setContent(FileUtils.readFileToByteArray(file));
        sysFile.setContentType(Files.probeContentType(Paths.get(filePath)));
        sysFile.setSize((int) file.length());
        return sysFile;
    }

    public static void save(SysFile sysFile, String filePath) throws IOException {
        FileOutputStream fos = new FileOutputStream(filePath);
        fos.write(sysFile.getContent());
        fos.close();
    }

    public static void main(String[] args) throws IOException {
        String xx = Files.probeContentType(Paths.get("T:\\Temp7\\收入快报201803.xlsx"));
        System.out.println(xx);
    }
}
