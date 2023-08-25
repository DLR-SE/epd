package de.emir.rcp.manager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

import org.slf4j.Logger;

import de.emir.rcp.commands.ep.CommandDescriptor;
import de.emir.rcp.editors.AbstractEditor;
import de.emir.rcp.ids.Basic;
import de.emir.rcp.keybindings.delta.IUserDefinedDelta;
import de.emir.rcp.keybindings.ep.AbstractKeyBinding;
import de.emir.rcp.keybindings.ep.CommandActionAdapter;
import de.emir.rcp.keybindings.ep.EditorKeyBinding;
import de.emir.rcp.keybindings.ep.GlobalKeyBinding;
import de.emir.rcp.keybindings.ep.KeyBindingExtensionPoint;
import de.emir.rcp.keybindings.ep.ViewKeyBinding;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;
import de.emir.rcp.views.AbstractView;
import de.emir.tuml.ucore.runtime.extension.IService;
import de.emir.tuml.ucore.runtime.extension.ServiceManager;
import de.emir.tuml.ucore.runtime.logging.ULog;

/**
 * Manages the key bindings
 * 
 * @author fklein
 *
 */
public class KeyBindingManager implements IService {

    private static Logger log = ULog.getLogger(KeyBindingManager.class);

    private KeyBindingExtensionPoint kbEP = new KeyBindingExtensionPoint();

    private PropertyContext ctx = PropertyStore.getContext(Basic.KEY_BINDING_PROP_CTX);

    private List<IUserDefinedDelta> deltas;

    public void fillBindings() {

        deltas = ctx.getValue(Basic.KEY_BINDING_PROP, new ArrayList<>());
        applyDeltas(deltas);

    }

    public void registerCurrentBindings() {

        // global bindings
        registerGlobalKeyBindings();

        // View Bindings
        Map<String, AbstractView> views = PlatformUtil.getViewManager().getActiveViews();

        for (Entry<String, AbstractView> entry : views.entrySet()) {

            registerViewKeyBindings(entry.getValue());

        }

        // Editor Bindings
        Collection<AbstractEditor> editors = PlatformUtil.getEditorManager().getOpenEditors();
        for (AbstractEditor editor : editors) {
            registerEditorKeyBindings(editor);
        }

    }

    public List<IUserDefinedDelta> getDeltas() {
        return deltas;
    }

    private void addBinding(String key, String commandID, InputMap iMap) {

        KeyStroke ks = getKeyStrokeFrom(key);

        iMap.put(ks, commandID);

    }

    public KeyStroke getKeyStrokeFrom(String unformated) {

        String[] parts = unformated.split("\\+");

        String formatedKey = "";

        for (int i = 0; i < parts.length; i++) {
            if (parts[i].length() == 1) {
                parts[i] = parts[i].toUpperCase();
            } else {

                String lowerCase = parts[i].toLowerCase();

                if (lowerCase.equals("control") || lowerCase.equals("ctrl") || lowerCase.equals("shift")
                        || lowerCase.equals("alt")) {
                    parts[i] = lowerCase;
                }

            }

            formatedKey += parts[i] + " ";
        }

        formatedKey.trim();

        KeyStroke ks = KeyStroke.getKeyStroke(formatedKey);

        if (ks == null) {
            log.error("KeyStroke for formated string [" + formatedKey + "] not found.");
        }

        return ks;

    }

    public String getUnformatedStringFrom(KeyStroke ks) {

        String s = ks.toString();

        s = s.replaceAll("pressed ", "");
        s = s.toUpperCase();
        s = s.replaceAll(" ", "+");

        return s;

    }

    public KeyBindingExtensionPoint getKeyBindingExtensionPoint() {
        return kbEP;
    }

    private void registerBindings(InputMap iMap, ActionMap aMap, List<AbstractKeyBinding> ubs) {

        CommandManager cm = ServiceManager.get(CommandManager.class);
        for (Entry<String, CommandDescriptor> entry : cm.getCommands().entrySet()) {

            CommandDescriptor cd = entry.getValue();

            aMap.put(entry.getKey(), new CommandActionAdapter(cd.getCommand()));

        }

        for (AbstractKeyBinding b : ubs) {
            addBinding(b.getKey(), b.getCommandID(), iMap);
        }

    }

    private void registerGlobalKeyBindings() {

        JFrame mainWindow = PlatformUtil.getWindowManager().getMainWindow();

        ActionMap aMap = ((JComponent) mainWindow.getContentPane()).getActionMap();
        InputMap iMap = ((JComponent) mainWindow.getContentPane())
                .getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        aMap.clear();
        iMap.clear();

        CommandManager cm = ServiceManager.get(CommandManager.class);
        for (Entry<String, CommandDescriptor> entry : cm.getCommands().entrySet()) {

            CommandDescriptor cd = entry.getValue();

            aMap.put(entry.getKey(), new CommandActionAdapter(cd.getCommand()));

        }

        List<GlobalKeyBinding> ubs = kbEP.getGlobalBindings();
        List<AbstractKeyBinding> ubsAsAbstract = ubs.stream().map(b -> (AbstractKeyBinding) b)
                .collect(Collectors.toList());
        registerBindings(iMap, aMap, ubsAsAbstract);

    }

    public void registerViewKeyBindings(AbstractView view) {

        InputMap iMap = ((JComponent) view.getContentPane()).getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        ActionMap aMap = ((JComponent) view.getContentPane()).getActionMap();

        iMap.clear();
        aMap.clear();

        List<ViewKeyBinding> viewUBs = kbEP.getViewBindings(view.getUniqueId());
        List<AbstractKeyBinding> ubsAsAbstract = viewUBs.stream().map(b -> (AbstractKeyBinding) b)
                .collect(Collectors.toList());

        registerBindings(iMap, aMap, ubsAsAbstract);

    }

    public void registerEditorKeyBindings(AbstractEditor editor) {

        String editorID = editor.getID();

        log.trace("Registering key bindings for editor [" + editorID + " - " + editor.getTitleText() + "]");

        InputMap iMap = ((JComponent) editor.getContentPane())
                .getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        ActionMap aMap = ((JComponent) editor.getContentPane()).getActionMap();

        iMap.clear();
        aMap.clear();

        List<EditorKeyBinding> editorUBs = kbEP.getEditorBindings(editorID);

        List<AbstractKeyBinding> ubsAsAbstract = editorUBs.stream().map(b -> (AbstractKeyBinding) b)
                .collect(Collectors.toList());

        registerBindings(iMap, aMap, ubsAsAbstract);

    }

    public Set<String> getBindedKeys() {
        return kbEP.getBindedKeys();
    }

    public List<AbstractKeyBinding> getBindingsOfKey(String key) {
        return kbEP.getBindingsOfKey(key);

    }

    public List<AbstractKeyBinding> getBindingsOfCommand(CommandDescriptor cmd) {
        return kbEP.getBindingsOfCommand(cmd);
    }

    public void addDeltas(List<IUserDefinedDelta> deltasToAdd) {
        deltas.addAll(deltasToAdd);
        applyDeltas(deltasToAdd);
    }

    private void applyDeltas(List<IUserDefinedDelta> deltasToApply) {

        for (IUserDefinedDelta d : deltasToApply) {
            d.apply(kbEP.getBindings());
        }

        registerCurrentBindings();

    }

}
