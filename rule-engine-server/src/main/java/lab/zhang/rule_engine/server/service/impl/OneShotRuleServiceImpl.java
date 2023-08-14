package lab.zhang.rule_engine.server.service.impl;

import lab.zhang.rule_engine.server.entity.Rule;
import lab.zhang.rule_engine.server.model.calculator.Context;
import lab.zhang.rule_engine.server.model.compiler.ExpressionMeta;
import lab.zhang.rule_engine.server.repository.RuleRepository;
import lab.zhang.rule_engine.server.service.CalculateService;
import lab.zhang.rule_engine.server.service.CompileService;
import lab.zhang.rule_engine.server.service.RuleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 规则计算（一次性）
 *
 * @author zhangrj
 */
@Service
public class OneShotRuleServiceImpl implements RuleService {

    @Resource
    private RuleRepository ruleRepository;

    @Resource
    CompileService<Boolean> compileService;

    @Resource
    CalculateService<Boolean> calculateService;


    @Override
    public Rule findById(long ruleId) {
        return ruleRepository.findRuleById(ruleId);
    }

    @Override
    public Boolean calc(String expressionStr, Context context) {
        ExpressionMeta<Boolean> expressionMeta = compileService.compile(expressionStr);
        Boolean result = calculateService.calculate(expressionMeta, context);
        return result;
    }
}
