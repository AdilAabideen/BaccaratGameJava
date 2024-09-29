import java.util.Scanner;

public class Baccarat {

private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean interactiveMode = args.length > 0 && (args[0].equals("-i") || args[0].equals("--interactive"));
        Shoe shoe = new Shoe(6);
        shoe.shuffle();

        int roundCount = 0;
        int playerWins = 0;
        int bankerWins = 0;
        int ties = 0;
        // Initial flag to control the game loop
        boolean continuePlaying = true;

        while (shoe.size() >= 6 && continuePlaying) {
            roundCount++;
            System.out.println("");
            System.out.println("Round " + roundCount);
    
            // Generate Hands
            BaccaratHand playerHand = new BaccaratHand();
            BaccaratHand bankerHand = new BaccaratHand();
    
            // Process game 
            dealInitialCards(shoe, playerHand, bankerHand);
            printHands(playerHand, bankerHand);
    
            String roundResult = determineRoundResult(playerHand, bankerHand, shoe);
            System.out.println(roundResult);
    
            // Update win/tie counts based on round result
            switch (roundResult) {
                case "Player win!":
                    playerWins++;
                    break;
                case "Banker win!":
                    bankerWins++;
                    break;
                case "Tie":
                    ties++;
                    break;
            }
    
            // In interactive mode, ask the user whether they want to continue at the end of a round
            if (interactiveMode) {
                // Update the flag based on user input
                continuePlaying = userWantsToContinue();
            }
        }
    
        // Output the game summary
        System.out.println(roundCount + " rounds played");
        System.out.println(playerWins + " player wins");
        System.out.println(bankerWins + " banker wins");
        System.out.println(ties + " ties");
    }

    // This function deals cards to each player hand
    private static void dealInitialCards(Shoe shoe, BaccaratHand playerHand, BaccaratHand bankerHand) {
        playerHand.add(shoe.deal());
        bankerHand.add(shoe.deal());
        playerHand.add(shoe.deal());
        bankerHand.add(shoe.deal());
    }

    private static void printHands(BaccaratHand playerHand, BaccaratHand bankerHand) {
        System.out.println("Player: " + playerHand + " = " + playerHand.value());
        System.out.println("Banker: " + bankerHand + " = " + bankerHand.value());
    }

    private static String determineRoundResult(BaccaratHand playerHand, BaccaratHand bankerHand, Shoe shoe) {
        // Check for naturals first
        if (playerHand.isNatural() || bankerHand.isNatural()) {
            if (playerHand.value() > bankerHand.value()) {
                return "Player win!";
            } else if (playerHand.value() < bankerHand.value()) {
                return "Banker win!";
            } else {
                return "Tie";
            }
        }

        // Player draws third card if total is 0-5
        if (playerHand.value() <= 5) {
            playerHand.add(shoe.deal());
            System.out.println("Dealing third card to player...");
            // Assuming this method prints the updated hands
            printHands(playerHand, bankerHand);
        }

        // Banker's turn to draw a third card based on the simplified rules
        if (bankerHand.value() <= 2 || (bankerHand.value() <= 6 && playerHand.size() == 3)) {
            bankerHand.add(shoe.deal());
            System.out.println("Dealing third card to banker...");
            // Assuming this method prints the updated hands
            printHands(playerHand, bankerHand);
        }
  
        // Determine the winner after all cards are dealt
        if (playerHand.value() > bankerHand.value()) {
            return "Player win!";
        } else if (playerHand.value() < bankerHand.value()) {
            return "Banker win!";
        } else {
            return "Tie";
        }
    }

    // If user wants To countinues
    private static boolean userWantsToContinue() {
        System.out.print("Another round? (y/n): ");
        String input = scanner.nextLine();
        return input.equalsIgnoreCase("y");
    }
}
