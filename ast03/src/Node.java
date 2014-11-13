import sun.net.idn.Punycode;

import java.util.ArrayList;
import java.util.List;

public class Node<T> {
    private T data;

    private Node<T> parent;
    private List<Node<T>> children;

    public Node() {
        data = null;

        parent = null;
        children = new ArrayList<>();
    }

    public Node(T data) {
        this();
        setData(data);
    }

    public Node(Node<T> parent, T data) {
        this();
        setParent(parent);
        setData(data);
    }

    public Node<T> setParent(Node<T> parent) {
        this.parent = parent;
        return this;
    }

    public Node<T> getParent() {
        return parent;
    }

    public Node<T> setData(T data) {
        this.data = data;
        return this;
    }

    public T getData() {
        return data;
    }

    public Node<T> setChildren(List<Node<T>> children) {
        for (Node<T> child : children) {
            child.setParent(this);
        }
        this.children = children;
        return this;
    }

    public List<Node<T>> getChildren() {
        return children;
    }

    public Node<T> removeChildren() {
        this.children = new ArrayList<>();
        return this;
    }

    public Node<T> addChild(Node<T> child) {
        child.setParent(this);
        this.children.add(child);
        return this;
    }

    public Node<T> addChildAt(int i, Node<T> child) throws IndexOutOfBoundsException {
        child.setParent(this);
        this.children.add(i, child);
        return this;
    }

    public Node<T> removeChildAt(int i) throws IndexOutOfBoundsException {
        this.children.remove(i);
        return this;
    }
}
