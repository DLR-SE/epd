package de.emir.runtime.plugin;

import javax.swing.JFrame;

import de.emir.tuml.ucore.runtime.UCorePlugin;

public abstract class AbstractUIPlugin implements UCorePlugin {

    @Override
    public void initializePlugin() {

    }

    public void registerExtensionPoints() {

    }

    public void addExtensions() {

    }

    public void preWindowOpen() {

    }

    public void onWindowCreate(JFrame window) {

    }

    public void postWindowOpen() {

    }

    public void postAddExtensions() {

    }

    /**
     * Called before the application is to be closed. At this point PreferenceStore and windows are still available
     */
    public void preApplicationClose() {

    }

    /**
     * Called right before the application will be closed (System.exit) at this point, there are no more framework
     * instances (e.g. Managers) available
     */
    public void postApplicationClose() {

    }

}
