package de.emir.epd.example.view;

import de.emir.rcp.views.AbstractView;

import javax.swing.*;
import java.awt.*;

/**
 * Simple ExampleView to display a message. Each view is registered on the Plugins main class and is opened
 * via the UI interface. createContent() is called each time the user requests to open this view. onOpen and
 * onClose are called correspondingly when this view is opened or closed.
 */
public class ExampleView extends AbstractView {

    public ExampleView(String id) {
        super(id);
    }

    @Override
    public Component createContent() {
        final JPanel panel = new JPanel();

        // Root layout is box which puts each item on a new line
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Add the header
        panel.add(new JLabel("Hello World View"));

        return panel;
    }

    @Override
    public void onOpen() {

    }

    @Override
    public void onClose() {

    }
}
