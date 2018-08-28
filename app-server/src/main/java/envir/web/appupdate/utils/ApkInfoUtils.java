package envir.web.appupdate.utils;

import pub.web.ServletUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Created by zzl on 2015/12/30.
 */
public class ApkInfoUtils {

    public static String getLatestVersion() {
        File apkFile = getLatestApkFile();
        return getVersion(apkFile);
    }

    public static String getVersion(File apkFile) {

        if (apkFile == null) return null;
        String version = null;
        try {
            ZipFile zipFile = new ZipFile(apkFile, ZipFile.OPEN_READ);
            ZipEntry entry = zipFile.getEntry("assets/www/js/app.js");
            InputStream zis = zipFile.getInputStream(entry);

            InputStreamReader isr = new InputStreamReader(zis);
            BufferedReader br = new BufferedReader(isr);
            String line;
            final String prolog = "version:";
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.startsWith(prolog)) {
                    int pos1 = line.indexOf('\'');
                    int pos2 = line.indexOf('\'', pos1 + 1);
                    version = line.substring(pos1 + 1, pos2);
                    System.out.println(version);
                }
            }
            br.close();
            zipFile.close();
            System.out.println("?");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return version;
    }

    public static File getLatestApkFile() {
        HttpServletRequest request = ServletUtils.getRequest();
        String appServerRoot = request.getServletContext().getRealPath("/");
        File appServerRootFile = new File(appServerRoot);
        File appRoot = new File(appServerRootFile.getParentFile(), "envir-app");

        File apkDir = appRoot;
        String[] fileNameArray = apkDir.list();
        if(fileNameArray == null) {
            return null;//?
        }
        List<String> fileNames = new ArrayList<>();
        for (String fileName : fileNameArray) {
            if (fileName.endsWith(".apk")) {
                fileNames.add(fileName);
            }
        }
        Collections.sort(fileNames, new Comparator<String>() {
            @Override
            public int compare(String n1, String n2) {
                return n1.compareTo(n2);
            }
        });
        if (fileNames.isEmpty()) {
            System.out.println("???");
            return null;
        }
        String apkFileName = fileNames.get(fileNames.size() - 1);
        File apkFile = new File(apkDir, apkFileName);
        return apkFile;
    }

}
