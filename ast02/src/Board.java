import java.util.Random;

public class Board {
    public final static int BOARD_SIZE = 8;

    private int [] b, save;

    public Board() {
        b = new int[BOARD_SIZE];
        save = new int[BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            b[i] = 0;
            save[i] = 0;
        }
    }

    public Board(Board b) {
        this();
        System.arraycopy(b.getB(), 0, this.b, 0, BOARD_SIZE);
        System.arraycopy(b.getSave(), 0, this.save, 0, BOARD_SIZE);
    }

    public int[] getB() {
        return b;
    }

    public int[] getSave() {
        return save;
    }

    public void shuffleBoard() {
        Random rnd = new Random();

        for (int i = 0; i < BOARD_SIZE; i++) {
            b[i] = rnd.nextInt(BOARD_SIZE);
        }
    }

    public void saveState() {
        System.arraycopy(this.b, 0, this.save, 0, BOARD_SIZE);
    }

    public void restoreState() {
        System.arraycopy(this.save, 0, this.b, 0, BOARD_SIZE);
    }

    public int heuristic() {
        int result = 0;

        for (int i = 0; i < BOARD_SIZE - 1; i++) {
            int x = b[i];
            for (int j = i + 1; j < BOARD_SIZE; j++) {
                int y = b[j];
                if (x == y || (j - i) == Math.abs(x - y)) {
                    result++;
                }
            }
        }

        return result;
    }

    public void nextState(int col, int inc) {
        b[col] = (BOARD_SIZE + b[col] + inc) % BOARD_SIZE;
    }

    @Override
    public String toString() {
        String result = "";

        for (int i = BOARD_SIZE - 1; i >= 0; i--) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (i == b[j]) {
                    result += "* ";
                } else {
                    result += ". ";
                }
            }
            result += "\n";
        }

        return result;
    }
}
