package com.turbo.mybatis.session;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.io.InputStream;

/**
 * @author zouxq
 */
class DocumentReader {

    /**
     * 创建 Document 对象
     * @param inputStream 输入流
     * @return Document 对象
     */
    static Document createDoument(InputStream inputStream) {
        Document document = null;
        try {
            document = new SAXReader().read(inputStream);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return document;
    }
}
