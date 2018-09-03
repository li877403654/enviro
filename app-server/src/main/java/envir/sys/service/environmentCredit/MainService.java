package envir.sys.service.environmentCredit;

import envir.web.environmentCredit.constant.*;
import envir.web.environmentCredit.entity.*;
import envir.web.environmentCredit.entity.comparator.AirTimeComparatorSmallToBig;
import envir.web.environmentCredit.entity.comparator.AirTimeComparatorSmallToBig02;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import pub.dao.GeneralDao;
import pub.dao.jdbc.ListQuery;
import pub.dao.query.Query;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/*
*   main首页页面 业务层
* */
@Service
public class MainService {

    @Autowired
    private GeneralDao generalDao;

    /**
     *  首页-> 空气质量优良天数比
     * @param date 时间
     * @return 含有 优良数量 以及 轻度污染数量的 集合
     *  date为空的时候 默认搜索当天时间
     */
    public List<AirQuality> init(String date) {
        if(date==null|| date.trim().equals("")){
            //若date为空 默认搜索当天的信息
            Date now=new Date();
            DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
           date= df.format(now);
        }
        String sql = " SELECT * from " +
                " (SELECT COUNT(1) as target FROM air_quality WHERE `level` in('优','良') AND times LIKE '" + date + "%') a," +
                " (SELECT COUNT(1) as typeAir FROM air_quality WHERE `level` = '轻度污染' AND times LIKE '" + date + "%') b ";
        List<AirQuality> list = generalDao.queryList(AirQuality.class, sql);
        return list;
    }

    /**
     *  首页-> 首要污染物天数占比
     * @param date 时间
     * @return 返回含有 O3 NO2 PM2.5 PM10 数值的 集合
     */
    public List<AirQuality> findAirContaminant(String date) {
        String sql = " SELECT * from \n " +
                " (SELECT SUM(target) as monitoringPoint FROM air_quality WHERE `type_air` = '2' AND times LIKE '" + date + "%') b , \n" +
                "\n" +
                " (SELECT SUM(target) as target FROM air_quality WHERE `type_air` = '3' AND times LIKE '" + date + "%') c ,\n" +
                "\n" +
                " (SELECT SUM(target) as level FROM air_quality WHERE `type_air` = '5' AND times LIKE '" + date + "%') e ,\n" +
                "\n" +
                " (SELECT SUM(target) as typeAir FROM air_quality WHERE `type_air` = '6' AND times LIKE '" + date + "%') f  ";
        //String sql="select * from air_quality";
        List<AirQuality> AirContaminants = generalDao.queryList(AirQuality.class, sql);
        return AirContaminants;
    }

    /**
     *  首页-->污染源监管 国控 省控 市控
     * @return 含有 国控 省控 市控 总数量的集合
     */
    public List polluSourCtrl() {
        Query query = new ListQuery();
        String select = " max((SELECT COUNT(1) FROM pollu_sour_ctro T WHERE T.supervisiontype='1')) as 'country'," +
                " max((SELECT COUNT(1) FROM pollu_sour_ctro T WHERE T.supervisiontype='2')) as 'pro'," +
                " max((SELECT COUNT(1) FROM pollu_sour_ctro T WHERE T.supervisiontype='3')) as 'city' ";
        query.select(select);
        generalDao.execute(query);
        List li = query.getResult().getRows();
        return li;
    }

    //首页 水质量
    public List wastewater() {
        Query query = new ListQuery();
        String select = " * ";
        String from = " wastewater ";
        query.select(select).from(from);
        generalDao.execute(query);
        List li = query.getResult().getRows();
        return li;
    }

