import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        try {
            // System.out.println(new BruteForceSolver().solve(AuctionProblemInstance.IO.read(args[0])).value); // replace with your solver here
            System.out.println(new DPASolver(Double.valueOf(args[1])).solve(AuctionProblemInstance.IO.read(args[0])).value);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
