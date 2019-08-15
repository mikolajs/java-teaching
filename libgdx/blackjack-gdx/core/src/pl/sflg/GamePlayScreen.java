package pl.sflg;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import pl.sflg.GState.*;


class GamePlayScreen extends AbstractScreen {
      final Texture cardBody;
      final Menu menu;

      final BitmapFont bitmapFont;
      final Gamer player;
      final Gamer croupier;
      final int sizeX = 1280;
      final int sizeY = 720;
      private int croupierAnimationTime = 0;
      final Deck deck;
      final Arbiter arbiter;
      final GameState gameState;
      final BettingMenu bettingMenu;
//    private FreeTypeFontGenerator generator;
//    private FreeTypeFontParameter parameter;
    private ClickedOption clickedOption = ClickedOption.NONE;

    public GamePlayScreen(ScreenManager manager) {
        super(manager);
        bitmapFont = new BitmapFont();
        bitmapFont.setColor(Color.BLACK);

        deck = new Deck();
        arbiter = new Arbiter();
        gameState = new GameState();
        bettingMenu = new BettingMenu(sizeX, sizeY);
        bettingMenu.setMoneyState(gameState.getMoney());

        cardBody = new Texture("karta.png");

        player = new Gamer(cardBody, bitmapFont);
        croupier = new Gamer(cardBody, bitmapFont);
        menu = new Menu(sizeX, sizeY);

    }


    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(0, 1, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gameState.progressTime(delta);
        if(gameState.clickedTime <= 0.0) clickedOption = checkClicked();

        batch.begin();
        if(gameState.state == GState.BET) {
            bettingMenu.draw(batch);
            if (clickedOption == ClickedOption.NEXT) {
                gameState.state = GState.CROUPIER;
            } else if (clickedOption == ClickedOption.EXIT) {
                Gdx.app.exit(); //add new state about exit!
            }
        } else if(gameState.state == GState.CROUPIER){
            //todo animation of adding cards for croupier
            if (gameState.clickedTime <= 0.0) {
                    if (croupier.countValues() < 17) {
                        croupier.addCard(deck.getNext());
                        gameState.clicked();
                    } else {
                        if(clickedOption == ClickedOption.NEXT){
                            gameState.state = GState.PLAYER;
                        }
                        //todo clicked on menu next not work!!!!!!!!!
                    }
                }
            croupier.draw(batch);
        } else if(gameState.state == GState.PLAYER){
            System.out.println("PLAYER TURN!!!");
            //todo create animation for adding cards for croupier
            player.addCard(deck.getNext());
            player.addCard(deck.getNext());
            gameState.state = GState.PLAYER;
            player.draw(batch);
        } else if(gameState.clickedTime <= 0.0) {
//            checkStep();
        }

        menu.draw(batch);
        this.drawCash();
        this.drawBet();
        this.drawScore();
        batch.end();
    }


    private ClickedOption checkClicked(){
        if(Gdx.input.isTouched()) {
            int x, y;
            for (int i = 0; i < 5; i++){
                x = Gdx.input.getX(i);
                y = sizeY - Gdx.input.getY(i);

                switch (gameState.state){
                    case BET:
                        if(bettingMenu.checkClicked((float) x, (float) y)) {
                            gameState.clicked();
                            return ClickedOption.OTHER;
                        } else if(menu.isClickedNext(x , y)) {
                                gameState.clicked();
                                return ClickedOption.NEXT;
                        } else if(menu.isClickedClose(x, y)){
                                gameState.clicked();
                                return ClickedOption.EXIT;
                        }
                        break;
                    case CROUPIER:
                        if(menu.isClickedNext(x, y)){
                            gameState.clicked();
                            return ClickedOption.NEXT;
                        }
                        break;
                    case PLAYER:
                        if(menu.isClickedNext(x, y)) {
                            gameState.clicked();
                            return  ClickedOption.NEXT;
                        } else if(menu.isClickedClose(x, y)) {
                            gameState.clicked();
                            return  ClickedOption.EXIT;
                        }
                        break;
                    case RESOULT:
                        break;
                    default:
                        break;
                }
            }
        }
        return  ClickedOption.NONE;
    }

    private void drawBetting(){

    }

    private void drawBet(){
        bitmapFont.draw(batch, "Bet Amount: " + bettingMenu.getBetAmount(), 1000, 600);
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
        bettingMenu.dispose();
    }
}
