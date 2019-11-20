import java.util.*;

public class ExactSolver implements Solver {


    public static <T> Set<Set<T>> powerSet(Set<T> originalSet) {
        Set<Set<T>> sets = new HashSet<Set<T>>();
        if (originalSet.isEmpty()) {
            sets.add(new HashSet<T>());
            return sets;
        }
        List<T> list = new ArrayList<T>(originalSet);
        T head = list.get(0);
        Set<T> rest = new HashSet<T>(list.subList(1, list.size()));
        for (Set<T> set : powerSet(rest)) {
            Set<T> newSet = new HashSet<T>();
            newSet.add(head);
            newSet.addAll(set);
            sets.add(newSet);
            sets.add(set);
        }
        return sets;
    }

    public int getOptimalValue(AuctionProblemInstance a, int[][] individualopt, int[][] optimals, int sizepowers) {
        int MaxProfit = 0;

        Set<Integer> mySet = new HashSet<Integer>();

        for (int i = 0; i < a.k; i++) {
            mySet.add(i);
        }

        Set<Set<Integer>> powersetitems = ExactSolver.powerSet(mySet);

        for (Set<Integer> s : powersetitems) {
            System.out.println(s);
        }

        for (int i = 0; i < a.n; i++) {
            int j = 0;
            Iterator sets = powersetitems.iterator();
            while (sets.hasNext()) {
                Set<Integer> currentset = (Set<Integer>) sets.next();
                Iterator<Integer> currentItr = currentset.iterator();
                int subsetsumtot = 0;

                while (currentItr.hasNext()) {
                    subsetsumtot += a.b[i][currentItr.next()];
                }
                individualopt[i][j] = Math.min(subsetsumtot, a.d[i]);
                j++;
            }
        }


        System.out.println(Arrays.deepToString(individualopt));

        return MaxProfit;
    }

    public AuctionProblemInstance.Solution solve(AuctionProblemInstance a) {
        int sizePowerSetk = (int) Math.pow(2, (a.k));
        int[][] individualopt = new int[a.n][sizePowerSetk];
        int[][] optimals = new int[2][sizePowerSetk];

        return new AuctionProblemInstance.Solution(getOptimalValue(a, individualopt, optimals, sizePowerSetk), 0);
    }

    @Override
    public String getName() {
        return "ExactSolver";
    }
}
