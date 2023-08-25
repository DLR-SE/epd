package de.emir.tuml.ucore.runtime;

/**
 * @generated
 */
public enum NotificationType {
    /**
     * @generated
     */
    SET(0, "SET", "SET"),

    /**
     * @generated
     */
    UNSET(1, "UNSET", "UNSET"),

    /**
     * @generated
     */
    ADD(2, "ADD", "ADD"),

    /**
     * @generated
     */
    ADD_MANY(3, "ADD_MANY", "ADD_MANY"),

    /**
     * @generated
     */
    REMOVE(4, "REMOVE", "REMOVE"),

    /**
     * @generated
     */
    REMOVE_MANY(5, "REMOVE_MANY", "REMOVE_MANY")

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

    private static final NotificationType[] VALUE_TYPES = new NotificationType[] { SET, UNSET, ADD, ADD_MANY, REMOVE,
            REMOVE_MANY, };

    private NotificationType(int value, String name, String label) {
        mValue = value;
        mName = name;
        if (label == null || label.isEmpty())
            mLabel = mName;
        else
            mLabel = label;
    }

    public static NotificationType get(String name) {
        for (int i = 0; i < VALUE_TYPES.length; ++i) {
            if (VALUE_TYPES[i].mName.equals(name))
                return VALUE_TYPES[i];
        }
        return null;
    }

    public static NotificationType getByLabel(String label) {
        for (int i = 0; i < VALUE_TYPES.length; ++i) {
            if (VALUE_TYPES[i].mLabel.equals(label))
                return VALUE_TYPES[i];
        }
        return null;
    }

    public static NotificationType get(int value) {
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
