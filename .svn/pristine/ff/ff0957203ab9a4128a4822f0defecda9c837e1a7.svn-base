package envir.web.appupdate.action;

import envir.web.appupdate.utils.ApkInfoUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * Created by zzl on 2015/12/30.
 */
@Controller
public class IndexAction {

    @RequestMapping
    public void execute(HttpServletRequest request, Model model) {
        File apkFile = ApkInfoUtils.getLatestApkFile();
        if(apkFile == null) {
            return;
        }
        model.addAttribute("version", ApkInfoUtils.getVersion(apkFile));
        String requestUrl = request.getRequestURL().toString();
        int pos = requestUrl.indexOf('/', 10);
        String server = requestUrl.substring(0, pos);
        String url = server + "/envir-app/" + apkFile.getName();
        model.addAttribute("url", url);
    }

}
