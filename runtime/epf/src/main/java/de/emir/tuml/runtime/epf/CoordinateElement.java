package de.emir.tuml.runtime.epf;

import java.util.HashMap;

public class CoordinateElement<VERSION extends Comparable<VERSION>> implements Comparable<CoordinateElement<VERSION>> {

    protected String mName;
    protected String mDescription;
    protected VERSION mVersion;
    private HashMap<String, String> mOptions;

    public CoordinateElement(String name, String desc, VERSION v) {
        mName = name;
        mDescription = desc;
        mVersion = v;
    }

    public String getName() {
        return mName;
    }

    public String getDescription() {
        return mDescription;
    }

    public VERSION getVersion() {
        return mVersion;
    }

    @Override
    public int compareTo(CoordinateElement<VERSION> o) {
        int c = mName.compareTo(o.mName);
        if (c == 0) {
            if (mVersion != null && o.mVersion != null) {
                return mVersion.compareTo(o.mVersion);
            }
            return 0;
        }
        return c;
    }

    public String getCoordinate() {
        return mName + ":" + (mVersion != null ? cleanVersion(mVersion) : "0.0.0");
    }
    
    public String cleanVersion(VERSION v) {
        if (v == null) return "0.0.0";
        return v.toString().replaceAll("[\\(\\)\\[\\]]", mName);
    }

    @Override
    public String toString() {
        return getCoordinate();
    }

    public void add(String key, String value) {
        if (mOptions == null) {
            mOptions = new HashMap<String, String>();
        }
        mOptions.put(key, value);
    }

    public String getOption(String key) {
        return mOptions != null ? mOptions.get(key) : null;
    }

}
