package games;

import java.util.*;
import java.util.List;
import static games.Card.getCardDignity;

public class Drunkard {

    private static final int PARS_TOTAL_COUNT = Par.values().length;

    private static final int CARDS_TOTAL_COUNT = PARS_TOTAL_COUNT * Suit.values().length; //36

    private static LinkedList<Card> deckOfCards = new LinkedList<Card>();

    private static LinkedList<Card> firstDeck = new LinkedList<Card>();

    private static LinkedList<Card> secondDeck = new LinkedList<Card>();

    static int getParsTotalCount() {
        return PARS_TOTAL_COUNT;
    }

    static int getCardsTotalCount() {
        return CARDS_TOTAL_COUNT;
    }

    private static List<Card> getDeckOfCards() {
        return deckOfCards;
    }

    private static void createDeckOfCards() {
        for (int i = 0; i < getCardsTotalCount(); i++) {
            getDeckOfCards().add(Card.createCard(i));
        }
    }

    private static void dealCards() {
        for (int i = 0; i < getCardsTotalCount() / 2; i++) {
            firstDeck.add(getDeckOfCards().get(i));
        }
        for (int y = getCardsTotalCount() / 2; y < getCardsTotalCount(); y++) {
            secondDeck.add(getDeckOfCards().get(y));
        }
    }

    private static void firstDeckWin() {

        System.out.printf("У Игрока №1 карта: %s, у Игрока №2 карта: %s%n", firstDeck.getFirst().toString(), secondDeck.getFirst().toString());

        firstDeck.add(secondDeck.getFirst()); // в 1 колоду добавляем карту из колоды 2

        secondDeck.remove(); // в колоде 2 удаляем карту которую добавили в колоду 1

        firstDeck.offerLast(firstDeck.getFirst()); // в колоде 1 перемещаем карту в низ колоды

        firstDeck.remove();

        System.out.printf("Выиграл Игрок1!%nУ Игрока №1 %d карт, у Игрока №2 %d.%n", firstDeck.size(), secondDeck.size());
        System.out.println("-----------------------------------------");

    }

    private static void secondDeckWin() {

        System.out.printf("У Игрока №1 карта: %s, у Игрока №2 карта: %s%n", firstDeck.getFirst().toString(), secondDeck.getFirst().toString());

        secondDeck.add(firstDeck.getFirst()); // в 2 колоду добавляем карту из колоды 1

        firstDeck.remove(); // в колоде 1 удаляем карту которую добавили в колоду 2

        secondDeck.offerLast(secondDeck.getFirst()); // в колоде 1 перемещаем карту в низ колоды

        secondDeck.remove();

        System.out.printf("Выиграл Игрок2!%nУ Игрока №1 %d карт, у Игрока №2 %d.%n", firstDeck.size(), secondDeck.size());
        System.out.println("-----------------------------------------");

    }

    private static void dispute() {

        System.out.printf("У Игрока №1 карта: %s, у Игрока №2 карта: %s%n", firstDeck.getFirst().toString(), secondDeck.getFirst().toString());

        Card firstCardInFirst = firstDeck.getFirst();

        firstDeck.offerLast(firstCardInFirst);

        firstDeck.remove();

        Card firstCardInSecond = firstDeck.getFirst();

        secondDeck.offerLast(firstCardInSecond);

        secondDeck.remove();

        System.out.printf("Спор! Игроки остаются при своих картах%nУ Игрока №1 %d карт, у Игрока №2 %d.%n", firstDeck.size(), secondDeck.size());
        System.out.println("-----------------------------------------");

    }


    private static void startGame() {

        int countOfIterations = 0;

        while (!firstDeck.isEmpty() || !secondDeck.isEmpty()) {

            countOfIterations += 1;

            System.out.printf("Итерация %S%n", countOfIterations);

            if (getCardDignity(firstDeck.getFirst()) > getCardDignity(secondDeck.getFirst())) {
                firstDeckWin();
            }

            if (getCardDignity(secondDeck.getFirst()) > getCardDignity(firstDeck.getFirst())) {
                secondDeckWin();
            }

            if ((getCardDignity(secondDeck.getFirst()) == getCardDignity(firstDeck.getFirst()))) {
                dispute();
            }
        }
    }

    private static void endGame() {

        if (firstDeck.isEmpty()) {
            System.out.println("Проиграл Игрок1");
        }
        if (secondDeck.isEmpty()) {
            System.out.println("Проиграл Игрок1");
        }
    }

    public static void main(String... __) {

        createDeckOfCards();

        Collections.shuffle(getDeckOfCards());

        dealCards();

        startGame();

    }
}