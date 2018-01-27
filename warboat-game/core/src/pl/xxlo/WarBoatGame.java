package pl.xxlo;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import pl.xxlo.screens.ScreenManager;

public class WarBoatGame extends Game {
    private SpriteBatch batch;
    private ScreenManager manager;

    @Override
    public void create() {
        batch = new SpriteBatch();
        System.out.println(Gdx.files.getLocalStoragePath());
        System.out.println(Gdx.files.getExternalStoragePath());

        manager = new ScreenManager(this);
    }

    @Override
    public void render() {
    	super.render();
//    	Gdx.gl.glClearColor(1, 0, 0, 1);
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//        batch.begin();
//        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
    public SpriteBatch getBatch() { return batch;}
}
