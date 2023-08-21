package lab.zhang.rule_engine.server.service;

import lab.zhang.rule_engine.server.model.calculator.Context;

/**
 * @author zhangrj
 */
public interface RuleService {

    Boolean calc(String expressionStr, Context context);
}
