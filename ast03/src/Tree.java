import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Tree {
    private Node root;

    public Tree() {
        root = new Node();
    }

    public Tree populateTree(Scanner file) {
        String line = file.nextLine();
        ArrayList<String> nums = new ArrayList<>(Arrays.asList(line.split(", ")));
        Long deep = Math.round((Math.log(nums.size()) / Math.log(3)));

        System.out.println(nums);
        System.out.println(deep);

        return this;
    }
}
