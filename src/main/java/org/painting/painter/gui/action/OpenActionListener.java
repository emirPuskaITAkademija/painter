package org.painting.painter.gui.action;

import org.painting.painter.gui.PaintWindow;
import static org.painting.painter.gui.action.SaveActionListener.EXTENZIJA_SLIKE;
import org.painting.painter.gui.paint.panel.PaintPanel;
import org.painting.painter.shape.PaintShape;
import org.painting.painter.xml.reader.XMLPictureReader;

import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class OpenActionListener implements ActionListener {
    private final XMLPictureReader xmlPictureReader;

    public OpenActionListener(XMLPictureReader xmlPictureReader) {
        this.xmlPictureReader = xmlPictureReader;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String pictureName = JOptionPane.showInputDialog("Unesite ime slike:");
        if (pictureName == null || pictureName.isBlank()) {
            return;
        }
        if (!pictureName.endsWith(EXTENZIJA_SLIKE)) {
            pictureName = pictureName + EXTENZIJA_SLIKE;
        }
        List<PaintShape> paintShapes = xmlPictureReader.read(pictureName);
        //Ažuriramo platno za slikanje
        PaintPanel paintPanel = PaintWindow.instance().getPaintPanel();
        paintPanel.setPaintShapes(paintShapes);
        paintPanel.repaint();
    }
}
