package pl.xxlo;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.Gdx;

public class Ship {
    final float maxDirGun = 135f;
    final float maxDirTorp = 65f;
    final float maxPower = 20f;
    final float minPower = -10f;
    final float maxRudder = 20f;
    float speed = 0f;
    float power = 0f;
    float HP = 100f;
    float rudder = 0f;
    float dir = 0f;
    float torpedoDir = 0f;
    float gunDir = 0f;
    private Texture hullTex;
    private  Texture gunTex;
    private Texture torpTex;
    private BitmapFont font;

    public Ship() {
       hullTex = new Texture(Gdx.files.internal("data/shiphull.png"));
        gunTex = new Texture(Gdx.files.internal("data/shipgun.png"));
        torpTex = new Texture(Gdx.files.internal("data/shiptorp.png"));
        font = new BitmapFont();
        font.setColor(Color.RED);
        font.getData().setScale(1f);
    }

    public void render(SpriteBatch batch) {
        float centerX = Gdx.graphics.getWidth()/2;
        float centerY = Gdx.graphics.getHeight()/2;
        batch.draw(hullTex, centerX - hullTex.getWidth()/2,
                centerY - hullTex.getHeight()/2);
        batch.draw(gunTex, centerX - gunTex.getWidth()/2,
                centerY - gunTex.getHeight()/2 + 19);
        batch.draw(torpTex, centerX - torpTex.getWidth()/2,
                centerY - torpTex.getHeight()/2 - 20);
    }

    public void drawInfo(SpriteBatch batch) {
        font.draw(batch, "Napis", 10, 100);
    }

    public void takeKeys() {

    }

}
