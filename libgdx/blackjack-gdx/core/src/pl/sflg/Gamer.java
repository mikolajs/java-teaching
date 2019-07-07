package pl.sflg;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Gamer {
    Card[] deck = new Card[11];
    final Sprite[] cardSprites;
    final Texture cardBody;
    int number = -1;
    final  BitmapFont font;

    public Gamer(Texture cardTexture, BitmapFont font){
        cardSprites  = new Sprite[11];
        cardBody = cardTexture;
        this.font = font;
    }

    public void addCard(Card card){
        number++;
        deck[number] = card;
        cardSprites[number] = new Sprite(cardBody);
    }
    public int countValues(){
        int sum = 0;
        for(int i = 0; i <= number; i++){
            sum += deck[i].getValue();
        }
        return sum;
    }

    public void draw(SpriteBatch batch){
        int posX = 5;
        int posY = 0;
        for(int i = 0; i <= number; i++){
            cardSprites[i].setPosition(posX, posY);
            cardSprites[i].draw(batch);
            if(deck[i].isBlack()) font.setColor(Color.BLACK);
            else font.setColor(Color.RED);
            font.draw(batch, deck[i].figure(), posX + 10, posY + 570);
            font.draw(batch, deck[i].color(), posX + 10, posY + 540);
            posY -= 10;
            posX += 80;
        }
    }

    public boolean doubleA() {
        return  (number + 1) == 2;
    }

    public int numberCards() { return number + 1; }
}