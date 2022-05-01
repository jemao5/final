import java.util.LinkedList;

public class Hand<E> extends LinkedList<E> { // Previously was Thing1. Renamed hand because it contains the cards a player has.
    public Hand() {}

    public void put(E o) {
        addLast(o);
    }

    public E get() {
        if (!this.isEmpty()) {
            return removeFirst();
        } else {
            System.err.println("You can't do that!");
            return null;
        }
    }

    public E peek() {
        return getFirst();
    }
}