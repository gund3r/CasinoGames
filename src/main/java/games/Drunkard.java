package games;

import java.awt.*;
import java.util.*;
import java.util.List;

class Drunkard {

    private static final int PARS_TOTAL_COUNT = Par.values().length;

    private static final int CARDS_TOTAL_COUNT = PARS_TOTAL_COUNT * Suit.values().length; //36

    private static LinkedList<String> deckOfCards = new <String> LinkedList();

    private static LinkedList<String> player1DeckOfCards = new <String> LinkedList();

    private static LinkedList<String> player2DeckOfCards = new <String> LinkedList();

    public static List<String> getDeckOfCards() {
        return deckOfCards;
    }

    private static Suit getSuit(int cardNumber) {
        return Suit.values()[cardNumber / PARS_TOTAL_COUNT];
    }

    public static List<String> getPlayer1DeckOfCards() {
        return player1DeckOfCards;
    }

    public static List<String> getPlayer2DeckOfCards() {
        return player2DeckOfCards;
    }

    private static Par getPar(int cardNumber) {

        return Par.values()[cardNumber % PARS_TOTAL_COUNT];
    }

    private static String createCard(int cardNumber) {
        return getPar(cardNumber) + "_" + getSuit(cardNumber);
    }

    private static void createDeckOfCards() {

        for (int i = 0; i < CARDS_TOTAL_COUNT; i++) {
            getDeckOfCards().add(createCard(i));
        }
    }

    private static void dealCards() {
        for (int i = 0; i < CARDS_TOTAL_COUNT / 2; i++) {
            player1DeckOfCards.add(deckOfCards.get(i));
        }
        for (int y = CARDS_TOTAL_COUNT / 2; y < CARDS_TOTAL_COUNT; y++) {
            player2DeckOfCards.add(deckOfCards.get(y));
        }
    }
/***
    private static void stepOfGame() {
        while (player2DeckOfCards.isEmpty() || player1DeckOfCards.isEmpty()) {
            int i = 0;
            i++;
            if (player1DeckOfCards.get(i))
        }
    }
***/

    public static void main(String... __) {

        createDeckOfCards();

/***        Collections.shuffle(getDeckOfCards());

        dealCards();

        int a = 0;
        for (String str: getPlayer1DeckOfCards()) {
            a++;
            System.out.println(a + " - card of player1 - " + str);
        }

        System.out.println("-------------");

        for (String str: getPlayer2DeckOfCards()) {
            a++;
            System.out.println(a + " - card of player2 - " + str);
        }
 ***/
        int a = 0;
        for (String str: getDeckOfCards()) {
            a++;
            System.out.println(a + " - card of player1 - " + str);
        }

        System.out.println(getPar(5).compareTo(getPar(4)));


    }
}

enum Suit {
    SPADES, // пики
    HEARTS, // червы
    CLUBS, // трефы
    DIAMONDS // бубны
}

enum Par {
    SIX,
    SEVEN,
    EIGHT,
    NINE,
    TEN,
    JACK, // Валет
    QUEEN, // Дама
    KING, // Король
    ACE // Туз
}
