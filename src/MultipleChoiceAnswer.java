/**
 * This class represents multiple choice answers.  Objects of this class store values of the MultipleChoiceOption enum.
 */
public class MultipleChoiceAnswer implements Answer<MultipleChoiceOption>, Comparable<Answer<MultipleChoiceOption>> {
    private MultipleChoiceOption value;

    public MultipleChoiceAnswer(MultipleChoiceOption v) {
        this.value = v;
    }

    /**
     * @return The value of this MultipleChoiceAnswer object.
     */
    public MultipleChoiceOption getValue() {
        return value;
    }

    /**
     * Compares two MultipleChoiceAnswer objects.
     * @param a Answer to compare this MultipleChoiceAnswer object to.
     * @return 0 if this.value == a.value, -1 if this.value < a.value, and 1 if this.value > a.value.
     */
    public int compareTo(Answer<MultipleChoiceOption> a) {
        return this.value.compareTo(a.getValue());
    }

    /**
     * @return String representation of the MultipleChoiceEnum stored by this MultipleChoiceAnswer object.
     */
    public String toString() {
        return this.value.toString();
    }
}
