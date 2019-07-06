package pl.xxlo;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import pl.xxlo.screens.ScreenManager;

public class StarBoat extends Game {
    SpriteBatch batch;
    ScreenManager manager;

   
    public void create() {
        batch = new SpriteBatch();
        System.out.println(Gdx.files.getLocalStoragePath());
        System.out.println(Gdx.files.getExternalStoragePath());
        manager = new ScreenManager(this);
        manager.setStartScreen();
    }

   
    public void render() {
        super.render();
    }

   
    public void dispose() {
        batch.dispose();
    }
    
    public SpriteBatch getBatch() { return batch; }
}
