package lab.zhang.rule_engine.server.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import lab.zhang.rule_engine.server.entity.Rule;
import lab.zhang.rule_engine.server.repository.RuleRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangrj
 */
@Configuration
public class CacheConfig {

    @Resource
    private RuleRepository ruleRepository;


    @Bean
    public LoadingCache<Long, Rule> ruleCache() {
        LoadingCache<Long, Rule> cache = Caffeine.newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(10, TimeUnit.SECONDS)
                .build(ruleId -> ruleRepository.findRuleById(ruleId));

        return cache;
    }
}
