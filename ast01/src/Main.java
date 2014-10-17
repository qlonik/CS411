import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        int NUMBER_OF_PUZZLES = 6;
        Search search = new Search();
        Puzzle goal = new Puzzle();

        Tile [] tile = {
                new Tile(7,0), new Tile(2,1), new Tile(0,2),
                new Tile(1,3), new Tile(3,4), new Tile(8,5),
                new Tile(4,6), new Tile(5,7), new Tile(6,8)
        };
        Puzzle puzzle = new Puzzle();

        for (int i = 0; i < NUMBER_OF_PUZZLES; i++) {
            puzzle.shuffle(400);
            System.out.println("Puzzle starting configuration:");
            System.out.println(puzzle);

            puzzle.saveState();
            puzzle.setManhattanHeuristic(true);
            Puzzle solvedManhattan = search.AStar(puzzle, goal);
            int nodesGenManhattan = search.getNumberOfCreatedNodes();
            if (solvedManhattan != null) {
                System.out.println("Solved with Manhattan heuristic");
                ArrayList<Puzzle> path = solvedManhattan.getPath();
                System.out.println("Steps taken to solve the problem: " + path.size());
                System.out.println("Nodes generated: " + nodesGenManhattan);
                System.out.println("Solution: ");
                System.out.println(Puzzle.getDirections(path));
            } else {
                System.out.println("Manhattan did not solve");
            }

            puzzle.restoreState();
            puzzle.setMisplacedHeuristic(true);
            Puzzle solvedMisplaced = search.AStar(puzzle, goal);
            int nodesGenMisplaced = search.getNumberOfCreatedNodes();
            if (solvedMisplaced != null) {
                System.out.println("Solved with Misplaced heuristic");
                ArrayList<Puzzle> path = solvedMisplaced.getPath();
                System.out.println("Steps taken to solve the problem: " + path.size());
                System.out.println("Nodes generated: " + nodesGenMisplaced);
                System.out.println("Solution: ");
                System.out.println(Puzzle.getDirections(path));
            } else {
                System.out.println("Misplaced did not solve");
            }
            System.out.println("----------------------------------------------");
        }
    }
}
