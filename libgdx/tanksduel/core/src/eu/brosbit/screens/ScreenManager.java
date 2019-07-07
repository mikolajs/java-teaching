package eu.brosbit.screens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import eu.brosbit.TankGame;

public class ScreenManager {

        final private TankGame game;
        public ScreenManager(TankGame game) {
            this.game = game;
            setStartScreen();
        }
        public void setStartScreen() {
            SplashScreen splash = new SplashScreen(this);
            game.setScreen(splash);
        }
        public void setHelpScreen() {
            HelpScreen help = new HelpScreen(this);
            game.setScreen(help);
        }
        public void setGameScreen() {
            GameScreen gameScr = new GameScreen(this);
            game.setScreen(gameScr);
        }
        public void setGameOverScreen() {

        }

        public SpriteBatch getBatch() {
            return game.getBatch();
        }
}
