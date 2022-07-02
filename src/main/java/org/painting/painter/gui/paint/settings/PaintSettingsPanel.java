package org.painting.painter.gui.paint.settings;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;
import java.awt.FlowLayout;

/**
 * 3 vrste:
 *
 * <li>1. Kontejner -> grupišu UI kontroler</li>
 * <li>2. Kontrole -> ubacujemo u kontejner</li>
 * <li>3. LayoutManager -> BorderLayout, FlowLayout, GridLayout, ..</li>
 *
 *
 * JPanel -> FlowLayout
 */
public class PaintSettingsPanel extends JPanel {
    private final JRadioButton circleRadioButton = new JRadioButton("Krug");
    private final JRadioButton squareRadioButton = new JRadioButton("Kvadrat");

    private final JRadioButton blueRadioButton = new JRadioButton("Plava");
    private final JRadioButton redRadioButton = new JRadioButton("Crvena");

    public PaintSettingsPanel(){
        setLayout(new FlowLayout(FlowLayout.LEFT));

        JPanel shapePanel = new JPanel();
        shapePanel.add(circleRadioButton);
        circleRadioButton.setSelected(true);
        shapePanel.add(squareRadioButton);
        TitledBorder shapeTitledBorder = new TitledBorder("Oblik");
        shapePanel.setBorder(shapeTitledBorder);
        ButtonGroup shapeButtonGroup = new ButtonGroup();
        shapeButtonGroup.add(circleRadioButton);
        shapeButtonGroup.add(squareRadioButton);

        JPanel colorPanel = new JPanel();
        colorPanel.add(blueRadioButton);
        blueRadioButton.setSelected(true);
        colorPanel.add(redRadioButton);
        TitledBorder colorTitledBorder = new TitledBorder("Boja");
        colorPanel.setBorder(colorTitledBorder);
        ButtonGroup colorButtonGroup = new ButtonGroup();
        colorButtonGroup.add(blueRadioButton);
        colorButtonGroup.add(redRadioButton);

        add(shapePanel);
        add(colorPanel);
    }

    public JRadioButton getCircleRadioButton() {
        return circleRadioButton;
    }

    public JRadioButton getSquareRadioButton() {
        return squareRadioButton;
    }

    public JRadioButton getBlueRadioButton() {
        return blueRadioButton;
    }

    public JRadioButton getRedRadioButton() {
        return redRadioButton;
    }
}
