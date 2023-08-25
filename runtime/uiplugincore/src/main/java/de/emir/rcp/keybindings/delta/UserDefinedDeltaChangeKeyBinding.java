package de.emir.rcp.keybindings.delta;

import java.util.List;
import java.util.Map;

import de.emir.rcp.keybindings.ep.AbstractKeyBinding;

public class UserDefinedDeltaChangeKeyBinding implements IUserDefinedDelta {

    private UserDefinedDeltaDeleteKeyBinding remove;
    private UserDefinedDeltaAddKeyBinding add;

    public UserDefinedDeltaChangeKeyBinding(AbstractKeyBinding oldBinding, AbstractKeyBinding newBinding) {

        remove = new UserDefinedDeltaDeleteKeyBinding(oldBinding);
        add = new UserDefinedDeltaAddKeyBinding(newBinding);

    }

    @Override
    public void apply(Map<String, List<AbstractKeyBinding>> bindings) {

        remove.apply(bindings);
        add.apply(bindings);

    }
}
