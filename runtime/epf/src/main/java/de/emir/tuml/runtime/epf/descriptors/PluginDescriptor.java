package de.emir.tuml.runtime.epf.descriptors;

import java.net.URL;

public class PluginDescriptor<VERSION extends Comparable<VERSION>> extends ClassPathDescriptor<VERSION> {

    protected String mPluginClassName;

    public PluginDescriptor(String cid, String desc, VERSION v, String pluginClassName, URL[] urls) {
        super(cid, desc, v, urls);
        mPluginClassName = pluginClassName;
    }

    public void setPluginClassName(String pcn) {
        mPluginClassName = pcn;
    }

    public String getPluginClassName() {
        return mPluginClassName;
    }
}
