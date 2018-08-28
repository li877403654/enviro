package pub.types;

/**
 * Created by zzl on 2015/11/6.
 */
public class DoublePair {

    private Double item1;
    private Double item2;

    public DoublePair() {
        //
    }

    public DoublePair(Double item1, Double item2) {
        this.item1 = item1;
        this.item2 = item2;
    }

    public Double getItem1() {
        return item1;
    }

    public void setItem1(Double item1) {
        this.item1 = item1;
    }

    public Double getItem2() {
        return item2;
    }

    public void setItem2(Double item2) {
        this.item2 = item2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DoublePair intPair = (DoublePair) o;

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
