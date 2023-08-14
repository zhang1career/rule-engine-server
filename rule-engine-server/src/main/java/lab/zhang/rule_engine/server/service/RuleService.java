package lab.zhang.rule_engine.server.service;

import lab.zhang.rule_engine.server.entity.Rule;
import lab.zhang.rule_engine.server.model.calculator.Context;

/**
 * @author zhangrj
 */
public interface RuleService {

    Rule findById(long ruleId);


    Boolean calc(String expressionStr, Context context);
}
