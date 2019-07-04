package com.turbo.mybatis.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.io.InputStream;

/**
 * @author zouxq
 */
public class DocumentReader {

    /**
     * 创建 Document 对象
     * @param inputStream 输入流
     * @return Document 对象
     */
    public static Document createDocument(InputStream inputStream) {
        Document document = null;
        try {
            document = new SAXReader().read(inputStream);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return document;
    }
}
