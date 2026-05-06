package de.emir.rcp.layout;

import de.emir.tuml.ucore.runtime.resources.IconManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Wrapper class for layout tabs. Allows the incorporation of editing buttons inside a JToggleButton.
 */
public class LayoutTab extends JToggleButton {

    private static final int ICON_SIZE = 28;
    private static final int ICON_GAP = 6;
    private static final int RIGHT_PADDING = 8;
    private static final Color CONTRAST_COLOR = Color.WHITE;

    private final Rectangle editBox = new Rectangle();
    private final Rectangle deleteBox = new Rectangle();
    private boolean editFocused = false;
    private boolean deleteFocused = false;
    private boolean unlocked = true;
    private final List<ActionTabListener> listeners = new ArrayList<>();
    private static final Icon deleteIcon = IconManager.getIcon(LayoutTab.class, "icons/emiricons/windowcontrols/32/close.png");
    private static final Icon editIcon = IconManager.getIcon(LayoutTab.class, "icons/emiricons/windowcontrols/32/edit_no_box_shadow.png");

    /**
     * Creates a new LayoutTab button.
     *
     * @param text Label of the button.
     */
    public LayoutTab(String text) {
        super(text);
        setHorizontalAlignment(LEFT);
        setFocusPainted(false);
        setContentAreaFilled(true);
        setBorderPainted(true);
        setRolloverEnabled(true);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        addMouseListener(new MouseAdapter() {
            /**
             * {@inheritDoc}
             *
             * @param e
             */
            @Override
            public void mouseExited(MouseEvent e) {
                editFocused = false;
                deleteFocused = false;
                setCursor(Cursor.getDefaultCursor());
                repaint();
            }
        });
    }

    /**
     * Sets the unlocked status of the LayoutTab button. This allows editing of the button and allows operations
     * using a integrated delete and edit button.
     *
     * @param unlocked True if edit and delete buttons should be activated.
     */
    public void setUnlocked(boolean unlocked) {
        if (this.unlocked == unlocked) return;
        this.unlocked = unlocked;
        revalidate();
        repaint();
    }

    /**
     * Adds an ActionTabListener to the button. This is fired upon delete/edit button events.
     *
     * @param l Listener to add.
     */
    public void addActionTabListener(ActionTabListener l) {
        listeners.add(l);
    }

    /**
     * Removes an ActionTabListener from the button.
     *
     * @param l Listener to remove.
     */
    public void removeActionTabListener(ActionTabListener l) {
        listeners.remove(l);
    }

    /**
     * Paints the button. Override in order to inject custom nested delete and edit buttons.
     *
     * @param g Graphics to paint button to.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        calculateButtonSizes();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        paintActionButton(g2, editBox, editFocused, resolveHighlightColor());
        paintActionButton(g2, deleteBox, deleteFocused, resolveHighlightColor());

        g2.dispose();
    }

    /**
     * Calculates the sizes for the delete and edit button.
     */
    private void calculateButtonSizes() {
        int h = getHeight();
        int w = getWidth();
        int y = (h - ICON_SIZE) / 2;
        int deleteX = w - RIGHT_PADDING - ICON_SIZE;
        int editX = deleteX - ICON_GAP - ICON_SIZE;
        editBox.setBounds(editX, y, ICON_SIZE, ICON_SIZE);
        deleteBox.setBounds(deleteX, y, ICON_SIZE, ICON_SIZE);
    }

    /**
     * Paints a single nested action button.
     *
     * @param g2             Graphics context.
     * @param bounds         Bounding box of the button.
     * @param focused        Whether the button is currently hovered.
     * @param highlightColor Background color used when the button is focused.
     */
    private void paintActionButton(Graphics2D g2, Rectangle bounds, boolean focused,
                                   Color highlightColor) {
        if (unlocked) {
            if (focused) {
                g2.setColor(highlightColor);
                g2.fillRoundRect(bounds.x - 2, bounds.y - 2,
                        bounds.width + 4, bounds.height + 4, 6, 6);
            }

            Icon icon = (bounds == editBox) ? editIcon : deleteIcon;
            int dx = bounds.x + (bounds.width - icon.getIconWidth()) / 2;
            int dy = bounds.y + (bounds.height - icon.getIconHeight()) / 2;
            if (focused) {
                Graphics2D iconG = (Graphics2D) g2.create();
                iconG.setColor(CONTRAST_COLOR);
                icon.paintIcon(this, iconG, dx, dy);
                iconG.dispose();
            } else {
                icon.paintIcon(this, g2, dx, dy);
            }
        }
    }

    /**
     * Gets the insets of the button. These specify the size of the button which depends on if the edit and delete
     * buttons are currently visible.
     *
     * @return Insets of the button.
     */
    @Override
    public Insets getInsets() {
        Insets insets = super.getInsets();
        if (unlocked) {
            insets.right += (ICON_SIZE * 2) + ICON_GAP + RIGHT_PADDING;
        }
        return insets;
    }

    /**
     * Highlights the buttons and changes the cursor when the mouse is hovering over a nested button.
     *
     * @param e Event to process.
     */
    @Override
    protected void processMouseMotionEvent(MouseEvent e) {
        boolean oldEditFocused = editFocused;
        boolean oldDeleteFocused = deleteFocused;
        editFocused = unlocked && editBox.contains(e.getPoint());
        deleteFocused = unlocked && deleteBox.contains(e.getPoint());
        if (editFocused || deleteFocused) {
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        } else {
            setCursor(Cursor.getDefaultCursor());
        }
        if (oldEditFocused != editFocused || oldDeleteFocused != deleteFocused) {
            repaint();
        }
        super.processMouseMotionEvent(e);
    }

    /**
     * Processes mouse clicks for nested buttons and the button itself. If the mouse is clicking a nested button,
     * the onEdit or onDelete listener method is fired, else it is delegated to the ToggleButton logic.
     *
     * @param e Mouse event to process.
     */
    @Override
    protected void processMouseEvent(MouseEvent e) {
        if (e.getID() == MouseEvent.MOUSE_PRESSED) {
            if (unlocked && editBox.contains(e.getPoint())) {
                fireEditEvent();
                return;
            }
            if (unlocked && deleteBox.contains(e.getPoint())) {
                fireDeleteEvent();
                return;
            }
        }
        super.processMouseEvent(e);
    }

    /**
     * Fires an edit button event for all registered listeners.
     */
    private void fireEditEvent() {
        for (ActionTabListener l : listeners) {
            l.onEdit(this);
        }
    }

    /**
     * Fires a delete button event for all registered listeners.
     */
    private void fireDeleteEvent() {
        for (ActionTabListener l : listeners) {
            l.onDelete(this);
        }
    }

    /**
     * Resolves the highlight color for a nested button depending on the current
     * selection state of the parent.
     *
     * @return Color visible against the current background.
     */
    private Color resolveHighlightColor() {
        Color c = UIManager.getColor("ToggleButton.selectedBackground");
        return isSelected() ? c.brighter() : c;
    }

    /**
     * Listener interface for nested button events.
     */
    public interface ActionTabListener {

        /**
         * Fired when the edit button of the LayoutTab is clicked.
         *
         * @param source LayoutTab from which the click is originating from.
         */
        void onEdit(LayoutTab source);

        /**
         * Fired when the delete button of the LayoutTab is clicked.
         *
         * @param source LayoutTab from which the click is originating from.
         */
        void onDelete(LayoutTab source);
    }
}
