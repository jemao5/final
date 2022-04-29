public class Main {
    public static void main(String[] args){
        Player[] players = setup();
    }

    public static Player[] setup(){
        return(new Player[]{new Player(), new Player()});
    }

    public static void deal(Player[] players){
        Deck deck = new Deck();
        for(int i = 0; i < 52; i+=2){
            players[0].put(deck.getCard());
            players[1].put(deck.getCard());
        }
    }

    public static void playRound(Player[] players){
        Card p0Card = players[0].get();
        Card p1Card = players[1].get();
        if(!(p0Card.equals(p1Card))){
            if(p0Card.compareTo(p1Card) > 0){ // Player 0 has greater card
                if(p0Card.compareTo(p1Card) != 1){
                    System.out.println("Player 0 wins!");
                    players[0].put(p0Card);
                    players[0].put(p1Card);
                } else { // Notched
                    System.out.println("Player 1 wins!");
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
                        System.out.println("Player 0 wins!");
                        players[0].put(p0Card);
                        players[0].put(p1Card);
                    }
                }
            }
        } else {
            war(players);
        }
    }

    public static int war(Player[] players){
        Card[] p0Cards = new Card[]{players[0].get(), players[0].get(), players[0].get(), players[0].get()};
        Card[] p1Cards = new Card[]{players[1].get(), players[1].get(), players[1].get(), players[1].get()};
        int winner = 0;
        int counter = 3;
        while(winner == 0){
            if(counter < 0){
                winner = war(players);
            }
            winner = (p0Cards[counter].compareTo(p1Cards[counter]));
            counter--;
        }
        if(winner > 0){
            players[0].put(p0Cards[0]);
            players[0].put(p0Cards[1]);
            players[0].put(p0Cards[2]);
            players[0].put(p0Cards[3]);
        } else {
            players[1].put(p0Cards[0]);
            players[1].put(p0Cards[1]);
            players[1].put(p0Cards[2]);
            players[1].put(p0Cards[3]);
        }
        return(winner);
    }
}
