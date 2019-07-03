package com.turbo.mybatis.config;

import org.dom4j.Document;
import org.dom4j.Element;

import java.io.InputStream;

/**
 * @author zouxq
 */
public class XmlConfigParser {

    private final Configuration configuration;

    public XmlConfigParser() {
        this.configuration = new Configuration();
    }

    /**
     * TODO 解析 Document, 封装为 Configuration 对象
     * @param document dom4j Document 对象
     * @return Configuration 对象
     */
    public Configuration parse(Document document) {
        // TODO
        parseConfiguration(document.getRootElement());
        return configuration;
    }

    private void parseConfiguration(Element rootElement) {
    }
}
