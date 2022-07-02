package org.painting.painter.xml.saver.dom;

import org.painting.painter.shape.EllipseShape;
import org.painting.painter.shape.PaintShape;
import org.painting.painter.xml.saver.XMLPictureSaver;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.Color;
import java.io.File;
import java.util.List;

/**
 * <li>{@link DocumentBuilderFactory}</li>
 * <li>{@link DocumentBuilder}</li>
 * <li>{@link Document}</li>
 * <li>{@link Element}</li>
 * <li>{@link TransformerFactory}</li>
 * <li>{@link Transformer}</li>
 * <li>{@link DOMSource}</li>
 * <li>{@link StreamResult}</li>
 */
public class DOMPictureSaver implements XMLPictureSaver {
    @Override
    public void savePicture(List<PaintShape> paintShapes, String filename) {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            Element shapesElement = document.createElement("shapes");//<shapes></shapes>
            document.appendChild(shapesElement);
            for (PaintShape paintShape : paintShapes) {
                Element shapeElement = document.createElement("shape");//<shape></shape>
                //x
                Element xElement = document.createElement("x");//<x></x>
                xElement.setTextContent(paintShape.getX() + "");
                shapeElement.appendChild(xElement);
                //y
                Element yElement = document.createElement("y");
                yElement.setTextContent(paintShape.getY() + "");
                shapeElement.appendChild(yElement);
                //color
                Element colorElement = document.createElement("color");
                colorElement.setTextContent(paintShape.getColor().equals(Color.BLUE) ? "Plava" : "Crvena");
                shapeElement.appendChild(colorElement);
                //type
                Element typeElement = document.createElement("type");
                typeElement.setTextContent((paintShape instanceof EllipseShape) ? "Krug" : "Kvadrat");
                shapeElement.appendChild(typeElement);

                shapesElement.appendChild(shapeElement);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            //DOMSource
            DOMSource domSource = new DOMSource(document);
            //StreamResult
            StreamResult streamResult = new StreamResult(new File(filename));

            transformer.transform(domSource, streamResult);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
