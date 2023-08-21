package lab.zhang.rule_engine.server.service.impl;

import lab.zhang.rule_engine.server.model.ast.TreeNode;
import lab.zhang.rule_engine.server.model.calculator.Context;
import lab.zhang.rule_engine.server.model.compiler.CompileMeta;
import lab.zhang.rule_engine.server.service.CalculateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author zhangrj
 */
@Service
@Slf4j
public class RecursiveCalculateServiceImpl<R> implements CalculateService<R> {

    @Override
    public R calculate(CompileMeta<R> compileMeta, Context context) {
        TreeNode<?, R> treeNode = compileMeta.getTreeNode();
        return treeNode.eval(context);
    }
}
