package envir.web.environmentCredit.entity;
import javax.persistence.*;

/**
 * 污染源监管
 */
@Entity
@Table
public class PolluSourCtro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer  id;//主键
    private String  supervisiontype;//监管类型 1，国控；2，省控；3，市控
    private String  cityName;//城市名称
    private String area;//区、县
    private String enterpriseName;//企业名称
    private String  staContEnterpType;//企业类型，废水1，废气2，污水3，重金属4，危险废物5

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getSupervisiontype() {
        return supervisiontype;
    }

    public void setSupervisiontype(String supervisiontype) {
        this.supervisiontype = supervisiontype;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getStaContEnterpType() {
        return staContEnterpType;
    }

    public void setStaContEnterpType(String staContEnterpType) {
        this.staContEnterpType = staContEnterpType;
    }

    @Override
    public String toString() {
        return "PolluSourCtro{" +
                "id=" + id +
                ", supervisiontype='" + supervisiontype + '\'' +
                ", cityName='" + cityName + '\'' +
                ", area='" + area + '\'' +
                ", enterpriseName='" + enterpriseName + '\'' +
                ", staContEnterpType='" + staContEnterpType + '\'' +
                '}';
    }
}
