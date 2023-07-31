package lab.zhang.rule_engine.server.service;

import lab.zhang.rule_engine.server.model.calculator.Context;
import lab.zhang.rule_engine.server.model.compiler.ExpressionMeta;

/**
 * 计算
 *
 * @author zhangrj
 */
public interface CalculateService<R> {

    R calculate(ExpressionMeta<R> expressionMeta, Context context);
}
