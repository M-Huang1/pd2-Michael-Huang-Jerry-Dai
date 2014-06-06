//this class represents the game board

import java.util.* ;
import java.io.* ;
import java.awt.Color ;

public class Track{

    //instance variables
    //-represent the start and end tiles, length of board

    private Tile start, finish ;
    private int length ;

    //constructors

    public Track()
    {
	start = null ;
	finish = null ;
	length = -1 ;
    }

    public Track(int length)
    {
	this.length = length ;
	//start line is white
	start = new Tile(Color.WHITE, 0);
	
	//track creation
	Tile temp = start ;
	int count = 0 ;
	int colorcount = 0 ;
	while(count < length)
	    {
		//cycle through 4 colored tiles
		Color c = null;
		if(colorcount == 0)
		    c = Color.RED ;
		else if(colorcount == 1)
		    c = Color.YELLOW ;
		else if(colorcount == 2)
		    c = Color.GREEN ;
		else if(colorcount == 3)
		    c = Color.BLUE ;

		//add next tile to the track
		temp.setNext(new Tile(c, count+1)) ;
		temp = temp.getNext() ;

		//reset color cycle
		colorcount++ ;
		if(colorcount > 3)
		    colorcount = 0 ;

		count++ ;

	    }

	//finish line is black
	finish = temp ;
	finish.setColor(Color.BLACK) ;

    }

    //methods

    //get starting tile
    public Tile getStart()
    {
	return start ;
    }

    //get finish line
    public Tile getEnd()
    {
	return finish ;
    }

    
}
