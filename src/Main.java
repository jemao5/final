import java.util.Arrays;

public class Main {
    public static void main(String[] args){
        Player[] players = setup();
        deal(players);
        System.out.println(players[0].toString());
        System.out.println(players[1].toString());
//        while(players[0].size() != 0 && players[1].size() != 0){
//            playRound(players);
//            System.out.println(players[0].toString());
//            System.out.println(players[1].toString());
//        }
        for(int i = 0; i < 60; i++){
            playRound(players);
//            System.out.println(players[0].toString());
//            System.out.println(players[1].toString());
            assert(players[0].size() > 1);
            assert(players[1].size() > 1);
        }

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
        System.out.println(p0Card.toString() + " vs. " + p1Card.toString());
        if(!(p0Card.equals(p1Card))){
            if(p0Card.compareTo(p1Card) > 0){ // Player 0 has greater card
                if(p0Card.compareTo(p1Card) != 1){
                    System.out.println("Player 0 wins!");
                    players[0].put(p0Card);
                    players[0].put(p1Card);
                } else { // Notched
                    System.out.println("Notched! Player 1 wins!");
                    players[1].put(p0Card);
                    players[1].put(p1Card);
                }
            } else {
                if (p0Card.compareTo(p1Card) < 0) { // Player 1 has greater card
                    if (p0Card.compareTo(p1Card) != 1) {
                        System.out.println("Player 1 wins!");
                        players[1].put(p0Card);
                        players[1].put(p1Card);
                    } else { // Notched
                        System.out.println("Notched! Player 0 wins!");
                        players[0].put(p0Card);
                        players[0].put(p1Card);
                    }
                }
            }
        } else {
            System.out.println(players[0].toString());
            System.out.println(players[1].toString());
            System.out.println("War!");
            war(players);
            System.out.println(players[0].toString());
            System.out.println(players[1].toString());
        }
    }

    public static int war(Player[] players){
        int p0Size = Math.min(players[0].size(), 4);
        int p1Size = Math.min(players[0].size(), 4);
        Card[] p0Cards = new Card[p0Size];
        Card[] p1Cards = new Card[p1Size];

        for(Card c : p0Cards){
            c = players[0].get();
        }
        for(Card c : p1Cards){
            c = players[1].get();
        }

        int p0Index = p0Size - 1;
        int p1Index = p1Size - 1;



        int winner = 0;
        int counter = 0;
        while(winner == 0){
            System.out.println(p0Cards[counter].toString() + " vs. " + p1Cards[counter]);
            if(counter > 3){
                winner = war(players);
            }
            winner = (p0Cards[counter].compareTo(p1Cards[counter]));
            if(winner == 0){
                System.out.println("Tie! Next Card");
            }
            counter++;
        }
        if((winner > 0 && winner != 1) || winner == -1){
            System.out.println("Player 0 wins!");
            players[0].put(p0Cards[0]);
            players[0].put(p0Cards[1]);
            players[0].put(p0Cards[2]);
            players[0].put(p0Cards[3]);
            players[0].put(p1Cards[0]);
            players[0].put(p1Cards[1]);
            players[0].put(p1Cards[2]);
            players[0].put(p1Cards[3]);
        } else {
            System.out.println("Player 1 wins!");
            players[1].put(p0Cards[0]);
            players[1].put(p0Cards[1]);
            players[1].put(p0Cards[2]);
            players[1].put(p0Cards[3]);
            players[1].put(p1Cards[0]);
            players[1].put(p1Cards[1]);
            players[1].put(p1Cards[2]);
            players[1].put(p1Cards[3]);
        }
        return(winner);
    }
}
