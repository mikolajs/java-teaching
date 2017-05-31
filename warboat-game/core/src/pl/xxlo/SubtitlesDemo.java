package pl.xxlo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.freetype.*;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.graphics.g2d.*;


public class SubtitlesDemo {
    private Texture texture;
    private FreeTypeFontGenerator generator;
    private FreeTypeFontParameter parameter;
    private BitmapFont font;
    private GlyphLayout layout;
    float scale = 1f;
    String[] subtitles;
    int[] positions;
    private boolean endFlag = false;

    public SubtitlesDemo() {
        texture = new Texture(Gdx.files.internal("data/river.png")
        );
        FileHandle fh = Gdx.files.internal("data/demotext.txt");
        subtitles = fh.readString().split("\n");

        generator = new FreeTypeFontGenerator(
                Gdx.files.internal("data/Comic_Sans_MS_Bold.ttf")
        );
        parameter = new FreeTypeFontParameter();
        parameter.size = 80;
        parameter.borderColor = Color.BLACK;
        parameter.borderWidth = 3;
        parameter.color = Color.YELLOW;
        font = generator.generateFont(parameter);
        generator.dispose();
        layout = new GlyphLayout();
        positions = new int[subtitles.length];
        for(int i = 0; i < positions.length; i++) {
            positions[i] = -110 * i;
        }
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, 0, 0);
        for(int i = 0; i < subtitles.length; i++) {
            int pos = positions[i];
            if(pos >=  0 && pos <= 960) {
                float posF = (float) pos;
                float Y =  posF / 1.414213562f;
                float mL = posF;
                float mR = marginR(Y);
                font.getData().setScale(
                        ((float) Gdx.graphics.getWidth() - mL - mR) /
                                (float)(Gdx.graphics.getWidth() - 180)
                );
                layout.setText(font,  subtitles[i]);
                font.draw(batch, subtitles[i],
                        (Gdx.graphics.getWidth() + mL - (layout.width + mR )) / 2, Y);
            }
            positions[i] += 1;
        }
        if(positions[positions.length -1] > 900) endFlag = true;
    }

    private float marginR(float y) { return (180.0f - 0.257142857f*y); }

    public void dispose() {
        texture.dispose();
        font.dispose();
    }

    public boolean isEnd() { return endFlag;}

}

