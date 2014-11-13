import java.util.ArrayList;

public class Node {
    private Node parent;
    private ArrayList<Node> children;

    private Integer data;

    public Node() {
        this.parent = null;
        this.children = new ArrayList<Node>();
        this.data = 0;
    }

    public Node setParent(Node parent) {
        this.parent = parent;
        return this;
    }

    public Node addChild(Node child) {
        this.children.add(child);
        return this;
    }

    public Node addData(Integer data) {
        this.data = data;
        return this;
    }
}
