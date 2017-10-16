/**
 * Created by Joshua on 10/12/2017.
 */
public class Student implements Voter {
    private long idNum;
    private String idString;

    public Student(long d) {
        this.idNum = d;
        this.idString = Double.toHexString(d);
    }

    /**
     * @return The ID of this Student object as a hex string.
     */
    public String getId() {
        return this.idString;
    }

    /**
     * @return The ID of this Student object as a long.
     */
    public long getIdNum() { return this.idNum; }

    /**
     * @param v Student object.
     * @return 0 if the objects have an identical id and -1 otherwise.
     */
    public int compareTo(Voter v) {
        return (int) (this.idNum - v.getIdNum());
    }
}
