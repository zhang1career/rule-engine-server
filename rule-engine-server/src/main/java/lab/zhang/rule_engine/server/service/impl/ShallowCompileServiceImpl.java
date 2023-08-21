package lab.zhang.rule_engine.server.service.impl;

import cn.hutool.core.util.StrUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lab.zhang.rule_engine.server.enums.vo.VoNodeOpCateEnum;
import lab.zhang.rule_engine.server.enums.vo.VoNodeTypeEnum;
import lab.zhang.rule_engine.server.model.ast.TreeNode;
import lab.zhang.rule_engine.server.model.compiler.CompileMeta;
import lab.zhang.rule_engine.server.service.CompileService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 浅表编译
 * 未经词法分析、语法分析
 *
 * @author zhangrj
 */
@Service
public class ShallowCompileServiceImpl<R> implements CompileService<R> {

    private static final String NAME = "name";

    private static final String TYPE = "type";

    private static final String VALUE = "value";


    /**
     * 编译
     * 字符串格式
     * {
     *     "name": "大于",
     *     "type": "OP_GT",
     *     "value": [
     *         {
     *             "name": "年龄",
     *             "type": "VAR_INT",
     *             "value": "age"
     *         },
     *         {
     *             "name": "18",
     *             "type": "INS_INT",
     *             "value": "18"
     *         }
     *     ],
     * }
     * @param expressionStr 输入字符串
     * @return 表达式
     */
    @Override
    public CompileMeta<R> compile(String expressionStr) {
        // tree node
        TreeNode<?, ?> treeNode = parseJson(expressionStr);
        // expression
        CompileMeta<R> compileMeta = new CompileMeta<>();
        compileMeta.setTreeNode((TreeNode<?, R>) treeNode);

        return compileMeta;
    }


    public TreeNode<?, ?> parseJson(String expressionStr) {
        JsonElement jElement = JsonParser.parseString(expressionStr);
        return doParseJson(jElement.getAsJsonObject());
    }


    public TreeNode<?, ?> doParseJson(JsonObject jObject) {
        // name
        JsonElement jName = jObject.get(NAME);
        String nameStr = jName.getAsString();
        // type
        JsonElement jType = jObject.get(TYPE);
        String typeStr = jType.getAsString();
        if (StrUtil.isEmpty(typeStr)) {
            throw new IllegalArgumentException("[doParseJson] type is empty");
        }
        VoNodeTypeEnum type = VoNodeTypeEnum.valueOf(typeStr);
        // value of operand
        if (type.getOpCate() == VoNodeOpCateEnum.OPERAND) {
            JsonElement jValue = jObject.get(VALUE);
            return type.createTreeNode(nameStr, jValue);
        }
        // value of operator
        TreeNode<?, ?> rootNode = type.createTreeNode(nameStr, null);
        JsonArray jValue = jObject.getAsJsonArray(VALUE);
        List<TreeNode<?, ?>> children = new ArrayList<>();
        for (JsonElement jElement : jValue) {
            TreeNode<?, ?> treeNode = doParseJson(jElement.getAsJsonObject());
            children.add(treeNode);
        }
        rootNode.setChildren(children);

        return rootNode;
    }
}
