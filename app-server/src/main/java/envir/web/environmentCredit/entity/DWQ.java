package envir.web.environmentCredit.entity;


//饮用水源地水质统计分析
public class DWQ {

    //序号
    private Integer id;

    //监测时间
    private String checkTime;
    //断面名称
    private String crossSectionName;
    //水源地名称
    private String waterSource;
    //取水量
    private String withdrawals;
    //水质指数
    private String wqi;
    //水质状况
    private String waterQuality;
    //主要超标项目
    private String majorProjects;

    //经纬度
    private String x;
    private String y;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(String checkTime) {
        this.checkTime = checkTime;
    }

    public String getCrossSectionName() {
        return crossSectionName;
    }

    public void setCrossSectionName(String crossSectionName) {
        this.crossSectionName = crossSectionName;
    }

    public String getWaterSource() {
        return waterSource;
    }

    public void setWaterSource(String waterSource) {
        this.waterSource = waterSource;
    }

    public String getWithdrawals() {
        return withdrawals;
    }

    public void setWithdrawals(String withdrawals) {
        this.withdrawals = withdrawals;
    }

    public String getWqi() {
        return wqi;
    }

    public void setWqi(String wqi) {
        this.wqi = wqi;
    }

    public String getWaterQuality() {
        return waterQuality;
    }

    public void setWaterQuality(String waterQuality) {
        this.waterQuality = waterQuality;
    }

    public String getMajorProjects() {
        return majorProjects;
    }

    public void setMajorProjects(String majorProjects) {
        this.majorProjects = majorProjects;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }
}
