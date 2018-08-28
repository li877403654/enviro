package pub.dao.query;

import java.io.Serializable;

/**
 * describe: Created by IntelliJ IDEA.
 * @author zzl
 * @version 2008-9-3
 */
public class QuerySettings implements Serializable {

	private static final long serialVersionUID = -74433747975640550L;

	protected QueryResultType resultType = QueryResultType.MAP;

	public QueryResultType getResultType() {
		return resultType;
	}
	public void setResultType(QueryResultType resultType) {
		this.resultType = resultType;
	}

	private String sort;
	private boolean generateRowNo;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public boolean isGenerateRowNo() {
        return generateRowNo;
    }

    public void setGenerateRowNo(boolean generateRowNo) {
        this.generateRowNo = generateRowNo;
    }

    public QuerySettings replaceSort(String sort0, String sort1) {
        if(sort != null || sort.length() > 0) {
            sort = sort.replace(sort0, sort1);
        }
        return this;
    }
}
