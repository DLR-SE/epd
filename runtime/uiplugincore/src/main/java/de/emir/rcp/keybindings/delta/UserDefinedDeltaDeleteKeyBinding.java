package de.emir.rcp.keybindings.delta;

import java.util.List;
import java.util.Map;

import de.emir.rcp.keybindings.ep.AbstractKeyBinding;

public class UserDefinedDeltaDeleteKeyBinding implements IUserDefinedDelta {

    private AbstractKeyBinding oldBinding;

    public UserDefinedDeltaDeleteKeyBinding(AbstractKeyBinding oldBinding) {
        this.oldBinding = oldBinding;
    }

    @Override
    public void apply(Map<String, List<AbstractKeyBinding>> bindings) {

        String key = oldBinding.getKey();

        List<AbstractKeyBinding> bindingList = bindings.get(key);

        if (bindingList == null) {
            // Can't be present, nothing to delete
            return;
        }

        AbstractKeyBinding bindingToDelete = null;

        for (AbstractKeyBinding kb : bindingList) {

            if (kb.equals(oldBinding)) {
                bindingToDelete = kb;
            }

        }

        if (bindingToDelete != null) {
            bindingList.remove(bindingToDelete);
        }

    }
}
