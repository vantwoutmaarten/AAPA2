import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//public class Main {
//    public static void main(String[] args) throws IOException {
//        String csvFile = "performance/GoedgedaanMaarten_epsilon030.csv";
//        FileWriter writer = new FileWriter(csvFile);
//        CSVUtils.writeLine(writer, Arrays.asList("n", "k", "dmax", "instanceNum", "epsilon", "RUNTIME"));
//
//        File dir = new File("instances1");
//        File[] directoryListing = dir.listFiles();
//        if (directoryListing != null) {
//            for (int i = 0; i <  directoryListing.length; i++) {
//                File file = directoryListing[i];
//                String filename = file.getName();
//                String n = filename.substring(filename.indexOf("n_") + 2, filename.indexOf("_k_"));
//                String k = filename.substring(filename.indexOf("_k_") + 3, filename.indexOf("_d"));
//                String dmax = filename.substring(filename.indexOf("x_") + 2, filename.lastIndexOf("_"));
//                String instanceNum = filename.substring(filename.lastIndexOf("_") + 1,
//                        filename.lastIndexOf("_") + 2);
//
//                double epsilon = 0.3;
//                System.out.println("n= " + n + " k= " + k + " dmax= " + dmax + "InstanceNum= " + instanceNum + " epsilon= " + epsilon);
//                try {
//                    long startTime = System.nanoTime();
//                    int solution = new DPASolver(epsilon).solve(AuctionProblemInstance.IO.read(
//                            "instances/n_" + n + "_k_" + k + "_dmax_" + dmax + "_" + instanceNum + ".txt")).value;
//                    long totaltime = (System.nanoTime() - startTime) / 1000;
//                    CSVUtils.writeLine(writer, Arrays.asList(n, k, dmax, instanceNum, String.valueOf(epsilon), String.valueOf(totaltime)));
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            }
//
//        } else {
//            System.out.println("this is not a directory");
//        }
//        writer.flush();
//        writer.close();
//    }
//}

public class Main {
    public static void main(String[] args) {
        try {
//             System.out.println(new BruteForceSolver().solve(AuctionProblemInstance.IO.read(args[0])).value); // replace with your solver here

//            System.out.println(new BruteForceSolver().solve(AuctionProblemInstance.IO.read(args[0])).value);
//
            System.out.println(new ExactSolver2().solve(AuctionProblemInstance.IO.read(args[0])).value);





        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}