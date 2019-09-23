package games;

import org.apache.commons.math3.util.MathArrays;
import java.io.IOException;

import static games.Card.*;
import static games.Choice.getCharacterFromUser;

public class BlackJack {

    private static final int MAX_VALUE = 21;

    private static final int MAX_CARDS_COUNT = 8;

    private static int[] playersMoney = {100, 100};

    private static int[][] playersCards;

    private static int[] cards;

    private static int[] playerCardsCursor;

    private static int cursorOfDeck;

    private static int bet = 10;

    private static String printCard(int cardNumber) {
        return getSuit(cardNumber) + "_" + getPar(cardNumber);
    }

    public static int getFirstPlayerMoney() {
        return playersMoney[0];
    }

    public static int getSecondPlayerMoney() {
        return playersMoney[1];
    }

    static int getCursorOfDeck() {
        return cursorOfDeck;
    }

    static void increaseCursorOfDeck() {
        cursorOfDeck += 1;
    }

    static void increasePlayersCardCursor(int player) {
        switch (player) {
            case 1:
                playerCardsCursor[0] += 1;
                break;
            case 2:
                playerCardsCursor[1] += 1;
                break;
        }
    }

    public static int getBet() {
        return bet;
    }

    static int[] getShuffledCards() {
        // колода подряд
        int[] cards = {
                0, 1, 2, 3, 4, 5, 6, 7, 8,  // бубны
                9, 10, 11, 12, 13, 14, 15, 16, 17,  // червы
                18, 19, 20, 21, 22, 23, 24, 25, 26,  // трефы
                27, 28, 29, 30, 31, 32, 33, 34, 35}; // пики

        MathArrays.shuffle(cards);
        return cards;
    }

    static boolean confirm() throws IOException {
        System.out.println("Берем еще? \"Y\" - Да, {любой другой символ} - нет (Чтобы выйти из игры, нажмите Ctrl + C)");
        switch (getCharacterFromUser()) {
            case 'Y':
            case 'y': return true;
            default: return false;
        }
    }

    private static void initRound() {
        System.out.printf("У Вас %d$, у компьютера - %d$. Начинаем новый раунд!%n", getFirstPlayerMoney(), getSecondPlayerMoney());
        cards = getShuffledCards();
        playersCards = new int[2][MAX_CARDS_COUNT];
        playerCardsCursor = new int[]{0, 0};
        cursorOfDeck = 0;
    }

    private static int getCard() {
        return cards[getCursorOfDeck()];
    }

    private static int getFirstPlayerSum() {
        int result = 0;
        for (int j = 0; j < playersCards[0].length; j++) {
            result = result + playersCards[0][j];
        }
        return result;
    }

    private static int getSecondPlayerSum() {
        int result = 0;
        for (int j = 0; j < playersCards[1].length; j++) {
            result = result + playersCards[1][j];
        }
        return result;
    }

    private static void addCardToPlayer(int player) {
        switch (player) {
            case 1: playersCards[0][playerCardsCursor[0]] = cardValue(getCard());
            case 2: playersCards[1][playerCardsCursor[1]] = cardValue(getCard());
        }
    }

    static void firstPlayerRound() throws IOException {
        addCardToPlayer(1);
        System.out.printf("Вам выпала карта %s%n", printCard(getCard()));
        increasePlayersCardCursor(1);
        increaseCursorOfDeck();
        addCardToPlayer(1);
        System.out.printf("Вам выпала карта %s%n", printCard(getCard()));
        increasePlayersCardCursor(1);
        increaseCursorOfDeck();
        System.out.printf("У вас %d%n", getFirstPlayerSum());
        while (confirm()) {
            addCardToPlayer(1);
            System.out.printf("Вам выпала карта %s%n", printCard(getCard()));
            System.out.printf("У вас %d%n", getFirstPlayerSum());
            increasePlayersCardCursor(1);
            increaseCursorOfDeck();
            }
        }

    static void secondPlayerRound() {
        addCardToPlayer(2);
        System.out.printf("Компьютеру выпала карта %s%n", printCard(getCard()));
        increasePlayersCardCursor(2);
        increaseCursorOfDeck();
        addCardToPlayer(2);
        System.out.printf("Компьютеру выпала карта %s%n", printCard(getCard()));
        increasePlayersCardCursor(2);
        increaseCursorOfDeck();
        System.out.printf("У Компьютера %s очков.%n", getSecondPlayerSum());
        do {
            addCardToPlayer(2);
            System.out.printf("Компьютер решил взять еще одну карту %s%n", printCard(getCard()));
            increaseCursorOfDeck();
            increasePlayersCardCursor(2);
        } while (getSecondPlayerSum() < 15);
    }

    static void startGame() throws IOException {
        while(getFirstPlayerMoney() > 0) {
            initRound();
            firstPlayerRound();
            secondPlayerRound();
            if (getFirstPlayerSum() < getSecondPlayerSum() || getFirstPlayerSum() == 21) {
                playersMoney[1] -= getBet();
                playersMoney[0] += getBet();
                System.out.printf("У Вас %d очков, у Компьютера %d очков.%n", getFirstPlayerSum(), getSecondPlayerSum());
                System.out.printf("%nЭтот раунд за Вами! :)" + "%n");
                System.out.println("");
            }
            else if (getFirstPlayerSum() > getSecondPlayerSum() || getSecondPlayerSum() == 21)  {
                playersMoney[0] -= getBet();
                playersMoney[1] += getBet();
                System.out.printf("У Вас %d очков, у Компьютера %d очков.%n", getFirstPlayerSum(), getSecondPlayerSum());
                System.out.printf("%nЭтот раунд за Компьютером! :)" + "%n");
                System.out.println("");
            }
            else if (getSecondPlayerSum() < 0) {
                break;
            }
        }
        if (getFirstPlayerMoney() > 0) {
            System.out.printf("%nВы выиграли! Поздравляем! :)%n");
        } else {
            System.out.printf("%nВы проиграли. Пичалька... :(%n");
        }
    }

    public static void main(String... __) throws IOException {
        startGame();
    }
}
