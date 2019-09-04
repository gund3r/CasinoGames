package games;

import java.util.Random;

public class Slot {

    private int money = 100;

    private int bet = 10;

    private int size = 7;

    private int reel1;

    private int reel2;

    private int reel3;

    public int getReel1() {
        return reel1;
    }

    public void setReel1(int reel1) {
        this.reel1 = reel1;
    }

    public int getReel2() {
        return reel2;
    }

    public void setReel2(int reel2) {
        this.reel2 = reel2;
    }

    public int getReel3() {
        return reel3;
    }

    public void setReel3(int reel3) {
        this.reel3 = reel3;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getBet() {
        return bet;
    }

    public int getSize() {
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
            System.out.printf("У Вас %d%s, ставка - %d%s%n", getMoney(), "$", getBet(), "$");
            System.out.println("Крутим барабаны! Розыгрыш принёс следующие результаты:");
            System.out.printf("первый барабан - %d, второй - %d, третий - %d%n", getReel1(), getReel2(), getReel3());
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
