import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * Test the IVoteService.
 */
public class SimulationDriver implements Runnable {
    Scanner in;

    public SimulationDriver() {
        this.in = new Scanner(System.in);
    }

    public static void main(String[] args) {
        SimulationDriver driver = new SimulationDriver();
        driver.run();
    }

    public void run() {
        IVoteService server = new IVoteService();
        server.start();
        // Get menu selection from user and call appropriate method.
        boolean running = true;
        while (running) {
            System.out.println("Select a question type:\n\t1) Multiple choice question\n\t2) True/False question\n\t"
                    + "3) Exit");
            String input = in.nextLine().trim();
            if (input.equalsIgnoreCase("1")) {
                Question<MultipleChoiceAnswer> question = this.getMultipleChoiceQuestion();
                server.setQuestion(question);
                this.sendMultipleChoiceAnswers(question.getId(), question.getNumAnswers());
                server.printResults();
            } else if (input.equalsIgnoreCase("2")) {
                Question<TrueFalseAnswer> question = this.getTrueFalseQuestion();
                server.setQuestion(question);
                this.sendTrueFalseAnswers(question.getId());
                server.printResults();
            } else if (input.equalsIgnoreCase("3")) {
                server.shutdown();
                running = false;
            } else {
                System.out.println("Selection must be 1, 2, or 3.");
            }
            System.out.println('\n');
        }
    }

    /**
     * Get user input for a multiple choice question.
     * @return Question with the user's question text and answer option text.
     */
    private Question<MultipleChoiceAnswer> getMultipleChoiceQuestion() {
        // Get question text.
        System.out.print("Enter question text:  ");
        String questionText = in.nextLine().trim();

        // Get number of answers.
        int numAnswers = -1;
        while (numAnswers == -1) {
            System.out.print("Enter number of answers (2 to 7):  ");
            String numAnswersString = in.nextLine().trim();
            try {
                numAnswers = Integer.parseInt(numAnswersString);
            } catch (NumberFormatException e) {
                System.out.println("Error:  Enter an integer between 2 and 7.");
            }
        }

        // Get answers.
        MultipleChoiceOption[] options = MultipleChoiceOption.values();
        String[] answersText = new String[numAnswers];
        for (int i = 0; i < numAnswers; i++) {
            System.out.print("Enter option " + options[i] + " text:  ");
            answersText[i] = in.nextLine().trim();
        }

        // Return question.
        Question<MultipleChoiceAnswer> output = new Question<MultipleChoiceAnswer>(questionText, answersText);
        return output;
    }

    /**
     * Send random votes to the vote server.
     * @param id Question id to send the votes to.
     */
    public void sendMultipleChoiceAnswers(int id, int numAnswers) {
        VoterFactory factory = new VoterFactory();
        MultipleChoiceOption[] options = MultipleChoiceOption.values();
        // Send a random number of random answers to the IVoteService
        try {
            for (int i = (int) (Math.random() * 200 + 10); i > 0; i--) {
                Socket socket = new Socket("localhost", 30000);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                MultipleChoiceAnswer answer = new MultipleChoiceAnswer(options[(int) (Math.random() * numAnswers)]);
                Vote vote = new Vote(factory.getStudent(), answer, id);
                out.writeObject(vote);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get user input for a multiple choice question.
     * @return Question with the user's question text and answer option text.
     */
    private Question<TrueFalseAnswer> getTrueFalseQuestion() {
        // Get question text.
        System.out.print("Enter question text:  ");
        String questionText = in.nextLine().trim();

        // Get answers.
        boolean[] options = {true, false};
        String[] answersText = new String[2];
        for (int i = 0; i < 2; i++) {
            System.out.print("Enter option " + options[i] + " text:  ");
            answersText[i] = in.nextLine().trim();
        }

        // Return question.
        Question<TrueFalseAnswer> output = new Question<TrueFalseAnswer>(questionText, answersText);
        return output;
    }

    /**
     * Send random true/false questions to the vote server.
     * @param id Question id to send the votes to.
     */
    public void sendTrueFalseAnswers(int id) {
        VoterFactory factory = new VoterFactory();
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
                Vote vote = new Vote(factory.getStudent(), answer, id);
                out.writeObject(vote);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
