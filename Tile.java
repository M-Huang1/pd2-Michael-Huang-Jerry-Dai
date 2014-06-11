//this class represents individual spaces on the board

import java.util.* ;
import java.io.* ;
import java.awt.Color ;

public class Tile{

    //instance variables
    //-represent next space infront, color of the tile, all players on the tile, and order in relation to other tiles

    private Tile next,First ;
    private Color color ;
    private ArrayList<Player> players ;
    private int order,xcor,ycor ;

    //constructors
    public Tile()
    {
	next = null ;
	color = Color.WHITE ;
	players = new ArrayList<Player>() ;
	order = -1 ;
	xcor = -1 ;
	ycor = -1 ;
    }

    public Tile(Color color,Tile First, int order, int xcor, int ycor)
    {
	next = null ;
	this.color = color ;
	players = new ArrayList<Player>() ;
	this.order = order ;
	this.xcor = xcor ;
	this.ycor = ycor ;
    }

    //methods
    
    //set/get next tile
    public void setNext(Tile next)
    {
	this.next = next ;
    }
    
    public Tile getNext()
    {
	return next ;
    }
    public Tile getFirst(){
	return First;
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

    //set/get order
    public void setOrder(int order)
    {
	this.order = order ;
    }

    public int getOrder()
    {
	return order ;
    }

    //set/get coordinates
    public void setXcor(int xcor)
    {
	this.xcor = xcor ;
    }

    public int getXcor()
    {
	return xcor ;
    }

    public void setYcor(int ycor)
    {
	this.ycor = ycor ;
    }

    public int getYcor()
    {
	return ycor ;
    }

    //interact with players on the tile
    //get list of all players
    public ArrayList<Player> getPlayers()
    {
	return players ;
    }

    //remove player from this tile
    public void remove(Player p)
    {
	try{
	    players.remove(p) ;
	}
	catch(Exception e) {} 
    }

    //add player to this tile
    public void add(Player p)
    {
	try{
	    players.add(p) ;
	}
	catch(Exception e) {}
    }

}