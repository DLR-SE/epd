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

public class XMLReader {
    Document doc = null;

    public XMLReader() {

    }

    public XMLReader(final Document d) {
        doc = d;
    }

    public void parseFile(final File file) throws SAXException, IOException, ParserConfigurationException {
        parseFile(file.getAbsolutePath());
    }

    public void parseFile(final String filename) throws SAXException, IOException, ParserConfigurationException {
        final DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder;
        docBuilder = docBuilderFactory.newDocumentBuilder();
        doc = docBuilder.parse(new File(filename));
        doc.getDocumentElement().normalize();
    }

    public void parseStream(final InputStream xml) throws SAXException, IOException, ParserConfigurationException {
        final DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder;
        docBuilder = docBuilderFactory.newDocumentBuilder();
        doc = docBuilder.parse(xml);
        doc.getDocumentElement().normalize();
    }

    public NodeList findNodes(final String tagname) {
        return doc.getElementsByTagName(tagname);
    }

    public NamedNodeMap findAttributes(final Node n, final String tagname) {
        if (n == null)
            return null;
        final NamedNodeMap nl = n.getAttributes();
        if (nl == null)
            return null;
        nl.getNamedItem(tagname);
        return nl;
    }

    public String getAttributeValue(final Node n, final String tagname) {
        final NamedNodeMap nnm = findAttributes(n, tagname);
        if (nnm != null && nnm.getNamedItem(tagname) != null)
            return nnm.getNamedItem(tagname).getNodeValue();
        return null;
    }

    public NodeList findNodes(final Node n, final String tag) {
        final NodeList nl = n.getChildNodes();
        final Element el = (Element) nl;
        return el.getElementsByTagName(tag);
    }

    public Document getDocument() {
        return doc;
    }

    public String getValue(final Node node) {
        if (node == null || node.getChildNodes() == null || node.getChildNodes().item(0) == null)
            return null;
        return node.getChildNodes().item(0).getNodeValue();
    }

    public String searchValue(final Node n, final String tag) {
        final NodeList nl = n.getChildNodes();
        final Element el = (Element) nl;
        return this.getValue(el.getElementsByTagName(tag).item(0));
    }

    public String getAttributeValueAsString(final Node node, final String item) {
        return getValue(node.getAttributes().getNamedItem(item));
    }

    public int getAttributeValueAsInteger(final Node node, final String item) {
        try {
            return Integer.parseInt(getAttributeValueAsString(node, item));
        } catch (final Exception exp) {
            return -1;
        }
    }

    public float getAttributeValueAsFloat(final Node node, final String item) {
        try {
            return Float.parseFloat(getAttributeValueAsString(node, item));
        } catch (final Exception exp) {
            return Float.NaN;
        }
    }

    public double getAttributeValueAsDouble(final Node node, final String item) {
        try {
            return Double.parseDouble(getAttributeValueAsString(node, item));
        } catch (final Exception exp) {
            return Double.NaN;
        }
    }

    public boolean getAttributeValueAsBoolean(final Node rn, final String string) {
        return Boolean.parseBoolean(getAttributeValueAsString(rn, string));
    }

    public String getFirstValue(final NodeList nl) {
        if (nl == null || nl.getLength() == 0)
            return null;
        return getValue(nl.item(1));
    }

    public Collection<Node> getChildNodes(final Node n, final String tag) {
        final NodeList nl = n.getChildNodes();
        final ArrayList<Node> out = new ArrayList<Node>();
        for (int i = 0; i < nl.getLength(); i++)
            if (nl.item(i).getNodeName().equals(tag))
                out.add(nl.item(i));
        return out;
    }

    public Collection<Node> getAllNodes(final Node node, final String tag) {
        final ArrayList<Node> out = new ArrayList<Node>();
        final NodeList nl = findNodes(node, tag);
        for (int i = 0; i < nl.getLength(); i++) {
            if (nl.item(i).getNodeName().equals(tag))
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
            if (nl.item(i).getNodeName().equals(tag))
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
            if (kav != null && kav.equals(keyAttrValue))
                out.add(n);
        }
        return out;
    }

    public Collection<Attr> getAttributes(Node node) {
        NamedNodeMap nl = node.getAttributes();
        ArrayList<Attr> out = new ArrayList<Attr>();
        for (int i = 0; i < nl.getLength(); i++) {
            if (nl.item(i) instanceof Attr)
                out.add((Attr) nl.item(i));
        }
        return out;
    }

    public Collection<Node> getChildren(Node n) {
        final NodeList nl = n.getChildNodes();
        final ArrayList<Node> out = new ArrayList<Node>();
        for (int i = 0; i < nl.getLength(); i++)
            out.add(nl.item(i));
        return out;

    }

    public Node findNode(Node node, String tagName) {
        return findNode(node, tagName, false);
    }

    public Node findNode(Node node, String tagName, boolean recursive) {
        final NodeList nl = node.getChildNodes();
        final Element el = (Element) nl;
        NodeList elements = el.getElementsByTagName(tagName);
        if (elements != null) {
            return elements.item(0);
        }
        if (recursive) {
            // did not found, so search recursive in each subnode
            for (int i = 0; i < nl.getLength(); i++) {
                Node n = findNode(nl.item(i), tagName, recursive);
                if (n != null)
                    return n;
            }
        }
        return null;
    }

    public Node getRootNode() {
        return doc.getDocumentElement();
    }
}
