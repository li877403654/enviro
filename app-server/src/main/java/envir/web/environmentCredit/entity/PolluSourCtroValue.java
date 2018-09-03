package envir.web.environmentCredit.entity;

import java.util.Date;

/**
 * 污染源监管 数值
 */
public class PolluSourCtroValue {

    //id 自动增长
    private Integer id;

    //废气排放量
    private String wasteGas;

    //时间 yyyy-MM-dd hh
    private Date times;

    //保留字段
    private String cont;

    public String getCont() {
        return cont;
    }

    public void setCont(String cont) {
        this.cont = cont;
    }

    public String getPolluSourCtroId() {
        return polluSourCtroId;
    }

    public void setPolluSourCtroId(String polluSourCtroId) {
        this.polluSourCtroId = polluSourCtroId;
    }

    //企业ID
    private String polluSourCtroId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWasteGas() {
        return wasteGas;
    }

    public void setWasteGas(String wasteGas) {
        this.wasteGas = wasteGas;
    }

    public Date getTimes() {
        return times;
    }

    public void setTimes(Date times) {
        this.times = times;
    }

    @Override
    public String toString() {
        return "PolluSourCtroValue{" +
                "id=" + id +
                ", wasteGas='" + wasteGas + '\'' +
                ", times=" + times +
                ", polluSourCtroId='" + polluSourCtroId + '\'' +
                '}';
    }
}
