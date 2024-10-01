package de.emir.rcp.settings.basics.keybindings;

import de.emir.model.universal.plugincore.var.AbstractKeyBinding;
import de.emir.model.universal.plugincore.var.IUserDefinedDelta;
import de.emir.model.universal.plugincore.var.UserDefinedDeltaChangeKeyBinding;
import de.emir.model.universal.plugincore.var.impl.UserDefinedDeltaAddKeyBindingImpl;
import de.emir.model.universal.plugincore.var.impl.UserDefinedDeltaChangeKeyBindingImpl;
import de.emir.model.universal.plugincore.var.impl.UserDefinedDeltaDeleteKeyBindingImpl;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.tree.DefaultMutableTreeNode;

import de.emir.rcp.commands.ep.CommandDescriptor;
import de.emir.rcp.manager.CommandManager;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.settings.AbstractSettingsPage;
import de.emir.rcp.ui.widgets.JTreeWithFilterAndDetailsArea;
import de.emir.runtime.plugin.AbstractUIPlugin;
import de.emir.tuml.ucore.runtime.extension.ServiceManager;

public class KeyBindingSettingsPage extends AbstractSettingsPage {

    private List<KeyBindingData> model = new ArrayList<>();

    private DefaultMutableTreeNode root;

    private List<IUserDefinedDelta> deltas = new ArrayList<>();

    /**
     * @wbp.parser.entryPoint
     */
    @Override
    public Component fillPage() {

        JPanel panel = new JPanel();

        GridBagLayout gbl_panel = new GridBagLayout();

        gbl_panel.columnWeights = new double[] { 1.0 };
        gbl_panel.rowWeights = new double[] { 0.0, 1.0 };
        panel.setLayout(gbl_panel);

        JLabel lblCommands = new JLabel(
                "<html>Choose a command to assign shortcuts to it. This assignment can be global or referring to a view/editor.</html>");
        lblCommands.setFont(new Font("Tahoma", Font.PLAIN, 11));
        GridBagConstraints gbc_lblCommands = new GridBagConstraints();
        gbc_lblCommands.fill = GridBagConstraints.HORIZONTAL;
        gbc_lblCommands.insets = new Insets(0, 5, 5, 0);
        gbc_lblCommands.gridx = 0;
        gbc_lblCommands.gridy = 0;
        panel.add(lblCommands, gbc_lblCommands);

        JTreeWithFilterAndDetailsArea tree = new JTreeWithFilterAndDetailsArea();
        GridBagConstraints gbc_treeWithFilterAndDetailsArea = new GridBagConstraints();
        gbc_treeWithFilterAndDetailsArea.fill = GridBagConstraints.BOTH;
        gbc_treeWithFilterAndDetailsArea.gridx = 0;
        gbc_treeWithFilterAndDetailsArea.gridy = 1;
        panel.add(tree, gbc_treeWithFilterAndDetailsArea);

        tree.setCellRenderer(new PluginCommandTreeCellRenderer());

        createModel();

        tree.setRootNode(root);
        tree.setTreeWidth(200);

        tree.setFilterMatcher(new KeyBindingFilterMatcher());

        tree.setDetailsAreaProvider(new KeyBindingDetailsAreaProvider());

        return panel;
    }

    private void createModel() {
        CommandManager cm = ServiceManager.get(CommandManager.class);
        Map<AbstractUIPlugin, List<CommandDescriptor>> cmds = cm.getCommandsGroupedByPlugin();

        root = new DefaultMutableTreeNode();

        for (Entry<AbstractUIPlugin, List<CommandDescriptor>> entry : cmds.entrySet()) {
            AbstractUIPlugin plugin = entry.getKey();
            List<CommandDescriptor> commandsOfPlugin = entry.getValue();

            DefaultMutableTreeNode pluginNode = new DefaultMutableTreeNode(plugin);

            for (CommandDescriptor cmd : commandsOfPlugin) {

                List<AbstractKeyBinding> bindings = PlatformUtil.getKeyBindingManager().getBindingsOfCommand(cmd);

                List<AbstractKeyBinding> copiedBindings = new ArrayList<>();

                for (AbstractKeyBinding b : bindings) {
                    AbstractKeyBinding copiedB = b.copy();
                    copiedBindings.add(copiedB);
                }

                KeyBindingData kbd = new KeyBindingData(cmd, copiedBindings);
                model.add(kbd);

                DefaultMutableTreeNode cmdNode = new DefaultMutableTreeNode(kbd);
                pluginNode.add(cmdNode);
            }

            root.add(pluginNode);

        }

    }

    @Override
    public boolean isDirty() {

        for (KeyBindingData kbd : model) {
            if (kbd.isDirty()) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void finish() {

        KeyBindingsUtil.toMap(model);

        PlatformUtil.getKeyBindingManager().addDeltas(deltas);

    }

    protected class KeyBindingData {
        private CommandDescriptor cmd;
        private List<AbstractKeyBinding> bindings;

        private boolean dirty = false;

        public KeyBindingData(CommandDescriptor cmd, List<AbstractKeyBinding> bindings) {
            this.cmd = cmd;
            this.bindings = bindings;
        }

        public void addBinding(AbstractKeyBinding b) {
            bindings.add(b);

            deltas.add(new UserDefinedDeltaAddKeyBindingImpl(b));

            dirty = true;
        }

        public void removeBinding(AbstractKeyBinding b) {
            bindings.remove(b);

            deltas.add(new UserDefinedDeltaDeleteKeyBindingImpl(b));

            dirty = true;
        }

        public void replaceBinding(AbstractKeyBinding oldB, AbstractKeyBinding newB) {
            int oldIndex = bindings.indexOf(oldB);
            bindings.remove(oldIndex);
            bindings.add(oldIndex, newB);

            deltas.add(new UserDefinedDeltaChangeKeyBindingImpl(oldB, newB));

            dirty = true;
        }

        public void setKey(AbstractKeyBinding b, String key) {

            AbstractKeyBinding oldBinding = b.copy();
            b.setKey(key);
            AbstractKeyBinding newBinding = b.copy();

            deltas.add(new UserDefinedDeltaChangeKeyBindingImpl(oldBinding, newBinding));

            dirty = true;
        }

        public boolean isDirty() {
            return dirty;
        }

        public List<AbstractKeyBinding> getBindings() {
            return Collections.unmodifiableList(bindings);
        }

        public CommandDescriptor getCommand() {
            return cmd;
        }
    }

}
