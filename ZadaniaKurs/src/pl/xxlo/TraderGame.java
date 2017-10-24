package pl.xxlo;

class Player {
    private int money = 1000;
    private int goods = 0;
    
    public int getMoney() { return money; }
    public int getGoods() { return goods; }
    
    public String toString() {
        return String.format("Masz %d $ i %d towaru\n", money, goods);
    }
}

class Game {
    int turn = 1;
    Player player = new Player();
    
    public void run() {
        while(true) {
            System.out.println("TURA: " + turn);
            System.out.println(player.toString());
            
            if(turn == 100) {
                System.out.println("KONIEC GRY. Posiadasz  " 
                        + player.getMoney());
                break;
            } 
            turn++;
        }     
    }
}


public class TraderGame {

    public static void main(String[] args) {
        Game game = new Game();
        game.run();
    }

}
