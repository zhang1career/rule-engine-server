package lab.zhang.rule_engine.server.service.impl;

import lab.zhang.rule_engine.server.RuleEngineServerTestApplication;
import lab.zhang.rule_engine.server.model.ast.TreeNode;
import lab.zhang.rule_engine.server.model.ast.operand.instant.Instant;
import lab.zhang.rule_engine.server.model.ast.operand.variable.VariableInteger;
import lab.zhang.rule_engine.server.model.ast.operator.GreaterThanOfInteger;
import lab.zhang.rule_engine.server.model.calculator.Context;
import lab.zhang.rule_engine.server.model.compiler.CompileMeta;
import lab.zhang.rule_engine.server.service.CalculateService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = {RuleEngineServerTestApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.NONE
)
@TestPropertySource(locations = "classpath:test.properties")
public class RecursiveCalculateServiceImplTest {

    @Resource
    private CalculateService<Boolean> calculateServiceForBool;

    @Resource
    private CalculateService<Integer> calculateServiceForInt;

    @Resource
    private CalculateService<Object> calculateServiceForObj;


    @Before
    public void setUp() {
    }


    @Test
    public void test_calculate_instant() throws Exception {
        // setup
        Integer param0 = 0;
        Integer param1 = 1;
        Instant<Integer> node0 = Instant.of("0", param0);
        CompileMeta<Integer> compileMeta = new CompileMeta<>();
        compileMeta.setTreeNode(node0);
        Context context = new Context();
        // execute
        Integer result = calculateServiceForInt.calculate(compileMeta, context);
        // verify
        assertEquals("整型常量，读取值==写入值", param0, result);
        assertNotEquals("整型常量，读取值!=错误的写入值", param1, result);
    }

    @Test
    public void test_calculate_variable_integer() throws Exception {
        // setup
        String paramAge = "age";
        Integer param18 = 18;
        Integer param20 = 20;
        VariableInteger nodeAge = VariableInteger.of("年龄", paramAge);
        CompileMeta<Integer> compileMeta = new CompileMeta<>();
        compileMeta.setTreeNode(nodeAge);
        Context context = new Context();
        context.putParam(paramAge, param18);
        // execute
        Object result = calculateServiceForInt.calculate(compileMeta, context);
        // verify
        assertEquals("整型变量，读取值==写入值", param18, result);
        assertNotEquals("整型变量，读取值!=错误的写入值", param20, result);
    }

    @Test
    public void test_calculate_operation() throws Exception {
        // setup
        String paramAge = "age";
        Integer param18 = 18;
        Integer param20 = 20;
        Instant<Integer> node18 = Instant.of("18", param18);
        VariableInteger nodeAge = VariableInteger.of("年龄", paramAge);
        TreeNode<Object, Boolean> nodeGt = GreaterThanOfInteger.of("大于", null);
        nodeGt.setChildren(Arrays.asList(node18, nodeAge));
        CompileMeta<Boolean> compileMeta = new CompileMeta<>();
        compileMeta.setTreeNode(nodeGt);
        Context context = new Context();
        context.putParam(paramAge, param20);
        // execute
        Boolean result = calculateServiceForBool.calculate(compileMeta, context);
        // verify
        assertEquals("整型数值比较，左值>右值", false, result);
    }
}
