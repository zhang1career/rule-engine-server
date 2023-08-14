package lab.zhang.rule_engine.server.entity;

import lombok.Data;

/**
 * 规则
 *
 * @author zhangrj
 */
@Data
public class Rule {

    private long id;

    private String name;

    private String cond;

    private long createTime;

    private long updateTime;
}