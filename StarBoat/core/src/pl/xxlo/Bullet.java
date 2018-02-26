package pl.xxlo;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

/**
 * Created by ms on 01.07.17.
 */
public class Bullet {
    private final float v = 8.0f;
    private float x;
    private float y;
    private final float cX;
    private final float cY;
    private float angle;
    private Sprite sprite;
    private float distance = 0.0f;
    private boolean moving = false;
    private BitmapFont font;

    public Bullet(float X, float Y, Texture texture){
        cX = X;
        cY = Y;
        x = cX;
        y = cY;
        sprite = new Sprite(texture);
        font = new BitmapFont();
        font.setColor(Color.RED);
        font.getData().setScale(1f);
    }

    public void render(SpriteBatch batch){
        if(moving) {
            move();
    		//sprite.setPosition(cX, cY);
            sprite.draw(batch);
            drawInfo(batch);
        }
    }

    public boolean isMoving(){ return moving;}

    public void launch(float angle){
        this.angle = angle;
        sprite.setRotation(Physics.angleToScreen( angle));
        x = cX; y = cY;
        moving = true;
    }

    public void drawInfo(SpriteBatch batch) {
        font.draw(batch, "Bull a: " + angle, 10, 10);
    }

    private void move(){
        if(distance < 800.0f ) {
            float vy = (v * MathUtils.sinDeg(angle));
            float vx = -(v * MathUtils.cosDeg(angle));

            x += vx;
            y += vy;
            distance += v;
            sprite.setPosition(x, y);
        } else {
            moving = false;
            distance = 0.0f;
            x = cX;
            y = cY;
        }
    }
}
