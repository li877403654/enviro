package pub.system.validation;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import pub.system.validation.model.FieldInfo;
import pub.system.validation.model.MessageModel;
import pub.system.validation.model.RuleInfo;
import pub.types.Desc;

import java.beans.PropertyDescriptor;

/**
 * Created by zzl on 2017-07-03.
 */
public class MessageUtils {

    public static String resolveMessage(Object bean, String field, RuleInfo ruleInfo) {
        String fieldDesc = "";
        try {
            PropertyDescriptor propDesc = PropertyUtils.getPropertyDescriptor(bean, field);
            Desc desc = propDesc.getReadMethod().getAnnotation(Desc.class);
            if (desc != null) {
                fieldDesc = desc.value();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String message = ruleInfo.message;

        if (message.indexOf('{') != -1) {
            FieldInfo fieldInfo = new FieldInfo();
            fieldInfo.setName(field);
            fieldInfo.setDesc(fieldDesc);
            fieldInfo.setValue("");

            MessageModel model = new MessageModel();
            model.setField(fieldInfo);
            model.setRule(ruleInfo);

            ExpressionParser parser = new SpelExpressionParser();
            TemplateParserContext context = new TemplateParserContext("{", "}");
            message = parser.parseExpression(message, context).getValue(model, String.class);
        }
        return message;
    }


}
