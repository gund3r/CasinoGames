package games;

import java.util.Random;
import static java.lang.Math.random;
import static java.lang.Math.round;

public class Slot {

    public int getMoney() {
        return money;
    }

    public int getRate() {
        return rate;
    }

    public int getSize() {
        return size;
    }

    private int money = 100;

    private int rate = 10;

    private int size = 7;

    private int reel1 = 0;
    private int reel2 = 0;
    private int reel3 = 0;

    private boolean isBingo() {
        return reel1 == reel2 && reel2 == reel3;
    }

    private void capital() {
        if (!isBingo()) {
            money = money - rate;
        } else {
            int win = 1000;
            money = money + win;
        }
    }

    void printResult() {
        System.out.printf("У Вас %d%s, ставка - %d%s%n", getMoney(), "$", getRate(), "$");
        System.out.println("Крутим барабаны!Розыгрыш принёс следующие результаты:");
        System.out.printf("первый барабан - %d, второй - %d, третий - %d%n", reel1, reel2, reel3);
        capital();
        if (!isBingo()) {
            System.out.printf("Проигрыш, ваш капитал теперь составляет: - %d%s%n", getMoney(), "$");
        } else {
            System.out.printf("Выигрыш 1000%s, ваш капитал теперь составляет: - %d%s%n", "$", getMoney(), "$");
        }
    }
    public static void main(String[] args) {
        Slot s = new Slot();
        while (s.getMoney() > 0) {
            if (s.getMoney()>10000) break;
            Random r = new Random();
            s.reel1 = r.nextInt(s.getSize());
            s.reel2 = r.nextInt(s.getSize());
            s.reel3 = r.nextInt(s.getSize());
            s.printResult();
        }
    }
}
