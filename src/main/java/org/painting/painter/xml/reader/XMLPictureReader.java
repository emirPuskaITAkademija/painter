package org.painting.painter.xml.reader;

import org.painting.painter.shape.PaintShape;

import java.util.List;

public interface XMLPictureReader {

    List<PaintShape> read(String filename);
}
