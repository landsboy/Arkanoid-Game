package gamesettings;
/**
 * this class making a counter that responsible for counting things.
 */
public class Counter {
    private int count;
    /**
     * construct a counter from a number.
     * @param initialCount the number.
     */
    public Counter(int initialCount) {
        this.count = initialCount;
    }
    /**
     * this method gets an integer and increases value of this counter.
     * @param num the given number.
     */
    public void increase(int num) {
        this.count += num;
    }
    /**
     * this method subtracts number from this counter.
     * @param num the given number.
     */
   public void decrease(int num) {
        this.count -= num;
    }
    /**
     * this method returns the current value of the counter.
     * @return this counter.
     */
   public int getValue() {
        return this.count;
    }
}