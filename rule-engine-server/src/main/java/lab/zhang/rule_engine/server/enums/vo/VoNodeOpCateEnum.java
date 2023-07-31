package lab.zhang.rule_engine.server.enums.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * 操作数/运算符
 *
 * @author zhangrj
 */
@AllArgsConstructor
@Getter
public enum VoNodeOpCateEnum {

    /**
     * 操作数
     */
    OPERAND("操作数"),


    /**
     * 运算符
     */
    OPERATOR("运算符"),

    ;

    private final String desc;
}
