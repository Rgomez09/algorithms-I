import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Usage: java Permutation k");
        }

        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> inputArray = new RandomizedQueue<>();

        while (!StdIn.isEmpty()) {
            inputArray.enqueue(StdIn.readString());
        }

        for (int i = 0; i < k; i++) {
            StdOut.println(inputArray.dequeue());
        }
    }
}


