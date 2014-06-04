//this class represents a card

import java.util.* ;
import java.io.* ;
import java.awt.Color ;

public class Card{

    //instance variables
    //-represents how many spaces to move based on color, and the color of the card
    private int movement ;
    private Color color ;

    //constructors

    public Card()
    {
	movement = 0 ;
	color = Color.WHITE ;
    }

    public Card(int movement, Color color)
    {
        this.movement = movement ;
	this.color = color ;
    }

}