package games;

import org.slf4j.Logger;

import java.util.*;
import java.util.List;

import static games.Card.*;

public class Drunkard {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(Drunkard.class);

    private static LinkedList<Card> deckOfCards = new LinkedList<Card>();

    private static LinkedList<Card> firstDeck = new LinkedList<Card>();

    private static LinkedList<Card> secondDeck = new LinkedList<Card>();

    private static List<Card> getDeckOfCards() {
        return deckOfCards;
    }

    private static void createDeckOfCards() {
        for (int i = 0; i < getCardsTotalCount(); i++) {
            getDeckOfCards().add(createCard(i));
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

    private static void whenFirstDeckWin() {
        log.info("У Игрока №1 карта: {}, у Игрока №2 карта: {}", firstDeck.getFirst().toString(), secondDeck.getFirst().toString());
        firstDeck.add(secondDeck.getFirst()); // в 1 колоду добавляем карту из колоды 2
        secondDeck.remove(); // в колоде 2 удаляем карту которую добавили в колоду 1
        firstDeck.offerLast(firstDeck.getFirst()); // в колоде 1 перемещаем карту в низ колоды
        firstDeck.remove();
        log.info("Выиграл Игрок1!");
        log.info("У Игрока №1 {} карт, у Игрока №2 {}.", firstDeck.size(), secondDeck.size());
        log.info("-----------------------------------------");
    }

    private static void whenSecondDeckWin() {
        System.out.printf("У Игрока №1 карта: %s, у Игрока №2 карта: %s%n", firstDeck.getFirst().toString(), secondDeck.getFirst().toString());
        secondDeck.add(firstDeck.getFirst()); // в 2 колоду добавляем карту из колоды 1
        firstDeck.remove(); // в колоде 1 удаляем карту которую добавили в колоду 2
        secondDeck.offerLast(secondDeck.getFirst()); // в колоде 1 перемещаем карту в низ колоды
        secondDeck.remove();
        log.info("Выиграл Игрок2!");
        log.info("У Игрока №1 {} карт, у Игрока №2 {}.", firstDeck.size(), secondDeck.size());
        log.info("-----------------------------------------");
    }

    private static void dispute() {
        log.info("У Игрока №1 карта: {}, у Игрока №2 карта: {}", firstDeck.getFirst().toString(), secondDeck.getFirst().toString());
        Card firstCardInFirst = firstDeck.getFirst();
        firstDeck.offerLast(firstCardInFirst);
        firstDeck.remove();
        Card firstCardInSecond = firstDeck.getFirst();
        secondDeck.offerLast(firstCardInSecond);
        secondDeck.remove();
        log.info("Спор! Игроки остаются при своих картах!");
        log.info("У Игрока №1 {} карт, у Игрока №2 {}.", firstDeck.size(), secondDeck.size());
        log.info("-----------------------------------------");
    }


    private static void startGame() {
        int countOfIterations = 0;
        while (!firstDeck.isEmpty() || !secondDeck.isEmpty()) {
            countOfIterations += 1;
            log.info("Итерация {}", countOfIterations);
            if (getCardDignity(firstDeck.getFirst()) > getCardDignity(secondDeck.getFirst())) {
                whenFirstDeckWin();
            }
            if (getCardDignity(secondDeck.getFirst()) > getCardDignity(firstDeck.getFirst())) {
                whenSecondDeckWin();
            }
            if ((getCardDignity(secondDeck.getFirst()) == getCardDignity(firstDeck.getFirst()))) {
                dispute();
            }
            else {endGame();};
        }
    }

    private static void endGame() {
        if (firstDeck.isEmpty()) {
            log.info("Проиграл Игрок1");
        }
        if (secondDeck.isEmpty()) {
            log.info("Проиграл Игрок2");
        }
    }

    public static void main(String... __) {
        createDeckOfCards();
        Collections.shuffle(getDeckOfCards());
        dealCards();
        startGame();
    }
}