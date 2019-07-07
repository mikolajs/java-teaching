package eu.brosbit.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class SplashScreen extends AbstractScreen {

    private Texture texture;
    private BitmapFont font;


    public SplashScreen(ScreenManager sceenManager){
        super(sceenManager);
        texture = new Texture("fablab.png");

    }

    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(1,1,1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(texture, 100, 100);
        batch.end();
    }

    @Override
    public void dispose(){
        texture.dispose();
    }
}
