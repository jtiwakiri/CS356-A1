import java.io.Serializable;

/**
 * Interface for voters.
 */
public interface Voter extends Comparable<Voter>, Serializable {
    public String getId();

    public long getIdNum();

    public int compareTo(Voter v);
}
