package envir.web.environmentCredit;

import envir.sys.service.environmentCredit.GisMapService;
import envir.sys.service.environmentCredit.MainService;
import envir.web.environmentCredit.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pub.spring.web.mvc.ActionResult;
import pub.spring.web.mvc.model.Model;
import pub.types.RefData;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    private MainService mainService;
    @Autowired
    private GisMapService gisMapService;

    @RequestMapping()
    public void index(Model model) {
        RefData refData = new RefData();
    }
    /**
     * 首页，空气质量优良差占比，
     *
     * @param model
     * @param request
     * @param time  时间
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
        refData.put("dataList",mainService.air_qualitySelect(pageNum));//列表数据
        refData.put("totalCount",mainService.pagecountAir());//总记录数
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
        RefData refData = new RefData();
        ActionResult result = new ActionResult();
        refData.put(mainService.pollutants_money_select());
        result.setData(refData);
        return result;
    }

    /*
     *   首页-> 空气热力图(周) 查询结果
     * */
    @RequestMapping()
    public ActionResult airhot(Model model, HttpServletRequest request){
        RefData refData = new RefData();
        ActionResult result = new ActionResult();
        refData.put(mainService.airHotQuery());
        result.setData(refData);
        return result;
    }

    /*
     *   首页-> 根据测点以及时间 搜索 平均数
     * */
    @RequestMapping()
    public ActionResult airfromtype(Model model, HttpServletRequest request){
        RefData refData = new RefData();
        ActionResult result = new ActionResult();
        refData.put(mainService.air());
        result.setData(refData);
        return result;
    }
    /**
     *  根据 测点获得 改测点的各种数值
     * */
    @RequestMapping()
    public ActionResult airbypoint(Model model, HttpServletRequest request,String point){
        RefData refData = new RefData();
        ActionResult result = new ActionResult();
        refData.put(mainService.getAirAVGByPoint(point));
        result.setData(refData);
        return result;
    }
    /*
     *   首页-> 佛山各地区近7天 AQI总数 对比
     * */
    @RequestMapping()
    public ActionResult airfindaql(Model model, HttpServletRequest request){

        RefData refData = new RefData();
        ActionResult result = new ActionResult();
        refData.put(mainService.airFindAQIList());
        result.setData(refData);
        return result;
    }
    /*
     *    首页-> 空气 佛山各地区近7天 PM2.5 每日平均数
     * */
    @RequestMapping()
    public ActionResult getAirPM(Model model, HttpServletRequest request){

        RefData refData = new RefData();
        ActionResult result = new ActionResult();
        refData.put(mainService.getAirFindPM());
        result.setData(refData);
        return result;
    }

    /**
     *   首页-> 空气 根据类型 获取 当前最近一个小时的数据
     *
     */
    @RequestMapping()
    public ActionResult getAirByType(Model model, HttpServletRequest request,String type){

        RefData refData = new RefData();
        ActionResult result = new ActionResult();
        refData.put(mainService.getAirByType(type));
        result.setData(refData);
        return result;
    }
    /*
     *   天气 搜索当前最大温度的时间
     *  风向 风力  温度 湿度
     * */
    @RequestMapping()
    public ActionResult weatherq(Model model, HttpServletRequest request){

        RefData refData = new RefData();
        ActionResult result = new ActionResult();
        refData.put(mainService.weatherq());
        result.setData(refData);
        return result;
    }
    /*
     *   天气 时间
     *
     * */
    @RequestMapping()
    public ActionResult weathertime(Model model, HttpServletRequest request){

        RefData refData = new RefData();
        ActionResult result = new ActionResult();
        refData.put(mainService.weatherTime());
        result.setData(refData);
        return result;
    }
    /*
        首页---》水  获取各区供水量
    * */
    @RequestMapping()
    public ActionResult mqwatersupply(Model model, HttpServletRequest request){

        RefData refData = new RefData();
        ActionResult result = new ActionResult();
        refData.put(mainService.getMqwatersupply());
        result.setData(refData);
        return result;
    }
    /*
    首页----》水  获取河流水质质量统计
* */
    @RequestMapping()
    public ActionResult watertypecount(Model model, HttpServletRequest request){

        RefData refData = new RefData();
        ActionResult result = new ActionResult();
        refData.put(mainService.getWaterType());
        result.setData(refData);
        return result;
    }



    /*
    首页----》水  获取河流水质质量统计
* */
    @RequestMapping()
    public ActionResult rivernamesum(Model model, HttpServletRequest request){

        RefData refData = new RefData();
        ActionResult result = new ActionResult();
        refData.put(mainService.getRiverName());
        result.setData(refData);
        return result;
    }
    /*
    首页----》水  获取gis 地图的坐标位置   污水处理厂 ， 供水处理厂 ， 饮用水源地统计分析
    * */
    @RequestMapping()
    public ActionResult waterpoint(Model model, HttpServletRequest request){

        RefData refData = new RefData();
        ActionResult result = new ActionResult();
        refData.put(mainService.getWaterPoint());
        result.setData(refData);
        return result;
    }
    /*
首页----》水  获取gis 地图的坐标位置   污水处理厂 ， 供水处理厂 ， 饮用水源地统计分析 总数
* */
    @RequestMapping()
    public ActionResult waterpointcount(Model model, HttpServletRequest request){

        RefData refData = new RefData();
        ActionResult result = new ActionResult();
        refData.put(mainService.getWaterPointCount());
        result.setData(refData);
        return result;
    }
    /*  public List getWithdrawalsByName(String name){
     *   获取  饮用水源地统计分析 供水量 根据水源名称
     *   SELECT * FROM dwq WHERE check_time LIKE "2018年%" AND water_source = '东江南支流'  GROUP BY check_time ORDER BY check_time DESC
     * */
    @RequestMapping()
    public ActionResult withdrawalsbyname(Model model, HttpServletRequest request,String name){
        RefData refData = new RefData();
        ActionResult result = new ActionResult();
        refData.put(mainService.getWithdrawalsByName(name));
        result.setData(refData);
        return result;
    }
