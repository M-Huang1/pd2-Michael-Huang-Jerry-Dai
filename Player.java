//this class represents a player

import java.util.* ;
import java.io.*  ;
import java.awt.Color ;
import java.awt.geom.*;

public class Player{

    //instance variables
    //-represents name of player, the order in which this player moves, its color, and its location
 
    private String name ;
    private int order ;
    private Color color ;
    private Tile loc ;
    private Track track ;
    private Ellipse2D shape;

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
    public void setShape(Ellipse2D x){
	shape = x;
    }
    public Ellipse2D getShape(){
	return shape;
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

}