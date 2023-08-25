package de.emir.tuml.ucore.runtime;

/**
 * @generated
 */
public enum UDirectionType {
    /**
     * @generated
     */
    IN(0, "IN", "IN"),

    /**
     * @generated
     */
    OUT(1, "OUT", "OUT"),

    /**
     * @generated
     */
    INOUT(2, "INOUT", "INOUT"),

    /**
     * @generated
     */
    RETURN(3, "RETURN", "RETURN")

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

    private static final UDirectionType[] VALUE_TYPES = new UDirectionType[] { IN, OUT, INOUT, RETURN, };

    private UDirectionType(int value, String name, String label) {
        mValue = value;
        mName = name;
        if (label == null || label.isEmpty())
            mLabel = mName;
        else
            mLabel = label;
    }

    public static UDirectionType get(String name) {
        for (int i = 0; i < VALUE_TYPES.length; ++i) {
            if (VALUE_TYPES[i].mName.equals(name))
                return VALUE_TYPES[i];
        }
        return null;
    }

    public static UDirectionType getByLabel(String label) {
        for (int i = 0; i < VALUE_TYPES.length; ++i) {
            if (VALUE_TYPES[i].mLabel.equals(label))
                return VALUE_TYPES[i];
        }
        return null;
    }

    public static UDirectionType get(int value) {
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
