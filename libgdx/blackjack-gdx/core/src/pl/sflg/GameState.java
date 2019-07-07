package pl.sflg;

public class GameState {
    private int money = 1000;
    int state = 0;
    // 0 początek

    public int getMoney() {
        return money;
    }

    public void addMoney(int money) {
        this.money += money;
    }

}
