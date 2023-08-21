package lab.zhang.rule_engine.server.util;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import groovy.lang.Binding;
import groovy.lang.Script;
import groovy.util.GroovyScriptEngine;
import lombok.Data;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class GroovyUtil {

    private static final Map<String, ChecksumableScript> SCRIPT_MAP = new ConcurrentHashMap<>();

    public static Object engine(String filePath, String fileName, Map<String, Object> variable) {
        Binding binding = new Binding();
        if (CollectionUtil.isNotEmpty(variable)) {
            variable.entrySet().stream().filter(entry -> StrUtil.isNotBlank(entry.getKey()) && ObjectUtil.isNotEmpty(entry.getValue()))
                    .forEach(entry -> binding.setVariable(entry.getKey(), entry.getValue()));
        }
        Object result;
        Script script = getScriptInstance(filePath, fileName);
        //对象锁，因为要赋值，所以一个对象同时只能被一个线程调用
        synchronized (script) {
            script.setBinding(binding);
            result = script.run();
        }
        return result;
    }

    private static Script getScriptInstance(String filePath, String fileName) {
        String fileAbPath = filePath + File.separator + fileName;
        File file = new File(fileAbPath);
        String md5Hex = DigestUtil.md5Hex(file);

        ChecksumableScript checksum = SCRIPT_MAP.getOrDefault(fileAbPath, null);
        if (checksum != null && md5Hex.equals(checksum.getMd5())) {
            return checksum.getScript();
        }

        Script script = null;
        try {
            GroovyScriptEngine engine = new GroovyScriptEngine(filePath);
            script = engine.createScript(fileName, new Binding());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("文件不存在");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("生成script文件失败");
        }
        SCRIPT_MAP.put(fileAbPath, new ChecksumableScript(md5Hex, script));
        return script;
    }

    @Data
    private static class ChecksumableScript {
        private String md5;
        private Script script;

        public ChecksumableScript(String md5, Script script) {
            this.md5 = md5;
            this.script = script;
        }
    }

}

