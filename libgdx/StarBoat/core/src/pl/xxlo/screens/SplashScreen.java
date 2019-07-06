package pl.xxlo.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;


public class SplashScreen extends AbstractScreen {

	private Texture texture;
	private FreeTypeFontGenerator generator;
	private FreeTypeFontParameter parameter;
	private BitmapFont font;
	private GlyphLayout layout;
	float scale = 1f;
	String[] subtitles;
	int[] positions;

	public SplashScreen(ScreenManager manager) {
		super(manager);
		texture = new Texture(Gdx.files.internal("data/galaxy.jpg"));
		FileHandle fh = Gdx.files.internal("data/demotext.txt");
		subtitles = fh.readString().split("\n");

		generator = new FreeTypeFontGenerator(Gdx.files.internal("data/Comic_Sans_MS_Bold.ttf"));
		parameter = new FreeTypeFontParameter();
		parameter.size = 80;
		parameter.borderColor = Color.BLACK;
		parameter.borderWidth = 3;
		parameter.color = Color.YELLOW;
		font = generator.generateFont(parameter);
		generator.dispose();
		layout = new GlyphLayout();
		positions = new int[subtitles.length];
		for (int i = 0; i < positions.length; i++) {
			positions[i] = -110 * i;
		}
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(texture, 0, 0);
		for (int i = 0; i < subtitles.length; i++) {
			int pos = positions[i];
			if (pos >= 0 && pos <= 960) {
				float posF = (float) pos;
				float Y = posF / 1.414213562f;
				float mL = posF;
				float mR = marginR(Y);
				font.getData().setScale(
						((float) Gdx.graphics.getWidth() - mL - mR) / (float) (Gdx.graphics.getWidth() - 180));
				layout.setText(font, subtitles[i]);
				font.draw(batch, subtitles[i], (Gdx.graphics.getWidth() + mL - (layout.width + mR)) / 2, Y);
			}
			positions[i] += 1;
		}
		batch.end();
		if (positions[positions.length - 1] > 900) {
			manager.setHelpScreen();
		} 
		if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE) || Gdx.input.isTouched()) {
			manager.setHelpScreen();
		}
	}

	@Override
	public void dispose() {
		texture.dispose();
		font.dispose();

	}

	private float marginR(float y) {
		return (180.0f - 0.257142857f * y);
	}

}
