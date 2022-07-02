package org.painting.painter.gui;

import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/**
 * Ovo je klasa čija je svrha startanje Java SWING aplikacije.
 * <p></p>
 */
public class ApplicationRunner {
    public static void main(String[] args) {


        /**
         * WHY SwingUtilities.invokeLater(....) ??????
         */
        SwingUtilities.invokeLater(ApplicationRunner::showWindow);
//        showWindow();
    }

    private static void showWindow() {
        System.out.println(Thread.currentThread().getName());
        PaintWindow paintWindow = PaintWindow.instance();
        paintWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        paintWindow.setVisible(true);
    }
}
