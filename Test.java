//test class for testing purposes only

import java.util.* ;
import java.io.* ; 
import java.awt.Color ;

public class Test{

    public static void main(String[] args)
    {
	
	//make the track
	Track map = new Track(144) ;
	Player player1 = new Player("Jerry",0,Color.PINK,map.getStart(),map) ;
	Tile temp = map.getStart() ;
	while(temp!=null)
	    {
		System.out.println(temp.getColor().toString()) ;
		temp = temp.getNext() ;
	    }


    }

}
