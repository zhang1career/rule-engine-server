package lab.zhang.rule_engine.server.model.compiler;

import lab.zhang.rule_engine.server.model.ast.TreeNode;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 编译后元数据
 *
 * @author zhangrj
 */
@NoArgsConstructor
@Data
public class CompileMeta<R> {

    /**
     * ast
     */
    private TreeNode<?, R> treeNode;
}
