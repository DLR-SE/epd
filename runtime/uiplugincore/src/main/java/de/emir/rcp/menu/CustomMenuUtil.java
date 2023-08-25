package de.emir.rcp.menu;

import javax.swing.SwingConstants;

public class CustomMenuUtil {

    public static int getOppositeOrientation(int orientation) {

        if (orientation == SwingConstants.VERTICAL) {
            return SwingConstants.HORIZONTAL;
        }

        return SwingConstants.VERTICAL;

    }

}
