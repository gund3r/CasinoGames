package games;

import java.util.*;

public class Drunkard {

    private static final int PARS_TOTAL_COUNT = Par.values().length;

    private static final int CARDS_TOTAL_COUNT = PARS_TOTAL_COUNT * Suit.values().length; //36

    private static int[] playerCardHeads = new int[2];

    private static int[] playerCardTails = new int[2];
    
    private static Suit getSuit(int cardNumber) {
        return Suit.values()[cardNumber / PARS_TOTAL_COUNT];
    }

    private static Par getPar(int cardNumber) {
        return Par.values()[cardNumber % PARS_TOTAL_COUNT];
    }

    private static String toString(int cardNumber) {
        return getPar(cardNumber) + " " + getSuit(cardNumber);
    }

    private static int incrementIndex(int i) {
      return (i + 1) % CARDS_TOTAL_COUNT;
    }

    private static boolean playerCardsIsEmpty(int playerIndex) {
      int tail = playerCardTails[playerIndex];
      int head = playerCardHeads[playerIndex];

      return tail == head;
    }

    private static String (int cardNumber1, int cardNumber2) {
      
    }

    private static void startGame() {
      System.out.printf("Итерация №1 Игрок №1 карта: %s; игрок №2 карта: %s.%n", player1.toString(), player2.toString()); 
      System.out.printf("Выиграл %s!%n", whoWin()); 
      System.out.printf("У игрока №1 %s карт, у игрока №2 %s карт%n", player1.playerCardHeads(), player2.playerCardHeads());
    }

    public static void main(String... __) {

        System.out.println(toString(35));

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