package lab.zhang.rule_engine.server.model.ast.operator;

import cn.hutool.core.util.NumberUtil;
import lab.zhang.rule_engine.server.enums.ast.AccessTypeEnum;
import lab.zhang.rule_engine.server.exception.RuleEngineCalculationException;
import lab.zhang.rule_engine.server.model.calculator.Context;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 加法
 * 整型
 *
 * @author zhangrj
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Slf4j
public class AddOfInteger extends Operator<Integer> {

    public static AddOfInteger of(String name) {
        AddOfInteger node = new AddOfInteger();
        node.setName(name);
        node.setAccessType(AccessTypeEnum.OPERATOR);
        node.setChildren(new ArrayList<>());
        return node;
    }


    /**
     * 计算
     *
     * @param context 运行上下文
     * @return 值
     */
    @Override
    public Integer calc(List<Object> paramList, Context context) {
        int sum = 0;

        for (Object paramObj : paramList) {
            if (paramObj == null) {
                continue;
            }

            if (paramObj instanceof Integer) {
                sum += (int) paramObj;
                continue;
            }

            if (paramObj instanceof String) {
                sum += NumberUtil.parseInt((String) paramObj);
                continue;
            }

            throw new RuleEngineCalculationException("[calc] paramObj is not a valid type, paramObj=" + paramObj);
        }

        return sum;
    }
}
