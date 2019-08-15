package pl.sflg;


public class GameState {
    private int money = 2000;
    private final int winLimit = 5000;
    private final double timeBetweenClick = 0.4;
    /**
     * 0 - bet
     * 1 - player draw  two random cards
     * 2 - player turn, choice if draw cards
     * 3 - croupier turn
     */
    GState state = GState.BET;
    // 0 początek
    double gameTime = 0.0;
    double clickedTime = 0.0;

    public int getMoney() {
        return money;
    }

    public void addMoney(int money) {
        this.money += money;
    }

    //positive win, negative lost, nautral not over
    public int isGameOver(){
        if(money <= 0) return  -1;
        if(money > winLimit) return 1;
        return  0;
    }

    public void progressTime(double delta) {
        gameTime += delta;
        clickedTime -= delta;
    }

    public void clicked(){
        clickedTime = timeBetweenClick;
    }

}

enum GState {
    BET, CROUPIER, PLAYER, RESOULT, END;
}
