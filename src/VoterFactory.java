import java.util.Set;
import java.util.TreeSet;

/**
 * This class is used to create a set of voters with unique IDs.
 */
public class VoterFactory {
    private Set<Voter> voterSet;
    private long nextId;

    public VoterFactory() {
        this.voterSet = new TreeSet<Voter>();
        this.nextId = 0;
    }

    /**
     * Creates a new Student object and adds it to the set of voters stored by this VoterFactory.
     * @return A new Student object with an ID unique to the set of voters created by this VoterFactory object.
     */
    public Student getStudent() {
        Student output = new Student(this.nextId);
        this.nextId = nextId + 1;
        this.voterSet.add(output);
        return output;
    }

    /**
     * Clones the set of voters stored by this VoterFactory.
     * @return A clone of the voter set using TreeSet's clone() method.  Elements in the set are not cloned.
     */
    public Set<Voter> getVoterSet() {
        return (Set<Voter>) ((TreeSet) this.voterSet).clone();
    }
}