    /**
     * 信息共享：空气环境检测 ->需显示字段(测点 时间 等级 分数)
     */
    public List<AirQuality> air_qualitySelect(String pageNum) {
        Query query = new ListQuery();
        String sql = " SELECT * FROM air_quality ORDER BY times DESC  LIMIT " + pageNum + "," + utilConstant.perPageNum;
        List<AirQuality> airQualities = generalDao.queryList(AirQuality.class, sql);
        //处理时间，年月日时分秒转  年月日
        List<AirQuality> list = new ArrayList<AirQuality>();
        for (AirQuality a : airQualities) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
            String time = sdf.format(a.getTimes());
            a.setCont(time + "时");
        }
        return airQualities;
    }

    /**
     * 信息共享：空气环境检测 ->总记录数
     */
    public List pagecountAir() {
        Query query = new ListQuery();
        String select = " COUNT(1) as count ";
        String from = " air_quality ";
        query.select(select).from(from);
        generalDao.execute(query);
        List list = query.getResult().getRows();
        return list;
    }

    /**
     * 广东省30万千瓦以上火电企业二氧化硫排污费开单入库情况公示
     * 企业数 	总税额
     */
    public Map<String, String> pollutants_money_select() {
        Query query = new ListQuery();
        String count = "SELECT COUNT(1) FROM pollutants_money";
        String sum = "SELECT SUM(actual_payment) FROM pollutants_money";
        Integer countInt = generalDao.queryValue(Integer.class, count);
        Integer sumInt = generalDao.queryValue(Integer.class, sum);
        Map<String, String> map = new HashMap<>();
        //处理总金额
        DecimalFormat df = new DecimalFormat("###,###");
        if(sumInt.toString().length()>5){
            String after=sumInt.toString().substring(0,sumInt.toString().length()-4);
            map.put("sum", df.format(Integer.parseInt(after)));
            map.put("unit", "万元");
        }else{
            map.put("sum", df.format(sumInt));
            map.put("unit", "元");
        }

        map.put("count", countInt.toString());

        return map;
    }

    /**
     * 污染源监管，国控企业类型，5个
     *
     * @return
     */
    public List countryctrltype() {
        Query query = new ListQuery();
        String select = " max((SELECT COUNT(1) FROM pollu_sour_ctro T WHERE T.supervisiontype='1' and t.sta_cont_enterp_type='1')) as 'ChemicalFactry'," +
                " max((SELECT COUNT(1) FROM pollu_sour_ctro T WHERE T.supervisiontype='1' and t.sta_cont_enterp_type='2')) as 'MetalFactry'," +
                " max((SELECT COUNT(1) FROM pollu_sour_ctro T WHERE T.supervisiontype='1' and t.sta_cont_enterp_type='3')) as 'pollutiong'," +
                " max((SELECT COUNT(1) FROM pollu_sour_ctro T WHERE T.supervisiontype='1' and t.sta_cont_enterp_type='4')) as 'sewage'," +
                " max((SELECT COUNT(1) FROM pollu_sour_ctro T WHERE T.supervisiontype='1' and t.sta_cont_enterp_type='5')) as 'airMonitor' ";
        String from = " pollu_sour_ctro ";
        query.select(select).from(from);
        generalDao.execute(query);
        List li = query.getResult().getRows();
        return li;
    }

    /**
     * 污染源监管，省控企业类型，5个
     */
    public List proctrltype() {
        Query query = new ListQuery();
        String select = " max((SELECT COUNT(1) FROM pollu_sour_ctro T WHERE T.supervisiontype='2' and t.sta_cont_enterp_type='1')) as 'ChemicalFactry'," +
                " max((SELECT COUNT(1) FROM pollu_sour_ctro T WHERE T.supervisiontype='2' and t.sta_cont_enterp_type='2')) as 'MetalFactry'," +
                " max((SELECT COUNT(1) FROM pollu_sour_ctro T WHERE T.supervisiontype='2' and t.sta_cont_enterp_type='3')) as 'pollutiong'," +
                " max((SELECT COUNT(1) FROM pollu_sour_ctro T WHERE T.supervisiontype='2' and t.sta_cont_enterp_type='4')) as 'sewage'," +
                " max((SELECT COUNT(1) FROM pollu_sour_ctro T WHERE T.supervisiontype='2' and t.sta_cont_enterp_type='5')) as 'airMonitor' ";
        String from = " pollu_sour_ctro ";
        query.select(select).from(from);
        generalDao.execute(query);
        List li = query.getResult().getRows();
        return li;
    }

    /**
     * 污染源监管，市控企业类型，5个
     */
    public List citytrltype() {
        Query query = new ListQuery();
        String select = " max((SELECT COUNT(1) FROM pollu_sour_ctro T WHERE T.supervisiontype='3' and t.sta_cont_enterp_type='1')) as 'ChemicalFactry'," +
                " max((SELECT COUNT(1) FROM pollu_sour_ctro T WHERE T.supervisiontype='3' and t.sta_cont_enterp_type='2')) as 'MetalFactry'," +
                " max((SELECT COUNT(1) FROM pollu_sour_ctro T WHERE T.supervisiontype='3' and t.sta_cont_enterp_type='3')) as 'pollutiong'," +
                " max((SELECT COUNT(1) FROM pollu_sour_ctro T WHERE T.supervisiontype='3' and t.sta_cont_enterp_type='4')) as 'sewage'," +
                " max((SELECT COUNT(1) FROM pollu_sour_ctro T WHERE T.supervisiontype='3' and t.sta_cont_enterp_type='5')) as 'airMonitor' ";
        String from = " pollu_sour_ctro ";
        query.select(select).from(from);
        generalDao.execute(query);
        List li = query.getResult().getRows();
        return li;
    }

