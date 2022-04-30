import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        Player[] players = setup();
        deal(players);
        playNotchWar(players);
    }

    public static Player[] setup(){
        return(new Player[]{new Player(), new Player()});
    }

    public static void deal(Player[] players){
        Deck deck = new Deck();
        deck.shuffle();
        for(int i = 0; i < 52; i+=2){
            players[0].put(deck.getCard());
            players[1].put(deck.getCard());
        }
    }

    public static void playRound(Player[] players){
        Card p0Card = players[0].get();
        Card p1Card = players[1].get();
        int winner = 500;
        System.out.println("Player 0 has " + p0Card.toString() + ". Player 1 has " + p1Card.toString() + ".");
        if(!(p0Card.equals(p1Card))){
            if(p0Card.compareTo(p1Card) > 0){ // Player 0 has greater card
                if(p0Card.compareTo(p1Card) != 1){
                    System.out.println("Player 0 wins!");
                    winner = 0;
                } else { // Notched
                    System.out.println("Notched! Player 1 wins!");
                    winner = 1;
                }
            } else {
                if (p0Card.compareTo(p1Card) < 0) { // Player 1 has greater card
                    if (p0Card.compareTo(p1Card) != -1) {
                        System.out.println("Player 1 wins!");
                        winner = 1;
                    } else { // Notched
                        System.out.println("Notched! Player 0 wins!");
                        winner = 0;
                    }
                }
            }
        } else {
            if(war(players)){
                winner = 0;
            } else {
                winner = 1;
            }
        }
        players[winner].put(p0Card);
        players[winner].put(p1Card);
        System.out.println();
    }

    public static Boolean war(Player[] players){
        int diff = 0;
        int counter = 1;
        ArrayList<Card> p0Used = new ArrayList<>();
        ArrayList<Card> p1Used = new ArrayList<>();
        while(diff == 0){
            System.out.println("WAR!");
            System.out.println("Round " + counter);
            counter++;
            int p0Size = Math.min(players[0].size(), 4);
            int p1Size = Math.min(players[1].size(), 4);
            for(int i = 0; i < p0Size; i++){
                p0Used.add(players[0].get());
            }
            for(int i = 0; i < p1Size; i++){
                p1Used.add(players[1].get());
            }
            System.out.println(p0Used.get(p0Used.size() - 1).toString() + " vs. " + p1Used.get(p1Used.size() - 1).toString());
            diff = p0Used.get(p0Used.size() - 1).compareTo(p1Used.get(p1Used.size() - 1));
        }

        boolean tf;
        tf = (((diff > 0) && diff != 1) || diff == -1);
        int winner = tf ? 0 : 1;

        if(diff == 1 || diff == -1){
            System.out.println("Notched! Player " + winner + " wins!");
        } else {
            System.out.println("Player " + winner + " wins!");
        }

        for (Card value : p0Used) {
            players[winner].put(value);
        }
        for (Card card : p1Used) {
            players[winner].put(card);
        }

        return(tf);
    }

    public static void playNotchWar(Player[] players){
        System.out.println("Game Start");
        while(players[0].size() != 0 && players[1].size() != 0){
            System.out.println("Player 0 has " + players[0].size() + " cards.");
            System.out.println("Player 1 has " + players[1].size() + " cards.");
            playRound(players);
        }
        int winner = (players[0].size() == 0) ? 1 : 0;
        System.out.println("Game Over!");
        System.out.println("Player " + winner + " wins the game!");
    }
}
