package games;

import org.slf4j.Logger;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Deque;
import java.util.List;

import static games.Card.*;

public class Drunkard {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(Drunkard.class);

    private static List<Card> deckOfCards = new LinkedList<>();

    private static Deque<Card> firstDeck = new LinkedList<>();

    private static Deque<Card> secondDeck = new LinkedList<>();

    private static void createDeckOfCards() {
        for (int i = 0; i < CARDS_TOTAL_COUNT; i++) {
            deckOfCards.add(createCard(i));
        }
    }

    private static void dealCards() {
        for (int i = 0; i < CARDS_TOTAL_COUNT / 2; i++) {
            firstDeck.add(deckOfCards.get(i));
        }
        for (int y = CARDS_TOTAL_COUNT / 2; y < CARDS_TOTAL_COUNT; y++) {
            secondDeck.add(deckOfCards.get(y));
        }
    }

    private static void whenFirstDeckWin() {
        log.info("У Игрока №1 карта: {}, у Игрока №2 карта: {}", firstDeck.getFirst().toString(), secondDeck.getFirst().toString());
        firstDeck.add(secondDeck.getFirst());
        secondDeck.remove();
        firstDeck.offerLast(firstDeck.getFirst());
        firstDeck.remove();
        log.info("Выиграл Игрок1!");
        log.info("У Игрока №1 {} карт, у Игрока №2 {}.", firstDeck.size(), secondDeck.size());
        log.info("-----------------------------------------");
    }

    private static void whenSecondDeckWin() {
        log.info("У Игрока №1 карта: {}, у Игрока №2 карта: {}", firstDeck.getFirst().toString(), secondDeck.getFirst().toString());
        secondDeck.add(firstDeck.getFirst());
        firstDeck.remove();
        secondDeck.offerLast(secondDeck.getFirst());
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
            else endGame();
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
        Collections.shuffle(deckOfCards);
        dealCards();
        startGame();
    }
}