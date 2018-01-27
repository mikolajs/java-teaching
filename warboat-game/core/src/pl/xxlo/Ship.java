package pl.xxlo;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;

public class Ship {
    //X screen axis left is 0 degree and 360 no negative degree
    final float startDirLauncher = 0f;
    final float rotationHull = 90f;
    final float startDirGun = 0f;
    final float maxPower = 20f;
    final float minPower = -10f;

    long timer = 0L;
    float speed = 0f;
    float power = 0f;
    float HP = 100f;
    final int readyWeapon = 50;
    int readyGun = 0;
    int readyLauncher = 0;
    float launcherDir = startDirLauncher;
    float gunDir = startDirGun;
 private Sprite hull;
    private Sprite gun;
    private Sprite launcher;
    private BitmapFont font;
    private Sprite redDot;
    private boolean mainGun = true;
    private float centerX = Gdx.graphics.getWidth() / 2f - 100f;
    private float centerY = Gdx.graphics.getHeight() / 2f;
    private Torpedo[] torps;
    private Bullet bullet;


    public Ship() {
        Physics.setCenter(centerX, centerY);
        Physics.testSinDeg();


        hull = new Sprite(new Texture(Gdx.files.internal("data/shiphull.png")));
        gun = new Sprite(new Texture(Gdx.files.internal("data/shipgun.png")));
        launcher = new Sprite(new Texture(Gdx.files.internal("data/shiptorp.png")));
        font = new BitmapFont();
        font.setColor(Color.RED);
        font.getData().setScale(1f);

        hull.setCenter(centerX, centerY);
        float gunX = centerX - gun.getWidth() / 2f + 5f;
        float gunY = centerY - gun.getHeight() / 2f;
        float launcherX = centerX - launcher.getWidth() / 2f - 19f;
        float launcherY = centerY - launcher.getHeight() / 2f;
        gun.setPosition(gunX, gunY);
        launcher.setPosition(launcherX, launcherY);

        Texture torpTex = new Texture(Gdx.files.internal("data/torpedo.png"));
        torps = new Torpedo[3];
        for(int i = 0; i < torps.length; i++) torps[i] = new Torpedo( launcherX, launcherY, torpTex );
        Texture bulletTex = new Texture(Gdx.files.internal("data/bullet.png"));
        bullet = new Bullet(gunX, gunY, bulletTex);

        Pixmap pxm = new Pixmap(6, 6, Pixmap.Format.RGBA8888);
        pxm.setColor(Color.RED);
        pxm.fillCircle(3, 3, 3);
        redDot = new Sprite(new Texture(pxm));
        redDot.setPosition(gun.getX() + (gun.getWidth() - redDot.getWidth()) / 2f,
                gun.getY() + (gun.getHeight() - redDot.getHeight()) / 2f);
    }

    public void render(SpriteBatch batch) {
        hull.setRotation(rotationHull);
        hull.draw(batch);
//             gun.setRotation(angleToScreen(this.gunDir, this.startDirGun));
        gun.draw(batch);
//        launcher.setRotation(angleToScreen(launcherDir, startDirLauncher));

        launcher.draw(batch);
        gun.setRotation(Physics.angleToScreen(this.gunDir));
        gun.draw(batch);
        redDot.draw(batch);
        takeInput(0L);
        timer += 1;
        //if(timer % 10L == 0) {
            readyGun += 5;
            readyLauncher += 1;
        //}
        for(Torpedo t: torps) t.render(batch, speed);
        bullet.render(batch);
    }

    public void drawInfo(SpriteBatch batch) {
        font.draw(batch, "Napis", 10, 100);
    }

