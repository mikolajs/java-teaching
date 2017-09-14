package pl.xxlo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class Torpedo {
    private final float v = 2.0f;
    private float x;
    private float y;
    private final float cX;
    private final float cY;
    private float angle;
    private Sprite sprite;
    private float distance = 0.0f;
    private boolean moving = false;
    private BitmapFont font;
    private int timeGo = 0;

    public Torpedo(float X, float Y, Texture texture){
        cX = X;
        cY = Y;
        x = cX;
        y = cY;
        sprite = new Sprite(texture);
        font = new BitmapFont();
        font.setColor(Color.RED);
        font.getData().setScale(2f);
    }

    public void render(SpriteBatch batch, float speed){
        timeGo += 1;
        if(moving) {
            move(speed);
            sprite.draw(batch);
            drawInfo(batch);
        }
    }

    public boolean isMoving(){ return moving;}
    public boolean isReady(){ return  (timeGo > 400) && !moving;}

    public void launch(float angle){
        timeGo = 0;
        this.angle = angle;
        sprite.setRotation(Physics.angleToScreen( angle));
        moving = true;
    }

    public void drawInfo(SpriteBatch batch) {
        font.draw(batch, "Torp a: " + angle, 10, 200);
        font.draw(batch, "Torp rotate: " + Physics.angleToScreen(angle), 10, 100);
    }

    private void move(float speed){
        if(distance < 500.0f ) {
            float vy = (v * MathUtils.sinDeg(angle));
            float vx = -((v + speed) * MathUtils.cosDeg(angle));

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
