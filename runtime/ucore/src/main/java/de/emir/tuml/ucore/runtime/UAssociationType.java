package de.emir.tuml.ucore.runtime;

/**
 * @generated
 */
public enum UAssociationType {
    /**
     * @generated
     */
    ASSOCIATION(0, "ASSOCIATION", "ASSOCIATION"),

    /**
     * @generated
     */
    AGGREGATION(1, "AGGREGATION", "AGGREGATION"),

    /**
     * @generated
     */
    COMPOSITION(2, "COMPOSITION", "COMPOSITION"),

    /**
     * @generated
     */
    PROPERTY(3, "PROPERTY", "PROPERTY")

    ;

    private int mValue = -1;
    private String mName = null;
    private String mLabel = null;

    public String getName() {
        return mName;
    }

    public String getLabel() {
        return mLabel;
    }

    public int getValue() {
        return mValue;
    }

    private static final UAssociationType[] VALUE_TYPES = new UAssociationType[] { ASSOCIATION, AGGREGATION,
            COMPOSITION, PROPERTY, };

    private UAssociationType(int value, String name, String label) {
        mValue = value;
        mName = name;
        if (label == null || label.isEmpty())
            mLabel = mName;
        else
            mLabel = label;
    }

    public static UAssociationType get(String name) {
        for (int i = 0; i < VALUE_TYPES.length; ++i) {
            if (VALUE_TYPES[i].mName.equals(name))
                return VALUE_TYPES[i];
        }
        return null;
    }

    public static UAssociationType getByLabel(String label) {
        for (int i = 0; i < VALUE_TYPES.length; ++i) {
            if (VALUE_TYPES[i].mLabel.equals(label))
                return VALUE_TYPES[i];
        }
        return null;
    }

    public static UAssociationType get(int value) {
        for (int i = 0; i < VALUE_TYPES.length; ++i) {
            if (VALUE_TYPES[i].mValue == value)
                return VALUE_TYPES[i];
        }
        return null;
    }

    @Override
    public String toString() {
        return mLabel;
    }
}
