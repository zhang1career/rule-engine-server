package lab.zhang.rule_engine.server.util;

import cn.hutool.core.util.StrUtil;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class GroovyUtilTest {

    private String filepath;

    private String filename;

    private Map<String, Object> paramMap;


    @Before
    public void setUp() {
        filepath = "/Users/zhangrj/Projects/rule-engine/rule-engine-server/src/test/resources/script/";
        filename = StrUtil.EMPTY;
        paramMap = new HashMap<>();
    }


    @Test
    public void test_engine_hello_without_param() throws Exception {
        String filename = "hello_without_param.groovy";

        Object result = GroovyUtil.engine(filepath, filename, paramMap);
        System.out.println(result);
    }

    @Test
    public void test_engine_hello_with_param() throws Exception {
        String filename = "hello_with_param.groovy";
        paramMap.put("userName", "zhangrj");
        Object result = GroovyUtil.engine(filepath, filename, paramMap);
        System.out.println(result);
    }
}