/*    public List<Integer> airHotQuery(){
        int day=20;
        List<Integer> set=new ArrayList<>();
        for (int i = day; i >day-7; i--) {
            String sql="SELECT SUM(target) FROM air_quality WHERE type_air=7 AND times LIKE '2018-08-"+i+"%' ";
            Integer hot=generalDao.queryValue(Integer.class,sql);
            System.out.println(hot+"-->"+i);
            set.add(hot==null?0:hot);

         }
        System.out.println(set.toString());
        return set;
    }*/
/*
 *   首页-> 空气热力图AQI(周) 查询结果
 * */
    public Map<String, List<AirQuality>> airHotQuery() {
        Map<String, List<AirQuality>> map = new TreeMap<String, List<AirQuality>>(new AirTimeComparatorSmallToBig());

        //获取当前时间
        Date date = new Date();
        //时间格式化工具类
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        //格式化当前时间
        String time = format.format(date);

        Calendar c = Calendar.getInstance();
        for (int i = 7; i > 0; i--) {
            //1.过去七天
            c.setTime(new Date());
            c.add(Calendar.DATE, -i);
            Date d = c.getTime();
            time = format.format(d);

            // query.select(select).from(from).where(where.toString()).groupBy(group).orderBy(order);
            String sql = "select target,times from air_quality where type_air=6 AND times like '" + time + "%'"
                    + "  GROUP BY times ORDER BY times ";

            List<AirQuality> airs = generalDao.queryList(AirQuality.class, sql);
            //格式化时间
            if (airs != null) {
                airs.forEach(air -> {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
                    String cont = sdf.format(air.getTimes());
                    air.setCont(cont);
                });
            }
            map.put(time, airs);
        }
        return map;
    }
    /*
     *   首页-> 根据测点以及时间 搜索 平均数
     * */

    public List air() {
        //获取当前时间
        Date date = new Date();
        //时间格式化工具类
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        //格式化当前时间
        String time = df.format(date);
        //time = "2018-08-20";
        Query query = new ListQuery();
        String select = " type_air,AVG(target) target";
        String from = " air_quality ";
        String where = " times LIKE '" + time + "%' ";
        String group = " type_air ";
        query.select(select).from(from).where(where).groupBy(group);
        generalDao.execute(query);
        List li = query.getResult().getRows();
        return li;


    }
    /**
     *   首页-> 空气 佛山各地区近7天 PM2.5 每日平均数
     * @return
     */
    public Map getAirFindPM() {

        Map map = new TreeMap(new AirTimeComparatorSmallToBig02());
        Query query = new ListQuery();
        String select = " AVG(target) target";
        String from = " air_quality ";

        //获取当前时间
        Date date = new Date();
        //时间格式化工具类
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat format02 = new SimpleDateFormat("MM/dd");
        //格式化当前时间
        String time = format.format(date);
        String time02=format02.format(date);
        // String where =" type_air=6 AND times like '"+time+"%'";
        Calendar c = Calendar.getInstance();
        for (int i = 7; i > 0; i--) {
            //1.过去七天
            //SELECT avg(target) FROM air_quality WHERE type_air = 5 AND  times LIKE '2018-08-21%'
            c.setTime(new Date());
            c.add(Calendar.DATE, -i);
            Date d = c.getTime();
            time = format.format(d);
            time02 = format02.format(d);
            String where = " type_air=5 AND times like '" + time + "%'";
            query.select(select).from(from).where(where);
            generalDao.execute(query);
            List li = query.getResult().getRows();
            map.put(time02, li);
        }
        //System.out.println(map);
        return map;
    }

    /**
     *  首页-> 根据 污染物类型 返回各测点当天 每小时的值
     * @param type 污染物类型
     * @return  各测点当天 每小时的值
     */
    public List getAirByType(String type){
        if(type==null){
            type="1";
        }
        //select * from air_quality where type_air= '1' and times in (select max(times) from air_quality) order by times desc
        String sql="select * from air_quality where type_air= '"+type+"' and times in (select max(times) from air_quality) order by times desc";
        List<AirQuality> airQualities = generalDao.queryList(AirQuality.class, sql);
        for (AirQuality a : airQualities) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
            String time = sdf.format(a.getTimes());
            a.setCont(time + "时");
        }
        return airQualities;
    }
    /**
     * 首页-> 根据测点 显示数值 各 平均数
     * @param point 测点
     * @return 返回 当天 该测点的 平均值
     */
    public List getAirAVGByPoint(String point){
        if(point == null || point.trim().equals("")){
            point="万顷沙";
        }else{
            String[] points={"万顷沙","九龙镇镇龙","体育西"
                    ,"市五中","市八十六中","市监测站"
                    ,"广东商学院","广雅中学","番禺中学"
                    ,"磨碟沙站","竹洞","花都师范","麓湖"};
            if(point.equals("三水区")){
                point=String.format("'%s'",points[0])+","+String.format("'%s'",points[1]);
            }else if(point.equals("高明区")){
                point=String.format("'%s'",points[2])+","+String.format("'%s'",points[3]);
            }else if(point.equals("禅城区")){
                point=String.format("'%s'",points[4])+","+String.format("'%s'",points[5])+","+String.format("'%s'",points[6]);
            }else if(point.equals("顺德区")){
                point=String.format("'%s'",points[7])+","+String.format("'%s'",points[8])+","+String.format("'%s'",points[9]);
            }else if(point.equals("南海区")){
                point=String.format("'%s'",points[10])+","+String.format("'%s'",points[11])+","+String.format("'%s'",points[12]);
            }
        }


        //获取当前时间
        Date date = new Date();
        //时间格式化工具类
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        //格式化当前时间
        String time = df.format(date);
        //time = "2018-08-20";
        Query query = new ListQuery();
        String select = " type_air,AVG(target) target";
        String from = " air_quality ";
        String where = " times LIKE '" + time + "%' and monitoring_point in ("+point+")  ";
        String group = " type_air ";
        query.select(select).from(from).where(where).groupBy(group);
        generalDao.execute(query);
        List li = query.getResult().getRows();
        return li;
    }
    /*
     *   首页-> 佛山各地区近7天 AQI总数 对比
     * */
    public Map airFindAQIList() {

        Map map = new TreeMap(new AirTimeComparatorSmallToBig02());
        Query query = new ListQuery();
        String select = " SUM(target) point";
        String from = " air_quality ";

        //获取当前时间
        Date date = new Date();
        //时间格式化工具类
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat format02 = new SimpleDateFormat("MM/dd");
        //格式化当前时间
        String time = format.format(date);
        String time02=format02.format(date);
        // String where =" type_air=6 AND times like '"+time+"%'";
        Calendar c = Calendar.getInstance();
        for (int i = 7; i > 0; i--) {
            //1.过去七天
            c.setTime(new Date());
            c.add(Calendar.DATE, -i);
            Date d = c.getTime();
            time = format.format(d);
            time02 = format02.format(d);
            String where = " type_air=6 AND times like '" + time + "%'";
            String group = " monitoring_point ";
            query.select(select).from(from).where(where).groupBy(group);
            generalDao.execute(query);
            List li = query.getResult().getRows();
            map.put(time02, li);
        }
        //System.out.println(map);
        return map;
    }

    /*
     *   天气 搜索当前最大温度的时间
     *  风向 风力  温度 湿度
     * */
    public List weatherq() {
        Query query = new ListQuery();
        String select = "  wd2dd direction,wd2ds speed,temp temp,rh humidity,obt_name name ";
        String from = " weather ";
        String where = " maxttime IN(SELECT MAX(maxttime) FROM weather) ";
        query.select(select).from(from).where(where);
        generalDao.execute(query);
        List li = query.getResult().getRows();

        //时间

        return li;
    }

    /*
     *   首页--> 预测天气时间接口
     * */
    public Map weatherTime() {
        Map map = new HashMap<>();
        Date date = new Date();
        List<Integer> hours = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            int hour = date.getHours() + i;
            if (hour >= 24) {
                hour = hour - 24;
            }
            hours.add(hour);
        }
        map.put("hours", hours);
       // System.out.println(hours);
        return map;
    }


    /*
        首页---》水  获取各区供水量
    * */
    public List getMqwatersupply(){
        Query query = new ListQuery();
        String select = " mbWater,AREA ";
        String from = " mqwatersupply ";
        query.select(select).from(from);
        generalDao.execute(query);
        List li = query.getResult().getRows();
        return li;
    }
    /*
        首页---》水  获取河流水质质量统计
    * */
    public List getWaterType(){

//  SELECT COUNT(1) count,water_type type FROM riv_qual_sta GROUP BY water_type
        Query query = new ListQuery();
        String select = " COUNT(1) count,water_type type ";
        String from = " riv_qual_sta ";
        String group=" water_type ";
        query.select(select).from(from).groupBy(group);
        generalDao.execute(query);
        List li = query.getResult().getRows();

        return li;
    }
