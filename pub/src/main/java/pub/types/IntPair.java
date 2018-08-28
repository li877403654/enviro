package pub.types;

/**
 * Created by zzl on 2015/11/6.
 */
public class IntPair {

    private Integer item1;
    private Integer item2;

    public IntPair() {
        //
    }

    public IntPair(Integer item1, Integer item2) {
        this.item1 = item1;
        this.item2 = item2;
    }

    public Integer getItem1() {
        return item1;
    }

    public void setItem1(Integer item1) {
        this.item1 = item1;
    }

    public Integer getItem2() {
        return item2;
    }

    public void setItem2(Integer item2) {
        this.item2 = item2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IntPair intPair = (IntPair) o;

        if (item1 != null ? !item1.equals(intPair.item1) : intPair.item1 != null) return false;
        return item2 != null ? item2.equals(intPair.item2) : intPair.item2 == null;

    }

    @Override
    public int hashCode() {
        int result = item1 != null ? item1.hashCode() : 0;
        result = 31 * result + (item2 != null ? item2.hashCode() : 0);
        return result;
    }
}
