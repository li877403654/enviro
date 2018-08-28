package pub.types;

import java.io.Serializable;

/**
 * Created by zzl on 2016-08-22.
 */
public class StringPair implements Serializable {

    private String item1;
    private String item2;

    public String getItem1() {
        return item1;
    }

    public void setItem1(String item1) {
        this.item1 = item1;
    }

    public String getItem2() {
        return item2;
    }

    public void setItem2(String item2) {
        this.item2 = item2;
    }
}
