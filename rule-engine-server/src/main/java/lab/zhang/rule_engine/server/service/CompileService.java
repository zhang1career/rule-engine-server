package lab.zhang.rule_engine.server.service;

import lab.zhang.rule_engine.server.model.compiler.ExpressionMeta;

/**
 * 编译
 *
 * @author zhangrj
 */
public interface CompileService<R> {

    ExpressionMeta<R> compile(String expressionStr);
}
