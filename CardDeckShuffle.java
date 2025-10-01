import java.util.*;

/**
 * Card Deck Shuffle Program - Random Seed Demonstration
 * 
 * This program demonstrates how random seeds work by shuffling a deck of cards.
 * Each shuffle produces exactly one configuration of all 52 cards, and different
 * seeds produce different configurations while the same seed produces identical results.
 * 
 * - Random Seed Generation: Use system time (milliseconds + nanoseconds) for unique seeds
 * - Same seed = same "random" sequence = same shuffle
 * - Fisher-Yates Shuffle Algorithm: Start from last card, work backwards
 * - For each position, swap with random earlier position
 * - Ensures each of 52! possible arrangements has equal probability
 * Fisher-Yates shuffle algorithm GOAL: Each of the 52! possible arrangements should have exactly equal probability (1/52!).
 * The Backwards Logic:
 *
 * Last position (index 51): Can be filled by any of the 52 cards → 52 choices
 * Second-to-last (index 50): Can be filled by any of the remaining 51 cards → 51 choices
 * Third-to-last (index 49): Can be filled by any of the remaining 50 cards → 50 choices
 * Continue UNTIL position 1: Gets one of the remaining 2 cards → 2 choices
 * Position 0: Gets the last remaining card → 1 choice (automatic)
 * 
 * DEMONSTRATION:
 * - Show that different seeds create different shuffles
 * - Show that same seed creates identical shuffles
 * - Show that each shuffle without specified seed is unique
 *   
 * 
 */
public class CardDeckShuffle {
    
    // STEP 2: Create Random Number Generator
    // 1. Create a random number generator object
    // 2. Initialize it (it will use current system time as default seed)
    private List<String> deck;              // Declare deck variable
    private Random rando;                  //Declare random generator
    
    /**
     * Constructor - Initialize the card deck and random generator
     */
    public CardDeckShuffle() {
        // PSEUDOCODE: Call initializeDeck() and create Random object
        //Initialize the deck
        initializeDeck();
        // Create new Random() object                   
        this.rando = new Random();      
    }
    
    /**
     * STEP 1: Initialize the Deck
     * 1. Create an empty list called "deck"
     * 2. Create arrays for suits = ["Hearts", "Diamonds", "Clubs", "Spades"]
     * 3. Create arrays for ranks = ["A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"]
     * 4. FOR each suit in suits:
     *    FOR each rank in ranks:
     *      Add "rank of suit" to deck
     * 5. Result: deck now contains 52 cards in order
     */
    private void initializeDeck() {
        // Step 1: Create empty list
        deck = new ArrayList<>();             
        
        // Step 2: Create suits array
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        
        // Step 3: Create ranks array  
        String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        
        // Step 4: FOR each suit in suits:
        for (String suit : suits) {
            // FOR each rank in ranks:
            for (String rank : ranks) {
                // Add that whole card to deck
                deck.add(rank + " of " + suit);
            }
        }
    }
    
    /**
     * STEP 3: Shuffle Method with New Seed Each Time
     * 1. Generate new seed = current_time_milliseconds + current_time_nanoseconds
     * 2. Set the random generator's seed to this new value
     * 3. Print "Shuffling with seed: [seed_value]"
     * 4. FOR i from (deck_size - 1) down to 1:
     *    a. Generate random number j between 0 and i (inclusive)
     *    b. Swap deck[i] with deck[j]
     */
    public void shuffleDeck() {
        // Step 1: Generate new seed
        long newSeed = System.currentTimeMillis() + System.nanoTime();
        
        // Step 2: Set the random generator's seed
        rando.setSeed(newSeed);   
        
        // Step 3: Print shuffling message
        System.out.println("Shuffling with seed: " + newSeed);
        
      
        // Step 4: FOR i from (deck_size - 1) down to 1:
        for (int i = deck.size() -1 ; i > 0; i--) { 
            // Step 4a: Generate random number j [0,i) --> we need the i+1 for inclusivity
            int j = rando.nextInt(i + 1); 
            
            // Step 4b: Swap deck[i] with deck[j]
            String temp = deck.get(i);  
            deck.set(i, deck.get(j));    
            deck.set(j, temp);  //now i holds j and j holds i
        }
    }
    
    /**
     * STEP 4: Shuffle Method with Specific Seed
     * 1. Reset deck to original order (call step 1 again)
     * 2. Set random generator's seed to specific_seed
     * 3. Print "Shuffling with seed: [specific_seed]"
     * 4. FOR i from (deck_size - 1) down to 1:
     *    a. Generate random number j between 0 and i (inclusive)
     *    b. Swap deck[i] with deck[j]
     */
    public void shuffleDeckWithSeed(long seed) {
        // Step 1: Reset deck to original order
        initializeDeck();
        
        // Step 2: Set random generator's seed to specific_seed
        rando.setSeed(seed);    // YOUR CODE: seed
        
        // Step 3: Print shuffling message
        System.out.println("Shuffling with specific seed: " + seed);

        // Step 4: Fisher-Yates shuffle algorithm (same as above)
        for (int i = deck.size() - 1; i > 0; i--) { 
            int j = rando.nextInt(i + 1);        
            String temp = deck.get(j);   
            deck.set(j, deck.get(i));    
            deck.set(i, temp);    
        }
    }
    
