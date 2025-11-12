package de.emir.rcp.views;

import bibliothek.gui.dock.common.MultipleCDockableFactory;
import bibliothek.gui.dock.common.MultipleCDockableLayout;
import bibliothek.util.xml.XElement;
import de.emir.rcp.manager.util.PlatformUtil;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * Layout factory which handles serializing and deserializing AbstractView configurations from XML layouts.
 */
public class AbstractViewFactory implements MultipleCDockableFactory<AbstractView, AbstractViewFactory.Layout> {

    /**
     * Converts the AbstractView to a serializable Layout.
     * @param abstractView the element whose properties should be collected.
     * @return Layout object which can be serialized.
     */
    @Override
    public Layout write(AbstractView abstractView) {
        return new Layout(abstractView.getDescriptorId(), abstractView.getUniqueId());
    }

    /**
     * Reads the serializable Layout and instantiates an AbstractView.
     * @param layout the set of properties that can be used to create the new AbstractView.
     * @return New instance of AbstractView.
     */
    @Override
    public AbstractView read(Layout layout) {
        return PlatformUtil.getViewManager().createView(PlatformUtil.getViewManager().getViewDescriptor(layout.descriptorID), layout.uniqueID);
    }

    /**
     * Checks if the currently loaded Layout object belongs to the instance of AbstractView.
     * @param abstractView some element that is shown or known to the view.
     * @param layout some layout that will be applied.
     * @return True if descriptor and uniqueID are matching.
     */
    @Override
    public boolean match(AbstractView abstractView, Layout layout) {
        return abstractView.getUniqueId().equals(layout.uniqueID) && abstractView.getDescriptorId().equals(layout.descriptorID);
    }

    /**
     * Creates a new Layout if there is no AbstractView.
     * @return Randomly generated layout object.
     */
    @Override
    public Layout create() {
        // This method is usually not called and only used for deserializing.
        String uid = UUID.randomUUID().toString();
        return new Layout(uid, uid);
    }

    /**
     * Layout object which is used for serializing and deserializing AbstractView information to layout configurations.
     */
    public static class Layout implements MultipleCDockableLayout {
        private String descriptorID, uniqueID;

        /**
         * Creates an empty Layout object.
         */
        public Layout() {
            // Only used for initial deserialization by the object mapper. Variables are injected when reading the layout.
        }

        /**
         * Creates a Layout object.
         * @param descriptorID ID of the ViewDescriptor.
         * @param uniqueID Unique ID of the AbstractView to serialize/deserialize.
         */
        public Layout(String descriptorID, String uniqueID) {
            this.descriptorID = descriptorID;
            this.uniqueID = uniqueID;
        }

        /**
         * Writes the layout configuration to a DataOutputStream.
         * @param dataOutputStream the stream to write into.
         * @throws IOException If writing to stream failed.
         */
        @Override
        public void writeStream(DataOutputStream dataOutputStream) throws IOException {
            dataOutputStream.writeUTF(descriptorID != null ? descriptorID : "");
            dataOutputStream.writeUTF(uniqueID != null ? uniqueID : "");
        }

        /**
         * Reads the layout configuration from a DataInputStream.
         * @param dataInputStream the stream to read.
         * @throws IOException If reading from stream failed.
         */
        @Override
        public void readStream(DataInputStream dataInputStream) throws IOException {
            descriptorID = dataInputStream.readUTF();
            uniqueID = dataInputStream.readUTF();
        }

        /**
         * Writes a layout element to a XML element.
         * @param xElement the xml element into which this method can write,
         * the attributes of <code>element</code> should not be changed.
         */
        @Override
        public void writeXML(XElement xElement) {
            xElement.addElement("descriptorID").setString(descriptorID);
            xElement.addElement("uniqueID").setString(uniqueID);
        }

        /**
         * Reads a layout element from a XML element.
         * @param xElement the element to read.
         */
        @Override
        public void readXML(XElement xElement) {
            descriptorID = xElement.getElement("descriptorID").getString();
            uniqueID = xElement.getElement("uniqueID").getString();
        }
    }
}
