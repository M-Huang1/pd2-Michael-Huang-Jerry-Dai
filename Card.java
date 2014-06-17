//this class represents a card (Nodes)

import java.util.* ;
import java.io.* ;
import java.awt.Color ;

public class Card{

    //instance variables
    //-represents how many spaces to move based on color, and the color of the card
    private int movement ;
    private boolean duble ;
    private Color color ;

    //constructors

    public Card()
    {
	movement = 0 ;
	color = Color.WHITE ;
    }

    public Card(int movement, Color color, boolean duble)
    {
        this.movement = movement ;
	this.color = color ;
	this.duble = duble ;
    }

    //methods

    //get color
    public Color getColor()
    {
	return color ;
    }
	
    //get movement
    public int getMovement()
    {
	return movement ;
    }

    //get duble
    public boolean getDuble()
    {
	return duble ;
    }

}
