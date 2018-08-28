package test;

import pub.functions.DateFuncs;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zzl.
 * Date: 2018/7/31
 */
public class T3 {

    public static void main(String[] args) {

        Date createTime = new Date();
        createTime = DateFuncs.truncMillis(createTime);
        SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmmss");
        String sTime = format.format(createTime);
//        String sql = "select count(*) from t_order where create_time >= ? and id < ?";
//        Integer count = generalDao.queryValue(Integer.class, sql, createTime, orderId);
        int num = 2;
        String orderNo = "HT" + sTime + String.format("%03d", num);
        System.out.println("?");

    }

}
