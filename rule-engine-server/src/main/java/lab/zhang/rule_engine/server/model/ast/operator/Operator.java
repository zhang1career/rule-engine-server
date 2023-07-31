package lab.zhang.rule_engine.server.model.ast.operator;

import lab.zhang.rule_engine.server.model.ast.TreeNode;
import lab.zhang.rule_engine.server.model.calculator.Context;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 运算符
 *
 * @author zhangrj
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Slf4j
abstract public class Operator<R> extends TreeNode<Object, R> {

    @Override
    public R eval(Context context) {
        List<Object> childrenValue = new ArrayList<>();
        for (TreeNode<?, ?> child : children) {
            Object childValue = child.eval(context);
            childrenValue.add(childValue);
        }

        return calc(childrenValue, context);
    }

    /**
     * 计算
     *
     * @param paramList 入参列表
     * @param context   运行上下文
     * @return 值
     */
    abstract public R calc(List<Object> paramList, Context context);
}
