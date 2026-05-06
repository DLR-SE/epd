package de.emir.rcp.layout;

import de.emir.tuml.ucore.runtime.resources.IconManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * This is a modified and simplified version of the Netbeans ToolbarWithOverflow which can be found here
 * https://github.com/apache/netbeans/blob/4797bd4a54ed40330de8a95f933b0825849e6a84/platform/openide.awt/src/org/openide/awt/ToolbarWithOverflow.java#L52.
 * This version was modified to fix resizing issues, align with the visual representation needed for tabbed
 * layouts and keep dependencies to the openIDE framework as small as possible.
 */
public class OverflowToolbar extends JToolBar {
    private JButton overflowButton;
    private final JPopupMenu popup;
    // The OverflowToolbar contains two toolbars. The default one and an extra one for all overflow items. These
    // exchange items between each other depending on the available space.
    private final JToolBar overflowToolbar;
    private final boolean displayOverflowOnHover = true;
    private boolean updateOverflow = false;
    private static final String PROP_PREF_ICON_SIZE = "PreferredIconSize";
    private static final String PROP_DRAGGER = "_toolbar_dragger_";
    private AWTEventListener awtEventListener;
    // keep track of the overflow popup that is showing, possibly from another overflow button, in order to hide it if necessary
    private static JPopupMenu showingPopup = null;

    /**
     * Creates a new tool bar; orientation defaults to
     * <code>HORIZONTAL</code>.
     */
    public OverflowToolbar() {
        this(null, HORIZONTAL);
    }

    /**
     * Creates a new tool bar with a specified
     * <code>name</code> and
     * <code>orientation</code>. All other constructors call this constructor.
     * If
     * <code>orientation</code> is an invalid value, an exception will be
     * thrown.
     *
     * @param name        the name of the tool bar
     * @param orientation the initial orientation -- it must be     *		either <code>HORIZONTAL</code> or <code>VERTICAL</code>
     * @throws IllegalArgumentException if orientation is neither
     *                                  <code>HORIZONTAL</code> nor <code>VERTICAL</code>
     */
    public OverflowToolbar(String name, int orientation) {
        super(name, orientation);
        setupOverflowButton();
        popup = new JPopupMenu();
        popup.setBorderPainted(false);
        popup.setBorder(BorderFactory.createEmptyBorder());
        overflowToolbar = new JToolBar("overflowToolbar", orientation == HORIZONTAL ? VERTICAL : HORIZONTAL);
        overflowToolbar.setFloatable(false);
        overflowToolbar.setBorder(BorderFactory.createLineBorder(UIManager.getColor("controlShadow"), 1));
        popup.addMouseListener(new MouseAdapter() {
            /**
             * {@inheritDoc}
             *
             * @param e
             */
            @Override
            public void mouseExited(MouseEvent e) {
                popup.setVisible(false);
            }
        });
    }

    /**
     * Gets the AWT event listener used for dynamic changes to the Toolbar.
     *
     * @return AWTEventListener.
     */
    private AWTEventListener getAWTEventListener() {
        if (awtEventListener == null) {
            awtEventListener = event -> {
                MouseEvent e = (MouseEvent) event;
                if (isVisible() && !isShowing() && popup.isShowing()) {
                    showingPopup = null;
                    popup.setVisible(false);
                    return;
                }
                if (event.getSource() == popup) {
                    if (popup.isShowing() && e.getID() == MouseEvent.MOUSE_EXITED) {
                        int minX = popup.getLocationOnScreen().x;
                        int maxX = popup.getLocationOnScreen().x + popup.getWidth();
                        int minY = popup.getLocationOnScreen().y;
                        int maxY = popup.getLocationOnScreen().y + popup.getHeight();
                        if (e.getXOnScreen() < minX || e.getXOnScreen() >= maxX || e.getYOnScreen() < minY || e.getYOnScreen() >= maxY) {
                            showingPopup = null;
                            popup.setVisible(false);
                        }
                    }
                } else {
                    if (popup.isShowing() && overflowButton.isShowing() && (e.getID() == MouseEvent.MOUSE_MOVED || e.getID() == MouseEvent.MOUSE_EXITED)) {
                        int minX = overflowButton.getLocationOnScreen().x;
                        int maxX = getOrientation() == HORIZONTAL ? minX + popup.getWidth()
                                : minX + overflowButton.getWidth() + popup.getWidth();
                        int minY = overflowButton.getLocationOnScreen().y;
                        int maxY = getOrientation() == HORIZONTAL ? minY + overflowButton.getHeight() + popup.getHeight()
                                : minY + popup.getHeight();
                        if (e.getXOnScreen() < minX || e.getYOnScreen() < minY || e.getXOnScreen() > maxX || e.getYOnScreen() > maxY) {
                            showingPopup = null;
                            popup.setVisible(false);
                        }
                    }
                }


            };
        }
        return awtEventListener;
    }

