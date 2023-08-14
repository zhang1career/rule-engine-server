package lab.zhang.rule_engine.server.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lab.zhang.rule_engine.server.dto.RuleDTO;
import lab.zhang.rule_engine.server.entity.Rule;
import lab.zhang.rule_engine.server.mapper.RuleMapper;
import lab.zhang.rule_engine.server.repository.RuleRepository;
import org.springframework.stereotype.Repository;

/**
 * @author zhangrj
 */
@Repository
public class RuleRepositoryImpl extends ServiceImpl<RuleMapper, Rule> implements RuleRepository {

    @Override
    public Rule findRuleById(long id) {
        return lambdaQuery()
                .getBaseMapper()
                .selectById(id);
    }
}
