import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;

/**
 * 
 * @author Berend-Jan Lange 4741390 & Maarten van 't Wout 4341414
 * TupleSet class to generate Solution Tuples
 *
 */
public class TupleSet {
	
	public int[] v;		// benefit of allocation of each bidder
	
	public BitSet[] a; 	// bit-vector of allocation to each bidder
	
	public int t; 		// total benefit of partial allocation
	
	TupleSet(int[] v, BitSet[] a, int t) {
		this.v = v;
		this.a = a;
		this.t = t;
	}
	
	TupleSet(AuctionProblemInstance a) {
		this.v = new int[a.n];
		
		this.a = new BitSet[a.n];
		Arrays.fill(this.a, new BitSet(a.k));
		
		this.t = 0;
	}
	
	/**
	 * 
	 * @param hashMap			Hashmap to store S_j tuples
	 * @param j					No of item to be added
	 * @param i					No of user
	 * @param a					Auction Problem Object
	 * @param intervalSize		Size of interval segment
	 * @param interval			Hashable array of integers
	 * @return					Returns tuple with maximum t
	 */
	public void addItem(HashMap<IntervalArray, TupleSet> hashMap, int j, int i, AuctionProblemInstance a, double intervalSize, IntervalArray interval, DPASolver.MaxTuple maxTuple) {

		IntervalArray interval_j = checkDominance(hashMap, v, t, Math.min(a.b[i][j], a.d[i] - v[i]), interval, intervalSize, i);
		
		if (interval_j != null) {
			
			TupleSet tuple = createTuple(v.clone(), CopyBitSetArrayAndAssign(this.a, i, j), t, Math.min(a.b[i][j], a.d[i] - v[i]), i, j);
			hashMap.put(interval_j, tuple);
			if (tuple.t > maxTuple.tupleSet.t) maxTuple.tupleSet = tuple; 
		}
	}
	
	/**
	 * 
	 * @param v					Array of values of each bidder
	 * @param a					Array of BitSets of assignments
	 * @param t					Sum of values of all bidders
	 * @param val				Added extra value for assigning item j to i
	 * @param i					bidder i
	 * @param j					bidder j
	 * @return					Return new tuple
	 */
	private TupleSet createTuple(int[] v, BitSet[] a, int t, int val, int i, int j) {
		v[i] += val;
		return new TupleSet(v, a, t + val);
	}
	
	/**
	 * 
	 * @param hashMap			Hashmap S_j
	 * @param v					Array of values of each bidder
	 * @param t					Sum of values of all bidders
	 * @param val				Added extra value for assigning item j to i
	 * @param interval			array of interval for each bidder
	 * @param intervalSize		size of single interval
	 * @param i					bidder i
	 * @return
	 */
	private IntervalArray checkDominance(HashMap<IntervalArray, TupleSet> hashMap, int[] v, int t, int val, IntervalArray interval, double intervalSize, int i) {
		int interval_old_i = interval.interval[i];
		double interval_new_i = ((v[i] + val) / intervalSize);
		interval.interval[i] = (int)(interval_new_i % 1 == 0 && interval_new_i != 0 ? interval_new_i - 1 : interval_new_i);
		if (hashMap.get(interval) == null || hashMap.get(interval).t <= t + val) {
			IntervalArray interval_j = new IntervalArray(interval.interval.clone());
			interval.interval[i] = interval_old_i;
			return interval_j;
		}
		else
			interval.interval[i] = interval_old_i;
			return null;
	}
	
	/**
	 * 
	 * @param bitSetArray 	BitSet array that should be deep-cloned
	 * @return				New BitSet array that is deep cloned.
	 */
	private BitSet[] CopyBitSetArrayAndAssign(BitSet[] bitSetArray, int i, int j) {
		BitSet[] newBitSetArray = new BitSet[bitSetArray.length];
		for (int k = 0; k < bitSetArray.length; k++) {
			newBitSetArray[k] = (BitSet) bitSetArray[k];
		}
		newBitSetArray[i].set(j);
		return newBitSetArray;
	}
}
