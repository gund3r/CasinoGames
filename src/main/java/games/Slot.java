package games;

import org.slf4j.Logger;

import java.util.Random;

public class Slot {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(Slot.class);

    private int money = 100;

    private int bet = 10;

    private int size = 7;

    private int reel1;

    private int reel2;

    private int reel3;

    private int getReel1() {
        return reel1;
    }

    private void setReel1(int reel1) {
        this.reel1 = reel1;
    }

    private int getReel2() {
        return reel2;
    }

    private void setReel2(int reel2) {
        this.reel2 = reel2;
    }

    private int getReel3() {
        return reel3;
    }

    private void setReel3(int reel3) {
        this.reel3 = reel3;
    }

    private int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    private int getBet() {
        return bet;
    }

    private int getSize() {
        return size;
    }

    private boolean isWin() {
        return reel1 == reel2 && reel2 == reel3;
    }

    private void plusMoney() {
        int prize = 1000;
        money += prize;
    }

    private void minusMoney() {
        money -= bet;
    }

    private void startGame() {
        while (getMoney() > 0) {
            Random r = new Random();
            setReel1(r.nextInt(getSize()));
            setReel2(r.nextInt(getSize()));
            setReel3(r.nextInt(getSize()));
            log.info("У Вас " + getMoney() + "$, ставка " + getBet() + "S");
            log.info("Крутим барабаны! Розыгрыш принёс следующие результаты:");
            log.info("первый барабан - %d, второй - %d, третий - %d%n", getReel1(), getReel2(), getReel3());
            if (!isWin()) {
                minusMoney();
                System.out.printf("Проигрыш, ваш капитал теперь составляет: - %d%s%n", getMoney(), "$");
            } else {
                plusMoney();
                System.out.printf("Выигрыш 1000%s, ваш капитал теперь составляет: - %d%s%n", "$", getMoney(), "$");
            }
        }
    }
    public static void main(String... __) {
        Slot slot = new Slot();
        slot.startGame();
    }
}



