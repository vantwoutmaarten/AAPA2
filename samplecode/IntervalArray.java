import java.util.Arrays;

/**
 * 
 * @author Berend-Jan Lange 4741390 & Maarten van 't Wout 4341414
 * IntervalArray is an array of integers which can be hashed based on ordered value of elements
 */
public class IntervalArray {
	final int[] interval;

    public IntervalArray(int[] interval) {
        this.interval = interval;
    }
    
    public IntervalArray(int n) {
    	this.interval = new int[n];
    }
    
    public IntervalArray(IntervalArray interval, int i, int val) {
    	this.interval = interval.interval.clone();
    	this.interval[i] = val;
    }
    
    public IntervalArray(int[] interval, int i, int val) {
    	this.interval = interval;
    	this.interval[i] = val;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof IntervalArray || object == null)) {
            return false;
        }

        final IntervalArray otherIntervalArray = (IntervalArray) object;
        if (!Arrays.equals(this.interval, otherIntervalArray.interval)) return false;
        return true;
    }

    @Override
    public int hashCode() {
    	return Arrays.hashCode(this.interval);
    }
}
