package lab.zhang.rule_engine.server.model.ast.operand.variable;

import cn.hutool.core.util.NumberUtil;
import lab.zhang.rule_engine.server.enums.ast.AccessTypeEnum;
import lab.zhang.rule_engine.server.exception.RuleEngineCalculationException;
import lab.zhang.rule_engine.server.model.calculator.Context;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Map;

/**
 * 整型变量
 *
 * @author zhangrj
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Slf4j
public class VariableInteger extends Variable<Integer> {

    public static VariableInteger of(String name, String value) {
        VariableInteger node = new VariableInteger();
        node.setName(name);
        node.setValue(value);
        node.setAccessType(AccessTypeEnum.VARIABLE);
        node.setChildren(new ArrayList<>());
        return node;
    }

    @Override
    public Integer eval(Context context) {
        Map<String, ?> paramMap = context.getParamMap();
        if (paramMap == null) {
            throw new RuleEngineCalculationException("[eval] paramMap is null, context=" + context);
        }

        Object resultObj = paramMap.getOrDefault(value, null);
        if (resultObj == null) {
            log.info("[eval] get null value, node=" + this);
        }
        // 类型匹配
        if (resultObj instanceof Integer) {
            return (Integer) resultObj;
        }
        // 类型不匹配
        if (!(resultObj instanceof String)) {
            throw new RuleEngineCalculationException("[eval] illegal variable type, node=" + this);
        }
        // 类型转换
        String resultStr = (String) resultObj;
        if (!NumberUtil.isInteger(resultStr)) {
            throw new RuleEngineCalculationException("[eval] value is not Integer, node=" + this);
        }
        return NumberUtil.parseInt(resultStr);
    }
}
