package pl.sflg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class Deck {
    private char[] colors = {'♥', '♦', '♣', '♠'};
    //heart (kier), diamond (karo), club (trefl), spade (pik)
    private char[] figures = {'A', 'J', 'Q', 'K'};
    private Stack<Card> stack;

    public Deck(){
        stack = newDeck();
    }

    public Card getNext(){
        if(stack.isEmpty()) {
           stack = newDeck();
        }
        return stack.pop();
    }

    private Stack<Card> newDeck(){
        ArrayList<Card> cards = new ArrayList<Card>();
        for(int i = 2; i < 11; i++){
            for(char c: colors){
                cards.add(new Card(String.valueOf(i), c, i));
            }
        }
        for(char figure: figures){
            for(char c: colors){
                if(figure != 'A')
                    cards.add(new Card(String.valueOf(figure), c, 10) );
                else cards.add(new Card(String.valueOf(figure), c, 11) );
            }
        }
        Collections.shuffle(cards);
        Stack<Card> newStack = new Stack<Card>();
        newStack.addAll(cards);
        return newStack;
    }

}


