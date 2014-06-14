//this class represents a player

import java.util.* ;
import java.io.*  ;
import java.awt.Color ;
import java.awt.geom.*;
import javax.swing.*;

public class Player{

    //instance variables
    //-represents name of player, the order in which this player moves, its color, and its location
 
    private String name ;
    private int order ;
    private Color color ;
    private Tile loc ;
    private Track track ;
    private Ellipse2D shape;
    private ImageIcon icon;
    
    //constructors

    public Player()
    {
	name = "CPU" ;
	order = -1 ;
	color = Color.WHITE ;
	loc = new Tile() ;
	track = null ;
    }

    public Player(String name, int order, Color color, Tile loc, Track track)
    {
	this.name = name ;
	this.order = order ;
	this.color = color ;
	this.loc = loc ;
	this.track = track ;
    }

    //methods

    //set/get name
    public void setName(String name)
    {
	this.name = name ;
    }

    public String getName()
    {
	return name ;
    }

    //set/get order
    public void setOrder(int order)
    {
	this.order = order ;
    }
  
    public int getOrder()
    {
	return order ;
    }

    //set/get color
    public void setColor(Color color)
    {
	this.color = color ;
    }

    public Color getColor()
    {
	return color ;
    }

    //set/get tile
    public void setTile(Tile loc)
    {
	this.loc = loc ;
    }
    
    public Tile getTile()
    {
	return loc ;
    }
    
    //set/get track
    public void setTrack(Track track)
    {
	this.track = track ;
    }
   
    public Track getTrack()
    {
	return track ;
    }

    //get coordinates of current tile
    public int getXcor()
    {
	return loc.getXcor() ;
    }

    public int getYcor()
    {
	return loc.getYcor() ;
    }

    //set/get shape
    public void setShape(Ellipse2D shape)
    {
	this.shape = shape ;
    }
    
    public Ellipse2D getShape()
    {
	return shape ;
    }
    public void setPic(ImageIcon image){
	icon = image;
    }
    public ImageIcon getIcon(){
	return icon;
    }
	
    //player movement based on card drawn, animation
    public void move(Card c){

	int mv = c.getMovement() ;
	Tile pTemp = getTile().getNext(); ;

	//forward movement
	if(mv > 0)
	    {
		while(mv > 0)
		    {
			try
			{
	    		    while(!pTemp.getColor().equals(c.getColor()))
				{
	   			    pTemp = pTemp.getNext();
				 
	    			}
			}
			//exception means the player has reached the end of the track, has won
			catch(Exception e)
			{
	    		    pTemp= track.getEnd();  
	    		    
			}
			//this allows movement to loop if a card with movement 2 is drawn
			if(mv > 1)
			    {
			    	pTemp = pTemp.getNext() ;
			
			    }
			mv-- ;
		    }
	    }
	//backward movement
	else
	    {
		pTemp = track.getStart() ;

		if(!(getTile().getOrder()-4 < 0)
		   && (!(pTemp.getOrder()-4 == 0)) )
		    {
			while(pTemp.getOrder() != getTile().getOrder()-4)
		    	    {
				pTemp = pTemp.getNext() ;
				
		    	    }
			while( pTemp.getColor() != c.getColor() )
			    {
				pTemp = pTemp.getNext() ;
				
			    }
	    	    }
	    }
       
	update(pTemp);
    }
    public int getXCor(){
	return loc.getXcor();
    }
    public int getYCor(){
	return loc.getYcor();
    }

    //update position on map
    public void update(Tile temp)
    {
	setShape(new Ellipse2D.Double(temp.getXcor(),temp.getYcor(),20,20));
	setTile(temp);
	//repaint();
	//<<<<<<< HEAD
	wait(500);
	//=======
	//wait(500);
	//>>>>>>> 7de851144046793f2b3155b3fb4aa862de7d35dc
    }

    //wait function
    public void wait(int time)
    {
	try {
	    Thread.sleep(time);
	} catch(InterruptedException ex) {
	    Thread.currentThread().interrupt();
	}
    }

}
