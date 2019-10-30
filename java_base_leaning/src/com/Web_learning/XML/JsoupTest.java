package com.Web_learning.XML;

import cn.wanghaomiao.xpath.model.JXDocument;
import cn.wanghaomiao.xpath.model.JXNode;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsoupTest {


    public static void main(String[] args) throws IOException {
        String path = JsoupTest.class.getClassLoader().getResource("start.xml").getPath();

        System.out.println(path);
        Document parse = Jsoup.parse(new File(path), "utf-8");

        JXDocument jxDocument = new JXDocument(parse);
        List<JXNode> jxNodes = jxDocument.selN("//users");

        for (JXNode jxNode :jxNodes) {
            System.out.println(jxNode);
        }
    }
}
