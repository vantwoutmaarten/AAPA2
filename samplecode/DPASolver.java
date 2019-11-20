import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Berend-Jan Lange 4741390 & Maarten van 't Wout 4341414
 * Dynamic Programming Algorithm Solver for auction problem
 *
 */
public class DPASolver implements Solver {
	
	double epsilon;
	
	public DPASolver(double epsilon) {
		this.epsilon = epsilon;
	}
	
	class MaxTuple {
		public TupleSet tupleSet;
		
		MaxTuple(TupleSet tupleSet) {
			this.tupleSet = tupleSet;
		}
	}
	
	/**
	 * Retrieves Optimal Value Of Problem Instance Using Dynamic Programming Approximation Algorithm.
	 * 
	 * @param a				AuctionProblemInstance.
	 * @param assignment	Binary Integer Array Containing Assignment Of Items
	 * @return				Return Maximal Revenue
	 * @throws Exception    Epsilon should be strictly greater than 0
	 */
    public int getOptimalValue(AuctionProblemInstance a, boolean[][] assignment) {

    	long startTime = System.nanoTime();

    	if (this.epsilon <= 0) return -1;

		// STEP 1
		// Construct gamma
		// int gamma = Arrays.stream(a.d).max().getAsInt();
		
		// STEP 2
		// Create segments of equal length intervals
		double intervalSize = Arrays.stream(a.d).max().getAsInt() * this.epsilon / a.k;
		
		// STEP 3
		// Initialize S_0
		HashMap<IntervalArray, TupleSet> S = new HashMap<IntervalArray, TupleSet>();
		S.put(new IntervalArray(a.n), new TupleSet(a));
		MaxTuple maxTuple = new MaxTuple(new TupleSet(a));
		
		
		// STEP 4
		// Loop through all items j
		// Create new HashMap for j items
		// Loop through all tuples 
		// Loop through all bidders i
		// Create and fill new HashMap and delete dominated hashes
		for (int j = 0; j < a.k; j++) {
			
			HashMap<IntervalArray, TupleSet> S_j = new HashMap<IntervalArray, TupleSet>();
			
			for (Map.Entry<IntervalArray, TupleSet> tuple : S.entrySet()) {
				
				for (int i = 0; i < a.n; i++) {
					
					tuple.getValue().addItem(S_j, j, i, a, intervalSize, tuple.getKey(), maxTuple);
					
				}
				
			}
			
			S = S_j;
		}
		System.out.println((System.nanoTime()-startTime)/1000000 + " miliseconds");
		
		// STEP 5
		// Get assignment and return maximum revenue
		for (int i = 0; i < maxTuple.tupleSet.a.length; i++) {
			for (int j = 0; j < a.k; j++) {
				assignment[i][j] = maxTuple.tupleSet.a[i].get(j);
			}
		}
    	return maxTuple.tupleSet.t;

    }

    /**
     * return name of class
     */
    @Override
    public String getName() {
        return "dynamicProgrammingAlgorithm";
    }

    /**
     * 
     * @param a				the auction problem read from file
     * @return				return new Solution Object
     */
	@Override
    public AuctionProblemInstance.Solution solve(AuctionProblemInstance a) {
        boolean[][] assignment = new boolean[a.n][a.k];
		return new AuctionProblemInstance.Solution(getOptimalValue(a, assignment), epsilon);
    }
}
