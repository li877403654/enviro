package pub.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created by zzl on 23/7/2016.
 */
public class ShortDoubleSerializer extends JsonSerializer<Double> {

    private NumberFormat numberFormat;

    public ShortDoubleSerializer() {
        this.numberFormat = new DecimalFormat("0.#####");
    }

    public ShortDoubleSerializer(int maxFractionDigits) {
        DecimalFormat format = new DecimalFormat();
        format.setMaximumFractionDigits(maxFractionDigits);
        format.setGroupingUsed(false);
        this.numberFormat = format;
    }

    @Override
    public Class<Double> handledType() {
        return Double.class;
    }

    @Override
    public void serialize(Double value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        if(value == null) {
            gen.writeNull();
        }
        else {
            gen.writeNumber(numberFormat.format(value));
        }

//        System.out.println("?");
    }

}
