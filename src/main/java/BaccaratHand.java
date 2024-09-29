
public class BaccaratHand extends CardCollection {

    public BaccaratHand() {
        super();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Card card : cards) {
            if (sb.length() > 0) {
                // Add a space between card representations
                sb.append(" ");
            }
            // Append the two-character representation of the card
            sb.append(card.toString());
        }
        return sb.toString();
    }

    @Override
    public int value() {
        // Calculate the total value of the hand according to Baccarat rules
        int sum = 0;
        for (Card card : cards) {
            sum += card.value();
        }
        // In Baccarat, only the rightmost digit of the sum matters
        return sum % 10;
    }

    public boolean isNatural() {
        // Check if the hand has exactly two cards and their value is 8 or 9
        return (cards.size() == 2) && (value() == 8 || value() == 9);
    }

    
}