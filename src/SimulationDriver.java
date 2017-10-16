import com.sun.org.apache.bcel.internal.generic.MULTIANEWARRAY;
import com.sun.org.apache.xpath.internal.operations.Mult;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Test the IVoteService.
 */
public class SimulationDriver {
    public static void main(String[] args) {
        IVoteService server = new IVoteService();
        VoterFactory factory = new VoterFactory();

        // Test multiple choice question type
        String question1String = "What direction is the North Pole?\nA) North\nB) South\nC) East\nD) West";
        Question<MultipleChoiceAnswer> question1 = new Question<MultipleChoiceAnswer>(question1String);
        server.setQuestion(question1);
        server.start();
        // Send a random number of random answers to the IVoteService
        try {
            for (int i = (int) (Math.random() * 200 + 10); i > 0; i--) {
                Socket socket = new Socket("localhost", 30000);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                MultipleChoiceAnswer answer = new MultipleChoiceAnswer(MultipleChoiceOption.getRandomOption());
                Vote vote = new Vote(factory.getStudent(), answer, question1.getId());
                out.writeObject(vote);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Print results
        server.printResults();

        // Test true/false question type
        String question2String = "Does 1 + 1 = 2?";
        Question<TrueFalseAnswer> question2 = new Question<TrueFalseAnswer>(question2String);
        server.setQuestion(question2);
        // Send a random number of random answers to the IVoteService
        try {
            for (int i = (int) (Math.random() * 200 + 10); i > 0; i--) {
                Socket socket = new Socket("localhost", 30000);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                TrueFalseAnswer answer;
                if ((int) (Math.random() * 2) > 0) {
                    answer = new TrueFalseAnswer(true);
                } else {
                    answer = new TrueFalseAnswer(false);
                }
                Vote vote = new Vote(factory.getStudent(), answer, question2.getId());
                out.writeObject(vote);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Print results
        server.printResults();

        // Shut down server
        server.shutdown();
    }
}
