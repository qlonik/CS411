import java.util.Random;

public class Search {

    private int numberOfCreatedNodes;

    public Search() {
        this.numberOfCreatedNodes = 0;
    }

    public int getNumberOfCreatedNodes() {
        return numberOfCreatedNodes;
    }

    public Board Hill_Climb(Board b) {
        Board m, curr;
        curr = new Board(b);
        numberOfCreatedNodes = 0;
        int sideways = 100;

        while (true) {
            m = getMinSuccessor(curr);
            if (m.heuristic() > curr.heuristic()) {
                return curr;
            } else if (m.heuristic() == curr.heuristic()) {
                if (sideways == 0) {
                    return curr;
                } else {
                    sideways--;
                }
            }
            curr = m;
        }
    }

    public Board Hill_Climb_Random_Restart(Board b, int maxRestart, boolean allowSideways) {
        Board m, curr;
        curr = new Board(b);
        numberOfCreatedNodes = 0;
        int i = 0, sideways = 100;


        while (true) {
            m = getMinSuccessor(curr);
            if (!allowSideways) {
                if (m.heuristic() >= curr.heuristic()) {
                    if (i < maxRestart) {
                        curr.shuffleBoard();
                        i++;
                    } else {
                        return curr;
                    }
                } else {
                    curr = m;
                }
            } else {
                if (m.heuristic() > curr.heuristic()) {
                    if (i < maxRestart) {
                        curr.shuffleBoard();
                        sideways = 0;
                        i++;
                    } else {
                        return curr;
                    }
                } else  if (m.heuristic() == curr.heuristic()) {
                    if (sideways == 0) {
                        if (i < maxRestart) {
                            curr.shuffleBoard();
                            sideways = 0;
                            i++;
                        } else {
                            return curr;
                        }
                    } else {
                        curr = m;
                        sideways--;
                    }
                } else {
                    curr = m;
                }
            }
        }
    }

    public Board Hill_Climb_Random_Restart(Board b, int goalState, int maxRestart) {
        Board m, curr;
        curr = new Board(b);
        numberOfCreatedNodes = 0;
        int i = 0;

        while (true) {
            m = Hill_Climb(curr);
            if (m.heuristic() == goalState) {
                return m;
            } else {
                if (i < maxRestart) {
                    curr.shuffleBoard();
                    i++;
                } else {
                    return curr;
                }
            }
        }
    }

    public Board Simulated_Annealing(Board b, Schedule s) {
        Board curr = new Board(b), n;
        int t = 0, dE;
        double T, acceptedProb, prob;
        numberOfCreatedNodes = 0;

        while (true) {
            T = s.getTemp(t);
            if (T == 0) { return curr; }

            n = getRndSuccessor(curr);
            dE = curr.heuristic() - n.heuristic();

            if (dE > 0) {
                curr = n;
            } else {
                acceptedProb = Math.pow(Math.E, (double) dE / T);
                prob = Math.random();
                if (prob < acceptedProb) {
                    curr = n;
                }
            }
            t++;
        }
    }

    public Board Simulated_Annealing(Board b, Schedule s, int goalState) {
        Board curr = new Board(b), n;
        int t = 0, dE;
        double T, acceptedProb, prob;
        numberOfCreatedNodes = 0;

        while (true) {
            T = s.getTemp(t);
            if (T == 0 || curr.heuristic() == goalState) { return curr; }

            n = getRndSuccessor(curr);
            dE = curr.heuristic() - n.heuristic();

            if (dE > 0) {
                curr = n;
            } else {
                acceptedProb = Math.pow(Math.E, (double) dE / T);
                prob = Math.random();
                if (prob < acceptedProb) {
                    curr = n;
                }
            }
            t++;
        }
    }

    private Board getMinSuccessor(Board b) {
        Board result = null, t;
        int min = Integer.MAX_VALUE, th;

        for (int i = 0; i < Board.BOARD_SIZE; i++) {
            for (int j = 0; j < Board.BOARD_SIZE; j++) {
                numberOfCreatedNodes++;
                t = new Board(b);
                t.nextState(i, j);
                th = t.heuristic();
                if (th < min) {
                    result = t;
                    min = th;
                }
            }
        }

        return result;
    }

    private Board getRndSuccessor(Board b) {
        Random rnd = new Random();
        Board result;
        int i, j;

        numberOfCreatedNodes++;
        i = rnd.nextInt(Board.BOARD_SIZE);
        j = rnd.nextInt(Board.BOARD_SIZE);
        result = new Board(b);
        result.nextState(i, j);

        return result;
    }
}
