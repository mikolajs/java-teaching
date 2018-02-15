package pl.xxlo.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import pl.xxlo.WarBoat;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "WarBoat Game";
        config.height = 700;
        config.width = 1200;
        config.forceExit = false;
        config.useGL30 = true;
        config.resizable = true;
        new LwjglApplication(new WarBoat(), config);
    }
}
