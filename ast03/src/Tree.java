import java.lang.*;
import java.util.*;

public class Tree {
    public static final int CHILDREN = 3;

    private int data;
    private String alias;
    private Tree parent;
    private ArrayList<Tree> children;

    public Tree() {
        data = 0;
        alias = "";
        parent = null;
        children = new ArrayList<>(CHILDREN);
    }

    public Tree(Tree parent, int data) {
        this();
        this.parent = parent;
        this.data = data;
    }

    public Tree setParent(Tree parent) {
        this.parent = parent;
        return this;
    }

    public Tree setData(int data) {
        this.data = data;
        return this;
    }

    public Tree setAlias(String alias) {
        this.alias = alias;
        return this;
    }

    public Tree getParent() {
        return parent;
    }

    public int getData() {
        return data;
    }

    public String getAlias() {
        return alias;
    }

    public Tree addChildren(List<Integer> children) {
        if (children.size() > CHILDREN) {
            throw new IndexOutOfBoundsException();
        }
        for (int n : children) {
            this.children.add(new Tree(this, n));
        }
        return this;
    }

    public Tree getChild(Integer index) {
        return this.children.get(index);
    }

    public ArrayList<Tree> getChildren() {
        return this.children;
    }

    public boolean hasChildren() {
        return this.children.size() > 0;
    }

    @Override
    public String toString() {
        String r = "";

        r += data;
        if (this.hasChildren()) {
            r += " " + this.getChild(0) + " " + this.getChild(1) + " " + this.getChild(2) + " ";
        }

        return r;
    }

    public Tree populateTree(Scanner file) {
        String line = file.nextLine();
        ArrayList<String> nums = new ArrayList<>(Arrays.asList(line.split(", ")));
        Long deep = Math.round((Math.log(nums.size()) / Math.log(CHILDREN)));

        this.addChildren(Arrays.asList(0, 0, 0));
        for (int i = 0; i < CHILDREN; i++) {
            ArrayList<Integer> nodes = new ArrayList<>();
            for (int j = 0; j < CHILDREN; j++) {
                nodes.add(Integer.parseInt(nums.get(CHILDREN * i + j)));
            }
            this.getChild(i).addChildren(nodes);
        }

        return this;
    }

    public void giveAliases() {
        String aliases = "abcdefghijklmnopqrstuvwxyz";
        int i = 0;
        List<Tree> queue = new ArrayList<>();
        queue.add(this);

        while (queue.size() > 0) {
            Tree current = queue.remove(0).setAlias("" + aliases.charAt(i));
            i++;

            for (Tree t : current.getChildren()) {
                queue.add(t);
            }
        }
    }

    public String getAction() {
        String action = "";
        Tree parent = this.getParent();
        if (parent != null) {
            action += parent.getAlias();

            List<Tree> children = parent.getChildren();
            int i = 0;
            while (i < children.size() && !this.equals(children.get(i))) {
                i++;
            }

            action += (i + 1);
        }
        return action;
    }
}
