package pl.xxlo;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;

public class Ship {
    final float maxDir = 120f;
    final float startDirLauncher = -90f;
    final float rotationHull = 90f;
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
    private Sprite redDot;
    private boolean mainGun = true;
    private float centerX = Gdx.graphics.getWidth()/2f - 100f;
    private float centerY = Gdx.graphics.getHeight()/2f;

    public Ship() {
        hull = new Sprite(new Texture(Gdx.files.internal("data/shiphull.png")));
        gun = new Sprite(new Texture(Gdx.files.internal("data/shipgun.png")));
        launcher = new Sprite(new Texture(Gdx.files.internal("data/shiptorp.png")));
        font = new BitmapFont();
        font.setColor(Color.RED);
        font.getData().setScale(1f);

        hull.setCenter(centerX, centerY);
        float gunX = centerX - gun.getWidth() / 2f - 19f;
        float gunY = centerY - gun.getHeight() / 2f;
        float launcherX = centerX - launcher.getWidth() / 2f +20f;
        float launcherY = centerY - launcher.getHeight() / 2f;
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
        hull.setRotation(rotationHull);
        hull.draw(batch);
        gun.setRotation(angleToScreen(this.gunDir, this.startDirGun));
        gun.draw(batch);
        launcher.setRotation(angleToScreen(launcherDir, startDirLauncher);
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
                //System.out.println("someware other point touched!");
                float angle = countAngleOfWeapon(x, y);
                System.out.println(String.format("kliked %d %d angle %f", x, y, angle ) );
                float delta = 0;
                if(mainGun) {
                    delta = angle - gunDir;
                    if (Math.abs(delta) > 1f) {
                        if(delta > 0) rotateGun(false);
                        else rotateGun(true);
                    }
                }  else {
                    delta = angle - gunDir;
                    if(Math.abs(delta) > 1f){
                        if(delta > 0) rotateLuncher(false);
                        else rotateLuncher(true);
                    }
                }
            }
        }
        if(Gdx.input.isKeyPressed(37)) rotateGun(true);
        if(Gdx.input.isKeyPressed(38)) rotateGun(false);
        if(Gdx.input.isKeyPressed(39)) rotateLuncher(true);
        if(Gdx.input.isKeyPressed(40)) rotateLuncher(false);
    }

    private boolean isTouched(Sprite s, int x, int y){
        //System.out.println(String.format("touch (%d, %d) sprite position (%f, %f)" +
        //        " size (%f, %f)",
        //        x, y, s.getX(), s.getY(), s.getWidth(), s.getHeight()));
        return (x > s.getX()  && x < (s.getX() + s.getWidth())
            && y > s.getY() && y < (s.getY() + s.getHeight()));
    }

    /* @return in degrees by up screen to clock wise
     */
    private float countAngleOfWeapon(int x, int y){
        float X = centerX;
        float Y = centerY;
        if(this.mainGun)  X -= 19f;
        else X += 20f;
        float dx =  (float) x - X;
        float dy =  Y - (float) y;
        System.out.println(String.format("tangens: %f for dx %f dy %f",
                Math.atan2(dx, dy), dx, dy));
        double alpha = (Math.atan2((double)dy, (double)dx)* (180.0/Math.PI));
        if(alpha < 0 ) return (float)(360.0 + alpha);
        else return  (float) alpha;
    }

    private void rotateGun(boolean left){
    if(left) {
        if(this.gunDir < this.maxDir)
            this.gunDir += 3f;
    } else {
        if(this.gunDir > this.maxDir)
                this.gunDir -= 3f;
        }
    }


    private  void rotateLuncher(boolean left){
        if(left) {
            if(this.launcherDir < this.maxDir  )
                this.launcherDir += 3f;
        } else {
            if(this.launcherDir >  this.maxDir)) {
                this.launcherDir -= 3f;
            }
        }
    }

    private float angleToScreen(float alpha, float dAlpha) {
        float angle = alpha + dAlpha;
        if(angle > 180.0f) angle -= 360.0f;
        return angle;
    }
}
