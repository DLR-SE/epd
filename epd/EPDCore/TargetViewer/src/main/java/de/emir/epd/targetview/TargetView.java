package de.emir.epd.targetview;

import de.emir.epd.targetview.panels.TargetViewerPanel;
import de.emir.rcp.views.AbstractView;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Target viewer which displays detailed target information for the selected or focused target on the map in a separate UI element.
 */
public class TargetView extends AbstractView {
    private final JScrollPane scrollPane;

    /**
     * Creates a new TargetView.
     *
     * @param id ID of the view.
     */
    public TargetView(String id) {
        super(id);
        TargetViewerPanel targetViewer = new TargetViewerPanel(createTargetTemplate());
        scrollPane = new JScrollPane(targetViewer);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    }

    /**
     * Creates a new template for the Target View. This template is the default set of attributes which are displayed
     * in the Target View panel-
     */
    private Map<String, String> createTargetTemplate() {
        Map<String, String> template = new LinkedHashMap<>();
        template.put("ID", "Unknown");
        template.put("Name", "Unknown");
        template.put("COG", "Unknown");
        template.put("SOG", "Unknown");
        template.put("Timestamp", "Unknown");
        template.put("Position", "Unknown");
        return template;
    }

    /**
     * Creates the content for the view.
     *
     * @return Content which should be displayed in the view.
     */
    @Override
    public Component createContent() {
        return scrollPane;
    }

    @Override
    public void onOpen() {

    }

    @Override
    public void onClose() {

    }
}
