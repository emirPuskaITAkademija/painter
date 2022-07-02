package org.painting.painter.xml.saver.sax;

import org.painting.painter.shape.EllipseShape;
import org.painting.painter.shape.PaintShape;
import org.painting.painter.xml.saver.XMLPictureSaver;

import java.awt.Color;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * <shapes>
 * <shape>
 * <x></x>
 * <y></y>
 * <color></color>
 * <type></type>
 * </shape>
 * <shape>
 * <x></x>
 * <y></y>
 * <color></color>
 * <type></type>
 * </shape>
 * </shapes>
 * <p>
 * SAX nema Java klase i funkcije koje bi nam pomogle pri snimanju.
 * <>
 * Mi zbog toga uzimamo poznate Java klase i generišemo sadržaj u fajl.
 * </>
 */
public class SAXPictureSaver implements XMLPictureSaver {

    @Override
    public void savePicture(List<PaintShape> paintShapes, String filename) {
        try (PrintWriter out = new PrintWriter(new FileWriter(filename))) {
            out.println("<?xml version=\"1.0\"?>");
            out.println("<shapes>");
            for (PaintShape paintShape : paintShapes) {
                out.println("<shape>");
                out.println("<x>" + paintShape.getX() + "</x>");
                out.println("<y>" + paintShape.getY() + "</y>");
                out.println("<color>" + (paintShape.getColor().equals(Color.BLUE) ? "Plava" : "Crvena") + "</color>");
                out.println("<type>" + (paintShape instanceof EllipseShape ? "Krug" : "Kvadrat") + "</type>");
                out.println("</shape>");
            }
            out.println("</shapes>");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
