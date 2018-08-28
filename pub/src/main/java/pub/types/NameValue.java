package pub.types;

import java.io.Serializable;

/**
 * Created by zzl.
 * Date: 2017/10/16
 */
public class NameValue implements Serializable {

    private String name;
    private Object value;

    public NameValue() {
    }

    public NameValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
