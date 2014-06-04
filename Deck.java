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
		stack.push(c) ;
	    }

    }

    //draw
    //-draw a card from top of deck
    //-put card to bottom after done
    //-after all cards are cycle, move bottom of the deck to actual deck

    public Card draw()
    {
	//switch decks if empty
	if(stack.empty())
	    {
		stack = bottom.getStack() ;
		bottom = null ;
	    }

	//pop the next card
	bottom.getStack().push(stack.peek()) ;
	return (Card)(stack.pop()) ;

    }

}