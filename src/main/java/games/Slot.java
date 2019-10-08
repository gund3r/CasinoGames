package games;

import org.slf4j.Logger;

import java.util.Random;

public class Slot {

    public static void main(String... __) {

        Logger log = org.slf4j.LoggerFactory.getLogger(Slot.class);

        int money = 100;

        int bet = 10;

        int size = 7;

        int reel1;

        int reel2;

        int reel3;

        while (money > 0) {
            Random r = new Random();
            reel1 = r.nextInt(size);
            reel2 = r.nextInt(size);
            reel3 = r.nextInt(size);
            log.info("У Вас {}$, ставка {}$", money, bet);
            log.info("Крутим барабаны! Розыгрыш принёс следующие результаты:");
            log.info("первый барабан - {}, второй - {}, третий - {}", reel1, reel2, reel3);
            if (reel1 == reel2 && reel2 == reel3) {
                money += 1000;
                log.info("Выигрыш 1000$, ваш капитал теперь составляет: - {}$", money);
            } else {
                money -= bet;
                log.info("Проигрыш, ваш капитал теперь составляет: - {}$", money);
            }
        }
    }
}