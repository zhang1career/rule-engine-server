package lab.zhang.rule_engine.server.service.impl;

import lab.zhang.rule_engine.server.RuleEngineServerTestApplication;
import lab.zhang.rule_engine.server.model.compiler.ExpressionMeta;
import lab.zhang.rule_engine.server.service.CompileService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = {RuleEngineServerTestApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.NONE
)
@TestPropertySource(locations = "classpath:test.properties")
public class CompileServiceImplTest {

    @Resource
    private CompileService<Boolean> compileServiceForBool;


    private String exp_age_greatThen_18;

    @Before
    public void setUp() {
        exp_age_greatThen_18 = "{\"name\":\"大于\",\"type\":\"OP_GT\",\"value\":[{\"name\":\"年龄\",\"type\":\"VAR_INT\",\"value\":\"age\"},{\"name\":\"18\",\"type\":\"INS_INT\",\"value\":\"18\"}]}";
    }

    @Test
    public void test_compile() throws Exception {
        ExpressionMeta<Boolean> expMeta = compileServiceForBool.compile(exp_age_greatThen_18);
        System.out.println(expMeta);
    }
}