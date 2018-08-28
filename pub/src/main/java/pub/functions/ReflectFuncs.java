package pub.functions;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Created by zzl on 8/23/2016.
 */
public class ReflectFuncs {

    public static void setFinalStatic(Field field, Object newValue) throws Exception {
        field.setAccessible(true);

        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

        field.set(null, newValue);
    }

}