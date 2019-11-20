import java.util.*;

public class ExactSolver implements Solver {


    public int getOptimalValue(AuctionProblemInstance a, int[][] optimals, int sizepowers) {
        int MaxProfit = 0;

        for(int i = 0; i < sizepowers; i++){
            for(int j = 0; j < a.k; j++){

            }
        }

        System.out.println(Arrays.deepToString(optimals));

        return MaxProfit;
    }

    public AuctionProblemInstance.Solution solve(AuctionProblemInstance a) {
        int sizePowerSetk = (int) Math.pow(2, (a.k));
        int[][] optimals = new int[sizePowerSetk][a.k];

        return new AuctionProblemInstance.Solution(getOptimalValue(a, optimals, sizePowerSetk), 0);
    }

    @Override
    public String getName() {
        return "ExactSolver";
    }
}