    public void takeInput(long time) {

        if (Gdx.input.isTouched()) {
            int x = Gdx.input.getX();
            int y = Gdx.input.getY();
            //System.out.println("touched at: " + x + ", " + y);
            if (isTouchedHull( x, y)) {
                if(mainGun) setLauncherOperate();
                else setGunOperate();
            } else {
                float angle = Physics.angleClicked(x, y);
//                System.out.println(String.format("kliked %d %d angle %f", x, y, angle));
                float delta = 0;


                if (mainGun) {
                    delta = angle - gunDir;
                    float deltaAbs = Math.abs(delta);

                    if (Math.abs(delta) >= 2f) {
                        if(delta >= 0f) {
                            if (delta > 180.0f) rotateGun(true);
                            else rotateGun(false);
                        } else {
                            if (delta >= -180.0f) rotateGun(true);
                            else rotateGun(false);
                        }
                    } else {
                        this.fireGun(angle);
                    }
                } else {
                    delta = angle - launcherDir;
                    if (Math.abs(delta) >= 2f) {
                        if(launcherDir < 123.0f && angle > 180.0f) rotateLuncher(true);
                        else if(launcherDir >= 243.0f && angle < 180.0f) rotateLuncher(false);
                        else {
                            if (delta > 0) rotateLuncher(false);
                            else rotateLuncher(true);
                        }
                    }  else {
                        this.launchTorpedo(angle);
                    }
                }
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) rotateGun(true);
        if (Gdx.input.isKeyPressed(Input.Keys.S)) rotateGun(false);
        if (Gdx.input.isKeyPressed(Input.Keys.D)) rotateLuncher(true);
        if (Gdx.input.isKeyPressed(Input.Keys.F)) rotateLuncher(false);
        if (Gdx.input.isKeyPressed(Input.Keys.L)) setLauncherOperate();
        if (Gdx.input.isKeyPressed(Input.Keys.G)) setGunOperate();
    }

    private boolean isTouched(Sprite s, int x, int y) {
        //System.out.println(String.format("touch (%d, %d) sprite position (%f, %f)" +
        //        " size (%f, %f)",
        //        x, y, s.getX(), s.getY(), s.getWidth(), s.getHeight()));
        return (x > s.getX() && x < (s.getX() + s.getWidth())
                && y > s.getY() && y < (s.getY() + s.getHeight()));
    }

    private boolean isTouchedHull(int x, int y){
        return (x > hull.getX() && x < (hull.getX() + hull.getWidth())
                && y > hull.getY() && y < (hull.getY() + hull.getHeight()));
    }

    /* @return in degrees by up screen to clock wise
     */
    private float countAngleOfWeapon(int x, int y) {
        float X = centerX;
        float Y = centerY;
        if (this.mainGun) X -= 19f;
        else X += 20f;
        float dx = (float) x - X;
        float dy = Y - (float) y;
        System.out.println(String.format("tangens: %f for dx %f dy %f",
                Math.atan2(dx, dy), dx, dy));
        double alpha = (Math.atan2((double) dy, (double) dx) * (180.0 / Math.PI));
        if (alpha < 0) return (float) (360.0 + alpha);
        else return (float) alpha;
    }

    private void rotateGun(boolean left) {
        if (left) {
            this.gunDir -= 2f;
            if(this.gunDir < 0.0f) this.gunDir = 360.0f + this.gunDir;
        }
        else {
            this.gunDir += 2f;
            if(this.gunDir >= 360.0f) this.gunDir = 360.0f - this.gunDir;
        }
    }



    private void rotateLuncher(boolean left) {
        if (left) {
            this.launcherDir -= 2f;
            if(this.launcherDir < 0.0f) this.launcherDir = 360.0f + this.launcherDir;
            else if(this.launcherDir < 240.0f && this.launcherDir >= 123.0f) this.launcherDir += 2f;
        }
        else {
            this.launcherDir += 2f;
            if(this.launcherDir >= 360.0f) this.launcherDir = 360.0f - this.launcherDir;
            else if(this.launcherDir > 120.0f && this.launcherDir <= 233.0f) this.launcherDir -= 2f;

        }
    }

    private void setLauncherOperate(){
        System.out.println("launcher touched!");
        if (this.mainGun) this.mainGun = false;
        redDot.setCenter(launcher.getX() + launcher.getWidth() / 2f,
                launcher.getY() + launcher.getHeight() / 2f);
    }

    private void setGunOperate(){
        System.out.println("gun touched!");
        if (!this.mainGun) this.mainGun = true;
        redDot.setCenter(gun.getX() + gun.getWidth() / 2f,
                gun.getY() + gun.getHeight() / 2f);
    }

    private void launchTorpedo( float angle){
        if(readyLauncher >= readyWeapon){
            System.out.println(String.format("Launch Torp! %f" , angle));
            readyLauncher = 0;
            for(Torpedo t: torps){
                if(!t.isMoving() && t.isReady()){
                    t.launch(angle);
                    break;
                }
            }
        }

    }

    private void fireGun(float angle){
        if(readyGun >= readyWeapon){
            System.out.println(String.format("Fire GUN! %f" , angle));
            if(!bullet.isMoving()) bullet.launch(angle);
            readyGun = 0;
        }
    }
    
    public void dispose() {
    	 
    	    font.dispose();;
    	
    }
}
