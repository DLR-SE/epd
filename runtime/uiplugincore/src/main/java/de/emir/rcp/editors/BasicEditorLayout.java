package de.emir.rcp.editors;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import bibliothek.gui.dock.common.MultipleCDockableLayout;
import bibliothek.util.xml.XElement;

public class BasicEditorLayout implements MultipleCDockableLayout {
    Path mPath = null;

    public Path getPath() {
        return mPath;
    }

    public BasicEditorLayout(Path path) {
        mPath = path;
    }

    @Override
    public void writeStream(DataOutputStream out) throws IOException {
        // Not implemented
    }

    @Override
    public void readStream(DataInputStream in) throws IOException {
        // Not implemented
    }

    @Override
    public void writeXML(XElement element) {

        element.addString("path", mPath.toString());
    }

    @Override
    public void readXML(XElement element) {
        String p = element.getString("path");
        mPath = new File(p).toPath();
    }

    public void setPath(Path path) {
        mPath = path;
    }

}
