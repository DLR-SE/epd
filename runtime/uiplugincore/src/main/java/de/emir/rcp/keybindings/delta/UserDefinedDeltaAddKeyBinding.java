package de.emir.rcp.keybindings.delta;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import de.emir.rcp.keybindings.ep.AbstractKeyBinding;

public class UserDefinedDeltaAddKeyBinding implements IUserDefinedDelta {
    private AbstractKeyBinding newBinding;

    public UserDefinedDeltaAddKeyBinding(AbstractKeyBinding newBinding) {
        this.newBinding = newBinding;
    }

    public AbstractKeyBinding getNewBinding() {
        return newBinding;
    }

    @Override
    public void apply(Map<String, List<AbstractKeyBinding>> bindings) {

        String key = newBinding.getKey();

        List<AbstractKeyBinding> bindingList = bindings.get(key);

        if (bindingList == null) {
            bindingList = new ArrayList<>();
            bindings.put(key, bindingList);
        }

        bindingList.add(newBinding.copy());

    }
}
