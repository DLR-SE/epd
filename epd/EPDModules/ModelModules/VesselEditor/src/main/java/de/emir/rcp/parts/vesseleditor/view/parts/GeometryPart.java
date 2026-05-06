package de.emir.rcp.parts.vesseleditor.view.parts;

import de.emir.model.universal.spatial.Geometry;
import de.emir.rcp.manager.util.PlatformUtil;
import de.emir.rcp.parts.VesselEditorBasic;
import de.emir.rcp.parts.vesseleditor.utils.View;
import de.emir.rcp.parts.vesseleditor.view.geometry.AbstractGeometryPanel;
import de.emir.rcp.parts.vesseleditor.view.geometry.GeometryPanel;
import de.emir.tuml.ucore.runtime.IDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GeometryPart extends JPanel {
    private AbstractGeometryPanel editorPanel;
    private Geometry geometry;
    private View view;

    private final List<Disposable> registeredConsumer = new ArrayList<>();
    private final List<IDisposable> registeredTreeListeners = new ArrayList<>();


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

        //fill extensionPoint toolbar
        JToolBar tb = new JToolBar();
        PlatformUtil.getMenuManager().fillToolbar(tb, VesselEditorBasic.TOOLBAR_ID);
        add(tb, BorderLayout.NORTH);
    }

    /**
     * Informs use that this panel got added to something. Thus, we can register listeners
     * which get removed when this panel is removed (removeNotify).
     */
    @Override
    public void addNotify() {
        // needed because removing and adding listeners to geometries causes concurrent modification exception
        registeredTreeListeners.add(
                getGeometry().registerTreeListener(notification -> getEditorPanel().changeGeometry(getGeometry(), getView()))
        );
        registeredConsumer.add(
                PlatformUtil.getSelectionManager().subscribe(VesselEditorBasic.CTX_VIEW_SELECTION_ID, e -> getEditorPanel().changeGeometry(getGeometry(), getView()))
        );

        super.addNotify();
    }

    /**
     * Unregister listeners when a panel got removed
     */
    @Override
    public void removeNotify() {
        for (Disposable disposable : registeredConsumer){
            disposable.dispose();
        }

        for (IDisposable disposable : registeredTreeListeners){
            disposable.dispose();
        }

        registeredTreeListeners.clear();
        registeredConsumer.clear();

        super.removeNotify();
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
