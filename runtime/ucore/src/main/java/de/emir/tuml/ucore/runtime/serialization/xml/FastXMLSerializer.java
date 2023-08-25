package de.emir.tuml.ucore.runtime.serialization.xml;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import de.emir.tuml.ucore.runtime.UClassifier;
import de.emir.tuml.ucore.runtime.UObject;
import de.emir.tuml.ucore.runtime.UStructuralFeature;
import de.emir.tuml.ucore.runtime.logging.ULog;
import de.emir.tuml.ucore.runtime.serialization.AbstractSerializer;
import de.emir.tuml.ucore.runtime.serialization.ISerializer;

public class FastXMLSerializer extends AbstractSerializer implements ISerializer {
    public static final String XMI_NS = "http://www.omg.org/XMI";
    public static final String XSI_NS = "http://www.w3.org/2001/XMLSchema-instance";

    class FXMLAttribute {
        public FXMLAttribute(String k, String v) {
            key = k;
            value = v;
        }

        String key;
        String value;
    }

    class FXMLElement {
        String mName = null;
        ArrayList<FXMLAttribute> mAttributes;
        ArrayList<FXMLElement> mChildren = null;

        protected FXMLElement() {
        }

        public FXMLElement(String name) {
            mName = name;
        }

        public void setAttribute(String k, String v) {
            if (mAttributes == null)
                mAttributes = new ArrayList<FastXMLSerializer.FXMLAttribute>();
            mAttributes.add(new FXMLAttribute(k, v));
        }

        private String mContent;

        public void appendChild(FXMLElement el) {
            if (mChildren == null)
                mChildren = new ArrayList<FXMLElement>();
            mChildren.add(el);
        }

        public void setTextContent(String string) {
            mContent = string;
        }

        void writeToStringBuffer(StringBuilder stream) {
            stream.append("<");
            stream.append(mName);
            stream.append(" ");
            if (mAttributes != null) {
                for (FXMLAttribute e : mAttributes) {
                    stream.append(e.key);
                    stream.append("=\"");
                    stream.append(e.value);
                    stream.append("\" ");
                }
            }
            if (mChildren == null && mContent == null) {
                stream.append("/>\n");
            } else {
                if (mContent != null) {
                    stream.append(">");
                    stream.append(mContent);
                } else {
                    stream.append(">\n");
                    for (FXMLElement c : mChildren) {
                        c.writeToStringBuffer(stream);
                    }
                }
                stream.append("</" + mName + ">\n");
            }
        }

    }

    class FXMLDocument extends FXMLElement {

        public FXMLElement createElement(String name) {
            return new FXMLElement(name);
        }

        @Override
        void writeToStringBuffer(StringBuilder sb) {
            sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n");
            FXMLElement fe = mChildren.get(0);
            if (mAttributes == null)
                mAttributes = new ArrayList<FastXMLSerializer.FXMLAttribute>();
            fe.mAttributes.add(0, new FXMLAttribute("xmlns:xsi", XSI_NS));
            fe.mAttributes.add(0, new FXMLAttribute("xmlns:xmi", XMI_NS));

            fe.writeToStringBuffer(sb);

        }

        void writeToStream(OutputStream stream) {
            try {
                StringBuffer sb = new StringBuffer();
                stream.write(sb.toString().getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class XMLWriter {

        private HashMap<UObject, String> mObjectIds = new HashMap<UObject, String>();

        public String createID(UObject instance) {
            String str = UUID.randomUUID().toString();
            mObjectIds.put(instance, str);
            return str;
        }

        public String getID(UObject instance) {
            return mObjectIds.get(instance);
        }

        OutputStream write(UObject instance, final OutputStream stream)
                throws ParserConfigurationException, TransformerException {
            FXMLDocument doc = new FXMLDocument();
            FXMLElement el = writeInstance(doc, instance);
            doc.appendChild(el);
            doc.writeToStream(stream);
            return stream;
        }

        String write(UObject instance) throws ParserConfigurationException, TransformerException {
            FXMLDocument doc = new FXMLDocument();
            FXMLElement el = writeInstance(doc, instance);
            doc.appendChild(el);
            StringBuilder sb = new StringBuilder();
            doc.writeToStringBuffer(sb);
            return sb.toString();
        }

        private FXMLElement writeInstance(FXMLDocument doc, UObject instance) {
            UClassifier cl = instance.getUClassifier();
            FXMLElement el = doc.createElement(cl.getName());
            el.setAttribute("xmi:id", createID(instance));

            writeAttributes(doc, el, instance, cl.getAllAttributes());
            writeReferences(doc, el, instance, cl.getAllReferences());
            return el;
        }

        private void writeReferences(FXMLDocument doc, FXMLElement el, UObject instance,
                List<UStructuralFeature> allReferences) {
            for (UStructuralFeature f : allReferences) {
                Object value = f.get(instance);
                if (value == null)
                    continue;
                if (f.isMany()) {
                    List list = (List) value;
                    for (Object v : list) {
                        if (v != null) {
                            FXMLElement child = writeSingleReference(doc, f, (UObject) v); // we now that it's an
                                                                                           // UObject, since its part of
                                                                                           // a reference
                            if (child != null)
                                el.appendChild(child);
                        }
                    }
                } else {
                    FXMLElement child = writeSingleReference(doc, f, (UObject) value);
                    if (child != null)
                        el.appendChild(child);
                }
            }
        }

        private FXMLElement writeSingleReference(FXMLDocument doc, UStructuralFeature f, UObject instance) {
            FXMLElement el = doc.createElement(f.getName());
            String id = getID(instance);
            if (id == null) {
                if (instance == null)
                    ULog.error("can not serialize null instance: " + instance + " Feature: " + f);
                UClassifier cl = instance.getUClassifier();
                if (cl != f.getType()) {
                    el.setAttribute("xsi:type", cl.getName());
                }
                el.setAttribute("xmi:id", createID(instance));
                writeAttributes(doc, el, instance, cl.getAllAttributes());
                writeReferences(doc, el, instance, cl.getAllReferences());
            } else {
                el.setAttribute("xmi:ref", id);
            }
            return el;
        }

        private void writeAttributes(FXMLDocument doc, FXMLElement el, UObject instance,
                List<UStructuralFeature> allAttributes) {
            for (UStructuralFeature attr : allAttributes) {
                Object value = attr.get(instance);
                if (value != null) { // no need to write anything, if the value is not defined
                    if (attr.isMany()) {
                        for (Object v : ((List) value)) {
                            FXMLElement list_entry = doc.createElement(attr.getName());
                            list_entry.setTextContent(v.toString());
                            el.appendChild(list_entry);
                        }
                    } else {
                        String str = value.toString();
                        el.setAttribute(attr.getName(), str);
                    }
                }
            }
        }
    }

    public String serialize(UObject instance) {
        try {
            return new XMLWriter().write(instance);
        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void serialize(UObject instance, OutputStream stream) {
        try {
            new XMLWriter().write(instance, stream);
        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public UObject deserialize(InputStream stream) {
        // try{
        // return new XMLReaderImpl().read(stream);
        // }catch(Exception e){
        // e.printStackTrace();
        // return null;
        // }
        return null;
    }

}
