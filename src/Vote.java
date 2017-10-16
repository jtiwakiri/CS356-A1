import java.io.Serializable;

/**
 * Class for sending votes to an IVoteService.
 */
public class Vote implements Serializable {
    private Voter voter;
    private Answer answer;
    private int questionId;

    public Vote(Voter v, Answer a, int id) {
        this.voter = v;
        this.answer = a;
        this.questionId = id;
    }

    /**
     * @return The Voter object stored by this Vote object.
     */
    public Voter getVoter() {
        return this.voter;
    };

    /**
     * @return The Answer object stored by this Vote object.
     */
    public Answer getAnswer() {
        return this.answer;
    }

    /**
     * @return The ID of the question this answer is directed to.
     */
    public int getId() {
        return questionId;
    }
}
