import java.util.ArrayList;
import java.util.List;

public class Hand {
    private final int NUMBER_OF_CARDS = 5;

    private final List<Card> cards;


    public Hand() {
        this.cards = new ArrayList<>(NUMBER_OF_CARDS);
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return cards;
    }
}
