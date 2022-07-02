package org.painting.painter.gui.action;

import org.painting.painter.gui.paint.panel.PaintPanel;
import org.painting.painter.gui.PaintWindow;
import org.painting.painter.shape.PaintShape;
import org.painting.painter.xml.saver.XMLPictureSaver;

import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Snimiti sliku u XML fajl:
 * <li>1. SAX parser</li>
 * <li>2. DOM parsera</li>
 * <li>3. StAX parser</li>
 * <li>4. JAXB parser</li>
 */
public class SaveActionListener implements ActionListener {

    public static final String EXTENZIJA_SLIKE = ".vfs";

    private final XMLPictureSaver xmlPictureSaver;

    public SaveActionListener(XMLPictureSaver xmlPictureSaver){
        this.xmlPictureSaver = xmlPictureSaver;
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        String filename = JOptionPane.showInputDialog("Unesite molim vas ime slike:");
        if(filename == null || filename.isBlank()){
            return;
        }
        if(!filename.endsWith(EXTENZIJA_SLIKE)){
            filename = filename+ EXTENZIJA_SLIKE;
        }
        PaintWindow paintWindow = PaintWindow.instance();
        PaintPanel paintPanel = paintWindow.getPaintPanel();
        List<PaintShape> paintShapes = paintPanel.getPaintShapes();
        xmlPictureSaver.savePicture(paintShapes, filename);
        paintPanel.getPaintShapes().clear();
        paintPanel.repaint();
    }
}
