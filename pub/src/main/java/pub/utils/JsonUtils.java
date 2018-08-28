package pub.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import pub.functions.DateFuncs;
import pub.types.IdDoubleValue;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by zzl on 4/7/2016.
 */
public class JsonUtils {

    public static String stringfy(Object object, int maxFractionDigits) {
        ObjectMapper mapper = new ObjectMapper();
         SimpleModule module = new SimpleModule();
         module.addSerializer(new ShortDoubleSerializer(maxFractionDigits));
         mapper.registerModule(module);

        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        StringWriter sw = new StringWriter();
        try {
            mapper.writeValue(sw, object);
            sw.flush();
            sw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return sw.toString();
    }

    public static void main(String[] args) {
        IdDoubleValue idVal = new IdDoubleValue();
        idVal.setId(34);
        idVal.setValue(2641/3d);
        String json = JsonUtils.stringfy(idVal, 4);
        System.out.println(json);
    }

    public static String stringfy(Object object, boolean withoutNullValues) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        if(withoutNullValues) {
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        }

        StringWriter sw = new StringWriter();
        try {
            mapper.writeValue(sw, object);
            sw.flush();
            sw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return sw.toString();
    }

    public static String stringfy(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        StringWriter sw = new StringWriter();
        try {
            mapper.writeValue(sw, object);
            sw.flush();
            sw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return sw.toString();
    }

    public static void write(OutputStream os, Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(os, obj);
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private static class MyDateSerializer extends StdDeserializer<Date> {

        private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

        public MyDateSerializer() {
            this(null);
        }

        protected MyDateSerializer(Class<?> vc) {
            super(vc);
        }

        @Override
        public Date deserialize(JsonParser jsonParser, DeserializationContext ctxt)
                throws IOException, JsonProcessingException {
            String sDate = jsonParser.getText();
            if(sDate == null || sDate.length() == 0) {
                return null;
            }
            if(sDate.indexOf(' ') != -1) {
                try {
                    return dateFormatter.parse(sDate);
                }
                catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
            else if(sDate.indexOf('-') == -1) {
                return new Date(Long.parseLong(sDate));
            }
            else { //time?
                return DateFuncs.parse(sDate);
            }
        }
    }

    public static <T> List<T> parseList(String json, Class<T> clazz) {
        ObjectMapper mapper = new ObjectMapper();

        SimpleModule module = new SimpleModule();
        module.addDeserializer(Date.class, new MyDateSerializer());
        mapper.registerModule(module);

        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            JavaType type = mapper.getTypeFactory().constructCollectionType(List.class, clazz);

            List<T> result = mapper.readValue(json, type);
            return result;
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static<T> T parse(String json, Class<T> clazz) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            T result = mapper.readValue(json, clazz);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static<T> T read(InputStream is, Class<T> clazz) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            T result = mapper.readValue(is, clazz);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
