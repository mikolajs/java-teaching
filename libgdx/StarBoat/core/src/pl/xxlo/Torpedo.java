package pl.xxlo;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
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
    

    public Torpedo(float X, float Y, Texture texture){
        cX = X;
        cY = Y;
        x = cX;
        y = cY;
        sprite = new Sprite(texture);
        font = new BitmapFont();
        font.setColor(Color.RED);
        font.getData().setScale(1f);
    }

    public void render(SpriteBatch batch, float speed){
        if(moving) {
            move(speed);
            sprite.draw(batch);
            drawInfo(batch);
        }
    }

    public boolean isMoving(){ return moving;}

    public void launch(float angle){
        this.angle = angle;
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
            sprite.setCenter(x, y);
        } else {
            moving = false;
            distance = 0.0f;
            x = cX;
            y = cY;
        }
    }

}
