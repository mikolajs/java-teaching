package pl.sflg;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;

class GameScreen extends AbstractScreen {
      final Texture cardBody;
      final Texture closePicture;
      final Sprite card1;
      final Sprite close;
      BitmapFont bitmapFont;
      final int sizeX = 1280;
      final int sizeY = 720;

    public GameScreen(ScreenManager manager) {
        super(manager);
        bitmapFont = new BitmapFont();
        bitmapFont.setColor(Color.BLACK);
        bitmapFont.getData().scale(1.4f);
        cardBody = new Texture("karta.png");
        closePicture = new Texture("close.png");
        card1 = new Sprite(cardBody);
        card1.setPosition(0,0);
        close = new Sprite(closePicture);
        close.setPosition(1000, 200);
    }


    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(0, 1, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        card1.draw(batch);
        close.draw(batch);
        this.drawInfo();
        batch.end();
    }

    public void drawInfo(){
        bitmapFont.draw(batch, "Informacja", 1000, 680);
    }
}
