package de.emir.rcp.parts.vesseleditor.utils;

import de.emir.rcp.parts.vesseleditor.view.parts.VesselEditorPart;
import de.emir.tuml.ucore.runtime.resources.ResourceManager;
import de.emir.tuml.ucore.runtime.utils.XMLReader;
import org.w3c.dom.Node;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class PredefinedGeometryItem {
    private static HashMap<String, PredefinedGeometryItem> sPredefinedItems = null;
    public String name;
    public String wktTop;
    public String wktSide;
    public String wktFront;

    public PredefinedGeometryItem(XMLReader r, Node n) {
        name = r.getAttributeValueAsString(n, "name");
        if (r.getChild(n, "TOP") != null)
            wktTop = r.getValue(r.getChild(n, "TOP"));
        if (r.getChild(n, "SIDE") != null)
            wktSide = r.getValue(r.getChild(n, "SIDE"));
        if (r.getChild(n, "FRONT") != null)
            wktFront = r.getValue(r.getChild(n, "FRONT"));
    }

    /**
     * Predefined geometry items for ship shapes
     *
     * @return
     */
    public static Map<String, PredefinedGeometryItem> getPredefinedGeometryItems() {
        if (sPredefinedItems == null) {
            try {
                sPredefinedItems = new HashMap<>();
                XMLReader r = new XMLReader();
                r.parseStream(ResourceManager.get(VesselEditorPart.class).getInputStream("PredefinedVesselGeometries.xml"));
                Collection<Node> vesselNodes = r.getAllNodes(r.getRootNode(), "Vessel");
                for (Node n : vesselNodes) {
                    PredefinedGeometryItem pgi = new PredefinedGeometryItem(r, n);
                    sPredefinedItems.put(pgi.name, pgi);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sPredefinedItems;
    }

    @Override
    public String toString() {
        return name;
    }
}
