package pl.xxlo;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

abstract  class Enemy {

	public Enemy(float x, float y, Texture texture) {
		
	}
	
	abstract void render(SpriteBatch batch);
}
