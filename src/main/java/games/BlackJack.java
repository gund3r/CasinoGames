package games;

import org.apache.commons.math3.util.MathArrays;
import org.slf4j.Logger;

import java.io.IOException;

import static games.Card.*;
import static games.Choice.getCharacterFromUser;

public class BlackJack {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(BlackJack.class);

    private static final int MAX_VALUE = 21;

    private static final int MAX_CARDS_COUNT = 8;

    private static int[] playersMoney = {100, 100};

    private static int[][] playersCards;

    private static int[] cards;

    private static int[] playerCardsCursor;

    private static int cursorOfDeck;

    private static String printCard(int cardNumber) {
        return getSuit(cardNumber) + "_" + getPar(cardNumber);
    }

    private static int getFirstPlayerMoney() {
        return playersMoney[0];
    }

    private static int getSecondPlayerMoney() {
        return playersMoney[1];
    }

    private static int getCursorOfDeck() {
        return cursorOfDeck;
    }

    private static void increaseCursorOfDeck() {
        cursorOfDeck += 1;
    }

    private static void increasePlayersCardCursor(int player) {
        switch (player) {
            case 1:
                playerCardsCursor[0] += 1;
                break;
            case 2:
                playerCardsCursor[1] += 1;
                break;
        }
    }

    private static int getBet() {
        return 10;
    }

    private static int[] getShuffledCards() {
        // колода подряд
        int[] cards = {
                0, 1, 2, 3, 4, 5, 6, 7, 8,
                9, 10, 11, 12, 13, 14, 15, 16, 17,
                18, 19, 20, 21, 22, 23, 24, 25, 26,
                27, 28, 29, 30, 31, 32, 33, 34, 35};

        MathArrays.shuffle(cards);
        return cards;
    }

    private static boolean confirm() throws IOException {
        log.info("Берем еще? \"Y\" - Да, {любой другой символ} - нет (Чтобы выйти из игры, нажмите Ctrl + C)");
        switch (getCharacterFromUser()) {
            case 'Y':
            case 'y': return true;
            default: return false;
        }
    }

    private static void initRound() {
        log.info("У Вас {}$, у компьютера - {}$. Начинаем новый раунд!", getFirstPlayerMoney(), getSecondPlayerMoney());
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

    private static void firstPlayerRound() throws IOException {
        addCardToPlayer(1);
        log.info("Вам выпала карта {}", printCard(getCard()));
        increasePlayersCardCursor(1);
        increaseCursorOfDeck();
        addCardToPlayer(1);
        log.info("Вам выпала карта {}", printCard(getCard()));
        increasePlayersCardCursor(1);
        increaseCursorOfDeck();
        log.info("У вас {}", getFirstPlayerSum());
        while (confirm()) {
            addCardToPlayer(1);
            log.info("Вам выпала карта {}", printCard(getCard()));
            log.info("У вас {}", getFirstPlayerSum());
            increasePlayersCardCursor(1);
            increaseCursorOfDeck();
            }
        }

    private static void secondPlayerRound() {
        addCardToPlayer(2);
        log.info("Компьютеру выпала карта {}", printCard(getCard()));
        increasePlayersCardCursor(2);
        increaseCursorOfDeck();
        addCardToPlayer(2);
        log.info("Компьютеру выпала карта {}", printCard(getCard()));
        increasePlayersCardCursor(2);
        increaseCursorOfDeck();
        log.info("У Компьютера {} очков", getSecondPlayerSum());
        do {
            addCardToPlayer(2);
            log.info("Компьютер решил взять еще одну карту {}", printCard(getCard()));
            increaseCursorOfDeck();
            increasePlayersCardCursor(2);
        } while (getSecondPlayerSum() < 15);
    }

    private static void startGame() throws IOException {
        while(getFirstPlayerMoney() > 0) {
            initRound();
            firstPlayerRound();
            secondPlayerRound();
            if (getFirstPlayerSum() < getSecondPlayerSum() || getFirstPlayerSum() == MAX_VALUE) {
                playersMoney[1] -= getBet();
                playersMoney[0] += getBet();
                log.info("У Вас {} очков, у Компьютера {} очков.", getFirstPlayerSum(), getSecondPlayerSum());
                log.info("Этот раунд за Вами! :)");
            }
            else if (getFirstPlayerSum() > getSecondPlayerSum() || getSecondPlayerSum() == MAX_VALUE)  {
                playersMoney[0] -= getBet();
                playersMoney[1] += getBet();
                log.info("У Вас {} очков, у Компьютера {} очков.", getFirstPlayerSum(), getSecondPlayerSum());
                log.info("Этот раунд за Компьютером! :)");
            }
            else if (getSecondPlayerSum() < 0) {
                break;
            }
        }
        if (getFirstPlayerMoney() > 0) {
            log.info("Вы выиграли! Поздравляем! :)");
        } else {
            System.out.printf("Вы проиграли. Пичалька... :(");
        }
    }

    public static void main(String... __) throws IOException {
        startGame();
    }
}