    /**
     * Adds a listener for mouse events which allow dynamic resizing of the toolbar.
     */
    @Override
    public void addNotify() {
        super.addNotify();
        Toolkit.getDefaultToolkit().addAWTEventListener(getAWTEventListener(), AWTEvent.MOUSE_EVENT_MASK | AWTEvent.MOUSE_MOTION_EVENT_MASK);
        addHierarchyBoundsListener(new HierarchyBoundsAdapter() {
            @Override
            public void ancestorResized(HierarchyEvent e) {
                forceOverflowUpdate();
            }
        });
    }

    /**
     * Forces an update of the overflow popup. This is to update the available space and realign buttons.
     */
    protected void forceOverflowUpdate() {
        popup.setVisible(false);
        Container parent = getParent();
        if (parent == null) return;
        int futureWidth = getOrientation() == HORIZONTAL
                ? parent.getWidth() - parent.getInsets().left - parent.getInsets().right
                : getWidth();
        int futureHeight = getOrientation() == HORIZONTAL
                ? getHeight()
                : parent.getHeight() - parent.getInsets().top - parent.getInsets().bottom;

        SwingUtilities.invokeLater(() -> {
            int maxSize = getOrientation() == HORIZONTAL ? futureWidth : futureHeight;
            recomputeOverflow(maxSize);
            revalidate();
            repaint();
        });
    }

    /**
     * Recomputes the overflow. This computes the number of buttons which can be displayed in the normal toolbar
     * and aligns all other buttons to the overflow toolbar.
     *
     * @param maxSize Maximum size of available space.
     */
    private void recomputeOverflow(int maxSize) {
        int visibleButtons = computeVisibleButtons(maxSize);
        if (visibleButtons == -1) {
            handleOverflowRemoval();
        } else {
            handleOverflowAddition(visibleButtons);
        }
        resetSize(this);
    }

    /**
     * Removes the notify listener for dynamic resizing.
     */
    @Override
    public void removeNotify() {
        super.removeNotify();
        if (awtEventListener != null) {
            Toolkit.getDefaultToolkit().removeAWTEventListener(awtEventListener);
        }
    }

    /**
     * Calculates the size of the toolbar based on available spa
     *
     * @return Preferred size of the toolbar.
     */
    @Override
    public Dimension getPreferredSize() {
        Component[] comps = getAllComponents();
        Insets insets = getInsets();
        int width = null == insets ? 0 : insets.left + insets.right;
        int height = null == insets ? 0 : insets.top + insets.bottom;
        for (Component comp : comps) {
            if (!comp.isVisible()) {
                continue;
            }
            width += getOrientation() == HORIZONTAL ? comp.getPreferredSize().width : comp.getPreferredSize().height;
            height = Math.max(height, (getOrientation() == HORIZONTAL
                    ? (comp.getPreferredSize().height + (insets == null ? 0 : insets.top + insets.bottom))
                    : (comp.getPreferredSize().width) + (insets == null ? 0 : insets.left + insets.right)));
        }
        if (overflowToolbar.getComponentCount() > 0) {
            width += getOrientation() == HORIZONTAL ? overflowButton.getPreferredSize().width : overflowButton.getPreferredSize().height;
        }
        return getOrientation() == HORIZONTAL
                ? new Dimension(width, height)
                : new Dimension(height, width);
    }

    /**
     * Sets the orientation of the toolbar.
     *
     * @param o the new orientation -- either <code>HORIZONTAL</code> or
     *          <code>VERTICAL</code>.
     */
    @Override
    public void setOrientation(int o) {
        super.setOrientation(o);
        setupOverflowButton();
    }

    /**
     * Removes all items from the toolbar.
     */
    @Override
    public void removeAll() {
        super.removeAll();
        overflowToolbar.removeAll();
    }

