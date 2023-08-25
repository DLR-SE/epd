package de.emir.rcp.parts.vesseleditor.view.parts;

import de.emir.model.universal.spatial.Geometry;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.parts.VesselEditorBasic;
import de.emir.rcp.parts.vesseleditor.utils.View;
import de.emir.rcp.parts.vesseleditor.view.geometry.AbstractGeometryPanel;
import de.emir.rcp.parts.vesseleditor.view.geometry.GeometryPanel;
import de.emir.tuml.ucore.runtime.ITreeValueChangeListener;
import de.emir.tuml.ucore.runtime.Notification;

import javax.swing.*;
import java.awt.*;

public class GeometryPart extends JPanel {
    private AbstractGeometryPanel editorPanel;
    private Geometry geometry;
    private View view;

    public GeometryPart(Geometry geometry, View view) {
        super();
        this.geometry = geometry;
        this.view = view;
        setup();
    }

    protected void setup() {
        setLayout(new BorderLayout());

        editorPanel = new GeometryPanel(getGeometry(), getView());

        add(editorPanel);

        initListeners();

        //fill extensionPoint toolbar
        JToolBar tb = new JToolBar();
        PlatformUtil.getMenuManager().fillToolbar(tb, VesselEditorBasic.TOOLBAR_ID);
        add(tb, BorderLayout.NORTH);
    }

    private void initListeners() {
//        needed because removing and adding listeners to geometries causes concurrent modification exception
        getGeometry().registerTreeListener(new ITreeValueChangeListener() {
            @Override
            public void onValueChange(Notification<Object> notification) {
                getEditorPanel().changeGeometry(getGeometry(), getView());
            }
        });
        PlatformUtil.getSelectionManager().subscribe(VesselEditorBasic.CTX_VIEW_SELECTION_ID, e -> getEditorPanel().changeGeometry(getGeometry(), getView()));
    }

    public View getView() {
        return view;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public AbstractGeometryPanel getEditorPanel() {
        return editorPanel;
    }
}
