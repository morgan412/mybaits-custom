package com.turbo.mybatis.config;

import com.turbo.mybatis.util.DocumentReader;
import org.apache.commons.dbcp2.BasicDataSource;
import org.dom4j.Document;
import org.dom4j.Element;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * 全局配置文件解析器
 * @author zouxq
 */
public class XmlConfigParser {

    private final Configuration configuration;

    public XmlConfigParser() {
        this.configuration = new Configuration();
    }

    /**
     * Document, 封装为 Configuration 对象
     * @param document dom4j Document 对象
     * @return Configuration 对象
     */
    public Configuration parse(Document document) {
        // 解析 <configuration>
        parseConfiguration(document.getRootElement());
        return configuration;
    }

    private void parseConfiguration(Element rootElement) {
        // <environments>
        parseEnvironments(rootElement.element("environments"));
        // <mappers>
        parseMappers(rootElement.element("mappers"));
    }

    private void parseEnvironments(Element element) {
        // <environments default = "dev">
        String defaultId = element.attributeValue("default");
        List<Element> environmentElements = element.elements("environment");

        for (Element environmentElement : environmentElements) {
            String elementId = environmentElement.attributeValue("id");
            // 解析与默认的环境id匹配
            if (elementId != null && elementId.equals(defaultId)) {
                // <dataSource>
                parseDataSource(environmentElement.element("dataSource"));
            }
        }
    }

    private void parseDataSource(Element element) {
        String type = element.attributeValue("type");
        if (type == null || type.equals("")) {
            type = "DBCP";
        }

        List<Element> propertyElements = element.elements("property");
        Properties properties = new Properties();
        for (Element propertyElement : propertyElements) {
            String name = propertyElement.attributeValue("name");
            String value = propertyElement.attributeValue("value");
            properties.setProperty(name, value);
        }

        // 配置数据源
        BasicDataSource dataSource = null;
        if ("DBCP".equals(type)) {
            dataSource = new BasicDataSource();
            dataSource.setDriverClassName(properties.getProperty("driver"));
            dataSource.setUrl(properties.getProperty("url"));
            dataSource.setUsername(properties.getProperty("username"));
            dataSource.setPassword(properties.getProperty("password"));
        }
        configuration.setDataSource(dataSource);
    }

    private void parseMappers(Element element) {
        List<Element> mapperElements = element.elements("mapper");
        for (Element mapperElement : mapperElements) {
            parseMapper(mapperElement);
        }
    }

    private void parseMapper(Element element) {
        String resource = element.attributeValue("resource");
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(resource);
        Document document = DocumentReader.createDocument(inputStream);
        // 映射文件解析
        new XmlMapperParser(configuration).parse(document);
    }
}
