package pub.spring.web.mvc.view;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import pub.spring.web.mvc.model.ModelResult;
import pub.spring.web.mvc.view.support.MyDateSerializer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.Map;

/**
 * describe: Created by IntelliJ IDEA.
 * @author zzl
 * @version 2010-2-16
 */
@SuppressWarnings("unchecked")
public class JsonView extends MappingJackson2JsonView {

    // when model contains this attribute, it's value will be the output,
    // 		otherwise, whole model would be output as a map
    public static final String directResult = "direct_result";

    private boolean singleValueAsDirectResult = false;

    public boolean isSingleValueAsDirectResult() {
        return singleValueAsDirectResult;
    }

    public void setSingleValueAsDirectResult(boolean singleValueAsDirectResult) {
        this.singleValueAsDirectResult = singleValueAsDirectResult;
    }

    public JsonView() {
        ObjectMapper objectMapper = getObjectMapper();
        //objectMapper.getSerializerProvider().
//        objectMapper.enable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//        System.out.println("?");
        SimpleModule module = new SimpleModule();
        module.addSerializer(java.sql.Date.class, new MyDateSerializer());
        objectMapper.registerModule(module);
    }

    @Override
    protected void prepareResponse(HttpServletRequest request, HttpServletResponse response) {
        // default no cache for json result
        if (!response.containsHeader("Cache-Control")) {
            response.setHeader("Cache-Control", "no-store");
        }
        super.prepareResponse(request, response);
    }

    @Override
    protected Object filterModel(Map<String, Object> model) {
        //
        String fullResult = "_full_result";
        if(model.containsKey(fullResult)) {
            model.remove(fullResult);
            return model;
        }

        //
        Object result;
        if (model.containsKey(directResult)) {
            return model.get(directResult);
        }
        //
        model = (Map<String, Object>) super.filterModel(model);
        if (singleValueAsDirectResult && model.size() <= 1) {
            Iterator<Object> iter = model.values().iterator();
            return iter.hasNext() ? iter.next() : null;
        }
        for (Object value : model.values()) {
            if (value instanceof ModelResult) {
                ModelResult modelResult = (ModelResult) value;
                if (modelResult.isDirectResult()) {
                    return modelResult;
                }
            }
        }
        return model;
    }
}
