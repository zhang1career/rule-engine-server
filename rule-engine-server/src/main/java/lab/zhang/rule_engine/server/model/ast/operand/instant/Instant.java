package lab.zhang.rule_engine.server.model.ast.operand.instant;

import lab.zhang.rule_engine.server.enums.ast.AccessTypeEnum;
import lab.zhang.rule_engine.server.model.ast.operand.Operand;
import lab.zhang.rule_engine.server.model.calculator.Context;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;

/**
 * 立即数
 *
 * @author zhangrj
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Instant<P> extends Operand<P, P> {

    public static <P> Instant<P> of(String name, P value) {
        Instant<P> node = new Instant<>();
        node.setName(name);
        node.setValue(value);
        node.setAccessType(AccessTypeEnum.INSTANT);
        node.setChildren(new ArrayList<>());
        return node;
    }

    @Override
    public P eval(Context context) {
        return value;
    }
}
