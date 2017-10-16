import java.io.Serializable;

/**
 * @param <T> The type of values used by this Answer object.
 */
public interface Answer<T> extends Serializable {
    public T getValue();

    public int compareTo(Answer<T> a);

    public String toString();
}
