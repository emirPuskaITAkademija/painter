package org.painting.painter.gui.action;

import org.painting.painter.gui.PaintWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        PaintWindow.instance().dispose();
    }
}
