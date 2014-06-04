//this class represents the deck of cards

import java.util.* ;
import java.io.* ;
import java.awt.Color ;

public class Deck{

    //instance variables
    //-represents the stack or the deck of cards, size of deck,  and bottom of the deck
    //-note: LENGTH must be 40
    private Stack stack ;
    private int length ;
    private static final int LENGTH = 40 ;
    private Deck bottom ;
    
    //constructors
    public Deck()
    {
	stack = new Stack() ;
	length = LENGTH ;
	bottom = new Deck() ;
    }

    //methods
    
    //shuffle acts as both a deck creator and a deck shuffler
    public void shuffle()
    {
	//all the cards 
	ArrayList<Card> cards = new ArrayList<Card>() ;

	//create new deck only if deck is empty
	if(length == 0)
	    {
		//creates 20 1-tile movement cards
		for(int x = 0 ; x < 5 ; x++)
		    {
			cards.add(new Card(1,Color.RED)) ;
			cards.add(new Card(1,Color.BLUE)) ;
			cards.add(new Card(1,Color.GREEN)) ;
			cards.add(new Card(1,Color.YELLOW)) ;
		    }
		//creates 8 2-tile movement cards
		for(int x = 0 ; x < 2 ; x++)
		    {
			cards.add(new Card(2,Color.RED)) ;
			cards.add(new Card(2,Color.BLUE)) ;
			cards.add(new Card(2,Color.GREEN)) ;
			cards.add(new Card(2,Color.YELLOW)) ;
		    }
		//creates 12 -1-tile movement cards
		for(int x = 0 ; x < 3 ; x++)
		    {
			cards.add(new Card(-1,Color.RED)) ;
			cards.add(new Card(-1,Color.BLUE)) ;
			cards.add(new Card(-1,Color.GREEN)) ;
			cards.add(new Card(-1,Color.YELLOW)) ;
		    }
	    }
	
	//next you have to do the actualy shuffling

    }
}