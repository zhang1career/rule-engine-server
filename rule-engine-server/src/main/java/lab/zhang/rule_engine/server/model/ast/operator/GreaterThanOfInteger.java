package lab.zhang.rule_engine.server.model.ast.operator;

import lab.zhang.rule_engine.server.enums.ast.AccessTypeEnum;
import lab.zhang.rule_engine.server.model.ast.TreeNode;
import lab.zhang.rule_engine.server.model.calculator.Context;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 整型加法
 *
 * @author zhangrj
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Slf4j
public class GreaterThanOfInteger extends Operator<Boolean> {

    public static GreaterThanOfInteger of(String name, Object value) {
        GreaterThanOfInteger node = new GreaterThanOfInteger();
        node.setName(name);
        node.setValue(value);
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
    public Boolean calc(List<Object> paramList, Context context) {
        Boolean result = false;

        Object paramObj0 = paramList.get(0);
        Object paramObj1 = paramList.get(1);
        if (paramObj0 == null || paramObj1 == null) {
            return null;
        }
        if (!(paramObj0 instanceof Integer) || !(paramObj1 instanceof Integer)) {
            return null;
        }

        Integer param0 = (Integer) paramObj0;
        Integer param1 = (Integer) paramObj1;

        return param0 > param1;
    }
}