    /**
     * Configures the layout for the toolbar. This sorts buttons either to the main or overflow toolbar depending
     * on available space.
     */
    @Override
    public void doLayout() {
        if (updateOverflow) {
            updateOverflow = false;
            int maxSize = getOrientation() == HORIZONTAL ? getWidth() : getHeight();
            int visibleButtons = computeVisibleButtons(maxSize);
            if (visibleButtons == -1) {
                handleOverflowRemoval();
            } else {
                handleOverflowAddition(visibleButtons);
            }
        }
        super.doLayout();
        if (getOrientation() == HORIZONTAL) {
            Insets insets = getInsets();
            int availableHeight = getHeight() - insets.top - insets.bottom;
            for (Component comp : getComponents()) {
                if (comp.isVisible() && comp != overflowButton) {
                    Rectangle bounds = comp.getBounds();
                    comp.setBounds(bounds.x, insets.top, bounds.width, availableHeight);
                }
            }
        } else {
            Insets insets = getInsets();
            int availableWidth = getWidth() - insets.left - insets.right;
            for (Component comp : getComponents()) {
                if (comp.isVisible() && comp != overflowButton) {
                    Rectangle bounds = comp.getBounds();
                    comp.setBounds(insets.left, bounds.y, availableWidth, bounds.height);
                }
            }
        }
    }

    /**
     * Invalidates the toolbar which triggers updating of the overflow bar.
     */
    @Override
    public void invalidate() {
        updateOverflow = true;
        super.invalidate();
    }

