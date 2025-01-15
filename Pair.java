// AYAN GUPTA
// B00962542

// This class represents the pair of Letters and their probabilities.
public class Pair implements Comparable<Pair> {
    private char value;
    private double prob;

    /**
     * Constructor
     * @param value The letter
     * @param prob The probability of the character
     */
    public Pair(char value, double prob) {
        this.value = value;
        this.prob = prob;
    }

    // Setters and Getters for letters and their probabilities.
    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    public double getProb() {
        return prob;
    }

    public void setProb(double prob) {
        this.prob = prob;
    }


    @Override
    public int compareTo(Pair p) {
        return Double.compare(this.prob, p.getProb());
    }

    @Override
    public String toString() {
        return "Pair [value=" + value + ", prob=" + prob + "]";
    }

}
