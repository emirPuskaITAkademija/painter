package org.painting.painter.gui;

import org.painting.painter.gui.action.ExitActionListener;
import org.painting.painter.gui.action.OpenActionListener;
import org.painting.painter.gui.action.SaveActionListener;
import org.painting.painter.gui.paint.panel.PaintPanel;
import org.painting.painter.gui.paint.settings.PaintSettingsPanel;
import org.painting.painter.xml.reader.dom.DOMPictureReader;
import org.painting.painter.xml.reader.jaxb.JAXBPictureReader;
import org.painting.painter.xml.reader.sax.SAXPictureReader;
import org.painting.painter.xml.reader.stax.StAXPictureReader;
import org.painting.painter.xml.saver.dom.DOMPictureSaver;
import org.painting.painter.xml.saver.jaxb.JAXBPictureSaver;
import org.painting.painter.xml.saver.sax.SAXPictureSaver;
import org.painting.painter.xml.saver.stax.StAXPictureSaver;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.BorderLayout;

/**
 * {@link JFrame} -> {@link BorderLayout}
 * <p>
 * Kakva je klasa PaintWindows ?
 * <p>
 * extends JFrame
 * </p>
 *
 * <li>PaintWindow konstruktor je PRIVATE</li>
 * <li>STATIC FIELD </li>
 * <li> STATIC metoda</li>
 * <p>
 * Kako kreiramo GUI:
 * <li>1. UI kontrole</li>
 * <li>2. UI kontejnere</li>
 * <li>3. Layout Manager</li>
 */
public class PaintWindow extends JFrame {

    private static PaintWindow PAINT_WINDOW = null;

    private final PaintSettingsPanel paintSettingsPanel = new PaintSettingsPanel();
    private final PaintPanel paintPanel = new PaintPanel();

    private PaintWindow() {
        setTitle("Picture Painting");
        setSize(500, 300);

        add(paintSettingsPanel, BorderLayout.NORTH);
        add(paintPanel, BorderLayout.CENTER);

        //Menu, MenuItem
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Fajl");


        JMenuItem exitMenuItem = new JMenuItem("Izlaz");
        exitMenuItem.addActionListener(new ExitActionListener());

        JMenu saveMenu = createSaveMenu();
        fileMenu.add(saveMenu);
        JMenu openMenu = createOpenMenu();
        fileMenu.add(openMenu);
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        setJMenuBar(menuBar);

    }

    private JMenu createSaveMenu() {
        JMenu saveMenu = new JMenu("Snimi");
        //SAX
        JMenuItem saxSaveMenuItem = new JMenuItem("SAX saver");
        saxSaveMenuItem.addActionListener(new SaveActionListener(new SAXPictureSaver()));
        //DOM
        JMenuItem domSaveMenuItem = new JMenuItem("DOM saver");
        domSaveMenuItem.addActionListener(new SaveActionListener(new DOMPictureSaver()));
        //StAX
        JMenuItem staxMenuItem = new JMenuItem("StAX saver");
        staxMenuItem.addActionListener(new SaveActionListener(new StAXPictureSaver()));
        //JAXB
        JMenuItem jaxbMenuItem = new JMenuItem("JAXB saver");
        jaxbMenuItem.addActionListener(new SaveActionListener(new JAXBPictureSaver()));


        saveMenu.add(saxSaveMenuItem);
        saveMenu.add(domSaveMenuItem);
        saveMenu.add(staxMenuItem);
        saveMenu.add(jaxbMenuItem);

        return saveMenu;
    }

    private JMenu createOpenMenu() {
        JMenu openMenu = new JMenu("Open");
        //SAX
        JMenuItem saxMenuItem = new JMenuItem("SAX reader");
        saxMenuItem.addActionListener(new OpenActionListener(new SAXPictureReader()));
        openMenu.add(saxMenuItem);
        //DOM
        JMenuItem domMenuItem = new JMenuItem("DOM reader");
        domMenuItem.addActionListener(new OpenActionListener(new DOMPictureReader()));
        openMenu.add(domMenuItem);
        //StAX
        JMenuItem staxMenuItem = new JMenuItem("StAX reader");
        staxMenuItem.addActionListener(new OpenActionListener(new StAXPictureReader()));
        openMenu.add(staxMenuItem);
        //JAXB
        JMenuItem jaxbMenuItem = new JMenuItem("JAXB reader");
        jaxbMenuItem.addActionListener(new OpenActionListener(new JAXBPictureReader()));
        openMenu.add(jaxbMenuItem);
        return openMenu;
    }

    public PaintSettingsPanel getPaintSettingsPanel() {
        return paintSettingsPanel;
    }

    public PaintPanel getPaintPanel() {
        return paintPanel;
    }

    /**
     * Zašto pozivamo ovu funkciju ???
     * <p>
     * ODGOVOR: da bi se izvršavao njihov blok koda
     *
     * </p>
     * <p>
     * Šta će se desiti kada neko pozove tu funkciju i desi se to da se taj njen blok koda
     * zaista izvršava ???
     * <p>
     * ODGOVOR: Provjeravamo da li je PAINT_WINDOW null i ako jeste kreiramo ga
     * a inače vraćamo PAINT_WINDOW.
     * </p>
     *
     * @return PAINT_WINDOW
     */
    public static PaintWindow instance() {
        if (PAINT_WINDOW == null) {
            PAINT_WINDOW = new PaintWindow();
        }
        return PAINT_WINDOW;
    }
}
