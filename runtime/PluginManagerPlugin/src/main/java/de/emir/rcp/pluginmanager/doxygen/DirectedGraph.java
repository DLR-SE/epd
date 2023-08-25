package de.emir.rcp.pluginmanager.doxygen;

import java.util.ArrayList;
import java.util.List;

public class DirectedGraph<T> {

    private T element;
    private DirectedGraph<T> parent;
    private List<DirectedGraph<T>> children;

    public DirectedGraph() {
    }

    public DirectedGraph(T element) {
        this(element, (DirectedGraph<T>) null);
    }

    public DirectedGraph(T element, List<DirectedGraph<T>> children) {
        this(element, null, children);
    }

    public DirectedGraph(T element, DirectedGraph<T> parent) {
        this(element, parent, null);
    }

    public DirectedGraph(T element, DirectedGraph<T> parent, List<DirectedGraph<T>> children) {
        this.element = element;
        this.parent = parent;
        this.children = children;
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public DirectedGraph<T> getParent() {
        return parent;
    }

    public void setParent(DirectedGraph<T> parent) {
        this.parent = parent;
    }

    public List<DirectedGraph<T>> getChildren() {
        if (children == null) {
            children = new ArrayList<>();
        }
        return children;
    }

    public void setChildren(List<DirectedGraph<T>> children) {
        this.children = children;
    }

    public void appendChild(T child) {
        appendChild(new DirectedGraph<>(child));
    }

    public void appendChild(DirectedGraph<T> child) {
        getChildren().add(child);
    }

    public boolean hasParent() {
        return parent != null;
    }

    public boolean hasChildren(){
        return getChildren().size() != 0;
    }

    public boolean isLeaf() {
        return getChildren().size() == 0;
    }
}