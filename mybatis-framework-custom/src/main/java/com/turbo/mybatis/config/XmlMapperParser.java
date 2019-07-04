package com.turbo.mybatis.config;

import org.dom4j.Document;
import org.dom4j.Element;

import java.util.List;

/**
 * 映射文件解析器
 * @author zouxq
 */
public class XmlMapperParser {

    private final Configuration configuration;
    private String namespace;

    public XmlMapperParser(Configuration configuration) {
        this.configuration = configuration;
    }


    public void parse(Document document) {
        // <mapper>
        Element rootElement = document.getRootElement();
        namespace = rootElement.attributeValue("namespace");

        // 将 select|insert|update|delete 标签解析为 MappedStatement 对象
        // 并将解析的 MappedStatement 放入 configuration 的 map 中
        parseStatements(rootElement.elements("select"));
        parseStatements(rootElement.elements("insert"));
        parseStatements(rootElement.elements("update"));
        parseStatements(rootElement.elements("delete"));
    }

    private void parseStatements(List<Element> elements) {
        for (Element element : elements) {
            parseStatement(element);
        }
    }

    private void parseStatement(Element element) {
        // statement ID
        String id = element.attributeValue("id");
        String statementId = namespace + "." + id;
        // 参数类型
        String parameterType = element.attributeValue("parameterType");
        Class<?> parameterTypeClass = getTypeClass(parameterType);
        // 映射结果类型
        String resultType = element.attributeValue("resultType");
        Class<?> resultTypeClass = getTypeClass(resultType);
        String statementType = element.attributeValue("statementType");

        // 把未处理的、还包含#{id}占位符的sql语句
        // 创建 SqlSource 对象，提供获取sql语句和sql语句中参数的功能
        // 获取的SQL语句：select * from user where id = #{id}
        // 需要的SQL语句：select * from user where id = ?
        String sqlText = element.getTextTrim();
        SqlSource sqlSource = new SimpleSqlSource(sqlText);

        // 封装 MappedStatement 对象
        // 可以使用构建者模式去创建 MappedStatement 对象
        MappedStatement mappedStatement = new MappedStatement(statementId, parameterTypeClass, resultTypeClass,
                statementType, sqlSource);
        configuration.addMappedStatement(statementId, mappedStatement);
    }

    private Class<?> getTypeClass(String className) {
        if (className == null || "".equals(className)) {
            return null;
        }
        try {
            Class<?> aClass = Class.forName(className);
            return aClass;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
