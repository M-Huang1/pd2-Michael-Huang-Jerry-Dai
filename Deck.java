//this class represents the deck of cards (Stack Data Structure)

import java.util.* ;
import java.io.* ;
import java.awt.Color ;

public class Deck{

    //instance variables
    //-represents the stack or the deck of cards and size of deck
    //-note: LENGTH must be 40
    private Stack stack, tempdeck, bottom ;
    private int length ;
    private static final int LENGTH = 68 ;
    
    //constructors
    public Deck()
    {
	stack = new Stack() ;
	tempdeck = new Stack() ;
	bottom = new Stack() ;
	length = LENGTH ;
    }

    //methods

    //get stack
    public Stack getStack()
    {
	return stack ;
    }

    //get length
    public int size()
    {
	return length ;
    }

    //create
    //-creates a new deck (40 cards)

    public void create()
    {
	//all the cards 
	ArrayList<Card> cards = new ArrayList<Card>() ;

	//creates 20 1-tile movement cards
	for(int x = 0 ; x < 5 ; x++)
	    {
		cards.add(new Card(1,Color.RED,false)) ;
		cards.add(new Card(1,Color.BLUE,false)) ;
		cards.add(new Card(1,Color.GREEN,false)) ;
		cards.add(new Card(1,Color.YELLOW,false)) ;
	    }
	//creates 8 2-tile movement cards
	for(int x = 0 ; x < 2 ; x++)
	    {
		cards.add(new Card(2,Color.RED,false)) ;
		cards.add(new Card(2,Color.BLUE,false)) ;
		cards.add(new Card(2,Color.GREEN,false)) ;
		cards.add(new Card(2,Color.YELLOW,false)) ;
	    }
	//creates 12 -1-tile movement cards
	for(int x = 0 ; x < 3 ; x++)
	    {
		cards.add(new Card(-1,Color.RED,false)) ;
		cards.add(new Card(-1,Color.BLUE,false)) ;
		cards.add(new Card(-1,Color.GREEN,false)) ;
		cards.add(new Card(-1,Color.YELLOW,false)) ;
	    }

	//creates 8 switch cards
	for(int x = 0 ; x < 8 ; x++)
	    {
		cards.add(new Card(0,Color.GRAY,false)) ;
	    }
	
	//creates 3 dummy cards
	for(int x = 0 ; x < 3 ; x++)
	    {
		cards.add(new Card(0,Color.WHITE,false)) ;
	    }

	//creates 2 shuffle cards
	for(int x = 0 ; x < 2 ; x++)
	    {
		cards.add(new Card(0,Color.BLACK,false)) ;
	    }

	//creates double cards ( draw twice from the deck in one turn )
	for(int x = 0 ; x < 2 ; x++)
	    {
		cards.add(new Card(1,Color.RED,true)) ;
		cards.add(new Card(1,Color.BLUE,true)) ;
		cards.add(new Card(1,Color.GREEN,true)) ;
		cards.add(new Card(1,Color.YELLOW,true)) ;
		cards.add(new Card(2,Color.RED,true)) ;
		cards.add(new Card(2,Color.BLUE,true)) ;
		cards.add(new Card(2,Color.GREEN,true)) ;
		cards.add(new Card(2,Color.YELLOW,true)) ;
		cards.add(new Card(-1,Color.RED,true)) ;
		cards.add(new Card(-1,Color.BLUE,true)) ;
		cards.add(new Card(-1,Color.GREEN,true)) ;
		cards.add(new Card(-1,Color.YELLOW,true)) ;
		cards.add(new Card(0,Color.GRAY,true)) ;
		cards.add(new Card(0,Color.WHITE,true)) ;
		cards.add(new Card(0,Color.BLACK,true)) ;
	    }

	for(Card c : cards)
	    {
		stack.push(c) ;
	    }
	
    }
    
    //shuffle
    //-shuffles the deck

    public void shuffle()
    {
	Random shuf = new Random();
	ArrayList<Card> cards = new ArrayList<Card>() ;
	ArrayList<Card> newdeck = new ArrayList<Card>() ;
	
	//move to deck to ArrayList
	while(!stack.empty())
	    {
		cards.add( (Card)(stack.pop()) ) ;
	    }
	
	//randomly add to newdeck, then remove from old deck
	while(cards.size() > 0)
	    {
		int index = shuf.nextInt(cards.size()) ;
		newdeck.add(cards.get(index)) ;
		cards.remove(index) ;
	    }
	
	//move back to stack
	for(Card c : newdeck)
	    {
		stack.push((Card)(c)) ;
	    }

    }

    //draw
    //-draw a card from top of deck
    //-put card to tempdeck after done
    //-after all cards are cycled, move tempdeck to bottom of the deck to actual deck (returns to original order) 

    public Card draw()
    {
	//switch decks if empty
	if(stack.empty())
	    {
		while(!tempdeck.empty())
		    {
			bottom.push(tempdeck.pop()) ;
		    }
		stack = bottom ;
		tempdeck = new Stack() ;
		bottom = new Stack() ;
	    }
		
	//pop the next card
	tempdeck.push(stack.peek()) ;
	return (Card)(stack.pop()) ;

    }

}
