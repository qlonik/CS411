import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner file = new Scanner(new File("UtilityValues.txt"));
        Tree tree = new Tree();
        tree.populateTree(file);

        Search s = new Search();
        int res = s.ABSearch(tree);

        System.out.println(res);
    }
}
