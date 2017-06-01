package pl.xxlo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WarBoat extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Ship ship;
	SubtitlesDemo subDemo;
	boolean demo = true;

	@Override
	public void create () {
		batch = new SpriteBatch();
		System.out.println(Gdx.files.getLocalStoragePath());
		System.out.println(Gdx.files.getExternalStoragePath());
		img = new Texture(Gdx.files.internal("data/river.png"));
		ship = new Ship();
		subDemo = new SubtitlesDemo();
	}

	@Override
	public void render () {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(img, 0, 0);
	    if(demo){
            subDemo.render(batch);
            if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE) || Gdx.input.isTouched() || subDemo.isEnd()) {
                demo = false;
	            subDemo.dispose();
                img = new Texture(Gdx.files.internal("data/sea.png"));
            }
        } else {
            ship.drawInfo(batch);
            ship.render(batch);
        }
		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
