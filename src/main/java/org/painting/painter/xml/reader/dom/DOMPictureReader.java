package org.painting.painter.xml.reader.dom;

import org.painting.painter.shape.EllipseShape;
import org.painting.painter.shape.PaintShape;
import org.painting.painter.shape.RectangleShape;
import org.painting.painter.xml.reader.XMLPictureReader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * <li>{@link DocumentBuilderFactory}</li>
 * <li>{@link DocumentBuilder}</li>
 * <li>{@link Document}</li>
 */
public class DOMPictureReader implements XMLPictureReader {

    @Override
    public List<PaintShape> read(String filename) {
        List<PaintShape> paintShapes = new ArrayList<>();
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(filename);
            //shapes
            Element documentElement = document.getDocumentElement();
            NodeList nodeList = documentElement.getElementsByTagName("shape");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element shapeElement = (Element) node;
                    Element xElement = (Element) shapeElement.getElementsByTagName("x").item(0);
                    Element yElement = (Element) shapeElement.getElementsByTagName("y").item(0);
                    Element colorElement = (Element) shapeElement.getElementsByTagName("color").item(0);
                    Element shapeTypeElement = (Element) shapeElement.getElementsByTagName("type").item(0);
                    PaintShape paintShape;
                    int x = Integer.parseInt(xElement.getTextContent());
                    int y = Integer.parseInt(yElement.getTextContent());
                    Color color = colorElement.getTextContent().equalsIgnoreCase("PLAVA") ?
                            Color.BLUE : Color.RED;
                    if (shapeTypeElement.getTextContent().equalsIgnoreCase("KRUG")) {
                        paintShape = new EllipseShape(x, y, color);
                    }else{
                        paintShape = new RectangleShape(x, y, color);
                    }
                    paintShapes.add(paintShape);
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return paintShapes;
    }
}
