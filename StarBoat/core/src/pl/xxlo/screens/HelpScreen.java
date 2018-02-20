package pl.xxlo.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class HelpScreen extends AbstractScreen {
	
	private final Texture background;
	private final String polish = 
			"Sterowanie:\n" +
			"Zmiana uzbrojenia – kliknij na okręt\n" +
			"Obrót wieży i strzał – trzymaj miejsce poza okrętem\n" +
			"gdy wieża się obróci nastąpi strzał. "
			+ "Po strzale potrzebny jest czas na załadunek";
	private final BitmapFont font;
	
	public HelpScreen(ScreenManager manager) {
		super(manager);
		background = new Texture(Gdx.files.internal("data/galaxy.jpg"));
		 font = new BitmapFont();
	     font.setColor(Color.RED);
	     font.getData().setScale(1f);

	}


	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 0, 0, 1);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(background, 0, 0);
	    font.draw(batch, polish, 10, 100);
		batch.end();
		 if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE) || Gdx.input.isTouched()) {
			 manager.setGameScreen();
		 }
	}


	@Override
	public void dispose() {
		background.dispose();
		font.dispose();
	}

}
