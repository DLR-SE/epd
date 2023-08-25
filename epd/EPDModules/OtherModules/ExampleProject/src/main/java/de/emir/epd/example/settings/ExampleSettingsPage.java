package de.emir.epd.example.settings;

import de.emir.epd.example.basic.ExampleBasic;
import de.emir.rcp.settings.AbstractSettingsPage;
import de.emir.rcp.ui.utils.properties.IPropertyWidget;
import de.emir.rcp.ui.utils.properties.PropertyCheckboxWidget;
import de.emir.rcp.ui.utils.properties.PropertySpinnerWidget;
import de.emir.rcp.ui.utils.properties.PropertyTextWidget;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Example implementation of a settings page. When the page is opened, fillPage() is called on this class which
 * create a component to be displayed. When the user closes the settings page, isDirty() is called to check whether
 * changes should be applied or not. This may prompt to the user to accept changed. finish() is finally called
 * to save settings.
 * This implementation uses Property and IPropertyWidgets as these keep track of changes. Properties are used by
 * plugins to save settings in a PropertyContext, which in turn is persisted in a properties file.
 *
 */
public class ExampleSettingsPage extends AbstractSettingsPage {

    // keep references to all editors. Necessary for reversing changes then a user cancels operation
    protected List<IPropertyWidget> widgets;

    public ExampleSettingsPage() {
        widgets = new ArrayList<>();
    }

    @Override
    public Component fillPage() {
        final JPanel panel = new JPanel();

        // Root layout is box which puts each item on a new line
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Add the header
        panel.add(new JLabel("These are the available options"));

        // Add a separator line
        panel.add(new JSeparator(SwingConstants.HORIZONTAL));

        { // add spinner
            panel.add(new JLabel("Spinner:"));
            PropertySpinnerWidget spinner = new PropertySpinnerWidget(
                    ExampleBasic.EXAMPLE_PROPERTY_CONTEXT,
                    ExampleBasic.EXAMPLE_SPINNER_PROPERTY,
                    5
            );
            panel.add(spinner);
            widgets.add(spinner);

            // Add a separator line
            panel.add(new JSeparator(SwingConstants.HORIZONTAL));
        }

        { // add checkbox (no need for a label as this is already displayed via the checkbox itself)
            PropertyCheckboxWidget checkbox = new PropertyCheckboxWidget(
                    "Checkbox:",
                    ExampleBasic.EXAMPLE_PROPERTY_CONTEXT,
                    ExampleBasic.EXAMPLE_CHECKBOX_PROPERTY,
                    false
            );
            panel.add(checkbox);
            widgets.add(checkbox);

            // Add a separator line
            panel.add(new JSeparator(SwingConstants.HORIZONTAL));
        }

        { // add text
            panel.add(new JLabel("Text:"));
            PropertyTextWidget textWidget = new PropertyTextWidget(
                    ExampleBasic.EXAMPLE_PROPERTY_CONTEXT,
                    ExampleBasic.EXAMPLE_TEXT_PROPERTY,
                    "Hello World"
            );
            panel.add(textWidget);
            widgets.add(textWidget);

            // Add a separator line
            panel.add(new JSeparator(SwingConstants.HORIZONTAL));
        }

        return panel;
    }

    @Override
    public boolean isDirty() {
        // We need to check each obj here, hence we need to keep a reference
        for (IPropertyWidget widget : widgets) {
            if (widget.isDirty()) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void finish() {
        // We need to dispose each obj, too. So take care.
        for (IPropertyWidget widget : widgets) {
           widget.finish();
        }
    }

}
