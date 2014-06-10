
//test class for testing purposes only

import java.util.* ;
import java.io.* ; 
import java.awt.Color ;

public class Test{

    public static void main(String[] args)
    {	
	//make the track
	Track map = new Track(30) ;
	Player player1 = new Player("Jerry",0,Color.PINK,map.getStart(),map) ;
	Tile temp = map.getStart() ;
	/*
	while(temp!=null)
	    {
		System.out.println(temp.getColor().toString()) ;
		temp = temp.getNext() ;
	    }
	*/
	System.out.println("KEY: ");
	System.out.println("-: EMPTY SPACE");
	System.out.println("S: START TILE");
	System.out.println("R: RED TILE");
	System.out.println("Y: YELLOW TILE");
	System.out.println("G: GREEN TILE");
	System.out.println("B: BLUE TILE");
	System.out.println("E: END TILE\n");
	System.out.println(map.toString()) ;
	Deck d = new Deck() ;
	d.create();
	d.shuffle();
	int c = 0;
	while(c < 50)
	    {   
		System.out.println(c);
		System.out.println(d.draw()) ;
		c++ ;
	    }
    }

}
