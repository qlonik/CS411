import java.util.ArrayList;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        Integer[][] tableManhattan = new Integer[32][2];
        Integer[][] tableMisplaced = new Integer[32][2];
        for (int i = 0; i < tableManhattan.length; i++) {
            for (int j = 0; j < tableManhattan[i].length; j++) {
                tableManhattan[i][j] = 0;
            }
        }
        for (int i = 0; i < tableMisplaced.length; i++) {
            for (int j = 0; j < tableMisplaced[i].length; j++) {
                tableMisplaced[i][j] = 0;
            }
        }

        int NUMBER_OF_PUZZLES = 5,
                MIN_DEPTH = 0,
                MAX_DEPTH = 100;
        Search search = new Search();
        Puzzle goal = new Puzzle();

        Puzzle puzzle;

        boolean printEachSolution = false;

        for (int j = MIN_DEPTH; j < MAX_DEPTH; j++) {
            for (int i = 0; i < NUMBER_OF_PUZZLES; i++) {
                puzzle = new Puzzle();
                puzzle.shuffle(j);
                System.out.println("puzzle j:" + j + " i:" + i);

                puzzle.saveState();
                puzzle.setManhattanHeuristic(true);
                Puzzle solvedManhattan = search.AStar(puzzle, goal);
                int nodesGenManhattan = search.getNumberOfCreatedNodes();
                ArrayList<Puzzle> manhattanPath = null;
                if (solvedManhattan != null) {
                    manhattanPath = solvedManhattan.getPath();
                    if (printEachSolution) System.out.println("Manhattan p:" + manhattanPath.size() + " n:" + nodesGenManhattan);
                } else {
                    if (printEachSolution) System.out.println("Manhattan none");
                }

                puzzle.restoreState();
                puzzle.setMisplacedHeuristic(true);
                Puzzle solvedMisplaced = search.AStar(puzzle, goal);
                int nodesGenMisplaced = search.getNumberOfCreatedNodes();
                ArrayList<Puzzle> misplacedPath = null;
                if (solvedMisplaced != null) {
                    misplacedPath = solvedMisplaced.getPath();
                    if (printEachSolution) System.out.println("Misplaced p:" + misplacedPath.size() + " n:" + nodesGenMisplaced);
                } else {
                    if (printEachSolution) System.out.println("Misplaced none");
                }

                if (printEachSolution) {
                    puzzle.restoreState();
                    if (manhattanPath != null &&
                            misplacedPath != null &&
                            manhattanPath.size() != misplacedPath.size()) {
                        System.out.println(puzzle);
                    }
                }

                if (manhattanPath != null){
                    tableManhattan[manhattanPath.size()][0] += nodesGenManhattan;
                    tableManhattan[manhattanPath.size()][1] ++;
                }
                if (misplacedPath != null) {
                    tableMisplaced[misplacedPath.size()][0] += nodesGenMisplaced;
                    tableMisplaced[misplacedPath.size()][1] ++;
                }

                if (printEachSolution) System.out.println("----------------------------------------------");
            }
        }

        System.out.println("d\t\tA*Manh\t\tA*Misp");
        System.out.println("----------------------");
        for (int i = 0; i < tableManhattan.length; i++) {
            System.out.println("" + i + "\t\t" +
                            ((double) tableManhattan[i][0] / (double) tableManhattan[i][1]) + "\t\t" +
                            ((double) tableMisplaced[i][0] / (double) tableMisplaced[i][1])
            );
        }
    }
}
