package games;

import org.slf4j.Logger;

import java.util.Random;

public class Slot {

    static Logger log = org.slf4j.LoggerFactory.getLogger(Slot.class);

    public static void main(String... __) {

        Random r = new Random();

        int money = 100;

        int bet = 10;

        int DELTA = 100;

        int SIZE = 7;

        int firstReel = 0;

        int secondReel = 0;

        int thirdReel = 0;

        while (money > 0) {
            firstReel = (firstReel + r.nextInt(DELTA)) % SIZE;
            secondReel = (secondReel + r.nextInt(DELTA)) % SIZE;
            thirdReel = (thirdReel + r.nextInt(DELTA)) % SIZE;
            log.info("У Вас {}$, ставка {}$", money, bet);
            log.info("Крутим барабаны! Розыгрыш принёс следующие результаты:");
            log.info("первый барабан - {}, второй - {}, третий - {}", firstReel, secondReel, thirdReel);
            if (firstReel == secondReel && secondReel == thirdReel) {
                money += 1_000;
                log.info("Выигрыш 1000$, ваш капитал теперь составляет: - {}$", money);
            } else {
                money -= bet;
                log.info("Проигрыш, ваш капитал теперь составляет: - {}$", money);
            }
        }
    }
}