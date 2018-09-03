package envir.web.user.action;

import envir.web.user.data.VerifyData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pub.spring.web.mvc.ActionResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by zzl on 2015/11/15.
 */
@Controller
public class RequestVeriCodeAction {

    @RequestMapping
    public ActionResult execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String lastVeriCode = (String) session.getAttribute("veriCode");
        int nVeriCode = (int)(Math.random() * 999999) + 1;
        String veriCode = String.format("%06d", nVeriCode);

        VerifyData verifyData = new VerifyData();
        verifyData.setMobileNum(request.getParameter("mobile"));
        verifyData.setVeriCode(veriCode);
        verifyData.setSendTime(new Date());

        session.setAttribute("verifyData", verifyData);
        ActionResult result = ActionResult.ok();
        result.setData(veriCode);
        return result;
    }

}
