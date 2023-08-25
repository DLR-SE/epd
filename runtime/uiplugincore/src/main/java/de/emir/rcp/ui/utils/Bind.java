package de.emir.rcp.ui.utils;

import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import de.emir.rcp.commands.AbstractCommand;
import de.emir.rcp.commands.ExecuteCommandAction;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.properties.PropertyContext;
import de.emir.rcp.properties.PropertyStore;
import de.emir.tuml.ucore.runtime.prop.IProperty;
import io.reactivex.rxjava3.disposables.Disposable;

public class Bind {

    public static Disposable toCommand(JButton b, String commandID) {

        AbstractCommand cmd = PlatformUtil.getCommandManager().getCommand(commandID);
        b.addActionListener(new ExecuteCommandAction(commandID));

        b.setEnabled(cmd.canExecute());

        Observer obs = (o, arg) -> b.setEnabled(cmd.canExecute());
        cmd.addObserver(obs);

        return new Disposable() {

            private boolean diposed = false;

            @Override
            public boolean isDisposed() {

                return diposed;
            }

            @Override
            public void dispose() {

                cmd.deleteObserver(obs);
                diposed = true;

            }
        };

    }

    public static Disposable toProperty(JTextField text, String ctxID, String propertyID) {

        PropertyContext ctx = PropertyStore.getContext(ctxID);
        IProperty<String> property = ctx.getProperty(propertyID, "");

        DocumentListener listener = new DocumentListener() {

            @Override
            public void removeUpdate(DocumentEvent e) {

                property.setValue(text.getText());

            }

            @Override
            public void insertUpdate(DocumentEvent e) {

                property.setValue(text.getText());

            }

            @Override
            public void changedUpdate(DocumentEvent e) {

                property.setValue(text.getText());

            }
        };

        text.setText(property.getValue());

        text.getDocument().addDocumentListener(listener);

        return new Disposable() {

            private boolean diposed = false;

            @Override
            public boolean isDisposed() {

                return diposed;
            }

            @Override
            public void dispose() {

                text.getDocument().removeDocumentListener(listener);
                diposed = true;

            }
        };

    }

}
