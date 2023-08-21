package lab.zhang.rule_engine.server.service.impl;

import lab.zhang.rule_engine.server.model.calculator.Context;
import lab.zhang.rule_engine.server.model.compiler.CompileMeta;
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

    @Resource(name = "shallowCompileServiceImpl")
    CompileService<Boolean> compileService;

    @Resource
    CalculateService<Boolean> calculateService;


    @Override
    public Boolean calc(String expressionStr, Context context) {
        CompileMeta<Boolean> compileMeta = compileService.compile(expressionStr);
        Boolean result = calculateService.calculate(compileMeta, context);
        return result;
    }
}
