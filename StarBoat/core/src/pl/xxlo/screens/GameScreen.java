package pl.xxlo.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import pl.xxlo.Ship;

public class GameScreen extends AbstractScreen {

	private final Texture img;
	private final Ship ship;
	public GameScreen(ScreenManager manager) {
		super(manager);
		img = new Texture(Gdx.files.internal("data/background.jpg"));
		ship = new Ship();
	}
	
	@Override 
	public void render(float delta) {
		
		batch.begin();
		batch.draw(img, 0, 0);
		ship.drawInfo(batch);
		ship.render(batch);
		batch.end();
		
	}
	
	@Override 
	public void dispose() {
		img.dispose();
		ship.dispose();
	}
}