/***************************信息共享**********************************/

    /**
     * 信息共享：空气环境检测,总记录数
     * @param model
     * @param request
     * @return
     */
    @RequestMapping()
    public ActionResult pagecountair(Model model, HttpServletRequest request) {
        RefData refData = new RefData();
        ActionResult result = new ActionResult();
        refData.put(mainService.pagecountAir());
        result.setData(refData);
        return result;
    }
    /**
     * 信息共享，污染源监管,所有字段 分页显示
     * @param model
     * @param request
     * @return
     */
    @RequestMapping
    public ActionResult pollution(Model model, HttpServletRequest request,String pageNum) {
        RefData refData = new RefData();
        ActionResult result = new ActionResult();
        refData.put(gisMapService.pollution(pageNum));
        result.setData(refData);
        return result;
    }
    //获得所有的企业信息 不分页
    @RequestMapping
    public ActionResult pollutionall(Model model, HttpServletRequest request) {
        RefData refData = new RefData();
        ActionResult result = new ActionResult();
        refData.put(gisMapService.pollutionAll());
        result.setData(refData);
        return result;
    }
    /**
     * 信息共享，污染源监管,所有字段  根据名称模糊查询
     * @param model
     * @param request
     * @return
     */
    @RequestMapping()
    public ActionResult pollutionbyname(Model model, HttpServletRequest request,String pollutionName,String pageNum) {
        RefData refData = new RefData();
        ActionResult result = new ActionResult();
        refData.put(gisMapService.getPollutionByName(pollutionName,pageNum));
        result.setData(refData);
        return result;
    }
    /**
     * 信息共享，污染源监管,所有字段  根据条件 查询
     * @param model
     * @param request
     * type:企业类型，废水1，废气2，污水3，重金属4，危险废物5
     * @return
     */
    @RequestMapping()
    public ActionResult pollutionlike(Model model, HttpServletRequest request,String pollutionName,String type,String area,String pageNum) {
        RefData refData = new RefData();
        ActionResult result = new ActionResult();
        refData.put(gisMapService.getPollutionLike(pollutionName,type,area,pageNum));
        result.setData(refData);
        return result;
    }
    /**
     * 信息共享，污染源监管, 根据ID 获取 企业信息
     * @param model
     * @param request
     * type:
     * @return
     */
    @RequestMapping()
    public ActionResult polluntionbyid(Model model, HttpServletRequest request,String id) {
        RefData refData = new RefData();
        ActionResult result = new ActionResult();
        refData.put(gisMapService.getPollutionById(id));
        result.setData(refData);
        return result;
    }

    /**
     * 应急机制，污染源监管, 根据ID 获取 企业信息 以及 风险数值
     * @param model
     * @param request
     * type:
     * @return
     */
    @RequestMapping()
    public ActionResult polluvaluebyid(Model model, HttpServletRequest request,String id) {
        RefData refData = new RefData();
        ActionResult result = new ActionResult();
        refData.put(gisMapService.getPolluSourCtroValueById(id));
        result.setData(refData);
        return result;
    }
}
