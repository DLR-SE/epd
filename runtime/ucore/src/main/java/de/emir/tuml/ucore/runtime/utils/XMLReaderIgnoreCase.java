package de.emir.tuml.ucore.runtime.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLReaderIgnoreCase extends XMLReader{

    public XMLReaderIgnoreCase() {
    	super();
    }

    public XMLReaderIgnoreCase(final Document d) {
        super(d);
    }

    public Collection<Node> getChildNodes(final Node n, final String tag) {
        final NodeList nl = n.getChildNodes();
        final ArrayList<Node> out = new ArrayList<Node>();
        for (int i = 0; i < nl.getLength(); i++)
            if (nl.item(i).getNodeName().equalsIgnoreCase(tag))
                out.add(nl.item(i));
        return out;
    }

    public Collection<Node> getAllNodes(final Node node, final String tag) {
        final ArrayList<Node> out = new ArrayList<Node>();
        final NodeList nl = findNodes(node, tag);
        for (int i = 0; i < nl.getLength(); i++) {
            if (nl.item(i).getNodeName().equalsIgnoreCase(tag))
                out.add(nl.item(i));
        }
        return out;
    }

    /**
     * returns the first sub note with the given tag
     * 
     * @param n
     * @param tag
     * @return
     */
    public Node getChild(final Node n, final String tag, boolean search) {
        NodeList nl = n.getChildNodes();
        for (int i = 0; i < nl.getLength(); i++) {
            if (nl.item(i).getNodeName().equalsIgnoreCase(tag))
                return nl.item(i);
        }
        if (search) {
            nl = findNodes(n, tag);
            if (nl.getLength() > 0)
                return nl.item(0);
        }
        return null;
    }

    /**
     * returns the first sub note with the given tag
     * 
     * @param n
     * @param tag
     * @return
     */
    public Node getChild(final Node n, final String tag) {
        return getChild(n, tag, true);
    }

    public Collection<Node> getChildNodes(final Node src, final String tagName, final String keyAttr,
            final String keyAttrValue) {
        final Collection<Node> tag_childs = getChildNodes(src, tagName);
        final ArrayList<Node> out = new ArrayList<Node>();
        for (final Node n : tag_childs) {
            final String kav = getAttributeValue(n, keyAttr);
            if (kav != null && kav.equalsIgnoreCase(keyAttrValue))
                out.add(n);
        }
        return out;
    }
}
