package games;

import static games.Drunkard.getParsTotalCount;

public class Card {

    private Suit suit;

    private Par par;

    public Card(Suit suit, Par par) {
        this.suit = suit;
        this.par = par;
    }

    public Card() {
        this.suit = suit;
        this.par = par;
    }

    private static Suit getSuit(int cardNumber) {
        return Suit.values()[cardNumber / getParsTotalCount()];
    }

    private static Par getPar(int cardNumber) {
        return Par.values()[cardNumber % getParsTotalCount()];
    }

    public static int getCardDignity(Card card) {
        return card.par.ordinal();
    }

    public static Card createCard(int cardNumber) {
        return new Card(getSuit(cardNumber), getPar(cardNumber));
    }

    @Override
    public String toString() {
        return par + "_" + suit;
    }
}

