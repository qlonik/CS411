import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Puzzle {
    // setting up data
    private Tile[] p, save;
    private int zeroPos;
    private boolean manhattanHeuristic;

    // setting up node
    private Puzzle parent;
    private int costSoFar, costToFinish;

    public Puzzle() {
        // setting up data
        p = new Tile[9];
        for (int i = 0; i < p.length; i++) {
            p[i] = new Tile(i, i);
        }
        this.zeroPos = 0;
        this.manhattanHeuristic = true;

        // setting up node
        this.parent = null;
        this.costSoFar = 0;
        this.costToFinish = calcCostToFinish();
    }

    public Puzzle(Tile[] p) {
        // setting up data
        this.p = new Tile[9];
        System.arraycopy(p, 0, this.p, 0, this.p.length);
        // find zero tile
        for (int i = 0; this.p[i].getValue() != 0 && i < this.p.length; i++)
            this.zeroPos = i + 1;
        this.manhattanHeuristic = true;

        // setting up node
        this.parent = null;
        this.costSoFar = 0;
        this.costToFinish = calcCostToFinish();
    }

    public Puzzle(Puzzle puzzle) {
        // setting up data
        this.p = new Tile[9];
        System.arraycopy(puzzle.getPuzzle(), 0, this.p, 0, this.p.length);
        this.zeroPos = puzzle.getZeroPos();
        this.manhattanHeuristic = puzzle.isManhattanHeuristic();

        // setting up node
        this.parent = puzzle;
        this.costSoFar = puzzle.getCostSoFar();
        this.costToFinish = calcCostToFinish();
    }

    private static direction whereMoved(Puzzle before, Puzzle after) {
        int pos_zero_before = before.getZeroPos(),
                pos_zero_after = after.getZeroPos();
//
//        Tile[] t_before = before.getPuzzle(), t_after = after.getPuzzle();
//
//        for (int i = 0; i < t_before.length; i++) {
//            if (i == pos_zero_before || i == pos_zero_after) {
//                if (!t_before[pos_zero_before].equals(t_after[pos_zero_after]) &&
//                        !t_before[pos_zero_after].equals(t_after[pos_zero_before])) {
//                    return null;
//                }
//            } else {
//                if (!t_before[i].equals(t_after[i])) {
//                    return null;
//                }
//            }
//        }

        int row_before = pos_zero_before / 3,
                col_before = pos_zero_before % 3,
                row_after = pos_zero_after / 3,
                col_after = pos_zero_after % 3;

        if (row_before == row_after) {
            if (col_before < col_after) {
                return direction.RIGHT;
            } else if (col_before > col_after) {
                return direction.LEFT;
            }
        } else if (col_before == col_after) {
            if (row_before < row_after) {
                return direction.DOWN;
            } else if (row_before > row_after) {
                return direction.UP;
            }
        }

        return null;
    }

    private static String convert(direction d) {
        switch (d) {
            case UP:
                return "UP";
            case DOWN:
                return "DOWN";
            case LEFT:
                return "LEFT";
            case RIGHT:
                return "RIGHT";
            default:
                return "";
        }
    }

    public static ArrayList<String> getDirections(ArrayList<Puzzle> path) {
        ArrayList<String> directions = new ArrayList<>();

        for (int i = 0; i < path.size() - 1; i++) {
            Puzzle p1 = path.get(i), p2 = path.get(i + 1);
            directions.add(convert(whereMoved(p1, p2)));
        }

        return directions;
    }

    public Puzzle getParent() {
        return parent;
    }

    public Tile[] getPuzzle() {
        return p;
    }

    public int getZeroPos() {
        return zeroPos;
    }

    public int getCostSoFar() {
        return costSoFar;
    }

    public int getCostToFinish() {
        return costToFinish;
    }

    public boolean isManhattanHeuristic() {
        return manhattanHeuristic;
    }

    public void setManhattanHeuristic(boolean b) {
        this.manhattanHeuristic = b;
    }

    public void setMisplacedHeuristic(boolean b) {
        this.manhattanHeuristic = !b;
    }

    private int misplacedTilesHeuristic() {
        int result = 0;

        for (Tile t : this.p) {
            if (t.getValue() != 0 && t.isNotInPosition()) {
                result++;
            }
        }

        return result;
    }

    private int manhattanNumberHeuristic() {
        int result = 0;

        for (Tile tmp : this.p) {
            if (tmp.getValue() != 0) {
                result += tmp.getManhattan();
            }
        }

        return result;
    }

    private int calcCostToFinish() {
        if (this.manhattanHeuristic) {
            return this.manhattanNumberHeuristic();
        } else {
            return this.misplacedTilesHeuristic();
        }
    }

    private void swapTiles(int pos1, int pos2) {
        if ((pos1 != pos2) &&
                (0 <= pos1 && pos1 <= 8) &&
                (0 <= pos2 && pos2 <= 8)) {
            Tile tmp = this.p[pos1];
            this.p[pos1] = this.p[pos2];
            this.p[pos2] = tmp;

            this.p[pos1].setPosition(pos1);
            this.p[pos2].setPosition(pos2);

            if (this.p[pos1].getValue() == 0) {
                this.zeroPos = pos1;
            } else if (this.p[pos2].getValue() == 0) {
                this.zeroPos = pos2;
            }

            this.costToFinish = calcCostToFinish();
            this.costSoFar++;
        }
    }

    public ArrayList<Puzzle> getPath() {
        Puzzle curr = this;
        ArrayList<Puzzle> path = new ArrayList<>();

        while (curr != null) {
            path.add(curr);
            curr = curr.getParent();
        }
        Collections.reverse(path);

        return path;
    }

    public boolean canMoveUp() {
        return (this.zeroPos / 3 - 1 >= 0);
    }

    public boolean canMoveDown() {
        return (this.zeroPos / 3 + 1 <= 2);
    }

    public boolean canMoveLeft() {
        return (this.zeroPos % 3 - 1 >= 0);
    }

    public boolean canMoveRight() {
        return (this.zeroPos % 3 + 1 <= 2);
    }

    public void shuffle(int n) {
        Random rnd = new Random();
        if (n < 0) {
            // create absolutely random puzzle
            // it might be unsolvable
            for (int i = 0; i < Math.abs(n); i++) {
                swapTiles(rnd.nextInt(9), rnd.nextInt(9));
            }
        } else {
            // create puzzle by randomly moving tile 0
            // this should always create solvable puzzle
            for (int i = 0; i < n; i++) {
                int r = rnd.nextInt(4);
                switch (r) {
                    case 0:
                        this.moveUp();
                        break;
                    case 1:
                        this.moveDown();
                        break;
                    case 2:
                        this.moveLeft();
                        break;
                    case 3:
                        this.moveRight();
                        break;
                }
            }

        }
        this.costSoFar = 0;
    }

    public void moveUp() {
        if (canMoveUp()) {
            int swapWith = this.zeroPos - 3;
            swapTiles(this.zeroPos, swapWith);
        }
    }

    public void moveDown() {
        if (canMoveDown()) {
            int swapWith = this.zeroPos + 3;
            swapTiles(this.zeroPos, swapWith);
        }
    }

    public void moveLeft() {
        if (canMoveLeft()) {
            int swapWith = this.zeroPos - 1;
            swapTiles(this.zeroPos, swapWith);
        }
    }

    public void moveRight() {
        if (canMoveRight()) {
            int swapWith = this.zeroPos + 1;
            swapTiles(this.zeroPos, swapWith);
        }
    }

    public void saveState() {
        if (this.save == null) {
            this.save = new Tile[9];
        }
        System.arraycopy(this.p, 0, this.save, 0, this.p.length);
    }

    public void restoreState() {
        System.arraycopy(this.save, 0, this.p, 0, this.save.length);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != this.getClass()) return false;

        boolean eq = true;

        Tile[] objPuz = ((Puzzle) obj).getPuzzle();
        for (int i = 0; i < this.p.length && eq; i++) {
            if (!this.p[i].equals(objPuz[i])) eq = false;
        }

        return eq;
    }

    @Override
    public String toString() {
        String result = "";

        for (int i = 0; i < p.length; i++) {
            result += p[i] + " ";
            if (i % 3 == 2 && i != 8) {
                result += "\n";
            }
        }

        return result;
    }

    private enum direction {
        UP, DOWN, LEFT, RIGHT
    }
}
