package de.emir.rcp.keybindings.ep;

import de.emir.model.universal.plugincore.var.AbstractKeyBinding;
import de.emir.model.universal.plugincore.var.EditorKeyBinding;
import de.emir.model.universal.plugincore.var.GlobalKeyBinding;
import de.emir.model.universal.plugincore.var.ViewKeyBinding;
import de.emir.model.universal.plugincore.var.impl.EditorKeyBindingImpl;
import de.emir.model.universal.plugincore.var.impl.GlobalKeyBindingImpl;
import de.emir.model.universal.plugincore.var.impl.ViewKeyBindingImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import de.emir.rcp.commands.ep.CommandDescriptor;
import de.emir.rcp.commands.ep.ICommandDescriptor;
import de.emir.rcp.editors.ep.Editor;
import de.emir.rcp.editors.ep.IEditor;
import de.emir.rcp.ep.ExtensionPointUtil;
import de.emir.rcp.manager.KeyBindingManager;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.views.ep.IViewDescriptor;
import de.emir.rcp.views.ep.ViewDescriptor;
import de.emir.tuml.ucore.runtime.extension.IExtensionPoint;

/**
 * KeyBindings can be registered via this ExtensionPoint. A distinction is made between editor, view and global
 * contexts. Global KeyBindings are active regardless of the focused UI component, Editor and View KeyBindings are only
 * active in the corresponding components.
 * 
 * @author fklein
 *
 */
public class KeyBindingExtensionPoint implements IExtensionPoint {

    private Map<String, List<AbstractKeyBinding>> bindings = new HashMap<>();

    public KeyBindingExtensionPoint() {

    }

    public KeyBindingExtensionPoint(Map<String, List<AbstractKeyBinding>> bindings) {
        this.bindings = bindings;
    }

    public void inView(String viewID, String key, ICommandDescriptor cmd) {
        inView(viewID, key, cmd.getId());
    }

    public void inView(IViewDescriptor view, String key, ICommandDescriptor cmd) {
        inView(view, key, cmd.getId());
    }

    public void inView(IViewDescriptor view, String key, String commandID) {

        ViewDescriptor v = (ViewDescriptor) view;
        inView(v.getId(), key, commandID);
    }

    public void inView(String viewID, String key, String commandID) {

        ExtensionPointUtil.getCurrentlyLoadingPlugin();

        ViewKeyBinding kb = new ViewKeyBindingImpl();
        kb.setViewID(viewID);
        kb.setCommandID(commandID);
        kb.setKey(key);
        addBinding(kb);

    }

    public void inEditor(String editorID, String key, ICommandDescriptor cmd) {
        inEditor(editorID, key, cmd.getId());
    }

    public void inEditor(IEditor editor, String key, ICommandDescriptor cmd) {

        Editor e = (Editor) editor;
        inEditor(e.getId(), key, cmd.getId());
    }

    public void inEditor(IEditor editor, String key, String commandID) {

        Editor e = (Editor) editor;
        inEditor(e.getId(), key, commandID);
    }

    public void inEditor(String editorID, String key, String commandID) {

        ExtensionPointUtil.getCurrentlyLoadingPlugin();

        EditorKeyBinding kb = new EditorKeyBindingImpl();
        kb.setEditorID(editorID);
        kb.setCommandID(commandID);
        kb.setKey(key);
        addBinding(kb);

    }

    public void global(String key, ICommandDescriptor cmd) {
        global(key, cmd.getId());
    }

    public void global(String key, String commandID) {

        ExtensionPointUtil.getCurrentlyLoadingPlugin();

        GlobalKeyBinding kb = new GlobalKeyBindingImpl();
        kb.setCommandID(commandID);
        kb.setKey(key);
        addBinding(kb);

    }

    private void addBinding(AbstractKeyBinding binding) {

        KeyBindingManager kbm = PlatformUtil.getKeyBindingManager();

        // harmonize
        String key = kbm.getUnformatedStringFrom(kbm.getKeyStrokeFrom(binding.getKey()));
        binding.setKey(key);

        List<AbstractKeyBinding> bindingList = bindings.get(binding.getKey());

        if (bindingList == null) {
            bindingList = new ArrayList<>();
            bindings.put(binding.getKey(), bindingList);
        }

        bindingList.add(binding);

    }

    public List<GlobalKeyBinding> getGlobalBindings() {

        List<GlobalKeyBinding> result = new ArrayList<>();

        bindings.values().forEach(list -> result.addAll(list.stream().filter(gb -> gb instanceof GlobalKeyBinding)
                .map(gb -> (GlobalKeyBinding) gb).collect(Collectors.toList())));

        return result;

    }

    public List<ViewKeyBinding> getViewBindings(String viewID) {

        List<ViewKeyBinding> result = new ArrayList<>();

        bindings.values()
                .forEach(list -> result
                        .addAll(list.stream().filter(vb -> vb instanceof ViewKeyBinding).map(vb -> (ViewKeyBinding) vb)
                                .filter(vb -> vb.getViewID().equals(viewID)).collect(Collectors.toList())));

        return result;

    }

    public List<EditorKeyBinding> getEditorBindings(String editorID) {

        List<EditorKeyBinding> result = new ArrayList<>();

        bindings.values()
                .forEach(list -> result.addAll(
                        list.stream().filter(eb -> eb instanceof EditorKeyBinding).map(eb -> (EditorKeyBinding) eb)
                                .filter(eb -> eb.getEditorID().equals(editorID)).collect(Collectors.toList())));

        return result;

    }

    public Set<String> getBindedKeys() {
        return bindings.keySet();
    }

    public List<AbstractKeyBinding> getBindingsOfKey(String key) {
        return bindings.get(key);
    }

    public List<AbstractKeyBinding> getBindingsOfCommand(CommandDescriptor cmd) {

        List<AbstractKeyBinding> result = new ArrayList<>();

        bindings.values().forEach(list -> result
                .addAll(list.stream().filter(b -> b.getCommandID().equals(cmd.getId())).collect(Collectors.toList())));

        return result;
    }

    public Map<String, List<AbstractKeyBinding>> getBindings() {
        return bindings;
    }

}
