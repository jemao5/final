import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args){
        Player[] players = setup(); // create array of 2 players
        deal(players); // deals 26 cards to each player
        playNotchWar(players);
    }

    public static Player[] setup(){ // Sets up the game by creating an array of 2 players
        return(new Player[]{new Player(), new Player()});
    }

    public static void deal(Player[] players){
        Deck deck = new Deck(); // creates deck to deal from
        deck.shuffle(); //shuffles deck
        for(int i = 0; i < 52; i+=2){ // distributes cards
            players[0].put(deck.getCard());
            players[1].put(deck.getCard());
        }
    }

    public static void playRound(Player[] players){
        Card p0Card = players[0].get(); // Stores player 1's top card
        Card p1Card = players[1].get(); // Stores player 2's top card
        int winner = 500; // Initializes the winner number to a random number to get rid of initialization error
        System.out.println("Player 0 has " + p0Card.toString() + ". Player 1 has " + p1Card.toString() + ".");
        if(!(p0Card.equals(p1Card))){ // Checks for ties -> war
            if(p0Card.compareTo(p1Card) > 0){ // Player 0 has greater card
                if(p0Card.compareTo(p1Card) != 1){ // Checks for Notch
                    System.out.println("Player 0 wins!");
                    winner = 0;
                } else { // Notched
                    System.out.println("Notched! Player 1 wins!");
                    winner = 1;
                }
            } else {
                if (p0Card.compareTo(p1Card) < 0) { // Player 1 has greater card
                    if (p0Card.compareTo(p1Card) != -1) { // Checks for Notch
                        System.out.println("Player 1 wins!");
                        winner = 1;
                    } else { // Notched
                        System.out.println("Notched! Player 0 wins!");
                        winner = 0;
                    }
                }
            }
        } else { // Tie -> war
            if(war(players, p0Card, p1Card)){
                winner = 0;
            } else {
                winner = 1;
            }
        }
        players[winner].put(p0Card); // places cards at bottom of winner deck
        players[winner].put(p1Card);
        System.out.println(); // prints new line
    }

    public static Boolean war(Player[] players, Card p0Removed, Card p1Removed){ // returns boolean to easily tell who won. {True} -> P0, {False} -> P1
        int diff = 0; // initializes difference between cards to 0
        int counter = 1; // counter for number of loops. Each loop constitutes a war.
        int p0InitialSize = players[0].size(); // Variables to check for edge case
        int p1InitialSize = players[1].size(); // Variables to check for edge case
        ArrayList<Card> p0Used = new ArrayList<>(); // stores the cards removed from player 0's hand
        ArrayList<Card> p1Used = new ArrayList<>(); // stores the cards removed from player 1's hand
        while(diff == 0){ // while the cards are at a tie
            System.out.println("WAR!");
            System.out.println("Round " + counter);
            counter++;
            int p0Size = Math.min(players[0].size(), 4); // takes the minimum between the number of cards in player 0's hand and 4.
            int p1Size = Math.min(players[1].size(), 4); // takes the minimum between the number of cards in player 1's hand and 4.
            if(p0InitialSize == 0) { // Handles edge case where there is a war when a player has only 1 card.
                p0Used.add(p0Removed);
            } else {
                for (int i = 0; i < p0Size; i++) { // Using the minimum, remove that many cards from the players' hands and store them in the respective arraylists.
                    p0Used.add(players[0].get());
                }
            }
            if(p1InitialSize == 0) { // Handles edge case where there is a war when a player has only 1 card.
                p1Used.add(p1Removed);
            } else {
                for (int i = 0; i < p1Size; i++) {
                    p1Used.add(players[1].get());
                }
            }
            System.out.println(p0Used.get(p0Used.size() - 1).toString() + " vs. " + p1Used.get(p1Used.size() - 1).toString());
            diff = p0Used.get(p0Used.size() - 1).compareTo(p1Used.get(p1Used.size() - 1)); // compares the last card in each array.
        }
        boolean tf;
        tf = (((diff > 0) && diff != 1) || diff == -1); // Checks who won and stores the corresponding TF value.
        int winner = tf ? 0 : 1; // Stores who the winner is a number

        if(diff == 1 || diff == -1){ // Changes the print statement based on whether there was a notch or not.
            System.out.println("Notched! Player " + winner + " wins!");
        } else {
            System.out.println("Player " + winner + " wins!");
        }

        if(p0InitialSize != 0){ // Handles edge case where there is a war when a player has only 1 card.
            for (Card card : p0Used) { // Moves the cards from the two used card arrays to the winner's hand.
                players[winner].put(card);
            }
        }
        if(p1InitialSize != 0) { // Handles edge case where there is a war when a player has only 1 card.
            for (Card card : p1Used) {
                players[winner].put(card);
            }
        }

        return(tf); // returns the Boolean value for the winner.
    }

    public static void playNotchWar(Player[] players){
        System.out.println("Game Start");
        while(players[0].size() != 0 && players[1].size() != 0){ // Continues running until a player is out of cards.
            System.out.println("Player 0 has " + players[0].size() + " cards.");
            System.out.println("Player 1 has " + players[1].size() + " cards.");
            playRound(players);
        }
        int winner = (players[0].size() == 0) ? 1 : 0; // Finds the winner.
        System.out.println("Game Over!");
        System.out.println("Player " + winner + " wins the game!");
    }
}