    /**
     * STEP 5: Display Methods
     * PSEUDOCODE for showTopCards(number_of_cards):
     * 1. Print "Top [number_of_cards] cards:"
     * 2. FOR i from 0 to min(number_of_cards, deck_size):
     *    Print position_number + ". " + deck[i]
     */
    public void showTopCards(int numCards) {
        // Step 1: Print however many cards inputted
        System.out.println("Top " + numCards + " cards:");
        
        // Step 2: FOR i from 0 to min(numCards, deck_size):
        for (int i = 0; i < Math.min(numCards, deck.size()); i++) {   //takes the smaller val to loop due to deck size constraint
            // Print the card at that position, shifting by 1 to prevent 0-index based 
            System.out.println((i + 1) + ". " + deck.get(i));
        }
        System.out.println(); // Empty line
    }
    
    /**
     * PSEUDOCODE for showFullDeck():
     * 1. Print "Complete deck configuration:"
     * 2. FOR i from 0 to deck_size:
     *    Print position + ". " + card_name
     *    IF position is multiple of 4: print new line
     */
    public void showFullDeck() {
        // Step 1: Print header
        System.out.println("Complete deck configuration:");
        
        // Step 2: FOR i from 0 to deck_size:
        for (int i = 0; i < deck.size(); i++) { 
    
            System.out.printf("%2d. %-15s", (i + 1), deck.get(i));
            
            // IF position is multiple of 4: print new line --THIS IS FOR ORGANIZATION
            if ((i + 1) % 4 == 0) {
                System.out.println();
            }
        }
        System.out.println("\n");
    }
    
    /**
     * Helper method to reset deck
     */
    public void resetDeck() {
        initializeDeck();
        System.out.println("Deck reset to original order.\n");
    }
    
    /**
     * STEP 6: Main Demonstration Logic
     * 1. Create new CardDeckShuffle object
     * 2. Show original deck order (first 10 cards)
     * 3. DEMONSTRATION 1 - Multiple Random Shuffles
     * 4. DEMONSTRATION 2 - Reproducible Shuffles  
     * 5. DEMONSTRATION 3 - Different Seeds
     */
    public static void main(String[] args) {
        // Step 1: Create new CardDeckShuffle object
        CardDeckShuffle cardGame = new CardDeckShuffle();

        System.out.println("Random Seed Demonstration using Fisher-Yates Shuffle using one deck of cards\n");

        // Step 2: Show original deck order (first 10 cards)
        System.out.println("Original deck order:");
        cardGame.showTopCards(10);
        
        // DEMONSTRATION 1 - Multiple Random Shuffles:
        // FOR shuffle_number from 1 to 3:
        System.out.println("--- Multiple Shuffles with Random Seeds ---");
        for (int i = 1; i <= 3; i++) {
            System.out.println("Shuffle #" + i + ":");
            // Call shuffleDeck() - uses new seed each time
            cardGame.shuffleDeck();
            // Show top 8 cards
            cardGame.showTopCards(8);

            // Wait brief moment (so timestamps differ)
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        
        // DEMONSTRATION 2 - Reproducible Shuffles:
        // Set fixed seed using L to make sure that its intentionally a long
        System.out.println("--- Reproducible Shuffles with Same Seed ---");
        long fixedSeed = 12345L;
        
        System.out.println("First shuffle with seed " + fixedSeed + ":");
        // Call shuffleDeckWithSeed(fixed_seed)
        cardGame.shuffleDeckWithSeed(fixedSeed);
        // Show top 8 cards
        cardGame.showTopCards(8);

        System.out.println("Second shuffle with same seed " + fixedSeed + ":");
        // Call shuffleDeckWithSeed(fixed_seed) again
        cardGame.shuffleDeckWithSeed(fixedSeed);
        // Show top 8 cards
        cardGame.showTopCards(8);   
        
        System.out.println("Notice: Same seed produces identical shuffle!");
        
        // DEMONSTRATION 3 - Different Seeds:
        // Create array of seeds = [1000, 2000, 3000]
        System.out.println("\n--- Different Seeds, Different Results ---");
        long[] seeds = {1000L, 2000L, 3000L};
        
        // FOR each seed in array call shuffleDeckWithSeed(seed)
        for (long seed : seeds) {
            cardGame.shuffleDeckWithSeed(seed);
            // Show first 5 cards with seed label
            System.out.print("Seed " + seed + " - Top 5: ");
            for (int i = 0; i < 5; i++) {
                System.out.print(cardGame.deck.get(i) + " | ");
            }
            System.out.println("\n");
        }
    }
}