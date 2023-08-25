package de.emir.tuml.ucore.runtime.utils.impl;

import java.util.ArrayList;
import java.util.List;

import de.emir.tuml.ucore.runtime.UClass;
import de.emir.tuml.ucore.runtime.annotations.UMLImplementation;
import de.emir.tuml.ucore.runtime.impl.UObjectImpl;
import de.emir.tuml.ucore.runtime.utils.QualifiedName;
import de.emir.tuml.ucore.runtime.utils.UtilsPackage;

/**
 * @generated
 */
@UMLImplementation(classifier = QualifiedName.class)
public class QualifiedNameImpl extends UObjectImpl implements QualifiedName {

    public static QualifiedNameImpl createWithRegEx(String str, String regEx) {
        return new QualifiedNameImpl(str.split(regEx));
    }

    /**
     * @generated
     */
    private List<String> mSegments = null;

    /**
     * Default constructor
     * 
     * @generated
     */
    public QualifiedNameImpl() {
        super();
    }

    /**
     * Default attribute constructor
     * 
     * @generated
     */
    public QualifiedNameImpl(List<String> _segments) {
        getSegments().addAll(_segments);
    }

    /**
     * @generated
     */
    public UClass getUClassifier() {
        return UtilsPackage.Literals.QualifiedName;
    }

    /**
     * Default copy constructor
     * 
     * @generated
     */
    public QualifiedNameImpl(final QualifiedName _copy) {
        getSegments().addAll(_copy.getSegments());
    }

    public QualifiedNameImpl(String... segments) {
        for (String s : segments)
            localAppendBack(s);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof QualifiedName) {
            QualifiedName qn = (QualifiedName) obj;
            if (qn.getSegments().size() != getSegments().size())
                return false;
            for (int i = 0; i < getSegments().size(); i++)
                if (qn.getSegments().get(i).equals(getSegments().get(i)) == false)
                    return false;
            return true;
        }
        return false;
    }

    /**
     * @generated not
     */
    @Override
    public List<String> getSegments() {
        if (mSegments == null) {
            mSegments = new ArrayList<>();
        }
        return mSegments;
    }

    /**
     * @inheritDoc
     * @generated not
     */
    @Override
    public String toString(final String _delimiter) {
        String str = "";
        for (String s : getSegments())
            str += s + _delimiter;
        if (numSegments() > 0)
            str = str.substring(0, str.length() - (_delimiter.length()));
        return str;
    }

    /**
     * @inheritDoc
     * @generated not
     */
    @Override
    public int numSegments() {
        return getSegments().size();
    }

    /**
     * @inheritDoc
     * @generated not
     */
    @Override
    public QualifiedName removeSegmentsFromEnd(final int _num) {
        if (getSegments().isEmpty())
            return null;
        QualifiedName qn = new QualifiedNameImpl(this);
        qn.localRemoveSegmentsFromEnd(_num);
        return qn;
    }

    /**
     * @inheritDoc
     * @generated not
     */
    @Override
    public QualifiedName removeSegmentsFromStart(final int _num) {
        if (getSegments().isEmpty())
            return null;
        QualifiedName qn = new QualifiedNameImpl(this);
        qn.localRemoveSegmentsFromStart(_num);
        return qn;
    }

    /**
     * @inheritDoc
     * @generated not
     */
    @Override
    public void localRemoveSegmentsFromEnd(final int _num) {
        for (int i = 0; i < _num; i++) {
            if (getSegments().size() > 0)
                getSegments().remove(getSegments().size() - 1);
        }
    }

    /**
     * @inheritDoc
     * @generated not
     */
    @Override
    public void localRemoveSegmentsFromStart(final int _num) {
        for (int i = 0; i < _num; i++)
            if (getSegments().size() > 0)
                getSegments().remove(0);
    }

    /**
     * @inheritDoc
     * @generated not
     */
    @Override
    public String lastSegment() {
        return getSegments().get(numSegments() - 1);
    }

    /**
     * @inheritDoc
     * @generated not
     */
    @Override
    public QualifiedName appendFront(final String _segment) {
        QualifiedName qn = new QualifiedNameImpl(this);
        qn.localAppendFront(_segment);
        return qn;

    }

    /**
     * @inheritDoc
     * @generated not
     */
    @Override
    public void localAppendFront(final String _segment) {
        mSegments.add(0, _segment);
    }

    /**
     * @inheritDoc
     * @generated not
     */
    @Override
    public QualifiedName appendBack(final String _segment) {
        QualifiedName qn = new QualifiedNameImpl(this);
        qn.localAppendBack(_segment);
        return qn;
    }

    /**
     * @inheritDoc
     * @generated not
     */
    @Override
    public boolean empty() {
        return numSegments() == 0;
    }

    /**
     * @inheritDoc
     * @generated not
     */
    @Override
    public boolean isEmpty() {
        return numSegments() == 0;
    }

    /**
     * @inheritDoc
     * @generated not
     */
    @Override
    public String segment(final int _idx) {
        return getSegments().get(_idx);
    }

    /**
     * @inheritDoc
     * @generated not
     */
    @Override
    public String firstSegment() {
        return getSegments().get(0);
    }

    /**
     * @inheritDoc
     * @generated not
     */
    @Override
    public void localAppendBack(final String _segment) {
        getSegments().add(_segment);
    }

    @Override
    public int compareTo(QualifiedName o) {
        return toString(".").compareTo(o.toString("."));
    }

    @Override
    public String toString() {
        return toString(".");
    }

}
