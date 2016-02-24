import java.util.ArrayList;
import java.util.List;

public class Search {
    private List<Tree> visited;

    public Search() {
        visited = new ArrayList<>();
    }

    public int ABSearch(Tree root) {
        return maxValue(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private int maxValue(Tree state, int alpha, int beta) {
        if (terminalTest(state)) {
            if (visited.indexOf(state) == -1) { visited.add(state); }
            return utility(state);
        }

        int val = Integer.MIN_VALUE;
        for (Tree t : state.getChildren()) {
            val = Math.max(val, minValue(t, alpha, beta));
            if (val >= beta) {
                state.setData(val);
                return val;
            }
            alpha = Math.max(alpha, val);
        }
        state.setData(val);
        return val;
    }

    private int minValue(Tree state, int alpha, int beta) {
        if (terminalTest(state)) {
            if (visited.indexOf(state) == -1) { visited.add(state); }
            return utility(state);
        }

        int val = Integer.MAX_VALUE;
        for (Tree t : state.getChildren()) {
            val = Math.min(val, maxValue(t, alpha, beta));
            if (val <= alpha) {
                state.setData(val);
                return val;
            }
            beta = Math.min(beta, val);
        }
        state.setData(val);
        return val;
    }

    public Tree getMaxAction(Tree state) {
        Tree max;
        if (state.hasChildren()) {
            List<Tree> children = state.getChildren();
            Tree child;
            max = children.get(0);
            for (int i = 1; i < children.size(); i++) {
                child = children.get(i);
                if (child.getData() > max.getData()) {
                    max = child;
                }
            }
        } else {
            max = state;
        }
        return max;
    }

    public Tree getMinAction(Tree state) {
        Tree min;
        if (state.hasChildren()) {
            List<Tree> children = state.getChildren();
            Tree child;
            min = children.get(0);
            for (int i = 1; i < children.size(); i++) {
                child = children.get(i);
                if (child.getData() < min.getData()) {
                    min = child;
                }
            }
        } else {
            min = state;
        }
        return min;
    }

    private boolean terminalTest(Tree state) {
        return !(state.hasChildren());
    }

    private int utility(Tree state) {
        return state.getData();
    }

    public List<Tree> getVisited() {
        return visited;
    }
}
