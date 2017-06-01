package pl.xxlo;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.Gdx;

public class Ship {
    final float maxDir = 115f;
    final float startDirLauncher = -90f;
    final float startDirGun = 90f;
    final float maxPower = 20f;
    final float minPower = -10f;
    final float maxRudder = 20f;
    float speed = 0f;
    float power = 0f;
    float HP = 100f;
    float rudder = 0f;
    float dir = 0f;
    float launcherDir = startDirLauncher;
    float gunDir = startDirGun;
    private Sprite hull;
    private Sprite gun;
    private Sprite launcher;
    private BitmapFont font;
    private float gunX, gunY, launcherX, launcherY, hullX, hullY;
    private Sprite redDot;
    private boolean mainGun = true;

    public Ship() {
        hull = new Sprite(new Texture(Gdx.files.internal("data/shiphull.png")));
        gun = new Sprite(new Texture(Gdx.files.internal("data/shipgun.png")));
        launcher = new Sprite(new Texture(Gdx.files.internal("data/shiptorp.png")));
        font = new BitmapFont();
        font.setColor(Color.RED);
        font.getData().setScale(1f);

        float centerX = Gdx.graphics.getWidth()/2f - 100f;
        float centerY = Gdx.graphics.getHeight()/2f;
        float hullHeight = hull.getHeight();
        hullX = centerX - hull.getWidth() / 2f;
        hullY = centerY - hullHeight / 2f;
        hull.setCenter(centerX, centerY);
        gunX = centerX - gun.getWidth() / 2f - 19f;
        gunY = centerY - gun.getHeight() / 2f;
        launcherX = centerX - launcher.getWidth() / 2f +20f;
        launcherY = centerY - launcher.getHeight() / 2f;
        //hull.setPosition(hullX, hullY);
        gun.setPosition(gunX, gunY);
        launcher.setPosition(launcherX, launcherY);

        Pixmap pxm = new Pixmap(6, 6, Pixmap.Format.RGBA8888);
        pxm.setColor(Color.RED);
        pxm.fillCircle(3,3,3);
        redDot = new Sprite(new Texture(pxm));
        redDot.setPosition(gun.getX() + (gun.getWidth() - redDot.getWidth()) / 2f,
                gun.getY() + (gun.getHeight() - redDot.getHeight()) / 2f );
    }

    public void render(SpriteBatch batch) {
        hull.setRotation(90f);
        hull.draw(batch);
        gun.setRotation(gunDir);
        gun.draw(batch);
        launcher.setRotation(launcherDir);
        launcher.draw(batch);
        redDot.draw(batch);
        takeInput(0L);
    }

    public void drawInfo(SpriteBatch batch) {
        font.draw(batch, "Napis", 10, 100);
    }

    public void takeInput(long time) {

        if(Gdx.input.isTouched()) {
            int x = Gdx.input.getX();
            int y = Gdx.input.getY();
            //System.out.println("touched at: " + x + ", " + y);
            if(isTouched(gun, x,y)){
                System.out.println("gun touched!");
                if(!this.mainGun) this.mainGun = true;
                redDot.setCenter(gun.getX() + gun.getWidth() / 2f,
                        gun.getY() + gun.getHeight() / 2f );
            } else if(isTouched(launcher, x, y)){
                System.out.println("launcher touched!");
                if(this.mainGun) this.mainGun = false;
                redDot.setCenter(launcher.getX() + launcher.getWidth() / 2f,
                        launcher.getY() + launcher.getHeight() / 2f );
            } else {
                System.out.println("someware other point touched!");
                //check if shoot
            }
        }
        if(Gdx.input.isKeyPressed(37) && this.gunDir > (this.startDirGun - this.maxDir))
            this.gunDir -= 3f;
        if(Gdx.input.isKeyPressed(38) && this.gunDir < (this.startDirGun + this.maxDir))
            this.gunDir += 3f;
        if(Gdx.input.isKeyPressed(39)
            && this.launcherDir > (this.startDirLauncher - this.maxDir))
                this.launcherDir -= 3f;
        if(Gdx.input.isKeyPressed(40)
                && this.launcherDir < (this.startDirLauncher + this.maxDir))
                 this.launcherDir += 3f;

    }

    private boolean isTouched(Sprite s, int x, int y){
        System.out.println(String.format("touch (%d, %d) sprite position (%f, %f)" +
                " size (%f, %f)",
                x, y, s.getX(), s.getY(), s.getWidth(), s.getHeight()));
        return (x > s.getX()  && x < (s.getX() + s.getWidth())
            && y > s.getY() && y < (s.getY() + s.getHeight()));
    }

}
