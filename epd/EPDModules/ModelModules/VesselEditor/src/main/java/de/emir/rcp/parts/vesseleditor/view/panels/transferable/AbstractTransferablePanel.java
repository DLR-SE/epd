package de.emir.rcp.parts.vesseleditor.view.panels.transferable;

import de.emir.model.universal.physics.ObjectSurfaceInformation;
import de.emir.model.universal.spatial.Coordinate;
import de.emir.model.universal.spatial.impl.CoordinateImpl;
import de.emir.model.universal.units.impl.EulerImpl;
import de.emir.rcp.parts.vesseleditor.provider.ITransferableProvider;
import de.emir.rcp.parts.vesseleditor.utils.View;
import de.emir.rcp.parts.vesseleditor.view.parts.AbstractPhysicalObjectPart;
import de.emir.tuml.ucore.runtime.ITreeValueChangeListener;
import de.emir.tuml.ucore.runtime.Notification;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.io.IOException;

public abstract class AbstractTransferablePanel extends JPanel {
    public static final DataFlavor GENERIC_DATA_FLAVOR = new DataFlavor(
            ITransferableProvider.class, "java/ITransferableProvider");

    protected AbstractPhysicalObjectPart editor;

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

    protected void initListeners() {
        editor.getPhysicalObject().registerTreeListener(new ITreeValueChangeListener() {
            @Override
            public void onValueChange(Notification<Object> notification) {
                Object newValue = notification.getNewValue();
                if (newValue instanceof ObjectSurfaceInformation) {
                    if (notification.getOldValue() == null) {
                        editor.getEditorPanel().setTransferHandler(new GenericImportHandler());
                    }
                }
            }
        });

        // enable drop
        if (editor.getEditorPanel() != null) {
            editor.getEditorPanel().setTransferHandler(new GenericImportHandler());
        }
    }

    protected class GenericTransferable implements Transferable {

        private ITransferableProvider mProvider;

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

        private ITransferableProvider mProvider;

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
                e.printStackTrace();
            }
            return super.importData(comp, t);
        }

        @Override
        public boolean canImport(JComponent comp, DataFlavor[] transferFlavors) {
            return super.canImport(comp, transferFlavors);
        }

    }
}
