package pl.sflg;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Button;

public class Menu  {
    Sprite close;
    Sprite next;
    final Texture closePicture;
    final Texture nextPicture;

    public Menu(int sizeX, int sizeY){
        closePicture = new Texture("close.png");
        nextPicture = new Texture("next.png");
        next = new Sprite(nextPicture);
        close = new Sprite(closePicture);

        next.setPosition(sizeX - next.getWidth() - 20, close.getHeight() + 20);
        close.setPosition(sizeX - close.getWidth() - 20, 20);
    }

    public void draw(SpriteBatch batch){
        close.draw(batch);
        next.draw(batch);
    }

    public boolean isClickedClose(int x, int y){
        return close.getBoundingRectangle().contains(x, y);
    }

    public boolean isClickedNext(int x, int y){
        return next.getBoundingRectangle().contains(x, y);
    }

    public void dispose() {
        nextPicture.dispose();
        closePicture.dispose();
    }
}
