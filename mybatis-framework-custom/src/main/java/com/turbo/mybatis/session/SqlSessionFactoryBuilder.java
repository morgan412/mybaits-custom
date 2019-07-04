package com.turbo.mybatis.session;

import com.turbo.mybatis.config.Configuration;
import com.turbo.mybatis.config.XmlConfigParser;
import com.turbo.mybatis.util.DocumentReader;
import org.dom4j.Document;

import java.io.InputStream;

/**
 * @author zouxq
 */
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(InputStream inputStream) {
        // 通过 InputStream 流对象，使用 dom4j 创建 Document 对象
        Document document = DocumentReader.createDocument(inputStream);
        // 解析全局配置文件，封装为 Configuration 对象
        // XMLConfigParser -解析全局配置文件
        // XMLMapperParser -解析映射配置文件
        XmlConfigParser parser = new XmlConfigParser();
        Configuration configuration = parser.parse(document);
        return build(configuration);
    }

    /**
     * 构建 SqlSessionFactory
     * @param configuration 全局配置信息
     * @return SqlSessionFactory
     */
    private SqlSessionFactory build(Configuration configuration) {
        return new DefaultSqlSessionFactory(configuration);
    }
}
