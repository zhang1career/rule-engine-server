package lab.zhang.rule_engine.server.model.ast.operand.variable;

import lab.zhang.rule_engine.server.model.ast.operand.Operand;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * 变量
 *
 * @author zhangrj
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Slf4j
abstract public class Variable<R> extends Operand<String, R> {
}
