package lab.zhang.rule_engine.rest;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author zhangrj
 */
@SpringBootApplication(
        scanBasePackages = {
                "lab.zhang.rule_engine.rest",
                "lab.zhang.rule_engine.server"
        },
        exclude = {
                DataSourceAutoConfiguration.class
        })
@MapperScan("lab.zhang.rule_engine.server.mapper")
@Slf4j
public class RuleEngineRestApplication {

    public static void main(String[] args) {
        log.info("[main] rule-engine rest starting...");
        SpringApplication.run(RuleEngineRestApplication.class, args);
        log.info("[main] rule-engine rest started");
    }
}
