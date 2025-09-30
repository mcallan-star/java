import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Magic8Ball implements Answerable {
    //instance variables
    private String[] arrayOfAnswers = {
        "It is certain",
        "Reply hazy, try again",
        "Don't count on it",
        "It is decidedly so",
        "Ask again later",
        "My reply is no",
        "Without a doubt",
        "Better not tell you now",
        "My sources say no",
        "Yes definitely",
        "Cannot predict now",
        "Outlook not so good",
        "You may rely on it",
        "Concentrate and ask again",
        "Very doubtful",
        "As I see it, yes",
        "Most likely",
        "Outlook good",
        "Yes",
        "Signs point to yes"
    };
    
    private Random random;
    private int currentAnswer;
    
    /*
     * Constructor for Magic8Ball
     * Magic8Ball objects can be created in outside classes because constructor is public
     */
    public Magic8Ball() {
        random = new Random();
        shake(); //calling this gets a new value for currentAnswer
    }
    
    //required methods from Answerable interface
    @Override
    public void shake() {
        //put random index in currentAnswer
        currentAnswer = random.nextInt(arrayOfAnswers.length);
        // Simulate shaking the ball
        System.out.println("*shake shake shake*");
    }
    
    @Override
    public String getAnswer() {
        //retrieves what Magic 8-Ball will say based on currentAnswer index
        return arrayOfAnswers[currentAnswer];
    }
    
    public static void main(String[] args) {
        Magic8Ball magic8Ball = new Magic8Ball();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        System.out.println("Welcome to the Magic 8-Ball!");
        System.out.println("Ask a yes/no question, or type 'quit' to exit.");
        
        try {
            /* 
             * try-catch is used because:
             * - reader.readLine() can throw an IOException (checked exception)
             * - Java forces us to handle checked exceptions - the code won't compile without it
             * - We want to handle errors gracefully instead of crashing
             */
            String question;
            while (true) { //keep running bc we dont know how many times user will ask (simpler than a boolean)
                System.out.print("\nWhat is your question? ");
                question = reader.readLine();
                
                if (question == null || question.trim().equalsIgnoreCase("quit")) {
                    System.out.println("Goodbye!");
                    break;
                }
                
                if (question.trim().isEmpty()) {
                    System.out.println("Please ask a question!");
                    continue;
                }
                
                System.out.println("You asked: " + question);
                magic8Ball.shake();
                System.out.println("Magic 8-Ball says: " + magic8Ball.getAnswer());
            }
        } catch (IOException e) {
            System.err.println("Error reading input: " + e.getMessage());
        } finally {  //buffered reader closed regardless if exception happened
            try {
                reader.close();
            } catch (IOException e) {
                System.err.println("Error closing reader: " + e.getMessage());
            }
        }
    }
}