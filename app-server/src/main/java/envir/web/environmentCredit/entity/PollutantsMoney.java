package envir.web.environmentCredit.entity;



//广东省30万千瓦以上火电企业二氧化硫排污费开单入库情况公示
public class PollutantsMoney {

    //id 自增长
    private Integer id;

    // 	企业名称
    private String enterpriseName;

    // 	征收时段
    private String ExpropriationPeriod;

    // 	应缴数额（元）
    private String shouldPayable;

    // 	实缴数额（元）
    private String actualPayment;

    // （省委托） 属地征收机关
    private String organ;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getExpropriationPeriod() {
        return ExpropriationPeriod;
    }

    public void setExpropriationPeriod(String expropriationPeriod) {
        ExpropriationPeriod = expropriationPeriod;
    }

    public String getShouldPayable() {
        return shouldPayable;
    }

    public void setShouldPayable(String shouldPayable) {
        this.shouldPayable = shouldPayable;
    }

    public String getActualPayment() {
        return actualPayment;
    }

    public void setActualPayment(String actualPayment) {
        this.actualPayment = actualPayment;
    }

    public String getOrgan() {
        return organ;
    }

    public void setOrgan(String organ) {
        this.organ = organ;
    }

    @Override
    public String toString() {
        return "ollutantsMoney{" +
                "id=" + id +
                ", enterpriseName='" + enterpriseName + '\'' +
                ", ExpropriationPeriod='" + ExpropriationPeriod + '\'' +
                ", shouldPayable='" + shouldPayable + '\'' +
                ", actualPayment='" + actualPayment + '\'' +
                ", organ='" + organ + '\'' +
                '}';
    }
}
