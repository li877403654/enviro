package envir.web.user.utils;

import envir.web.user.data.VerifyData;
import pub.types.ValidationError;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by zzl.
 * Date: 2017-08-26
 */
public class VeriCodeUtils {

    public static void validate(HttpServletRequest request) {
        String veriCode = request.getParameter("veriCode");
        VerifyData verifyData = (VerifyData) request.getSession().getAttribute("verifyData");
        if(verifyData == null ||
                // 6 min
                (new Date().getTime() - verifyData.getSendTime().getTime() > 6* 60*1000)) {
            throw new ValidationError("验证码已过期");
        }
        if(!verifyData.getVeriCode().equals(veriCode)) {
            throw new ValidationError("验证码无效");
        }
    }

}
