package envir.web.environmentCredit;

import envir.sys.service.environmentCredit.MainService;
import envir.web.environmentCredit.entity.AirQuality;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pub.spring.web.mvc.ActionResult;
import pub.spring.web.mvc.model.Model;
import pub.types.RefData;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    private MainService mainService;

    @RequestMapping()
    public void index(Model model) {
        RefData refData = new RefData();
    }

    /**
     * 首页，空气质量优良差占比，
     *
     * @param model
     * @param request
     * @param
     * @return
     */
    @RequestMapping()
    public ActionResult airqual(Model model, HttpServletRequest request,String time) {
        List<AirQuality> list = mainService.init(time);
        if (list != null && list.size() > 0) {
            return ActionResult.ok("air", list.get(0));
        } else {
            return ActionResult.okWithData("noresult");
        }
    }
    /**
     * 首页，空气首要污染物占比
     *
     * @param model
     * @param request
     * @param date
     * @return
     */
    @RequestMapping()
    public ActionResult air(Model model, HttpServletRequest request, String date) {
        List<AirQuality> list = mainService.findAirContaminant(date);
        return ActionResult.ok("pm", list.get(0));
    }

    /**
     * 首页，污染源监管程度
     */
    @RequestMapping()
    public ActionResult pullosourctrl(Model model, HttpServletRequest request) {
        RefData refData = new RefData();
        ActionResult result = new ActionResult();
        refData.put(mainService.polluSourCtrl());
        result.setData(refData);
        return result;
    }
    /**
     * 信心共享，污染源监管,所有字段
     * @param model
     * @param request
     * @return
     */
    @RequestMapping
    public ActionResult pollution(Model model, HttpServletRequest request,String pageNum) {
        RefData refData = new RefData();
        ActionResult result = new ActionResult();
        refData.put("dataLsit",mainService.pollution(pageNum));
        refData.put("totalCount",mainService.pagecountPollu());
        result.setData(refData);
        return result;
    }

    /**
     * 信心共享，污染源监管,所有字段,总记录数
     * @param model
     * @param request
     * @return
     */
    @RequestMapping
    public ActionResult pagecountpollu(Model model, HttpServletRequest request) {
        RefData refData = new RefData();
        ActionResult result = new ActionResult();
        refData.put(mainService.pagecountPollu());
        result.setData(refData);
        return result;
    }


    /**
     * 首页，国控企业类型，5个分类
     * @param model
     * @param request
     * @return
     */
    @RequestMapping
    public ActionResult countrytype(Model model, HttpServletRequest request) {
        RefData refData = new RefData();
        ActionResult result = new ActionResult();
        refData.put(mainService.countryctrltype());
        result.setData(refData);
        return result;
    }
    /**
     * 首页，省控企业类型，5个分类
     * @param model
     * @param request
     * @return
     */
    @RequestMapping
    public ActionResult protype(Model model, HttpServletRequest request) {
        RefData refData = new RefData();
        ActionResult result = new ActionResult();
        refData.put(mainService.proctrltype());
        result.setData(refData);
        return result;
    }

    /**
     * 首页，市控企业类型，5个分类
     * @param model
     * @param request
     * @return
     */
    @RequestMapping
    public ActionResult citytype(Model model, HttpServletRequest request) {
        RefData refData = new RefData();
        ActionResult result = new ActionResult();
        refData.put(mainService.citytrltype());
        result.setData(refData);
        return result;
    }
    /*
    * 信息共享：空气环境检测 ->需显示字段(测点 时间 等级 分数)
     * */
    @RequestMapping()
    public ActionResult airenviroctrl(Model model, HttpServletRequest request,String pageNum){
        RefData refData = new RefData();
        ActionResult result = new ActionResult();
        refData.put("dataList",mainService.air_qualitySelect(pageNum));
        refData.put("totalCount",mainService.pagecountAir());
        result.setData(refData);
        return result;
    }

    /**
     * 信息共享：空气环境检测,总记录数
     * @param model
     * @param request
     * @return
     */
    @RequestMapping
    public ActionResult pagecountair(Model model, HttpServletRequest request) {
        RefData refData = new RefData();
        ActionResult result = new ActionResult();
        refData.put(mainService.pagecountAir());
        result.setData(refData);
        return result;
    }

    /**
     * 污水
     * @param model
     * @param request
     * @return
     */
    @RequestMapping()
    public ActionResult wastewater(Model model, HttpServletRequest request){
        RefData refData = new RefData();
        ActionResult result = new ActionResult();
        refData.put(mainService.wastewater());
        result.setData(refData);
        return result;
    }
    /*
     * 首页：环保税征情况  -> 需显示字段 （企业数 总税额 时间）
     * */
    @RequestMapping()
    public ActionResult pollutantsq(Model model, HttpServletRequest request){
        System.out.println("web----------->");
        RefData refData = new RefData();
        ActionResult result = new ActionResult();
        refData.put(mainService.pollutants_money_select());
        result.setData(refData);
        return result;
    }

}