    /**
     * Sets up the overflow button and registers listeners.
     */
    private void setupOverflowButton() {
        overflowButton = new JButton(getOrientation() == HORIZONTAL
                ? IconManager.getIcon(OverflowToolbar.class, "icons/emiricons/32/arrow_right.png") : IconManager.getIcon(OverflowToolbar.class, "icons/emiricons/32/arrow_drop_down.png")) {
        };
        overflowButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (popup.isShowing()) {
                    showingPopup = null;
                    popup.setVisible(false);
                } else {
                    displayOverflow();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (showingPopup != null && showingPopup != popup) {
                    showingPopup.setVisible(false);
                    showingPopup = null;
                }
                if (displayOverflowOnHover) {
                    displayOverflow();
                }
            }
        });
    }

    /**
     * Displays the overflow toolbar inside a popup next to the mouse cursor when it is requested.
     */
    private void displayOverflow() {
        if (!overflowButton.isShowing()) {
            popup.setVisible(false);
            return;
        }
        int x = getOrientation() == HORIZONTAL ? overflowButton.getLocationOnScreen().x : overflowButton.getLocationOnScreen().x + overflowButton.getWidth();
        int y = getOrientation() == HORIZONTAL ? overflowButton.getLocationOnScreen().y + overflowButton.getHeight() : overflowButton.getLocationOnScreen().y;
        popup.setLocation(x, y);
        showingPopup = popup;
        popup.setVisible(true);
    }

    /**
     * Computes how many buttons can be visible.
     *
     * @param maxSize Maximum size of the main toolbar.
     * @return Number of buttons which can be displayed without colliding with other items.
     */
    private int computeVisibleButtons(int maxSize) {
        if (isShowing()) {
            int w = getOrientation() == HORIZONTAL ? overflowButton.getIcon().getIconWidth() + 4 : getWidth() - getInsets().left - getInsets().right;
            int h = getOrientation() == HORIZONTAL ? getHeight() - getInsets().top - getInsets().bottom : overflowButton.getIcon().getIconHeight() + 4;
            overflowButton.setMaximumSize(new Dimension(w, h));
            overflowButton.setMinimumSize(new Dimension(w, h));
            overflowButton.setPreferredSize(new Dimension(w, h));
        }
        handleIconResize();
        Component[] comps = getAllComponents();
        int sizeSoFar = 0;
        int overflowButtonSize = getOrientation() == HORIZONTAL ? overflowButton.getPreferredSize().width : overflowButton.getPreferredSize().height;
        int showingButtons = 0; // all that return true from isVisible()
        int visibleButtons = 0; // all visible that fit into the given space (maxSize)
        Insets insets = getInsets();
        if (null != insets) {
            sizeSoFar = getOrientation() == HORIZONTAL ? insets.left + insets.right : insets.top + insets.bottom;
        }
        for (Component comp : comps) {
            if (!comp.isVisible()) {
                continue;
            }
            if (showingButtons == visibleButtons) {
                int size = getOrientation() == HORIZONTAL ? comp.getPreferredSize().width : comp.getPreferredSize().height;
                if (sizeSoFar + size <= maxSize) {
                    sizeSoFar += size;
                    visibleButtons++;
                }
            }
            showingButtons++;
        }
        if (visibleButtons < showingButtons && visibleButtons > 0 && sizeSoFar + overflowButtonSize > maxSize) {
            // overflow button needed but would not have enough space, remove one more button
            visibleButtons--;
        }
        if (visibleButtons == 0 && comps.length > 0 && isDragger(comps[0])) {
            visibleButtons = 1; // always include the dragger if present
        }
        if (visibleButtons == showingButtons) {
            visibleButtons = -1;
        }
        return visibleButtons;
    }

    /**
     * Check if the component is a dragger or not.
     *
     * @param c Component to check.
     * @return True if it is a dragger.
     */
    private static boolean isDragger(Component c) {
        return c instanceof JComponent && Boolean.TRUE.equals(((JComponent) c).getClientProperty(PROP_DRAGGER));
    }

    /**
     * Handles the addition to the overflow toolbar.
     *
     * @param visibleButtons Number of buttons visible.
     */
    private void handleOverflowAddition(int visibleButtons) {
        if (overflowToolbar.getComponentCount() > 0 && visibleButtons == getComponentCount() - 1) {
            return;
        }
        Component[] comps = getAllComponents();
        removeAll();
        overflowToolbar.setOrientation(getOrientation() == HORIZONTAL ? VERTICAL : HORIZONTAL);
        popup.removeAll();

        for (Component comp : comps) {
            if (visibleButtons > 0) {
                resetSize(comp);
                add(comp);
                if (comp.isVisible()) {
                    visibleButtons--;
                }
            } else {
                if (comp instanceof JComponent jc) {
                    jc.setAlignmentX(Component.RIGHT_ALIGNMENT);
                }
                overflowToolbar.add(comp);
            }
        }
        popup.add(overflowToolbar);
        add(overflowButton);
        // Find the maximum width and height for the overflow toolbar. This specifies the size for all buttons.
        // TODO is there a more performant way than iterating thrice?
        int maxWidth = 0;
        int maxHeight = 0;
        for (Component comp : overflowToolbar.getComponents()) {
            resetSize(comp);
        }
        for (Component comp : overflowToolbar.getComponents()) {
            maxWidth = Math.max(maxWidth, comp.getPreferredSize().width);
            maxHeight = Math.max(maxHeight, comp.getPreferredSize().height);
        }
        for (Component comp : overflowToolbar.getComponents()) {
            Dimension d = new Dimension(maxWidth, maxHeight);
            comp.setPreferredSize(d);
            comp.setMinimumSize(d);
            comp.setMaximumSize(d);
        }
        resetSize(this);
        revalidate();
        repaint();
    }

    /**
     * Handles removal from the overflow toolbar.
     */
    private void handleOverflowRemoval() {
        if (overflowToolbar.getComponentCount() == 0) {
            return;
        }
        remove(overflowButton);
        handleIconResize();
        for (Component comp : overflowToolbar.getComponents()) {
            resetSize(comp);
            add(comp);
        }
        overflowToolbar.removeAll();
        popup.removeAll();
        resetSize(this);
        revalidate();
        repaint();
    }

    private void handleIconResize() {
        for (Component comp : overflowToolbar.getComponents()) {
            boolean smallToolbarIcons = getClientProperty(PROP_PREF_ICON_SIZE) == null;
            if (smallToolbarIcons) {
                ((JComponent) comp).putClientProperty(PROP_PREF_ICON_SIZE, null);
            } else {
                ((JComponent) comp).putClientProperty(PROP_PREF_ICON_SIZE, 24);
            }
        }
    }

    /**
     * Gets all registered components from both toolbars, the overflow and normal toolbar.
     *
     * @return Array of components for both toolbars.
     */
    private Component[] getAllComponents() {
        if (overflowToolbar.getComponentCount() == 0) {
            return getComponents();
        } else {
            Component[] toolbarComps;
            if (getComponentCount() > 0) {
                toolbarComps = new Component[getComponentCount() - 1];
                System.arraycopy(getComponents(), 0, toolbarComps, 0, toolbarComps.length);
            } else {
                toolbarComps = new Component[0];
            }
            Component[] overflowComps = overflowToolbar.getComponents();
            Component[] comps = new Component[toolbarComps.length + overflowComps.length];
            System.arraycopy(toolbarComps, 0, comps, 0, toolbarComps.length);
            System.arraycopy(overflowComps, 0, comps, toolbarComps.length, overflowComps.length);
            return comps;
        }
    }

    /**
     * Resets the size parameters for a given component if they have been changed.
     *
     * @param comp Component to reset.
     */
    protected void resetSize(Component comp) {
        comp.setPreferredSize(null);
        comp.setMinimumSize(null);
        comp.setMaximumSize(null);
    }
}