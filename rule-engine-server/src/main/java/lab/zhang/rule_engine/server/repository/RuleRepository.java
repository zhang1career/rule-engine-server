package lab.zhang.rule_engine.server.repository;

import lab.zhang.rule_engine.server.entity.Rule;

/**
 * @author zhangrj
 */
public interface RuleRepository {

    /**
     * @param id
     * @return
     */
    Rule findRuleById(long id);
}
