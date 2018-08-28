package pub.types;

import java.io.Serializable;

/**
 * Created by zzl on 2016-07-04.
 */
public class IdDoubleValue implements Serializable {

    private Integer id;
    private Double value;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
