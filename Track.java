//this class represents the game track (Linked List Data Structure)

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
	start = new Tile(Color.WHITE, 0, 0, 0);
	
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

		//create next Tile in Linked List
		temp.setNext(new Tile(c, count+1, count+1, 0 )) ;
		temp.getNext().setOrder(count+1) ;
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

    //string representation of the track
    public String toString()
    {

	String[][] grid = new String[length][length] ;
	Tile temp = start ;
	String answer = "" ;

	for(int a = 0 ; a < length ; a++)
	    {
		for(int b = 0 ; b < length ; b++)
		    {				
			grid[a][b] = "-" ;
		    }
	    }

	while(temp.getNext()!=null)
	    {
		int x = temp.getXcor() ;
		int y = temp.getYcor() ;
		
		if(temp.getOrder()==0)
		    grid[x][y] = "S" ;
		else if(temp.getOrder()==(length-1))
		    grid[x][y] = "E" ;
		else if(temp.getColor()==Color.RED)
		    grid[x][y] = "R" ;
		else if(temp.getColor()==Color.YELLOW)
		    grid[x][y] = "Y" ;
		else if(temp.getColor()==Color.GREEN)
		    grid[x][y] = "G" ;    
		else if(temp.getColor()==Color.BLUE)
		    grid[x][y] = "B" ;

		temp = temp.getNext() ;

	    }

	for(int c = 0 ; c < length ; c++)
	    {
		for(int d = 0 ; d < length ; d++)
		    {				
			answer += grid[c][d] ;
		    }
		answer+= "\n" ;
	    }
	
	return answer ;

    }
}
