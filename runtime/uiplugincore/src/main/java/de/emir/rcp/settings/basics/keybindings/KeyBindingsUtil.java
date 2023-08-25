package de.emir.rcp.settings.basics.keybindings;

import java.awt.Window;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.tree.DefaultMutableTreeNode;

import de.emir.rcp.commands.ep.CommandDescriptor;
import de.emir.rcp.editors.ep.Editor;
import de.emir.rcp.keybindings.ep.AbstractKeyBinding;
import de.emir.rcp.keybindings.ep.EditorKeyBinding;
import de.emir.rcp.keybindings.ep.GlobalKeyBinding;
import de.emir.rcp.keybindings.ep.ViewKeyBinding;
import de.emir.rcp.manager.CommandManager;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.settings.basics.keybindings.KeyBindingSettingsPage.KeyBindingData;
import de.emir.rcp.ui.widgets.TreeSelectionDialog;
import de.emir.rcp.views.ep.ViewDescriptor;
import de.emir.runtime.plugin.AbstractUIPlugin;
import de.emir.tuml.ucore.runtime.extension.ServiceManager;

public class KeyBindingsUtil {

    public static Map<String, List<AbstractKeyBinding>> toMap(List<KeyBindingData> data) {

        Map<String, List<AbstractKeyBinding>> result = new HashMap<>();

        for (KeyBindingData kbd : data) {

            for (AbstractKeyBinding binding : kbd.getBindings()) {
                addBinding(result, binding);
            }

        }

        return result;
    }

    private static void addBinding(Map<String, List<AbstractKeyBinding>> bindings, AbstractKeyBinding binding) {

        List<AbstractKeyBinding> bindingList = bindings.get(binding.getKey());

        if (bindingList == null) {
            bindingList = new ArrayList<>();
            bindings.put(binding.getKey(), bindingList);
        }

        bindingList.add(binding);

    }

    public static DefaultMutableTreeNode createCommandsModel() {

        CommandManager cm = ServiceManager.get(CommandManager.class);
        Map<AbstractUIPlugin, List<CommandDescriptor>> cmds = cm.getCommandsGroupedByPlugin();

        DefaultMutableTreeNode root = new DefaultMutableTreeNode();

        for (Entry<AbstractUIPlugin, List<CommandDescriptor>> entry : cmds.entrySet()) {
            AbstractUIPlugin plugin = entry.getKey();
            List<CommandDescriptor> commandsOfPlugin = entry.getValue();

            DefaultMutableTreeNode pluginNode = new DefaultMutableTreeNode(plugin);

            for (CommandDescriptor cmd : commandsOfPlugin) {

                DefaultMutableTreeNode cmdNode = new DefaultMutableTreeNode(cmd);
                pluginNode.add(cmdNode);
            }

            root.add(pluginNode);

        }

        return root;

    }

    public static DefaultMutableTreeNode createKeysModel() {

        Set<String> keys = PlatformUtil.getKeyBindingManager().getBindedKeys();

        DefaultMutableTreeNode root = new DefaultMutableTreeNode();

        for (String key : keys) {
            DefaultMutableTreeNode keyNode = new DefaultMutableTreeNode(key);
            root.add(keyNode);
        }

        return root;

    }

    public static AbstractKeyBinding showContextDialog(Window parent) {

        TreeSelectionDialog dialog = new TreeSelectionDialog(parent);

        dialog.setModal(true);
        dialog.setInfoText(
                "<html>Select the context that specifies when the key binding is active. If the context is a view or editor, the binding only applies if the focus is on this component. A global binding is always active.</html>");
        dialog.setValidator(new ContextSelectionValidator());
        dialog.setCellRenderer(new ContextSelectionTreeCellRenderer());
        dialog.setRootNode(createContextModel());

        dialog.setVisible(true);

        Object selection = dialog.getSelection();
        if (selection == null) {
            return null;
        }

        AbstractKeyBinding kb = null;

        if (selection instanceof ViewDescriptor) {
            ViewKeyBinding vkb = new ViewKeyBinding();

            vkb.setViewID(((ViewDescriptor) selection).getId());
            kb = vkb;
        } else if (selection instanceof Editor) {
            EditorKeyBinding ekb = new EditorKeyBinding();

            ekb.setEditorID(((Editor) selection).getId());
            kb = ekb;
        } else if (selection instanceof GlobalKeyBinding) {

            kb = (AbstractKeyBinding) selection;
        }

        return kb;

    }

    private static DefaultMutableTreeNode createContextModel() {

        DefaultMutableTreeNode root = new DefaultMutableTreeNode();

        DefaultMutableTreeNode viewRoot = new DefaultMutableTreeNode("View");
        DefaultMutableTreeNode editorRoot = new DefaultMutableTreeNode("Editor");

        Map<String, ViewDescriptor> views = PlatformUtil.getViewManager().getRegisteredViews();

        for (Entry<String, ViewDescriptor> entry : views.entrySet()) {

            viewRoot.add(new DefaultMutableTreeNode(entry.getValue()));
        }

        Map<String, Editor> editors = PlatformUtil.getEditorManager().getEditorExtensionPoint().getEditors();

        for (Entry<String, Editor> entry : editors.entrySet()) {
            editorRoot.add(new DefaultMutableTreeNode(entry.getValue()));
        }

        root.add(new DefaultMutableTreeNode(new GlobalKeyBinding()));
        root.add(viewRoot);
        root.add(editorRoot);

        return root;
    }

}
