package pl.sflg;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ScreenManager {
    private MainGame game;
    private SpriteBatch batch;

    public ScreenManager(MainGame game){
        this.game = game;
        batch = game.getBatch();
    }

    public void starGame(){
        GameScreen gameScreen = new GameScreen(this);
        game.setScreen(gameScreen);
    }

    public SpriteBatch getBatch() { return batch;}
}
