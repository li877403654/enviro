package envir.sys.service.environmentCredit;

import envir.web.environmentCredit.constant.utilConstant;
import envir.web.environmentCredit.entity.AirQuality;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pub.dao.GeneralDao;
import pub.dao.jdbc.ListQuery;
import pub.dao.query.Query;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MainService {

    @Autowired
    private GeneralDao generalDao;

    public List<AirQuality> init(String date){
        String sql = " SELECT * from " +
                " (SELECT COUNT(1) as target FROM air_quality WHERE `level` in('优','良') AND times LIKE '"+date+"%') a," +
                " (SELECT COUNT(1) as typeAir FROM air_quality WHERE `level` = '轻度污染' AND times LIKE '"+date+"%') b ";
        List<AirQuality> list=generalDao.queryList(AirQuality.class,sql);
        return list;
    }
//    public List init(){
//        Query query = new ListQuery();
//        String select = " max((SELECT COUNT(1) FROM pollu_sour_ctro T WHERE T.supervisiontype='1')) as 'country'," +
//                " max((SELECT COUNT(1) FROM pollu_sour_ctro T WHERE T.supervisiontype='2')) as 'pro'," +
//                " max((SELECT COUNT(1) FROM pollu_sour_ctro T WHERE T.supervisiontype='3')) as 'city' ";
//        query.select(select);
//        generalDao.execute(query);
//        List li= query.getResult().getRows();
//        return li;
//    }
    public List<AirQuality> findAirContaminant(String date){
        String sql = " SELECT * from \n " +
                " (SELECT COUNT(1) as monitoringPoint FROM air_quality WHERE `type_air` = '2' AND times LIKE '"+date+"%') b , \n" +
                "\n" +
                " (SELECT COUNT(1) as target FROM air_quality WHERE `type_air` = '3' AND times LIKE '"+date+"%') c ,\n" +
                "\n" +
                " (SELECT COUNT(1) as level FROM air_quality WHERE `type_air` = '5' AND times LIKE '"+date+"%') e ,\n" +
                "\n" +
                " (SELECT COUNT(1) as typeAir FROM air_quality WHERE `type_air` = '6' AND times LIKE '"+date+"%') f  " ;
        //String sql="select * from air_quality";
        List<AirQuality> AirContaminants = generalDao.queryList(AirQuality.class, sql);
        return AirContaminants;
    }
    /**
     * 污染源监管
     */
    public List polluSourCtrl(){
        Query query = new ListQuery();
        String select = " max((SELECT COUNT(1) FROM pollu_sour_ctro T WHERE T.supervisiontype='1')) as 'country'," +
                " max((SELECT COUNT(1) FROM pollu_sour_ctro T WHERE T.supervisiontype='2')) as 'pro'," +
                " max((SELECT COUNT(1) FROM pollu_sour_ctro T WHERE T.supervisiontype='3')) as 'city' ";
        query.select(select);
        generalDao.execute(query);
        List li= query.getResult().getRows();
        return li;
    }


    public List pollution(String pageNum){
        Query query = new ListQuery();
        String select = " T.area,T.city_name,T.enterprise_name,T.sta_cont_enterp_type,T.supervisiontype ";
        String from=" pollu_sour_ctro T LIMIT "+pageNum +","+ utilConstant.perPageNum ;
        query.select(select).from(from);
        generalDao.execute(query);
        List list= query.getResult().getRows();
        return list;
    }
    public List pagecountPollu(){
        Query query = new ListQuery();
        String select = " COUNT(1) as pagecount ";
        String from=" pollu_sour_ctro ";
        query.select(select).from(from);
        generalDao.execute(query);
        List list= query.getResult().getRows();
        return list;
    }
    public List wastewater(){
        Query query = new ListQuery();
        String select = " * ";
        String from=" wastewater ";
        query.select(select).from(from);
        generalDao.execute(query);
        List li= query.getResult().getRows();
        return li;
    }
    /**
      *       信息共享：空气环境检测 ->需显示字段(测点 时间 等级 分数)
      */
    public List<AirQuality> air_qualitySelect(String pageNum){
        Query query = new ListQuery();
        String sql = " SELECT * FROM air_quality ORDER BY times DESC  LIMIT "+pageNum +","+ utilConstant.perPageNum;
        List<AirQuality> airQualities = generalDao.queryList(AirQuality.class, sql);
        //处理时间，年月日时分秒转  年月日
        List<AirQuality> list=new ArrayList<AirQuality>();
        for (AirQuality a:airQualities){
            SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH");
            String time= sdf.format(a.getTimes());
            a.setCont(time+"时");
        }
        return airQualities;
    }
    /**
     *       信息共享：空气环境检测 ->总记录数
     */
    public List pagecountAir(){
        Query query = new ListQuery();
        String select = " COUNT(1) as count ";
        String from=" air_quality ";
        query.select(select).from(from);
        generalDao.execute(query);
        List list= query.getResult().getRows();
        return list;
    }



    /**
      *   广东省30万千瓦以上火电企业二氧化硫排污费开单入库情况公示
     *      企业数 	总税额
      */
    public Map<String,String> pollutants_money_select(){
        Query query =new ListQuery();
        String count = "SELECT COUNT(1) FROM pollutants_money";
        String sum   = "SELECT SUM(actual_payment) FROM pollutants_money";
        Integer countInt=generalDao.queryValue(Integer.class,count);
        Integer sumInt=generalDao.queryValue(Integer.class,sum);
        //处理总金额
        DecimalFormat df = new DecimalFormat("###,###,"
                + "###.##");
        Map<String,String> map=new HashMap<>();
        map.put("count",countInt.toString());
        map.put("sum",df.format(sumInt));
        return map;
    }

    /**
     * 污染源监管，国控企业类型，5个
     * @return
     */
    public List countryctrltype(){
        Query query = new ListQuery();
        String select = " max((SELECT COUNT(1) FROM pollu_sour_ctro T WHERE T.supervisiontype='1' and t.sta_cont_enterp_type='1')) as 'ChemicalFactry'," +
                " max((SELECT COUNT(1) FROM pollu_sour_ctro T WHERE T.supervisiontype='1' and t.sta_cont_enterp_type='2')) as 'MetalFactry'," +
                " max((SELECT COUNT(1) FROM pollu_sour_ctro T WHERE T.supervisiontype='1' and t.sta_cont_enterp_type='3')) as 'pollutiong'," +
                " max((SELECT COUNT(1) FROM pollu_sour_ctro T WHERE T.supervisiontype='1' and t.sta_cont_enterp_type='4')) as 'sewage'," +
                " max((SELECT COUNT(1) FROM pollu_sour_ctro T WHERE T.supervisiontype='1' and t.sta_cont_enterp_type='5')) as 'airMonitor' ";
        String from=" pollu_sour_ctro ";
        query.select(select).from(from);
        generalDao.execute(query);
        List li= query.getResult().getRows();
        return li;
    }
    /**
     * 污染源监管，省控企业类型，5个
     */
    public List proctrltype(){
        Query query = new ListQuery();
        String select = " max((SELECT COUNT(1) FROM pollu_sour_ctro T WHERE T.supervisiontype='2' and t.sta_cont_enterp_type='1')) as 'ChemicalFactry'," +
                " max((SELECT COUNT(1) FROM pollu_sour_ctro T WHERE T.supervisiontype='2' and t.sta_cont_enterp_type='2')) as 'MetalFactry'," +
                " max((SELECT COUNT(1) FROM pollu_sour_ctro T WHERE T.supervisiontype='2' and t.sta_cont_enterp_type='3')) as 'pollutiong'," +
                " max((SELECT COUNT(1) FROM pollu_sour_ctro T WHERE T.supervisiontype='2' and t.sta_cont_enterp_type='4')) as 'sewage'," +
                " max((SELECT COUNT(1) FROM pollu_sour_ctro T WHERE T.supervisiontype='2' and t.sta_cont_enterp_type='5')) as 'airMonitor' ";
        String from=" pollu_sour_ctro ";
        query.select(select).from(from);
        generalDao.execute(query);
        List li= query.getResult().getRows();
        return li;
    }
    /**
     * 污染源监管，市控企业类型，5个
     */
    public List citytrltype(){
        Query query = new ListQuery();
        String select = " max((SELECT COUNT(1) FROM pollu_sour_ctro T WHERE T.supervisiontype='3' and t.sta_cont_enterp_type='1')) as 'ChemicalFactry'," +
                " max((SELECT COUNT(1) FROM pollu_sour_ctro T WHERE T.supervisiontype='3' and t.sta_cont_enterp_type='2')) as 'MetalFactry'," +
                " max((SELECT COUNT(1) FROM pollu_sour_ctro T WHERE T.supervisiontype='3' and t.sta_cont_enterp_type='3')) as 'pollutiong'," +
                " max((SELECT COUNT(1) FROM pollu_sour_ctro T WHERE T.supervisiontype='3' and t.sta_cont_enterp_type='4')) as 'sewage'," +
                " max((SELECT COUNT(1) FROM pollu_sour_ctro T WHERE T.supervisiontype='3' and t.sta_cont_enterp_type='5')) as 'airMonitor' ";
        String from=" pollu_sour_ctro ";
        query.select(select).from(from);
        generalDao.execute(query);
        List li= query.getResult().getRows();
        return li;
    }

}
