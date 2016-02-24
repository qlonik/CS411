import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)
            throws FileNotFoundException, UnsupportedEncodingException {
        Scanner file = new Scanner(new File("UtilityValues.txt"));
        Tree tree = new Tree();
        tree.populateTree(file);
        tree.giveAliases();

        Search s = new Search();
        s.ABSearch(tree);
        Tree max = s.getMaxAction(tree),
                min = s.getMinAction(max);
        List<Tree> visited = s.getVisited();

        PrintWriter writer = new PrintWriter("Results.txt", "UTF-8");
        writer.println("MAX: " + max.getAction());
        writer.println("MIN: " + min.getAction());
        writer.print("Visited Nodes: ");
        for (int i = 0; i < visited.size(); i++) {
            writer.print(visited.get(i));
            if (i != visited.size() - 1) {
                writer.print(", ");
            }
        }
        writer.println();
        writer.println();
        writer.close();
    }
}
