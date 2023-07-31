package lab.zhang.rule_engine.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@Slf4j
public class RuleEngineServerTestApplication {

    public static void main(String[] args) {
        log.info("[main] rule engine starting...");
        SpringApplication.run(RuleEngineServerTestApplication.class, args);
        log.info("[main] rule engine started");
    }
}
