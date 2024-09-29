
import java.util.Collections;

public class Shoe extends CardCollection{

    public Shoe(int decks) {
        // Calls the CardCollection constructor to initialize the cards list
        super();
        if (decks != 6 && decks != 8) {
            throw new CardException("Invalid number of decks. Only 6 or 8 decks are allowed.");
        }
        
        // Loop to add the specified number of complete decks of BaccaratCard objects
        for (int deck = 0; deck < decks; deck++) {
            for (Card.Suit suit : Card.Suit.values()) {
                for (Card.Rank rank : Card.Rank.values()) {
                    // Add a new BaccaratCard to the Shoe
                    this.add(new BaccaratCard(rank, suit));
                }
            }
        }
        // Do not shuffle the cards here since the constructor should not reorder them
    }

    public void shuffle() {
        // Randomly permute the list of cards
        Collections.shuffle(cards);
    };

    public Card deal() {
        if (isEmpty()) {
            // If there are no cards left in the shoe, throw a CardException
            throw new CardException("Cannot deal from an empty shoe.");
        } else {
            // Remove the first card from the collection and return it
            return cards.remove(0);
        }
    }
}