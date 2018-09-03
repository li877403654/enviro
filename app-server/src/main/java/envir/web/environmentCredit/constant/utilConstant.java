package envir.web.environmentCredit.constant;

/**
 * 常量工具类
 */
public class utilConstant {

    //页数
    public static final Integer perPageNum=13;

    public static Integer getPageNum(String pageNum,Integer perPageNum){
        Integer page=0;
        try {
            if(pageNum==null){
                pageNum="0";
            }else{
                page =Integer.parseInt(pageNum);
                if(page>0){
                    page=(page-1)*perPageNum;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return page;
    }
}
