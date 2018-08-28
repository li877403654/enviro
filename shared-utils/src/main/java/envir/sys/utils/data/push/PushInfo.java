package envir.sys.utils.data.push;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by zzl.
 * Date: 2017-09-20
 */
public class PushInfo implements Serializable {

    private String title;
    private String content;
    private Map<String, String> extras;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Map<String, String> getExtras() {
        return extras;
    }

    public void setExtras(Map<String, String> extras) {
        this.extras = extras;
    }
}

