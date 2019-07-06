package pl.sflg;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainGame extends Game {
	SpriteBatch batch;

//	OrthographicCamera cam;
	
	@Override
	public void create () {
		batch = new SpriteBatch();


		ScreenManager manager = new ScreenManager(this);
		manager.starGame();
	}

	@Override
	public void render () {
	    super.render();

	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}

	public SpriteBatch getBatch() { return batch; }
}
