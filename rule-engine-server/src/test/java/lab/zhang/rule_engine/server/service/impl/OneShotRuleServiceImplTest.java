package lab.zhang.rule_engine.server.service.impl;

import lab.zhang.rule_engine.server.RuleEngineServerTestApplication;
import lab.zhang.rule_engine.server.model.calculator.Context;
import lab.zhang.rule_engine.server.service.RuleService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = {RuleEngineServerTestApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.NONE
)
@TestPropertySource(locations = "classpath:test.properties")
public class OneShotRuleServiceImplTest {

    @Resource
    private RuleService ruleService;

    private String exp_age_greatThen_18;

    @Before
    public void setUp() {
        exp_age_greatThen_18 = "{\"name\":\"大于\",\"type\":\"OP_GT\",\"value\":[{\"name\":\"年龄\",\"type\":\"VAR_INT\",\"value\":\"age\"},{\"name\":\"18\",\"type\":\"INS_INT\",\"value\":\"18\"}]}";
    }

    @Test
    public void test_rule_age_gt_18() throws Exception {
        String paramAge = "age";
        Integer param20 = 20;
        Context context = new Context();
        context.putParam(paramAge, param20);

        Boolean result = ruleService.calc(exp_age_greatThen_18, context);
        assertTrue(result);
    }

    @Test
    public void test_rule_age_lt_18() throws Exception {
        String paramAge = "age";
        Integer param20 = 16;
        Context context = new Context();
        context.putParam(paramAge, param20);

        Boolean result = ruleService.calc(exp_age_greatThen_18, context);
        assertFalse(result);
    }

}