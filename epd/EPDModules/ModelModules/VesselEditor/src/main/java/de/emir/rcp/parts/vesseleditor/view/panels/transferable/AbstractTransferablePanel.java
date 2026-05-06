package de.emir.rcp.parts.vesseleditor.view.panels.transferable;

import de.emir.model.universal.physics.ObjectSurfaceInformation;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.impl.CoordinateImpl;
import de.emir.model.universal.units.impl.EulerImpl;
import de.emir.rcp.parts.vesseleditor.provider.ITransferableProvider;
import de.emir.rcp.parts.vesseleditor.utils.View;
import de.emir.rcp.parts.vesseleditor.view.parts.AbstractPhysicalObjectPart;
import de.emir.tuml.ucore.runtime.IDisposable;
import de.emir.tuml.ucore.runtime.logging.ULog;
import io.reactivex.rxjava3.disposables.Disposable;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractTransferablePanel extends JPanel {
    public static final DataFlavor GENERIC_DATA_FLAVOR = new DataFlavor(
            ITransferableProvider.class, "java/ITransferableProvider"
    );

    protected AbstractPhysicalObjectPart editor;

    private final List<Disposable> registeredConsumer = new ArrayList<>();
    private final List<IDisposable> registeredTreeListeners = new ArrayList<>();

    public AbstractTransferablePanel(AbstractPhysicalObjectPart editor) {
        this.editor = editor;
    }

    public boolean placeObject(ITransferableProvider provider, Point location) {
        Coordinate geom_dep_coord = editor.getEditorPanel().mouseToCoordinate(location); //applies scale factors, for the cell
        Coordinate position = getViewDependendCoordinate(geom_dep_coord, editor.getView()); //change the coordinate depending on the view (e.g. x,y,z)
        if (provider.place(editor.getPhysicalObject(), position, new EulerImpl())) {
            editor.getEditorPanel().repaint();
            return true;
        }
        return false;
    }

    private Coordinate getViewDependendCoordinate(Coordinate c, View view) {
        switch (view) {
            case TOP: //shows x = width, y = length
                return new CoordinateImpl(c);
            case SIDE: //shows x=length, y = height
                return new CoordinateImpl(0, c.getX(), c.getY(), c.getCrs());
            case FRONT: //shows x = width, y = height
                return new CoordinateImpl(c.getX(), 0.0, c.getY(), c.getCrs());
        }
        return null;
    }

    /**
     * Informs use that this panel got added to something. Thus, we can register listeners
     * which get removed when this panel is removed (removeNotify).
     */
    @Override
    public void addNotify() {
        // check for changes in the selected geometry or view
        IDisposable disposable = editor.getPhysicalObject().registerTreeListener(notification -> {
            Object newValue = notification.getNewValue();
            if (newValue instanceof ObjectSurfaceInformation) {
                if (notification.getOldValue() == null) {
                    editor.getEditorPanel().setTransferHandler(new GenericImportHandler());
                }
            }
        });
        registeredTreeListeners.add(disposable);

        // enable drop
        if (editor.getEditorPanel() != null) {
            editor.getEditorPanel().setTransferHandler(new GenericImportHandler());
        }

        super.addNotify();
    }

    /**
     * Unregister listeners when a panel got removed
     */
    @Override
    public void removeNotify() {
        // unregister consumers
        for (Disposable disposable : registeredConsumer){
            disposable.dispose();
        }

        // unregister tree listeners
        for (IDisposable disposable : registeredTreeListeners){
            disposable.dispose();
        }

        registeredTreeListeners.clear();
        registeredConsumer.clear();

        super.removeNotify();
    }

    protected class GenericTransferable implements Transferable {

        private final ITransferableProvider mProvider;

        public GenericTransferable(ITransferableProvider prov) {
            mProvider = prov;
        }

        @Override
        public DataFlavor[] getTransferDataFlavors() {
            return new DataFlavor[]{GENERIC_DATA_FLAVOR};
        }

        @Override
        public boolean isDataFlavorSupported(DataFlavor flavor) {
            return flavor.equals(GENERIC_DATA_FLAVOR);
        }

        @Override
        public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
            return mProvider;
        }
    }

    protected class GenericExportHandler extends TransferHandler {

        private final ITransferableProvider mProvider;

        public GenericExportHandler(ITransferableProvider prov) {
            mProvider = prov;
        }

        @Override
        protected Transferable createTransferable(JComponent c) {
            return new AbstractTransferablePanel.GenericTransferable(mProvider);
        }

        @Override
        public int getSourceActions(JComponent c) {
            return DnDConstants.ACTION_COPY_OR_MOVE;
        }

    }

    protected class GenericImportHandler extends TransferHandler {

        @Override
        public boolean canImport(TransferSupport support) {
            return support.isDataFlavorSupported(GENERIC_DATA_FLAVOR);
        }

        @Override
        public boolean importData(JComponent comp, Transferable t) {
            try {
                ITransferableProvider provider = (ITransferableProvider) t.getTransferData(GENERIC_DATA_FLAVOR);
                return placeObject(provider, comp.getMousePosition());
            } catch (UnsupportedFlavorException | IOException e) {
                ULog.error(e);
            }
            return super.importData(comp, t);
        }

        @Override
        public boolean canImport(JComponent comp, DataFlavor[] transferFlavors) {
            return super.canImport(comp, transferFlavors);
        }

    }
}
