package envir.sys.service.environmentCredit;

import envir.web.environmentCredit.constant.*;
import envir.web.environmentCredit.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pub.dao.GeneralDao;
import pub.dao.jdbc.ListQuery;
import pub.dao.query.Query;

import java.text.SimpleDateFormat;
import java.util.*;

/*
*   信息共享页面(gis地图页面) 业务层
* */
@Service
public class GisMapService {
    @Autowired
    private GeneralDao generalDao;

    /**
     * 查询所有的企业信息
     */
    public List pollutionAll(){
        Map map=new HashMap();

        String sql=" SELECT * FROM pollu_sour_ctro";

        //System.out.println(sql);
        List<PolluSourCtro> polluSourCtros = generalDao.queryList(PolluSourCtro.class, sql);

        return polluSourCtros;
    }
    /**
     * 信息共享页面 -> 需要传 pageNum 返回 企业详细信息
     */
    public Map pollution(String pageNum){
        Map map=new HashMap();
        //获得分页的开始行号
        Integer page=utilConstant.getPageNum(pageNum,utilConstant.perPageNum);

        String sql=" SELECT * FROM pollu_sour_ctro";
        //计算count
        Integer count=this.count(sql);

        if(count==null){
            count=0;
        }else if(count%utilConstant.perPageNum==0){
            count=count/utilConstant.perPageNum;
        }else{
            count=count/utilConstant.perPageNum+1;
        }
        map.put("count",count);
        sql+=" LIMIT "+page+","+utilConstant.perPageNum;
        //System.out.println(sql);
        List<PolluSourCtro> polluSourCtros = generalDao.queryList(PolluSourCtro.class, sql);
        map.put("polluSourCtros",polluSourCtros);
        return map;
    }
    /**
     *  企业信息 总记录数
     * */
    public List pagecountPollu(){
        Query query = new ListQuery();
        String select = " COUNT(1) as pagecount ";
        String from=" pollu_sour_ctro ";
        query.select(select).from(from);
        generalDao.execute(query);
        List list= query.getResult().getRows();
        return list;
    }

    /**
     *  根据企业名称模糊查询 分页 一页13条数据 查询企业名称
     * */
    public Map getPollutionByName(String pollutionName,String pageNum){
        Map map=new HashMap();
        //获得分页的开始行号
        Integer page=utilConstant.getPageNum(pageNum,utilConstant.perPageNum);
        if(pollutionName==null){
            pollutionName="";
        }
        String sql=" SELECT * FROM pollu_sour_ctro WHERE enterprise_name LIKE '%"+pollutionName+"%'";
       //计算count
        Integer count=this.count(sql);

        if(count==null){
            count=0;
        }else if(count%utilConstant.perPageNum==0){
            count=count/utilConstant.perPageNum;
        }else{
            count=count/utilConstant.perPageNum+1;
        }
        map.put("count",count);

        sql+=" LIMIT "+page+","+utilConstant.perPageNum;
        List<PolluSourCtro> polluSourCtros = generalDao.queryList(PolluSourCtro.class, sql);
        map.put("polluSourCtros",polluSourCtros);
        return map;
    }

    /**
     *  根据前台条件模糊查询 分页 一页13条数据
     * @param pollutionName     企业名称
     * @param type               企业类型 -> 废水1，废气2，污水3，重金属4，危险废物5
     * @param area               地区
     * @param pageNum            页面
     * @return                    总数 以及 对应的企业信息
     */
    public Map getPollutionLike(String pollutionName,String type,String area,String pageNum){
        Map map=new HashMap<>();
        //获得分页的开始行号
        Integer page=utilConstant.getPageNum(pageNum,utilConstant.perPageNum);
        //String sql=" SELECT * FROM pollu_sour_ctro WHERE enterprise_name LIKE '"+pollutionName+"%' LIMIT "+page+","+utilConstant.perPageNum;
        StringBuffer sql=new StringBuffer();
        sql.append("SELECT * FROM pollu_sour_ctro WHERE");
        if(pollutionName==null){
            pollutionName="";
        }
        sql.append(" enterprise_name LIKE '%"+pollutionName+"%'");

        //若 类型不为null 则加上类型条件
        if(type != null && !type.trim().equals("")){
            sql.append(" and sta_cont_enterp_type='"+type+"'");
        }
        if(area != null && !area.trim().equals("")){
            sql.append(" and AREA = '"+area+"' ");
        }
        //获取 count
        Integer count=this.count(sql.toString());

        if(count==null){
            count=0;
        }else if(count%utilConstant.perPageNum==0){
            count=count/utilConstant.perPageNum;
        }else{
            count=count/utilConstant.perPageNum+1;
        }
        map.put("count",count);

        sql.append(" LIMIT "+page+" , "+utilConstant.perPageNum);
        //System.out.println(sql.toString());
        List<PolluSourCtro> polluSourCtros = generalDao.queryList(PolluSourCtro.class, sql.toString());
        map.put("polluSourCtros",polluSourCtros);
        return map;
    }

    /**
     * 获取总数的工具类
     * @param sql
     * @return 该SQL的结果的总数
     */
    public Integer count(String sql){
        String countSql=sql;
        countSql=countSql.replaceFirst("\\*","count(1) count");
        //System.out.println("计算总数是sql："+countSql);
        Integer count = generalDao.queryValue(Integer.class, countSql.toString());
        return count;
    }

    /**
     * 根据ID 获取企业信息
     * @param id
     * @return 企业信息
     */
    public PolluSourCtro getPollutionById(String id){
        if(id == null && id.trim().equals("")){
            return null;
        }
        String sql="SELECT *  FROM pollu_sour_ctro WHERE id="+id;
        PolluSourCtro polluSourCtro = generalDao.queryValue(PolluSourCtro.class,sql);
        return polluSourCtro;
    }

    /*******************************应急机制************************************/
    /**
     * 根据企业ID 获取企业数值
     * @param id
     * @return 企业数值
     */
    public List<PolluSourCtroValue> getPolluSourCtroValueById(String id){
        if(id == null){
            id="1";
        }else if(id.trim().equals("")){
            id="1";
        }
        String sql="SELECT *  FROM pollu_sour_ctro_value WHERE pollu_sour_ctro_id="+id;
        List<PolluSourCtroValue> polluSourCtroValues = generalDao.queryList(PolluSourCtroValue.class,sql);
        //处理时间，年月日时分秒转  年月日
        List<AirQuality> list = new ArrayList<AirQuality>();
        for (PolluSourCtroValue pv:polluSourCtroValues) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
            String time = sdf.format(pv.getTimes());
            pv.setCont(time + "时");
        }
        return polluSourCtroValues;
    }
}
