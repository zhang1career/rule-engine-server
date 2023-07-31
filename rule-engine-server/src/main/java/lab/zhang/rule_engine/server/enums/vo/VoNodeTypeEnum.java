package lab.zhang.rule_engine.server.enums.vo;

import com.google.gson.JsonElement;
import lab.zhang.rule_engine.server.model.ast.TreeNode;
import lab.zhang.rule_engine.server.model.ast.operand.instant.Instant;
import lab.zhang.rule_engine.server.model.ast.operand.variable.VariableInteger;
import lab.zhang.rule_engine.server.model.ast.operator.GreaterThanOfInteger;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * @author zhangrj
 */
@AllArgsConstructor
@Getter
public enum VoNodeTypeEnum {

    /**
     * 常量: 无符号整型
     */
    INS_INT("常量: 无符号整型") {
        @Override
        public VoNodeOpCateEnum getOpCate() {
            return VoNodeOpCateEnum.OPERAND;
        }

        @Override
        public TreeNode<?, ?> createTreeNode(String nameStr, JsonElement valueJson) {
            int valueInt = valueJson.getAsInt();
            return Instant.of(nameStr, valueInt);
        }
    },


    /**
     * 变量: 无符号整型
     */
    VAR_INT("变量: 无符号整型r") {
        @Override
        public VoNodeOpCateEnum getOpCate() {
            return VoNodeOpCateEnum.OPERAND;
        }

        @Override
        public TreeNode<?, ?> createTreeNode(String nameStr, JsonElement valueJson) {
            String valueStr = valueJson.getAsString();
            return VariableInteger.of(nameStr, valueStr);
        }
    },


    /**
     * 运算符: 大于
     */
    OP_GT("运算符: 大于") {
        @Override
        public VoNodeOpCateEnum getOpCate() {
            return VoNodeOpCateEnum.OPERATOR;
        }

        @Override
        public TreeNode<?, ?> createTreeNode(String nameStr, JsonElement valueJson) {
            return GreaterThanOfInteger.of(nameStr, null);
        }
    },

    ;

    private final String desc;

    abstract public VoNodeOpCateEnum getOpCate();

    abstract public TreeNode<?, ?> createTreeNode(String nameStr, JsonElement valueJson);
}
