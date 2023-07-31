package lab.zhang.rule_engine.server.model.calculator;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 执行上下文
 *
 * @author zhangrj
 */
@Data
public class Context {

    /**
     * 入参
     */
    private Map<String, Object> paramMap;


    public Context() {
        this.paramMap = new HashMap<>();
    }

    public void putParam(String key, Object value) {
        paramMap.put(key, value);
    }

}
