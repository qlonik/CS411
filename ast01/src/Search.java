import java.util.ArrayList;

public class Search {

    private int numberOfCreatedNodes;

    public Search() {
        this.numberOfCreatedNodes = 0;
    }

    public int getNumberOfCreatedNodes() {
        return numberOfCreatedNodes;
    }

    public void resetCounters() {
        this.numberOfCreatedNodes = 0;
    }

    public Puzzle AStar(Puzzle init, Puzzle goal) {
        return AStar_new(init, goal);
    }

    public Puzzle AStar_old(Puzzle init, Puzzle goal) {
        this.resetCounters();

        ArrayList<Puzzle> frontier = new ArrayList<>();
        ArrayList<Puzzle> explored = new ArrayList<>();
        frontier.add(init);

        while (frontier.size() > 0) {
            Puzzle cur = getBest(frontier);
            frontier.remove(cur);
            ArrayList<Puzzle> children = expandChildren(cur);
            for (Puzzle p : children) {
                if (p.equals(goal)) {
                    return p;
                }

                int fi = frontier.indexOf(p);
                if (fi > -1) {
//                    Puzzle fp = frontier.get(fi);
//                    if (fp.getCostSoFar() + fp.getCostToFinish() < p.getCostSoFar() + p.getCostToFinish()) {
                    continue;
//                    } else {
//                        frontier.add(fp);
//                    }
                }
                int ei = explored.indexOf(p);
                if (ei > -1) {
//                    Puzzle ep = explored.get(ei);
//                    if (ep.getCostSoFar() + ep.getCostToFinish() < p.getCostSoFar() + p.getCostToFinish()) {
                    continue;
//                    } else {
//                        frontier.add(ep);
//                    }
                }
                frontier.add(p);
            }
            explored.add(cur);
        }

        return null;
    }

    public Puzzle AStar_new(Puzzle init, Puzzle goal) {
        this.resetCounters();

        ArrayList<Puzzle> frontier = new ArrayList<>();
        ArrayList<Puzzle> explored = new ArrayList<>();
        frontier.add(init);

        while (frontier.size() > 0) {
            Puzzle cur = getBest(frontier);
            frontier.remove(cur);

            if (cur.equals(goal)) {
                return cur;
            }

            if (frontier.indexOf(cur) > -1 || explored.indexOf(cur) > -1) {
                continue;
            }

            ArrayList<Puzzle> children = expandChildren(cur);
            for (Puzzle p : children) {
                frontier.add(p);
            }
            explored.add(cur);
            this.numberOfCreatedNodes++;
        }

        return null;
    }

    private Puzzle getBest(ArrayList<Puzzle> list) {
        Puzzle result = null;
        int score = Integer.MAX_VALUE;

        for (Puzzle p : list) {
            int newScore = p.getCostSoFar() + p.getCostToFinish();
            if (newScore < score) {
                result = p;
                score = newScore;
            }
        }

        return result;
    }

    private ArrayList<Puzzle> expandChildren(Puzzle parent) {
        ArrayList<Puzzle> children = new ArrayList<>();

        if (parent.canMoveUp()) {
            Puzzle child = new Puzzle(parent);
            child.moveUp();
            children.add(child);
        }
        if (parent.canMoveDown()) {
            Puzzle child = new Puzzle(parent);
            child.moveDown();
            children.add(child);
        }
        if (parent.canMoveLeft()) {
            Puzzle child = new Puzzle(parent);
            child.moveLeft();
            children.add(child);
        }
        if (parent.canMoveRight()) {
            Puzzle child = new Puzzle(parent);
            child.moveRight();
            children.add(child);
        }

        return children;
    }
}
