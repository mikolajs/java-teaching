package eu.brosbit;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import eu.brosbit.screens.ScreenManager;

public class TankGame extends Game {
	SpriteBatch batch;
	Texture img;
	ScreenManager screenManager;
	
	@Override
	public void create () {
        screenManager = new ScreenManager(this);
        screenManager.setStartScreen();
		batch = new SpriteBatch();
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}

	public SpriteBatch getBatch(){
	    return batch;
    }
}
