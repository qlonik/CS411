public class Main {
    public static void main(String[] args) {
        boolean debug = true;
        int total = 0, solved = 0, step, nodesForSolved = 0, nodesForUnsolved = 0,
                maxPuzzles = 1_000, maxPuzzlesStep = maxPuzzles / 100;
        double genNodes = 0;
        Board a, b;
        Search search = new Search();
        Schedule s = new Schedule();

        if (debug) {
            System.out.println("Progress (Steps by " + maxPuzzlesStep + " puzzles):");
        }
        for (int i = 0; i < maxPuzzles; i++) {
            if (debug) {
                if (i % maxPuzzlesStep == 0) {
                    step = i / maxPuzzlesStep;
                    if (step % 20 != 0) { System.out.print('.'); }
                    if (step < 10) { System.out.print('0'); }
                    System.out.print(step);
                    if ((step == 99) || ((step + 1) % 20 == 0)) { System.out.println(); }
                }
            }

            a = new Board();
            a.shuffleBoard();
            b = search.Hill_Climb(a);

            genNodes += (double) search.getNumberOfCreatedNodes() / maxPuzzles;
            if (b.heuristic() == 0) {
                solved++;
                nodesForSolved += search.getNumberOfCreatedNodes();
            } else {
                nodesForUnsolved += search.getNumberOfCreatedNodes();
            }
            total++;
        }

        System.out.println("Hill climbing");
        System.out.println("Nodes generated for each puzzle : " + Math.round(genNodes));
        System.out.println("Solved : " + solved + " out of : " + total);
        System.out.println("Nodes generated for solved runs : " + Math.round((double) nodesForSolved / solved));
        System.out.println("Nodes generated for unsolved runs : " + Math.round((double) nodesForUnsolved / (total - solved)));
        System.out.println("Percentage : " + (double) solved * 100 / total + "%");

        total = 0;
        solved = 0;
        genNodes = 0;
        nodesForSolved = 0;
        nodesForUnsolved = 0;
        System.out.println();
        if (debug) {
            System.out.println("Progress (Steps by " + maxPuzzlesStep + " puzzles):");
        }
        for (int i = 0; i < maxPuzzles; i++) {
            if (debug) {
                if (i % maxPuzzlesStep == 0) {
                    step = i / maxPuzzlesStep;
                    if (step % 20 != 0) { System.out.print('.'); }
                    if (step < 10) { System.out.print('0'); }
                    System.out.print(step);
                    if ((step == 99) || ((step + 1) % 20 == 0)) { System.out.println(); }
                }
            }

            a = new Board();
            a.shuffleBoard();
            b = search.Hill_Climb_Random_Restart(a, 1000, false);

            genNodes += (double) search.getNumberOfCreatedNodes() / maxPuzzles;
            if (b.heuristic() == 0) {
                solved++;
                nodesForSolved += search.getNumberOfCreatedNodes();
            } else {
                nodesForUnsolved += search.getNumberOfCreatedNodes();
            }
            total++;
        }

        System.out.println("Hill climbing with random restart");
        System.out.println("Nodes generated for each puzzle : " + Math.round(genNodes));
        System.out.println("Solved : " + solved + " out of : " + total);
        System.out.println("Nodes generated for solved runs : " + Math.round((double) nodesForSolved / solved));
        System.out.println("Nodes generated for unsolved runs : " + Math.round((double) nodesForUnsolved / (total - solved)));
        System.out.println("Percentage : " + (double) solved * 100 / total + "%");

        total = 0;
        solved = 0;
        genNodes = 0;
        nodesForSolved = 0;
        nodesForUnsolved = 0;
        System.out.println();
        if (debug) {
            System.out.println("Progress (Steps by " + maxPuzzlesStep + " puzzles):");
        }
        for (int i = 0; i < maxPuzzles; i++) {
            if (debug) {
                if (i % maxPuzzlesStep == 0) {
                    step = i / maxPuzzlesStep;
                    if (step % 20 != 0) { System.out.print('.'); }
                    if (step < 10) { System.out.print('0'); }
                    System.out.print(step);
                    if ((step == 99) || ((step + 1) % 20 == 0)) { System.out.println(); }
                }
            }

            a = new Board();
            a.shuffleBoard();
            b = search.Hill_Climb_Random_Restart(a, 0, 1000);

            genNodes += (double) search.getNumberOfCreatedNodes() / maxPuzzles;
            if (b.heuristic() == 0) {
                solved++;
                nodesForSolved += search.getNumberOfCreatedNodes();
            } else {
                nodesForUnsolved += search.getNumberOfCreatedNodes();
            }
            total++;
        }

        System.out.println("Hill climbing with random restart with goal state");
        System.out.println("Nodes generated for each puzzle : " + Math.round(genNodes));
        System.out.println("Solved : " + solved + " out of : " + total);
        System.out.println("Nodes generated for solved runs : " + Math.round((double) nodesForSolved / solved));
        System.out.println("Nodes generated for unsolved runs : " + Math.round((double) nodesForUnsolved / (total - solved)));
        System.out.println("Percentage : " + (double) solved * 100 / total + "%");

        total = 0;
        solved = 0;
        genNodes = 0;
        nodesForSolved = 0;
        nodesForUnsolved = 0;
        System.out.println();
        if (debug) {
            System.out.println("Progress (Steps by " + maxPuzzlesStep + " puzzles):");
        }
        for (int i = 0; i < maxPuzzles; i++) {
            if (debug) {
                if (i % maxPuzzlesStep == 0) {
                    step = i / maxPuzzlesStep;
                    if (step % 20 != 0) { System.out.print('.'); }
                    if (step < 10) { System.out.print('0'); }
                    System.out.print(step);
                    if ((step == 99) || ((step + 1) % 20 == 0)) { System.out.println(); }
                }
            }

            a = new Board();
            a.shuffleBoard();
            b = search.Simulated_Annealing(a, s);

            genNodes += search.getNumberOfCreatedNodes() / maxPuzzles;
            if (b.heuristic() == 0) {
                solved++;
                nodesForSolved += search.getNumberOfCreatedNodes();
            } else {
                nodesForUnsolved += search.getNumberOfCreatedNodes();
            }
            total++;
        }

        System.out.println("Simulated annealing");
        System.out.println("Nodes generated for each puzzle : " + Math.round(genNodes));
        System.out.println("Solved : " + solved + " out of : " + total);
        System.out.println("Nodes generated for solved runs : " + Math.round((double) nodesForSolved / solved));
        System.out.println("Nodes generated for unsolved runs : " + Math.round((double) nodesForUnsolved / (total - solved)));
        System.out.println("Percentage : " + (double) solved * 100 / total + "%");

        total = 0;
        solved = 0;
        genNodes = 0;
        nodesForSolved = 0;
        nodesForUnsolved = 0;
        System.out.println();
        if (debug) {
            System.out.println("Progress (Steps by " + maxPuzzlesStep + " puzzles):");
        }
        for (int i = 0; i < maxPuzzles; i++) {
            if (debug) {
                if (i % maxPuzzlesStep == 0) {
                    step = i / maxPuzzlesStep;
                    if (step % 20 != 0) { System.out.print('.'); }
                    if (step < 10) { System.out.print('0'); }
                    System.out.print(step);
                    if ((step == 99) || ((step + 1) % 20 == 0)) { System.out.println(); }
                }
            }

            a = new Board();
            a.shuffleBoard();
            b = search.Simulated_Annealing(a, s, 0);

            genNodes += search.getNumberOfCreatedNodes() / maxPuzzles;
            if (b.heuristic() == 0) {
                solved++;
                nodesForSolved += search.getNumberOfCreatedNodes();
            } else {
                nodesForUnsolved += search.getNumberOfCreatedNodes();
            }
            total++;
        }

        System.out.println("Simulated annealing with goal state");
        System.out.println("Nodes generated for each puzzle : " + Math.round(genNodes));
        System.out.println("Solved : " + solved + " out of : " + total);
        System.out.println("Nodes generated for solved runs : " + Math.round((double) nodesForSolved / solved));
        System.out.println("Nodes generated for unsolved runs : " + Math.round((double) nodesForUnsolved / (total - solved)));
        System.out.println("Percentage : " + (double) solved * 100 / total + "%");
    }
}
