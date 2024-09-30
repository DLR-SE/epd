package de.emir.rcp.settings.basics.keybindings;

import de.emir.model.universal.plugincore.var.AbstractKeyBinding;
import de.emir.rcp.editors.ep.IEditor;
import de.emir.rcp.ui.widgets.ITreeSelectionValidator;
import de.emir.rcp.views.ep.IViewDescriptor;

public class ContextSelectionValidator implements ITreeSelectionValidator {

    @Override
    public boolean isValid(Object selection) {

        if (selection == null) {
            return false;
        }

        if (selection instanceof AbstractKeyBinding) {
            return true;
        }

        if (selection instanceof IViewDescriptor) {
            return true;
        }

        if (selection instanceof IEditor) {
            return true;
        }

        return false;
    }

}
