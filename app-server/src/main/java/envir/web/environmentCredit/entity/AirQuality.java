package envir.web.environmentCredit.entity;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table
public class AirQuality {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    //监测点
    private String monitoringPoint;
    //指标
    private String  target;
    //等级
    private  String level;
    //时间
    private Date times;
    //空气类别
    private String typeAir;

    //备用字段
    private String cont;

    public String getCont() {
        return cont;
    }

    public void setCont(String cont) {
        this.cont = cont;
    }

    public String getTypeAir() {
        return typeAir;
    }

    public void setTypeAir(String typeAir) {
        this.typeAir = typeAir;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMonitoringPoint() {
        return monitoringPoint;
    }

    public void setMonitoringPoint(String monitoringPoint) {
        this.monitoringPoint = monitoringPoint;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Date getTimes() {
        return times;
    }

    public void setTimes(Date times) {
        this.times = times;
    }

    @Override
    public String toString() {
        return "AirQuality{" +
                "id=" + id +
                ", monitoringPoint='" + monitoringPoint + '\'' +
                ", target='" + target + '\'' +
                ", level='" + level + '\'' +
                ", times=" + times +
                ", typeAir='" + typeAir + '\'' +
                '}';
    }
}
