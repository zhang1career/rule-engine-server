package lab.zhang.rule_engine.server.model.ast;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import lab.zhang.rule_engine.server.enums.ast.AccessTypeEnum;
import lab.zhang.rule_engine.server.model.calculator.Context;
import lombok.Data;

import java.util.List;

/**
 * 节点
 *
 * @author zhangrj
 */
@Data
abstract public class TreeNode<P, R> {

    private static Snowflake snowflake = IdUtil.getSnowflake();

    /**
     * 唯一ID
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 存入数值
     */
    protected P value;

    /**
     * 取值方式
     */
    private AccessTypeEnum accessType;

    /**
     * 子节点
     */
    protected List<TreeNode<?, ?>> children;

    /**
     * 添加子节点
     *
     * @param child 子节点
     */
    public void addChild(TreeNode<?, ?> child) {
        children.add(child);
    }

    /**
     * 取值
     *
     * @param context 运行上下文
     * @return 值
     */
    abstract public R eval(Context context);
}
