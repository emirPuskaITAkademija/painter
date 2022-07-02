package org.painting.painter.xml.saver;

import org.painting.painter.shape.PaintShape;

import java.util.List;

@FunctionalInterface
public interface XMLPictureSaver {
    void savePicture(List<PaintShape> paintShapes, String filename);
}
