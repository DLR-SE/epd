/**
 * Based on Block: http://www.javaspecialists.co.za/archive/Issue145.html
 */
package de.emir.ui.utils.tscb;

public enum TristateState {
    SELECTED {
        public TristateState next() {
            return INDETERMINATE;
        }
    },
    INDETERMINATE {
        public TristateState next() {
            return DESELECTED;
        }
    },
    DESELECTED {
        public TristateState next() {
            return SELECTED;
        }
    };

    public abstract TristateState next();
}
