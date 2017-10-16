import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Map;
import java.util.TreeMap;

/**
 * Class for accepting votes for a question.
 */
public class IVoteService extends Thread {
    private ServerSocket serverSocket;
    private boolean running;
    private Question question;

    public IVoteService() {
        this.running = false;
        try {
            // Set serverSocket to listen on port 30000 and set the timeout for it's accept() method to 5 seconds.
            this.serverSocket = new ServerSocket(30000);
            this.serverSocket.setSoTimeout(2000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the Question object that the collected votes will be added to.
     * @param q
     */
    public void setQuestion(Question q) {
        this.question = q;
    }

    public void printResults() {
        TreeMap<Answer, Integer> map = (TreeMap<Answer, Integer>) this.question.getTotals();
        // Iterate through set of answers and print out each answer with the total votes for that answer.
        System.out.println("Answer totals:");
        while(map.size() > 0) {
            Map.Entry<Answer, Integer> entry = map.pollFirstEntry();
            System.out.println(entry.getKey().toString() + " - " + entry.getValue().toString());
        }
    }

    /**
     * Sets the IVoteService to accept connections and collect votes.
     */
    public void run() {
        this.running = true;
        while (running) {
            try {
                // Listen for connections.  Timeout is set to 5 seconds.
                Socket socket = this.serverSocket.accept();
                // If a connection is established, read the Vote object and add it to the question object.  If no
                // question is set, print an error.  Only add the vote if its question id matches the current question.
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                Vote vote = (Vote) in.readObject();
                if (vote.getId() == this.question.getId()) {
                    if (this.question != null) {
                        this.question.add(vote);
                    } else {
                        System.err.println("Server error:  No question set.");
                    }
                }
                socket.close();
            } catch (SocketTimeoutException e) {
                // Thrown whenever the accept() method times out.
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                // Don't accept answers of the wrong type.
            } catch (ClassCastException e) {
                // Don't accept answers of the wrong type.
                System.out.println("ada");
            }
        }
    }

    /**
     * Stop this thread from collecting votes.
     */
    public void shutdown() {
        this.running = false;
    }
}
