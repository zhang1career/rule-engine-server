package lab.zhang.rule_engine.server.controller;

import cn.hutool.core.util.NumberUtil;
import com.github.benmanes.caffeine.cache.LoadingCache;
import lab.zhang.rule_engine.server.entity.Rule;
import lab.zhang.rule_engine.server.model.calculator.Context;
import lab.zhang.rule_engine.server.service.RuleService;
import lab.zhang.rule_engine.server.util.GroovyUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 执行规则
 *
 * @author zhangrj
 */
@RestController
@RequestMapping("rule")
public class RuleController {

    @Resource
    private RuleService ruleService;

    @Resource
    private LoadingCache<Long, Rule> ruleCache;


    @RequestMapping(method = RequestMethod.POST, value = "/eval")
    @ResponseBody
    public Boolean eval(@RequestParam Map<String, Object> param) {

        Object ruleIdObj = param.get("rule_id");
        long ruleId = NumberUtil.parseLong((String) ruleIdObj);

        // judge
        // get rule
        Rule rule = ruleCache.get(ruleId);
        if (rule == null) {
            return false;
        }
        String cond = rule.getCond();
        // get context
        Context context = new Context();
        context.setParamMap(param);
        // calc
        Boolean calcResult = ruleService.calc(cond, context);

        // execute
        String filepath = "/Users/zhangrj/Projects/rule-engine/rule-engine-server/src/main/resources/script/";
        String filename = "dummy.groovy";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("calc", calcResult);
        Object exeResult = GroovyUtil.engine(filepath, filename, paramMap);

        return (Boolean) exeResult;
    }

}
