package pub.types;

import java.io.Serializable;

/**
 * Created by zzl on 25/7/2016.
 */
public class IdCount implements Serializable {

    private Integer id;
    private Integer count;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
