package envir.web.appupdate.action;

import envir.web.appupdate.utils.ApkInfoUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pub.spring.web.mvc.ActionResult;

/**
 * Created by zzl on 2015/12/30.
 */
@Controller
public class GetVersionAction {

    @RequestMapping
    public ActionResult execute() {
        ActionResult result = ActionResult.ok();
        result.setData(ApkInfoUtils.getLatestVersion());
        return result;
    }

}
