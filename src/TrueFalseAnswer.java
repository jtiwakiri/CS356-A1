/**
 * This class represents multiple choice answers.  Objects of this class store boolean values.
 */
public class TrueFalseAnswer implements Answer<Boolean>, Comparable<Answer<Boolean>> {
    private Boolean value;

    public TrueFalseAnswer(boolean b) {
        this.value = b;
    }

    /**
     * @return The value of this TrueFalseAnswer object.
     */
    public Boolean getValue() {
        return value;
    }

    /**
     * Compares two TrueFalseAnswer objects.
     * @param a Answer to compare this TrueFalseAnswer object to.
     * @return 0 if this.value == a.value, and some other number if they are not equal.
     */
    public int compareTo(Answer<Boolean> a) {
        return this.value.compareTo(a.getValue());
    }

    /**
     * @return String representation of the value of this TrueFalseAnswer.
     */
    public String toString() {
        return value.toString();
    }
}
