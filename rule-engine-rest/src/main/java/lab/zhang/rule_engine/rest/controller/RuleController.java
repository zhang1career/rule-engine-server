package lab.zhang.rule_engine.rest.controller;

import cn.hutool.core.util.StrUtil;
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

        Object ruleObj = param.get("rule");
        String ruleStr = StrUtil.toString(ruleObj);

        Context context = new Context();
        context.setParamMap(param);

        Boolean result = ruleService.rule(ruleStr, context);
        return result;
    }

}
