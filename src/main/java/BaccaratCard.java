
public class BaccaratCard extends Card{


    public BaccaratCard(Rank r, Suit s) {
        // Call the constructor of the Card class
        super(r, s);
        
    }

    // We dont need to define getRank or getSuit as thye are already in the superclass
    // Same for the toString the card class does what we want it to do

    @Override
    public int value() {
        if (this.getRank() == Rank.KING ||this.getRank() == Rank.JACK || this.getRank() ==Rank.QUEEN || this.getRank() ==Rank.TEN){
            return 0 ;
        } else {
            // This covers Tthe rest
            return Math.min(this.getRank().ordinal() + 1, 10);
        }
        
        }


}