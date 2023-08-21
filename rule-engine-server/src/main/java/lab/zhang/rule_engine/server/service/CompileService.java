package lab.zhang.rule_engine.server.service;

import lab.zhang.rule_engine.server.model.compiler.CompileMeta;

/**
 * 编译
 *
 * @author zhangrj
 */
public interface CompileService<R> {

    CompileMeta<R> compile(String expressionStr);
}
