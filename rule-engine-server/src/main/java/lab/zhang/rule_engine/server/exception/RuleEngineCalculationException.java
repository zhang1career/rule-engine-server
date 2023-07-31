package lab.zhang.rule_engine.server.exception;

/**
 * 计算异常
 *
 * @author zhangrj
 */
public class RuleEngineCalculationException extends RuntimeException {

    public RuleEngineCalculationException() {
        super();
    }

    public RuleEngineCalculationException(String message) {
        super(message);
    }

    public RuleEngineCalculationException(String message, Throwable cause) {
        super(message, cause);
    }

    public RuleEngineCalculationException(Throwable cause) {
        super(cause);
    }
}
