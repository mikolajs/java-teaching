package pl.sflg;

public class Arbiter {

    public boolean exceed(Gamer gamer){
        return  (gamer.countValues() > 22 ||
                (gamer.countValues() == 22 && !gamer.doubleA()));
    }

    public int whoWins(Gamer player, Gamer croupier) {
        if(croupier.countValues() > player.countValues()){
            return 2;
        } else if(croupier.countValues() == player.countValues()
                && croupier.numberCards() <= player.numberCards()) {
            return 2;
        } else {
            return 1;
        }
    }
}