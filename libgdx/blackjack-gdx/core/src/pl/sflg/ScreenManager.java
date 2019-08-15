package pl.sflg;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ScreenManager {
    private MainGame game;
    private SpriteBatch batch;

    public ScreenManager(MainGame game){
        this.game = game;
        batch = game.getBatch();

    }

    public void starGame(){
        GamePlayScreen gameScreen = new GamePlayScreen(this);
        game.setScreen(gameScreen);
    }

    public SpriteBatch getBatch() { return batch;}
}
