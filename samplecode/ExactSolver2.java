import java.util.*;

public class ExactSolver2 implements Solver {


    public int getOptimalValue(AuctionProblemInstance a, int[][] alloc) {

        int maxProfit = 0;

        // length of powerset
        int len = (int) Math.pow(2, a.k);

        // array of allocation for powerset [allocation bits]
        BitSet[] bitAlloc = new BitSet[len];

        // ArrayList of Bitset allocations needed for subset checking for each powerset.
        ArrayList<BitSet>[] bitSetListArray = new ArrayList[len];
        for (int j = 0; j < len; j++)
            bitSetListArray[j] = new ArrayList<BitSet>();

        // preprocessing matrix, value of allocated items [allocation][bidders]
        int[][] prePro = new int[len][a.n];

        // value of current and previous optimal answers for an allocation.
        int[] profit = new int[len];
        int[] prevprofit = new int[len];

        // Filling allocation bits & preprocessing matrix of max value for given allocation
        // Initilize the profit
        // Fill array of arraylists of bitsets which represent the comparison subsets for checking.
        for (int j = 0; j < len; j++) {                             // allocations
            bitAlloc[j] = BitSet.valueOf(new long[]{j});
            for (int i = 0; i < a.n; i++) {                         // no bidders
                for (int k = 0; k < a.k; k++) {                     // no items
                    if (bitAlloc[j].get(k) == true) {
                        prePro[j][i] += a.b[i][k];
                        alloc[j][k] = 1;
                    }
                }
                prePro[j][i] = Math.min(prePro[j][i], a.d[i]);
            }
            profit[j] = prePro[j][0];
            prevprofit[j] = prePro[j][0];
            bitSetListArray[j].add((BitSet) bitAlloc[j].clone());
            for (int i = bitAlloc[j].nextSetBit(0); i >= 0; i = bitAlloc[j].nextSetBit(i+1)) {
                int n = bitSetListArray[j].size();
                for (int bs = 0; bs < n; bs++) {
                    BitSet new_bs = (BitSet) bitSetListArray[j].get(bs).clone();
                    new_bs.set(i, false);
                    bitSetListArray[j].add(new_bs);
                }
            }
        }

        // For each bidder, for each
        int s = 0;
        for (int i = 1; i < a.n - 1; i++) {                         // no bidders
            for (int j = 1; j < len; j++) {                         // no allocations
                for (int k = 0; k < bitSetListArray[j].size(); k++) {
                    s = bitSetListArray[j].get(k).cardinality() == 0 ? 0 : (int) bitSetListArray[j].get(k).toLongArray()[0];
                    if (profit[j] < prevprofit[s] + prePro[j - s][i]) {
                        profit[j] = prevprofit[s] + prePro[j - s][i];
                    }
                }
            }
            for (int j = 0; j < len; j++)
                prevprofit[j] = profit[j];
        }
        for (int j = 0; j < len; j++) {
            if (maxProfit < profit[j] + prePro[len - j - 1][a.n - 1])
                maxProfit = profit[j] + prePro[len - j - 1][a.n - 1];
        }

        return maxProfit;
    }

    public AuctionProblemInstance.Solution solve(AuctionProblemInstance a) {
        int sizePowerSetk = (int) Math.pow(2, (a.k));
        int[][] optimals = new int[sizePowerSetk][a.k];

        return new AuctionProblemInstance.Solution(getOptimalValue(a, optimals), 0);
    }

    @Override
    public String getName() {
        return "ExactSolver";
    }
}
