package de.emir.rcp.keybindings.delta;

import java.util.List;
import java.util.Map;

import de.emir.rcp.keybindings.ep.AbstractKeyBinding;

public interface IUserDefinedDelta {
    public void apply(Map<String, List<AbstractKeyBinding>> bindings);
}
