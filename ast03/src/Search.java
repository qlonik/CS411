public class Search {
    public Search() {

    }

    public int ABSearch(Tree root) {
        int val = maxValue(root, Integer.MIN_VALUE, Integer.MAX_VALUE);

        return val;
    }

    private int maxValue(Tree state, Integer alpha, Integer beta) {
        if (terminalTest(state)) { return utility(state); }

        Integer val = Integer.MIN_VALUE;
        for (Tree t : state.getChildren()) {
            val = Math.max(val, minValue(t, alpha, beta));
            if (val >= beta) { return val; }
            alpha = Math.max(alpha, val);
        }
        return val;
    }

    private int minValue(Tree state, Integer alpha, Integer beta) {
        if (terminalTest(state)) { return utility(state); }

        Integer val = Integer.MAX_VALUE;
        for (Tree t : state.getChildren()) {
            val = Math.min(val, maxValue(t, alpha, beta));
            if (val <= alpha) { return val; }
            beta = Math.min(beta, val);
        }
        return val;
    }

    private boolean terminalTest(Tree state) {
        return !(state.hasChildren());
    }

    private int utility(Tree state) {
        return state.getData();
    }
}
