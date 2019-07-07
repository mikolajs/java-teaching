package pl.sflg;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;


class GameScreen extends AbstractScreen {
      final Texture cardBody;
      final Menu menu;

      BitmapFont bitmapFont;
      final Gamer player;
      final Gamer croupier;
      final int sizeX = 1280;
      final int sizeY = 720;
      private double gameTime = 0.0;
      private  int lastTime = -1;
      private int croupierAnimationTime = 0;
      final Deck deck;
      final Arbiter arbiter;
//    private FreeTypeFontGenerator generator;
//    private FreeTypeFontParameter parameter;
    final private GameState gameState;
    private float clikedTimer;

    public GameScreen(ScreenManager manager) {
        super(manager);
        bitmapFont = new BitmapFont();
        bitmapFont.setColor(Color.BLACK);

        deck = new Deck();
        arbiter = new Arbiter();
        gameState = new GameState();

        cardBody = new Texture("karta.png");

        player = new Gamer(cardBody, bitmapFont);
        croupier = new Gamer(cardBody, bitmapFont);
        menu = new Menu(sizeX, sizeY);

    }


    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(0, 1, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gameTime += delta;
        clikedTimer -= delta;

        batch.begin();
        if(gameState.state == 0){
            player.addCard(deck.getNext());
            player.addCard(deck.getNext());
            gameState.state = 1;
            player.draw(batch);
        } else if(clikedTimer <= 0.0f) {
            checkStep();
        }

        if(gameState.state < 2)  player.draw(batch);
        else croupier.draw(batch);

        menu.draw(batch);
        this.drawCash();
        this.drawTime();
        this.drawScore();
        batch.end();
    }

    public void checkStep(){
        int inputState;
        switch (gameState.state){
            case 1:
                inputState = checkClicked();

                if(Gdx.input.isKeyJustPressed(Input.Keys.N  )){
                    inputState = 1;
                } else  if(Gdx.input.isKeyJustPressed((Input.Keys.S))){
                    inputState = 2;
                }
                if(inputState == 1){
                    player.addCard(deck.getNext());
                } else if(inputState == 2) {
                    gameState.state = 2;
                }

                break;
            case 2:
                int t = (int) gameTime / 5;
                if (lastTime != t) {
                    if (croupier.countValues() < 17) {
                        croupier.addCard(deck.getNext());
                    }
                }
                break;
        }


    }

    private int checkClicked(){
        if(Gdx.input.isTouched()) {
            int x, y;
            for (int i = 0; i < 5; i++){
                x = Gdx.input.getX(i);
                y = sizeY - Gdx.input.getY(i);
                if(menu.isClickedNext(x, y)) {
                    clikedTimer =  0.8f;
                    return  1;
                }
                else if(menu.isClickedClose(x, y)) return 2;
            }
        } else {
           clikedTimer = 0.0f;
        }
        return  0;
    }

    private void drawTime(){
        bitmapFont.draw(batch, "Time: " + gameTime, 1000, 600);
    }

    private void drawScore(){
        bitmapFont.draw(batch, "Croupier cards: " + croupier.countValues(), 1000, 660);
        bitmapFont.draw(batch, "Player cards:" + player.countValues(), 1000, 630);
    }

    private void drawCash(){
        bitmapFont.draw(batch, "$" + gameState.getMoney(), 1000, 690);
    }

    @Override
    public void dispose() {
        super.dispose();
        bitmapFont.dispose();
       menu.dispose();
        cardBody.dispose();
    }
}

class Point {
    int x, y;
}
