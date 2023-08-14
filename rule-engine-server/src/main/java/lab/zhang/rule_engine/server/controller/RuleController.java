package lab.zhang.rule_engine.server.controller;

import cn.hutool.core.util.NumberUtil;
import lab.zhang.rule_engine.server.entity.Rule;
import lab.zhang.rule_engine.server.model.calculator.Context;
import lab.zhang.rule_engine.server.service.RuleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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


    @RequestMapping(method = RequestMethod.POST, value = "/eval")
    @ResponseBody
    public Boolean eval(@RequestParam Map<String, Object> param) {

        Object ruleIdObj = param.get("rule_id");
        long ruleId = NumberUtil.parseLong((String) ruleIdObj);

        Rule rule = ruleService.findById(ruleId);
        if (rule == null) {
            return false;
        }
        String cond = rule.getCond();

        Context context = new Context();
        context.setParamMap(param);

        Boolean result = ruleService.calc(cond, context);
        return result;
    }

}
