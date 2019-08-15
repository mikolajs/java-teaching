package pl.sflg;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.math.Rectangle;

public class BettingMenu {
    final Sprite banknote;
    final Sprite up;
    final Sprite down;
    final Sprite[] moneyBet;
    final int[] nominals = {5, 10, 50, 100};
    private int moneyChoose = 0;
    final Texture banknoteTexture;
    final Texture upTexture;
    final Texture downTexture;
    final Texture moneyTexture;
    final BitmapFont bitmapFont;
    private int betAmount = 0;
    private int moneyState;


    public BettingMenu(int sizeX, int sizeY){
        final float marginX = 50;
        final float marginY = sizeY*0.1f;
        bitmapFont = new BitmapFont();
        bitmapFont.setColor(Color.WHITE);
        banknoteTexture = new Texture("dollar.png");
        upTexture = new Texture("up_money.png");
        downTexture = new Texture("down_money.png");
        moneyTexture = new Texture("dollar-small.png");
        up = new Sprite(upTexture);
        down = new Sprite(downTexture);
        final float height = 256.0f;
        final float width = banknoteTexture.getWidth()*(height/banknoteTexture.getHeight());
        up.setSize(height, height);
        up.setPosition(width+height+marginX, marginY);
        down.setSize(height, height);
        down.setPosition(marginX, marginY);
        banknote = new Sprite(banknoteTexture);
        banknote.setSize(width, height);
        banknote.setPosition(marginX +  down.getHeight(), marginY);
        moneyBet = new Sprite[4];
        for(int i = 0; i < moneyBet.length; i++) {
            moneyBet[i] = new Sprite(moneyTexture);
            moneyBet[i].setSize(height, height);
            moneyBet[i].setPosition(marginX + i*height, marginY + up.getHeight());
            if(i == 0) moneyBet[i].setColor(Color.FOREST);
        }

    }

    public void draw(SpriteBatch batch){
        down.draw(batch);
       banknote.draw(batch);
        bitmapFont.draw(batch, String.valueOf(betAmount), banknote.getX() + (banknote.getWidth() - 2*bitmapFont.getXHeight())/2.0f,
                banknote.getY() + (banknote.getHeight() - 2*bitmapFont.getLineHeight())/2.0f);
       up.draw(batch);
       moneyBet[moneyChoose].setColor(Color.FOREST);
        for(int i = 0; i < moneyBet.length; i++) {
            moneyBet[i].draw(batch);
            bitmapFont.draw(batch, String.valueOf(nominals[i]), moneyBet[i].getX() + (moneyBet[0].getHeight() - 2*bitmapFont.getXHeight())/2.0f,
            moneyBet[i].getY() + (moneyBet[0].getHeight() - 2*bitmapFont.getLineHeight())/2.0f);
        }
    }


    public boolean checkClicked(float x, float y){
        int result = clickedMoneyChoise(x, y);
        if(result > -1){
            moneyBet[moneyChoose].setColor(Color.WHITE);
            moneyChoose = result;
            return true;
        }
        if(upClicked(x, y) ){
            betAmount += nominals[moneyChoose];
            if(betAmount > moneyState ) betAmount -= nominals[moneyChoose];
            return true;
        }
        if(downClicked(x, y)){
            betAmount -= nominals[moneyChoose];
            if(betAmount < 0) betAmount += nominals[moneyChoose];
            return true;
        }
        return false;
    }

    public int getBetAmount(){ return  betAmount;}


    public void dispose() {
        banknoteTexture.dispose();
        upTexture.dispose();
        downTexture.dispose();
        moneyTexture.dispose();
    }

    private int clickedMoneyChoise(float x, float y){
        Rectangle rect;
        for(int i = 0; i < moneyBet.length; i++){
           rect = moneyBet[i].getBoundingRectangle();
           if(rect.contains(x, y)) return i;
        }
        return -1;
    }

    private boolean upClicked(float x, float y){
        return upOrDownClicked(up.getBoundingRectangle(), x, y);
    }

    private boolean downClicked(float x, float y){
        return upOrDownClicked(down.getBoundingRectangle(), x, y);
    }

    private boolean upOrDownClicked(Rectangle rect, float x, float y){
        return rect.contains(x, y);
    }

    public void setMoneyState(int money) {
        moneyState = money;
    }


}
