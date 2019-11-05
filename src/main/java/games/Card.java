package games;

public class Card {

    public static final int PARS_TOTAL_COUNT = Par.values().length;

    public static final int CARDS_TOTAL_COUNT = PARS_TOTAL_COUNT * Suit.values().length; //36

    private Suit suit;

    private Par par;

    public Card(Suit suit, Par par) {
        this.suit = suit;
        this.par = par;
    }

    static Suit getSuit(int cardNumber) {
        return Suit.values()[cardNumber / PARS_TOTAL_COUNT];
    }

    static Par getPar(int cardNumber) {
        return Par.values()[cardNumber % PARS_TOTAL_COUNT];
    }

    static int getCardDignity(Card card) {
        return card.par.ordinal();
    }

    static Card createCard(int cardNumber) {
        return new Card(getSuit(cardNumber), getPar(cardNumber));
    }

    @Override
    public String toString() {
        return par + "_" + suit;
    }

    static int cardValue(int cardNumber) {
        switch (getPar(cardNumber)) {
            case JACK: return 2;
            case QUEEN: return 3;
            case KING: return 4;
            case SIX: return 6;
            case SEVEN: return 7;
            case EIGHT: return 8;
            case NINE: return 9;
            case TEN: return 10;
            case ACE:
            default: return 11;
        }
    }
}