/*
    首页----》水  获取河流水质质量统计  水质综合污染指数
* */
    @RequestMapping()
    public List getRiverName(){
        //SELECT SUM(comp_pollution_index) VALUE,river_name NAME FROM riv_qual_sta GROUP BY river_name
        Query query = new ListQuery();
        String select = " AVG(comp_pollution_index) value,river_name name ";
        String from = " riv_qual_sta ";
        String group=" river_name ";
        query.select(select).from(from).groupBy(group);
        generalDao.execute(query);
        List li = query.getResult().getRows();

        return li;
    }

    /*
    *   获取 污水处理厂 ， 供水处理厂 ， 饮用水源地统计分析
    * */
    public Map getWaterPoint(){
        Map map=new HashMap();
        // 污水处理厂
        String sql="SELECT * FROM pollu_sour_ctro WHERE sta_cont_enterp_type = 3";
        List<PolluSourCtro> polluSourCtros = generalDao.queryList(PolluSourCtro.class, sql);
        map.put("1",polluSourCtros);
        //供水处理厂 SELECT * FROM gsfactry
        Query query = new ListQuery();
        String select = " * ";
        String from = " gsfactry ";
        query.select(select).from(from);
        generalDao.execute(query);
        List li = query.getResult().getRows();
        map.put("2",li);
        // 饮用水源地统计分析
       /* sql="SELECT * FROM dwq";
        List<DWQ> dwqs = generalDao.queryList(DWQ.class, sql);*/
        DWQ d1=new DWQ();
        d1.setId(2);
        d1.setX("112.9216619010383");
        d1.setY("22.955159106172798");
        d1.setWaterSource("中堂水道");
        d1.setCrossSectionName("万江水厂");
        d1.setWithdrawals("356.47");
        d1.setWqi("28");
        d1.setWaterQuality("良");

        DWQ d2=new DWQ();
        d2.setId(3);
        d2.setWaterSource("东江南支流");
        d2.setX("113.18702987687799");
        d2.setY("22.848510084787447");
        d2.setCrossSectionName("樟村水厂");
        d2.setWithdrawals("3690");
        d2.setWqi("29");
        d2.setWaterQuality("良");
        List<DWQ> dwqs =new ArrayList<>();
        dwqs.add(d1);
        dwqs.add(d2);
        map.put("3",dwqs);
        return map;
    }
    /**
     * 获取  饮用水源地统计分析 供水量 根据水源名称
     * @param name 水源名称
     * @return  饮用水源地统计分析 供水量
     */
    public List getWithdrawalsByName(String name){
        if(name==null){
            name="东江南支流";
        }
        Query query = new ListQuery();
        String select = " * ";
        String from = " dwq ";
        String where=" check_time LIKE '2018年%' AND water_source = '"+name+"' ";
        String group=" check_time ";
        String order=" check_time DESC";
        query.select(select).from(from).where(where).groupBy(group).orderBy(order);
        generalDao.execute(query);
        List li = query.getResult().getRows();
        return li;
    }
    /*
     *   获取 污水处理厂 ， 供水处理厂 ， 饮用水源地统计分析 总数
     * */
    public Map getWaterPointCount(){
        Map map=new HashMap();
        // 污水处理厂
        String sql="SELECT count(1) FROM pollu_sour_ctro WHERE sta_cont_enterp_type = 3";
        Integer count = generalDao.queryValue(Integer.class, sql);
        map.put("1",count);
        //供水处理厂 SELECT * FROM gsfactry
         sql="SELECT COUNT(1) FROM gsfactry ";
        count = generalDao.queryValue(Integer.class, sql);
        map.put("2",count);
        // 饮用水源地统计分析
        sql="SELECT water_source FROM dwq GROUP BY water_source";
        List<DWQ> li = generalDao.queryList(DWQ.class, sql);

        map.put("3",li.size());
        return map;
    }
/*    public static void main(String[] args) {

        List<Integer> hours = new ArrayList<>();
        Date date = new Date();
        for (int i = 0; i < 8; i++) {
            int hour = date.getHours() + i;
            if (hour >= 24) {
                hour = hour - 24;
            }
            hours.add(hour);
        }
        System.out.println(hours);
    }*/

}
