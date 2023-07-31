package lab.zhang.rule_engine.server.enums.ast;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * 取值方式
 *
 * @author zhangrj
 */
@AllArgsConstructor
@Getter
@Slf4j
public enum AccessTypeEnum {

    /**
     * 不支持
     */
    NA("not available"),

    /**
     * 立即数
     */
    INSTANT("instance"),

    /**
     * 变量
     */
    VARIABLE("variable"),

    /**
     * 运算符
     */
    OPERATOR("operator"),
    ;


    private final String desc;
}
